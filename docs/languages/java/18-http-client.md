# Networking (HttpClient)

Introduced in **Java 11 (2018)** as a modern replacement for the legacy
`HttpURLConnection` API. `HttpClient` supports **HTTP/1.1** and **HTTP/2**,
both synchronous and asynchronous request execution, WebSocket connections,
and a fluent, immutable builder-based API.

```mermaid
flowchart TD
    subgraph Client["HttpClient"]
        direction TB
        VER["HTTP version"] -->|HTTP/1.1 or HTTP/2| CONN["Connection pool"]
        REDIR["Redirect policy"] -->|follow / never| AUTH["Authenticator"]
        PROXY["Proxy selector"] -->|optional| COOKIE["Cookie handler"]
    end

    subgraph Request["HttpRequest"]
        direction TB
        METH["Method"] -->|GET/POST/PUT/...| URI["URI"]
        HEAD["Headers"] -->|key: value| BODY["BodyPublisher"]
    end

    subgraph Response["HttpResponse<T>"]
        direction TB
        STATUS["Status code"] -->|200, 404, ...| RHEAD["Response headers"]
        RBODY["Body<T>"] -->|String/byte[]/InputStream/...| RVER["HTTP version"]
    end

    Client -->|send / sendAsync| Request
    Request -->|returns| Response

    style Client fill:#e1f5fe,stroke:#0288d1
    style Request fill:#fff3e0,stroke:#f4a261
    style Response fill:#e8f5e9,stroke:#388e3c
```

---

## Creating an HttpClient

```java
HttpClient client = HttpClient.newBuilder()
    .version(HTTP_2)                          // HTTP/2 preferred, fallback to HTTP/1.1
    .connectTimeout(Duration.ofSeconds(10))
    .followRedirects(Redirect.NORMAL)         // follow 301, 302, 303, 307, 308
    .authenticator(new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("user", "pass".toCharArray());
        }
    })
    .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 8080)))
    .cookieHandler(new CookieManager())       // in-memory cookie storage
    .executor(Executors.newFixedThreadPool(4)) // custom executor for async calls
    .build();
```

### HttpClient configuration options

| Builder method | Purpose | Default |
|---|---|---|
| `version(HTTP_2)` | Preferred HTTP version | HTTP/2 |
| `connectTimeout` | TCP connection timeout | No timeout |
| `followRedirects` | Redirect behavior | `Redirect.NEVER` |
| `authenticator` | Basic/Digest auth | None |
| `proxy` | Proxy server | System proxy |
| `cookieHandler` | Cookie storage | None |
| `executor` | Thread pool for async ops | `ForkJoinPool.commonPool()` |
| `sslContext` / `sslParameters` | TLS configuration | JVM defaults |
| `priority` | HTTP/2 stream priority | 256 |

### Reusing the client

```java
// Create once, reuse for all requests
private static final HttpClient CLIENT = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(10))
    .build();

// Same client handles connection pooling automatically
HttpResponse<String> r1 = CLIENT.send(request1, BodyHandlers.ofString());
HttpResponse<String> r2 = CLIENT.send(request2, BodyHandlers.ofString());
```

> `HttpClient` is **immutable and thread-safe**. Create one instance and reuse
> it across your application. It manages an internal connection pool
> automatically.

---

## Building requests

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Accept", "application/json")
    .header("Authorization", "Bearer " + token)
    .GET()                                   // explicit, or omit for default
    .timeout(Duration.ofSeconds(5))          // per-request timeout
    .build();
```

### Request methods

```java
// GET (default)
HttpRequest get = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/items"))
    .build();

// POST with JSON body
HttpRequest post = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/items"))
    .header("Content-Type", "application/json")
    .POST(BodyPublishers.ofString("""{"name":"item"}"""))
    .build();

// POST with file upload
HttpRequest upload = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/upload"))
    .header("Content-Type", "application/octet-stream")
    .POST(BodyPublishers.ofFile(Path.of("data.bin")))
    .build();

// PUT
HttpRequest put = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/items/1"))
    .header("Content-Type", "application/json")
    .PUT(BodyPublishers.ofString("""{"name":"updated"}"""))
    .build();

// DELETE
HttpRequest delete = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/items/1"))
    .DELETE()
    .build();
```

### BodyPublishers

| Publisher | Content | Use case |
|---|---|---|
| `ofString(String)` | String body | JSON, XML, form data |
| `ofByteArray(byte[])` | Raw bytes | Binary payloads |
| `ofFile(Path)` | File contents | File uploads |
| `ofInputStream(Supplier)` | Stream | Large streaming uploads |
| `ofLines(Stream)` | Line-separated | CSV, logs |
| `noBody()` | Empty | GET, DELETE |
| `fromPublisher(Flow.Publisher)` | Reactive stream | Backpressure-sensitive |

### Sending form data

```java
HttpRequest form = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/login"))
    .header("Content-Type", "application/x-www-form-urlencoded")
    .POST(BodyPublishers.ofString("username=alice&password=secret"))
    .build();

// Or using a helper
String formData = Map.of("username", "alice", "password", "secret")
    .entrySet().stream()
    .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), UTF_8))
    .collect(Collectors.joining("&"));
```

---

## Handling responses

```java
HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

int statusCode = response.statusCode();                    // 200, 404, etc.
HttpHeaders headers = response.headers();                  // response headers
String body = response.body();                             // response body
HttpClient.Version version = response.version();           // HTTP_1_1 or HTTP_2
URI uri = response.uri();                                  // final URI (after redirects)
Optional<HttpResponse<String>> prev = response.previousResponse(); // redirect chain
```

### BodyHandlers

| Handler | Body type | Use case |
|---|---|---|
| `ofString()` | `String` | JSON, XML, HTML responses |
| `ofString(Charset)` | `String` | Non-UTF-8 responses |
| `ofByteArray()` | `byte[]` | Binary data |
| `ofFile(Path)` | `Path` | Download to file |
| `ofFileDownload(Path, OpenOption...)` | `Path` | Download with options |
| `ofLines()` | `Stream<String>` | Line-by-line processing |
| `ofInputStream()` | `InputStream` | Streaming consumption |
| `discarding()` | `Void` | Fire-and-forget |
| `fromLineSubscriber(Subscriber)` | `Void` | Reactive line processing |
| `ofPublisher()` | `Flow.Publisher<List<ByteBuffer>>` | Full reactive control |

### Response as JSON (with Jackson)

```java
HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

if (response.statusCode() == 200) {
    User user = objectMapper.readValue(response.body(), User.class);
}
```

### Downloading a file

```java
HttpResponse<Path> response = client.send(
    request,
    BodyHandlers.ofFile(Path.of("download.zip"))
);

if (response.statusCode() == 200) {
    Path file = response.body();  // fully written file path
}
```

### Discarding response body

```java
// For DELETE or HEAD where body is irrelevant
HttpResponse<Void> response = client.send(request, BodyHandlers.discarding());
```

---

## Synchronous requests

```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.github.com/users/octocat"))
    .header("Accept", "application/vnd.github.v3+json")
    .GET()
    .build();

try {
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    System.out.println("Status: " + response.statusCode());
    System.out.println("Body: " + response.body());
} catch (IOException e) {
    // Network error
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

> `send()` blocks until the complete response is received. For concurrent or
> non-blocking I/O, use `sendAsync()`.

---

## Asynchronous requests

```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .GET()
    .build();

// sendAsync returns CompletableFuture<HttpResponse<T>>
client.sendAsync(request, BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenApply(body -> parseJson(body))
    .thenAccept(data -> System.out.println("Received: " + data))
    .exceptionally(ex -> {
        System.err.println("Request failed: " + ex.getMessage());
        return null;
    });
```

### Parallel requests

```java
List<HttpRequest> requests = List.of(
    HttpRequest.newBuilder().uri(URI.create("https://api.a.com/data")).GET().build(),
    HttpRequest.newBuilder().uri(URI.create("https://api.b.com/data")).GET().build(),
    HttpRequest.newBuilder().uri(URI.create("https://api.c.com/data")).GET().build()
);

// Execute in parallel, wait for all
List<CompletableFuture<HttpResponse<String>>> futures = requests.stream()
    .map(r -> client.sendAsync(r, BodyHandlers.ofString()))
    .toList();

CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
    .thenRun(() -> {
        List<String> bodies = futures.stream()
            .map(CompletableFuture::join)
            .map(HttpResponse::body)
            .toList();
        System.out.println("All responses: " + bodies);
    });
```

### First successful response

```java
// Race multiple endpoints, return the first successful one
CompletableFuture<Object> any = CompletableFuture.anyOf(
    client.sendAsync(requestA, BodyHandlers.ofString()),
    client.sendAsync(requestB, BodyHandlers.ofString()),
    client.sendAsync(requestC, BodyHandlers.ofString())
);

any.thenAccept(response -> {
    HttpResponse<String> r = (HttpResponse<String>) response;
    System.out.println("First response from: " + r.uri());
});
```

---

## HTTP/2

HTTP/2 is the default in `HttpClient`. It enables:
- **Multiplexing** — multiple requests share a single TCP connection
- **Server push** — server can push resources proactively
- **Header compression** — HPACK reduces overhead
- **Binary framing** — more efficient parsing

```java
HttpClient client = HttpClient.newBuilder()
    .version(HTTP_2)                         // preferred version
    .priority(256)                           // stream priority hint
    .build();

HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
System.out.println(response.version());      // HTTP_2
```

### HTTP/2 server push

```java
// Set up a push promise handler
Map<HttpRequest, CompletableFuture<HttpResponse<String>>> pushes = new ConcurrentHashMap<>();

HttpResponse<String> response = client.send(request, BodyHandlers.ofString(),
    (initiatingRequest, pushPromiseRequest, acceptor) -> {
        // Accept the push promise and collect the response
        pushes.put(pushPromiseRequest, acceptor.apply(BodyHandlers.ofString()));
    }
);

// Wait for all pushed resources
pushes.forEach((req, future) -> {
    future.thenAccept(r -> System.out.println("Pushed: " + req.uri()));
});
```

> Server push is **rarely used in practice** (deprecated in HTTP/3). Most
> modern applications use resource hints (`<link rel="preload">`) instead.

---

## WebSocket client

```java
WebSocket webSocket = client.newWebSocketBuilder()
    .buildAsync(URI.create("wss://echo.websocket.org/"), new WebSocket.Listener() {
        @Override
        public void onOpen(WebSocket ws) {
            System.out.println("Connected");
            ws.sendText("Hello", true);  // true = last frame
            WebSocket.Listener.super.onOpen(ws);
        }

        @Override
        public CompletionStage<?> onText(WebSocket ws, CharSequence data, boolean last) {
            System.out.println("Received: " + data);
            return WebSocket.Listener.super.onText(ws, data, last);
        }

        @Override
        public void onError(WebSocket ws, Throwable error) {
            System.err.println("Error: " + error.getMessage());
        }

        @Override
        public CompletionStage<?> onClose(WebSocket ws, int statusCode, String reason) {
            System.out.println("Closed: " + reason);
            return WebSocket.Listener.super.onClose(ws, statusCode, reason);
        }
    })
    .join();

// Send more messages
webSocket.sendText("More data", true);

// Close gracefully
webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Done").join();
```

---

## Comparison with alternatives

| Feature | `HttpClient` (Java 11+) | `HttpURLConnection` | Apache HttpClient | OkHttp |
|---|---|---|---|---|
| **HTTP/2** | Native | No | Via config | Native |
| **Async API** | `CompletableFuture` | No | `Future` / callbacks | `Call` / ` enqueue` |
| **WebSocket** | Native | No | Extension | Native |
| **Body streaming** | Yes (`ofInputStream`) | Limited | Yes | Yes |
| **Connection pooling** | Automatic | Per-URL | Configurable | Automatic |
| **Redirect handling** | Configurable | Manual | Configurable | Configurable |
| **Cookie support** | `CookieHandler` | `CookieManager` | `CookieStore` | `CookieJar` |
| **External deps** | None (built-in) | None (built-in) | Apache library | Square library |
| **API style** | Fluent / immutable | Verbose / mutable | Fluent | Fluent |

> Use `HttpClient` for new projects that don't require features unique to
> OkHttp or Apache HttpClient. It is sufficient for most HTTP use cases
> and eliminates external dependencies.

---

## Best practices

### Create one client per application

```java
public final class HttpClients {
    private static final HttpClient INSTANCE = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .followRedirects(Redirect.NORMAL)
        .build();

    public static HttpClient get() { return INSTANCE; }

    private HttpClients() {}
}
```

### Handle timeouts properly

```java
HttpClient client = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(10))   // TCP connect timeout
    .build();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/slow"))
    .timeout(Duration.ofSeconds(30))          // total request timeout
    .GET()
    .build();
```

### Reuse request builders

```java
HttpRequest.Builder base = HttpRequest.newBuilder()
    .header("Accept", "application/json")
    .header("Authorization", "Bearer " + token)
    .timeout(Duration.ofSeconds(5));

HttpRequest users = base.copy()
    .uri(URI.create("https://api.example.com/users"))
    .GET()
    .build();

HttpRequest createUser = base.copy()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .POST(BodyPublishers.ofString("""{"name":"Alice"}"""))
    .build();
```

### Error handling

```java
client.sendAsync(request, BodyHandlers.ofString())
    .thenApply(response -> {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("HTTP " + response.statusCode() +
                ": " + response.body());
        }
        return response;
    })
    .thenApply(HttpResponse::body)
    .thenAccept(this::process)
    .exceptionally(ex -> {
        if (ex.getCause() instanceof HttpTimeoutException) {
            System.err.println("Request timed out");
        } else if (ex.getCause() instanceof ConnectException) {
            System.err.println("Connection failed");
        } else {
            System.err.println("Error: " + ex.getMessage());
        }
        return null;
    });
```

---

## Summary

| Class / Interface | Purpose |
|---|---|
| `HttpClient` | Entry point; manages connections, configuration, request execution |
| `HttpClient.Builder` | Configures version, timeouts, redirects, auth, proxy, executor |
| `HttpRequest` | Immutable request: method, URI, headers, body, timeout |
| `HttpRequest.Builder` | Fluent builder for requests |
| `HttpResponse<T>` | Response: status, headers, body, version, URI |
| `HttpResponse.BodyHandlers` | Standard body handling strategies |
| `HttpResponse.BodyPublishers` | Standard body publishing strategies |
| `HttpHeaders` | Multi-map of response headers |
| `HttpTimeoutException` | Thrown when request timeout expires |
| `WebSocket` | Bidirectional communication over WebSocket |
| `WebSocket.Builder` | Configures subprotocols, headers for WebSocket |
