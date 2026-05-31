# Large Language Models

How language models are built, how they work, and how software engineers
integrate them into real systems — from transformer fundamentals to
production RAG pipelines and agent architectures.

---

## Position in the Atlas

| Dimension     | Value                                                                 |
|---------------|-----------------------------------------------------------------------|
| Group         | Machine Learning & LLMs                                               |
| Connects to   | Architecture (RAG, agents), Developer Tools (Copilot, Cursor), Distributed Systems (serving infrastructure), Databases (vector stores) |
| Key authors   | Vaswani, Kaplan, Brown, Wei, Ouyang, Karpathy                        |
| Key works     | Attention Is All You Need (2017), Scaling Laws (2020), GPT-3 (2020), InstructGPT (2022), Chain-of-Thought (2022) |

---

## What This Topic Covers

This topic approaches LLMs from the perspective of a **software engineer**
building with them — not an ML researcher training them from scratch.
The goal is to understand the machinery well enough to make good design
decisions: when to prompt, when to fine-tune, when to use RAG, how to
evaluate, and how to reason about failure modes.

---

## Map of Concepts

```mermaid
flowchart TD
    T["Transformer Architecture"]

    T --> TOK["Tokenization & Embeddings"]
    T --> ATT["Attention Mechanism<br/>(self-attention, multi-head)"]
    T --> PRE["Pretraining<br/>(next-token prediction)"]

    PRE --> SCA["Scaling Laws<br/>(model size, data, compute)"]
    PRE --> INST["Instruction Tuning & RLHF"]

    INST --> PROM["Prompting Strategies"]

    PROM --> ZS["Zero-shot"]
    PROM --> FS["Few-shot"]
    PROM --> COT["Chain-of-Thought"]

    ZS & FS & COT --> APP["Applied Patterns"]

    APP --> RAG["RAG<br/>―<br/>Vector stores<br/>Chunking<br/>Reranking"]
    APP --> AGT["Agents<br/>―<br/>Tool use<br/>ReAct loop<br/>Memory"]

    RAG & AGT --> INT["Integration Patterns"]

    INT --> EVA["Evaluation"]
    INT --> SAF["Safety & Alignment"]

    style T fill:#e1f5fe,color:#333333,stroke:#90caf9
    style PROM fill:#e8f5e9,color:#333333,stroke:#90caf9
    style APP fill:#e8f5e9,color:#333333,stroke:#90caf9
    style INT fill:#e8f5e9,color:#333333,stroke:#90caf9
    style EVA fill:#fff3e0,color:#333333,stroke:#90caf9
    style SAF fill:#fff3e0,color:#333333,stroke:#90caf9
```

---

## Foundations

### How LLMs Work

A large language model is a neural network trained to predict the next
token in a sequence. The architecture that made this practical at scale
is the **transformer**, introduced by Vaswani et al. in 2017.

The transformer replaced recurrent networks (RNNs, LSTMs) with a
mechanism called **self-attention**, which allows every token in a
sequence to attend directly to every other token — regardless of distance.
This made parallel training over long sequences feasible and unlocked
the scaling that defines modern LLMs.

Three concepts are worth understanding before moving to applied work:

**Tokenization.** LLMs do not process characters or words — they process
tokens, subword units produced by algorithms like Byte-Pair Encoding (BPE).
"unbelievable" might become ["un", "believ", "able"]. Token count is the
unit of cost, context, and limit. Understanding tokenization explains
why LLMs struggle with character-level tasks (counting letters, reversing
strings) and why whitespace and punctuation behave unexpectedly.

**Embeddings.** Each token is mapped to a vector in a high-dimensional
space. Proximity in that space reflects semantic similarity. Embeddings
are also the basis for retrieval in RAG systems — documents and queries
are embedded so that nearest-neighbor search can find relevant content.

**Context window.** The model processes a fixed-length window of tokens
at inference time. Everything the model "knows" about the current
interaction must fit in that window — system prompt, conversation history,
retrieved documents, and the current user input. Managing the context
window is one of the core engineering challenges in LLM systems.

### Transformer internals at a glance

```mermaid
flowchart LR
    IN["Input text"] --> TOK["Tokenizer<br/>(BPE)"]
    TOK --> EMB["Token<br/>Embeddings"]
    EMB --> POS["+ Positional<br/>Encoding"]
    POS --> ATT["Self-Attention<br/>× N layers"]
    ATT --> FFN["Feed-Forward<br/>Network"]
    FFN --> OUT["Next-token<br/>Probabilities"]

    style IN  fill:#e1f5fe,color:#333333,stroke:#90caf9
    style TOK fill:#e8f5e9,color:#333333,stroke:#90caf9
    style EMB fill:#e8f5e9,color:#333333,stroke:#90caf9
    style POS fill:#e8f5e9,color:#333333,stroke:#90caf9
    style ATT fill:#fff3e0,color:#333333,stroke:#90caf9
    style FFN fill:#fff3e0,color:#333333,stroke:#90caf9
    style OUT fill:#e1f5fe,color:#333333,stroke:#90caf9
```

---

### Pretraining and Scaling Laws

LLMs are pretrained on large text corpora using next-token prediction
as a self-supervised objective. No labels are needed — the text itself
provides the signal. This is why pretraining can scale to trillions of tokens.

The 2020 **Scaling Laws** paper (Kaplan et al.) showed that model
performance follows predictable power laws as a function of model size,
dataset size, and compute budget. The practical implication: given a
fixed compute budget, there are optimal ways to allocate parameters
versus training tokens. The Chinchilla paper (Hoffmann et al., 2022)
revised the original scaling law estimates and showed that many large
models at the time were undertrained relative to their size.

---

### Instruction Tuning and RLHF

A pretrained model predicts text — it does not follow instructions.
To make models useful as assistants, two further steps are common:

**Instruction tuning** (also called supervised fine-tuning, SFT) trains
the model on examples of instructions paired with ideal responses.
This teaches the model to follow the format of a question-answer or
instruction-completion interaction.

**Reinforcement Learning from Human Feedback (RLHF)** uses human
preference judgments to train a reward model, then uses that reward
model to further fine-tune the language model with reinforcement
learning. InstructGPT (Ouyang et al., 2022) introduced this pipeline
for GPT-3 and demonstrated large improvements in helpfulness and
harmlessness relative to SFT alone.

**Direct Preference Optimization (DPO)** is a more recent alternative
to RLHF that achieves similar alignment results without a separate
reward model, by framing the preference learning problem directly as
a classification objective over the language model.

```mermaid
flowchart LR
    PT["Pretrained<br/>Base Model"]

    PT --> SFT["Supervised Fine-Tuning<br/>(instruction examples)"]
    SFT --> RM["Reward Model<br/>training<br/>(human preferences)"]
    RM --> RLHF["RL Fine-Tuning<br/>(PPO vs reward model)"]
    RLHF --> CHAT["Instruction-following<br/>Model"]

    PT --> DPO["Direct Preference<br/>Optimization"]
    DPO --> CHAT

    style PT   fill:#e1f5fe,color:#333333,stroke:#90caf9
    style SFT  fill:#e8f5e9,color:#333333,stroke:#90caf9
    style RM   fill:#e8f5e9,color:#333333,stroke:#90caf9
    style RLHF fill:#fff3e0,color:#333333,stroke:#90caf9
    style DPO  fill:#fff3e0,color:#333333,stroke:#90caf9
    style CHAT fill:#e1f5fe,color:#333333,stroke:#90caf9
```

---

## Prompting

Prompting is the primary interface between software engineers and LLMs.
A prompt is the text (or structured input) sent to the model at inference
time. How that prompt is constructed dramatically affects output quality.

### Prompting strategies compared

```mermaid
flowchart TD
    P["Prompting<br/>Strategies"]

    P --> ZS["Zero-shot<br/>―<br/>No examples<br/>Task description only"]
    P --> FS["Few-shot<br/>―<br/>Input→output examples<br/>before the real query"]
    P --> COT["Chain-of-Thought<br/>―<br/>Step-by-step reasoning<br/>before the answer"]
    P --> SO["Structured Output<br/>―<br/>JSON mode /<br/>function calling"]

    ZS --> WHEN_ZS["Works when:<br/>task is well-known<br/>format is obvious"]
    FS --> WHEN_FS["Works when:<br/>format or tone<br/>must be demonstrated"]
    COT --> WHEN_COT["Works when:<br/>multi-step reasoning,<br/>arithmetic, logic"]
    SO --> WHEN_SO["Works when:<br/>downstream code<br/>consumes the output"]

    style P        fill:#e1f5fe,color:#333333,stroke:#90caf9
    style ZS       fill:#e8f5e9,color:#333333,stroke:#90caf9
    style FS       fill:#e8f5e9,color:#333333,stroke:#90caf9
    style COT      fill:#e8f5e9,color:#333333,stroke:#90caf9
    style SO       fill:#e8f5e9,color:#333333,stroke:#90caf9
    style WHEN_ZS  fill:#fff3e0,color:#555555,stroke:#90caf9
    style WHEN_FS  fill:#fff3e0,color:#555555,stroke:#90caf9
    style WHEN_COT fill:#fff3e0,color:#555555,stroke:#90caf9
    style WHEN_SO  fill:#fff3e0,color:#555555,stroke:#90caf9
```

### Zero-shot prompting

Ask the model to perform a task with no examples. Works well for tasks
within the model's training distribution and where the desired format
is self-evident.

```
Classify the sentiment of this review as positive, negative, or neutral.

Review: "The battery life is excellent but the screen is dim."
```

### Few-shot prompting

Provide a small number of input-output examples before the actual input.
Demonstrates the desired format, tone, and reasoning style without
any gradient updates to the model.

```
Review: "Fast shipping, terrible packaging." → negative
Review: "Exactly as described, very happy." → positive
Review: "The battery life is excellent but the screen is dim." → ?
```

### Chain-of-thought prompting

Wei et al. (2022) showed that asking the model to reason step by step
before giving a final answer dramatically improves performance on
multi-step reasoning tasks (arithmetic, symbolic reasoning, commonsense).

```
Q: Roger has 5 tennis balls. He buys 2 more cans of 3 balls each.
   How many tennis balls does he have now?

A: Roger starts with 5 balls. He buys 2 cans × 3 balls = 6 balls.
   5 + 6 = 11. The answer is 11.

Q: The cafeteria had 23 apples. They used 20 to make lunch and bought
   6 more. How many apples do they have?
```

The key insight: chain-of-thought works because the intermediate
reasoning steps are themselves tokens, and the model's next-token
prediction can condition on them. The model is not doing internal
symbolic reasoning — it is generating text that happens to be
reasoning, and that text helps it generate better text next.

### Structured output

Most production LLM systems need structured data, not prose. Modern
APIs support **JSON mode** or **function calling** (also called tool
use or structured output) that constrains the model's output to a
schema. This is more reliable than asking for JSON in a prompt and
parsing the result.

```json
{
  "name": "extract_sentiment",
  "parameters": {
    "sentiment": { "type": "string", "enum": ["positive", "negative", "neutral"] },
    "confidence": { "type": "number" }
  }
}
```

### System prompts and prompt architecture

In chat-format APIs, the **system prompt** sets the model's persona,
constraints, and task context before the conversation begins. Production
systems commonly structure their prompt as:

```mermaid
flowchart TD
    CTX["Context Window"]

    CTX --> SYS["System prompt<br/>―<br/>persona · constraints · output format"]
    CTX --> RET["Retrieved context<br/>―<br/>documents from RAG<br/>ranked by relevance"]
    CTX --> HIS["Conversation history<br/>―<br/>prior turns<br/>compressed if needed"]
    CTX --> USR["Current user input<br/>―<br/>the actual request"]

    SYS --> NOTE_SYS["Consumes tokens<br/>even before the user speaks"]
    RET --> NOTE_RET["Must be ranked<br/>and truncated to fit"]
    HIS --> NOTE_HIS["Grows unboundedly<br/>without compression"]

    style CTX      fill:#e1f5fe,color:#333333,stroke:#90caf9
    style SYS      fill:#e8f5e9,color:#333333,stroke:#90caf9
    style RET      fill:#e8f5e9,color:#333333,stroke:#90caf9
    style HIS      fill:#e8f5e9,color:#333333,stroke:#90caf9
    style USR      fill:#e8f5e9,color:#333333,stroke:#90caf9
    style NOTE_SYS fill:#fff3e0,color:#555555,stroke:#90caf9
    style NOTE_RET fill:#fff3e0,color:#555555,stroke:#90caf9
    style NOTE_HIS fill:#fff3e0,color:#555555,stroke:#90caf9
```

---

## Retrieval-Augmented Generation (RAG)

RAG is an architecture pattern that augments an LLM's response with
content retrieved from an external knowledge source at inference time.
It solves two problems simultaneously: the model's knowledge cutoff
(pretraining data has a date), and the context window constraint
(you cannot fit an entire knowledge base into a prompt).

### Basic RAG pipeline

```mermaid
flowchart TD
    Q["User query"]
    Q --> EMB["Embed query<br/>(embedding model)"]
    EMB --> VS["Vector search<br/>(ANN over document store)"]
    VS --> RET["Retrieve top-k chunks"]
    RET --> RNK["Rank / filter / rerank<br/>(cross-encoder)"]
    RNK --> CTX["Insert into prompt context"]
    CTX --> LLM["LLM generates answer"]

    DOCS["Document corpus"] --> CHUNK["Chunking"]
    CHUNK --> DEMB["Embed chunks<br/>(same embedding model)"]
    DEMB --> STORE["Vector store<br/>(pgvector · Pinecone · Qdrant)"]
    STORE --> VS

    style Q     fill:#e1f5fe,color:#333333,stroke:#90caf9
    style LLM   fill:#e1f5fe,color:#333333,stroke:#90caf9
    style DOCS  fill:#e1f5fe,color:#333333,stroke:#90caf9
    style STORE fill:#fff3e0,color:#333333,stroke:#90caf9
    style RNK   fill:#fff3e0,color:#333333,stroke:#90caf9
    style CTX   fill:#fff3e0,color:#333333,stroke:#90caf9
```

### Key design decisions

**Chunking strategy.** How documents are split into retrievable units
matters more than most teams expect. Fixed-size chunks lose sentence
boundaries. Semantic chunking (split on topic change) is more expensive
but improves retrieval precision. Chunk size is a hyperparameter:
smaller chunks improve precision, larger chunks preserve more context.

**Embedding model.** The model used to embed documents and queries must
be the same, and its quality directly determines retrieval quality.
Dedicated embedding models (e.g., text-embedding-3, BGE, E5) outperform
general-purpose LLMs for this task.

**Vector store.** Retrieved by approximate nearest-neighbor (ANN) search
over embedding vectors. Common choices: pgvector (PostgreSQL extension),
Pinecone, Weaviate, Qdrant, Chroma. The right choice depends on scale,
latency requirements, and whether you need hybrid search (vector +
keyword).

**Reranking.** A first-stage retrieval returns the top-k candidates by
embedding similarity. A second-stage reranker (a cross-encoder model)
rescores those candidates more accurately by jointly encoding the query
and each candidate. Reranking significantly improves precision at the
cost of additional latency.

**Context assembly.** Retrieved chunks must be inserted into the prompt
in a way that the model can use effectively. Research (the "lost in the
middle" paper) shows that LLMs perform worse on content placed in the
middle of long contexts — placing the most relevant retrieved content
at the beginning or end of the context window improves performance.

### When RAG fits — and when it does not

```mermaid
flowchart LR
    DEC{"Does RAG fit?"}

    DEC -->|"Knowledge changes<br/>frequently"| YES1["✓ Use RAG"]
    DEC -->|"Answers must be<br/>traceable to sources"| YES2["✓ Use RAG"]
    DEC -->|"Knowledge exceeds<br/>fine-tune budget"| YES3["✓ Use RAG"]

    DEC -->|"Must reason over<br/>entire corpus at once"| NO1["✗ RAG won't help"]
    DEC -->|"Latency budget is<br/>very tight"| NO2["✗ RAG won't help"]
    DEC -->|"Knowledge is stable<br/>and small"| NO3["✗ Fine-tune instead"]

    style DEC  fill:#e1f5fe,color:#333333,stroke:#90caf9
    style YES1 fill:#c8e6c9,color:#333333,stroke:#81c784
    style YES2 fill:#c8e6c9,color:#333333,stroke:#81c784
    style YES3 fill:#c8e6c9,color:#333333,stroke:#81c784
    style NO1  fill:#ffcdd2,color:#333333,stroke:#e57373
    style NO2  fill:#ffcdd2,color:#333333,stroke:#e57373
    style NO3  fill:#ffcdd2,color:#333333,stroke:#e57373
```

---

## Agents and Tool Use

An LLM agent is a system in which the language model is the reasoning
engine, and it is given access to tools (functions, APIs, databases,
code interpreters) that it can call to gather information or take actions.
The model generates a plan, calls tools, observes results, and continues
reasoning until it produces a final response.

### The ReAct loop

```mermaid
sequenceDiagram
    participant U  as User
    participant LM as LLM
    participant T  as Tool

    U  ->> LM: Question / task
    loop Until answer is ready
        LM ->> LM: Thought — reason about next step
        LM ->> T:  Action — call tool with arguments
        T  ->> LM: Observation — tool result
    end
    LM ->> U: Final answer
```

### Tool use and function calling

Modern LLM APIs support **function calling**: the model can emit a
structured request to call a function, and the application executes
it and returns the result. This is more reliable than asking the model
to emit a tool call as free text and parsing it.

```python
tools = [
    {
        "name": "get_stock_price",
        "description": "Get the current stock price for a ticker symbol",
        "parameters": {
            "ticker": {"type": "string", "description": "e.g. AAPL"}
        }
    }
]
```

### Memory patterns

Agents operating over multiple turns or long sessions need memory
beyond the context window:

| Memory type      | Implementation                         | Trade-off                            |
|------------------|----------------------------------------|--------------------------------------|
| In-context       | Append all history to prompt           | Simple; limited by context window    |
| Summarization    | Compress old turns into summary        | Loses detail; saves tokens           |
| External (RAG)   | Store and retrieve past interactions   | Scalable; retrieval latency          |
| Entity memory    | Extract and track key entities         | Structured; requires extraction step |

### Agent reliability challenges

```mermaid
flowchart TD
    AGT["Agent system"]

    AGT --> CE["Cascading errors<br/>―<br/>wrong step 2 corrupts<br/>all subsequent reasoning"]
    AGT --> PI["Prompt injection<br/>―<br/>malicious content in<br/>tool output hijacks plan"]
    AGT --> IL["Infinite loops<br/>―<br/>no explicit termination<br/>condition"]
    AGT --> UL["Unpredictable latency<br/>―<br/>multi-step tool calls<br/>non-deterministic time"]

    CE --> MG["Mitigations"]
    PI --> MG
    IL --> MG
    UL --> MG

    MG --> M1["Explicit error handling<br/>per tool call"]
    MG --> M2["Maximum step budget"]
    MG --> M3["Human-in-the-loop<br/>for high-stakes actions"]
    MG --> M4["Input / output<br/>sanitization"]

    style AGT fill:#e1f5fe,color:#333333,stroke:#90caf9
    style MG  fill:#e8f5e9,color:#333333,stroke:#90caf9
    style CE  fill:#ffcdd2,color:#333333,stroke:#e57373
    style PI  fill:#ffcdd2,color:#333333,stroke:#e57373
    style IL  fill:#ffcdd2,color:#333333,stroke:#e57373
    style UL  fill:#ffcdd2,color:#333333,stroke:#e57373
    style M1  fill:#c8e6c9,color:#333333,stroke:#81c784
    style M2  fill:#c8e6c9,color:#333333,stroke:#81c784
    style M3  fill:#c8e6c9,color:#333333,stroke:#81c784
    style M4  fill:#c8e6c9,color:#333333,stroke:#81c784
```

---

## Integration Patterns

### Patterns overview

```mermaid
flowchart TD
    INT["LLM Integration<br/>Patterns"]

    INT --> DIR["Direct API call<br/>―<br/>Prompt in, text out<br/>Simplest form"]
    INT --> PIPE["LLM as pipeline step<br/>―<br/>One deterministic stage<br/>replaced by LLM"]
    INT --> ORCH["LLM as orchestrator<br/>―<br/>LLM decides routing<br/>and tool calls"]

    DIR  --> GOOD_DIR["Best for:<br/>well-defined tasks<br/>stable prompt<br/>predictable output"]
    PIPE --> GOOD_PIPE["Best for:<br/>classification · extraction<br/>summarization · translation"]
    ORCH --> GOOD_ORCH["Best for:<br/>open-ended workflows<br/>multi-step reasoning"]

    DIR  --> BAD_DIR["Risk:<br/>no fallback if<br/>output is malformed"]
    PIPE --> BAD_PIPE["Risk:<br/>LLM errors isolated<br/>but not always caught"]
    ORCH --> BAD_ORCH["Risk:<br/>cascading failures<br/>unpredictable latency"]

    style INT       fill:#e1f5fe,color:#333333,stroke:#90caf9
    style DIR       fill:#e8f5e9,color:#333333,stroke:#90caf9
    style PIPE      fill:#e8f5e9,color:#333333,stroke:#90caf9
    style ORCH      fill:#e8f5e9,color:#333333,stroke:#90caf9
    style GOOD_DIR  fill:#c8e6c9,color:#333333,stroke:#81c784
    style GOOD_PIPE fill:#c8e6c9,color:#333333,stroke:#81c784
    style GOOD_ORCH fill:#c8e6c9,color:#333333,stroke:#81c784
    style BAD_DIR   fill:#ffcdd2,color:#333333,stroke:#e57373
    style BAD_PIPE  fill:#ffcdd2,color:#333333,stroke:#e57373
    style BAD_ORCH  fill:#ffcdd2,color:#333333,stroke:#e57373
```

### Direct API integration

The simplest integration: call an LLM API, pass a prompt, receive text.
Appropriate for well-defined, low-complexity tasks where the prompt
is stable and the output format is predictable.

Design considerations:
- Retry logic with exponential backoff (rate limits, transient errors)
- Timeout handling (LLM latency is variable)
- Cost tracking (token consumption can grow unexpectedly)
- Output validation before using the result downstream

### LLM as a component in a pipeline

The LLM handles one step in a larger data or processing pipeline:
classification, extraction, summarization, translation. Surrounding
steps are deterministic code. This is the most reliable integration
pattern because failures are isolated and the system degrades gracefully.

```mermaid
flowchart LR
    IN["Input"] --> PP["Pre-process<br/>(deterministic)"]
    PP --> LLM["LLM<br/>classify · extract<br/>summarise · translate"]
    LLM --> VAL["Validate output<br/>(schema · confidence)"]
    VAL --> DN["Downstream logic<br/>(deterministic)"]

    style LLM fill:#fff3e0,color:#333333,stroke:#90caf9
```

### LLM as orchestrator

The LLM decides what to do next: which tool to call, which branch to
take, whether to ask the user a clarifying question. This enables
flexible, open-ended workflows but introduces the reliability challenges
described in the agents section.

### Caching

LLM API calls are expensive and slow. Caching is viable when:
- The same prompt (or semantically equivalent prompt) is sent repeatedly
- **Exact-match caching** — hash the prompt, cache the response
- **Semantic caching** — embed the prompt, retrieve cached responses for
  similar queries (higher hit rate, lower precision)

### Observability

LLM systems require observability at a different layer than traditional
software:

| Signal           | What to track                                      |
|------------------|----------------------------------------------------|
| Latency          | Time-to-first-token, total completion time         |
| Token usage      | Prompt tokens, completion tokens, cost per request |
| Quality          | Automated eval scores, human feedback, thumbs      |
| Failures         | Rate limit errors, context overflow, refusals      |
| Prompt versions  | Which prompt template produced which output        |

Tools like LangSmith, Weights & Biases Weave, and Helicone provide
LLM-specific tracing and eval infrastructure.

---

## Evaluation

Evaluating LLM systems is harder than evaluating deterministic software.
There is no ground-truth output for most open-ended tasks.

### Evaluation approaches compared

```mermaid
quadrantChart
    title Evaluation approaches — cost vs. reliability
    x-axis Low cost --> High cost
    y-axis Low reliability --> High reliability
    quadrant-1 Ideal
    quadrant-2 Expensive but trustworthy
    quadrant-3 Avoid
    quadrant-4 Fast iteration
    Task-specific evals: [0.45, 0.80]
    Human evaluation: [0.85, 0.90]
    LLM-as-judge: [0.35, 0.65]
    Benchmark eval: [0.20, 0.50]
    Reference metrics (BLEU/ROUGE): [0.15, 0.30]
```

**Benchmark evaluation.** Run the model on a standardized dataset with
known answers (MMLU, HumanEval, MATH, HellaSwag). Useful for tracking
capability across model versions. Not a reliable proxy for task-specific
quality.

**Human evaluation.** Ask humans to rate outputs on dimensions like
helpfulness, accuracy, coherence. Gold standard, but expensive and slow.
Not practical for continuous evaluation during development.

**LLM-as-judge.** Use a stronger LLM (e.g., GPT-4) to evaluate outputs
from a weaker or different model. Scalable and surprisingly consistent
with human judgments on many tasks, but inherits the judge model's biases
and is not reliable for tasks where the judge model also struggles.

**Reference-based metrics.** Compare generated text to a reference output
using metrics like BLEU, ROUGE, or BERTScore. Useful for translation
and summarization where reference outputs exist. Poor proxies for overall
quality on open-ended generation.

**Task-specific evals.** Define the success criteria for your specific
use case and build an eval suite around them. This is the most
practically useful form of evaluation and the hardest to build without
domain knowledge.

### The eval loop

```mermaid
flowchart LR
    DEF["Define task<br/>& success criteria"] --> COL["Collect<br/>examples"]
    COL --> RUN["Run model"]
    RUN --> SCO["Score outputs"]
    SCO --> ITR["Iterate on<br/>prompt / model"]
    ITR --> RUN

    style DEF fill:#e1f5fe,color:#333333,stroke:#90caf9
    style SCO fill:#fff3e0,color:#333333,stroke:#90caf9
    style ITR fill:#fff3e0,color:#333333,stroke:#90caf9
```

Treat evals like tests: automate them, run them on every prompt change,
and track metrics over time. Regression in LLM quality is as real as
regression in software behavior.

---

## Safety and Alignment

```mermaid
flowchart TD
    SAF["Safety & Alignment<br/>Challenges"]

    SAF --> HAL["Hallucination<br/>―<br/>plausible but false output<br/>structural, not a bug"]
    SAF --> INJ["Prompt Injection<br/>―<br/>untrusted input overrides<br/>system instructions"]
    SAF --> BIA["Bias & Fairness<br/>―<br/>training data patterns<br/>reflected in outputs"]
    SAF --> RLH["RLHF limits<br/>―<br/>Goodhart's Law:<br/>optimising the proxy<br/>not the goal"]

    HAL --> M_HAL["Mitigate with:<br/>RAG · citation requirements<br/>verification step<br/>Uncertainty in UI"]
    INJ --> M_INJ["Mitigate with:<br/>Input sanitisation<br/>Instruction / data separation<br/>Agent action limits"]
    BIA --> M_BIA["Mitigate with:<br/>Fairness audit<br/>proportional to deployment risk"]
    RLH --> M_RLH["Accept with:<br/>Diversity of annotators<br/>Red-teaming<br/>Ongoing monitoring"]

    style SAF   fill:#e1f5fe,color:#333333,stroke:#90caf9
    style HAL   fill:#ffcdd2,color:#333333,stroke:#e57373
    style INJ   fill:#ffcdd2,color:#333333,stroke:#e57373
    style BIA   fill:#ffcdd2,color:#333333,stroke:#e57373
    style RLH   fill:#ffcdd2,color:#333333,stroke:#e57373
    style M_HAL fill:#c8e6c9,color:#333333,stroke:#81c784
    style M_INJ fill:#c8e6c9,color:#333333,stroke:#81c784
    style M_BIA fill:#c8e6c9,color:#333333,stroke:#81c784
    style M_RLH fill:#c8e6c9,color:#333333,stroke:#81c784
```

### Hallucination

LLMs generate plausible-sounding text, not necessarily true text.
They have no internal world model that they check facts against — they
predict tokens that are likely given the context. Hallucination is a
structural property of the architecture, not a bug that will be fixed.

Mitigation strategies:
- **RAG** — ground answers in retrieved source documents
- **Citation requirements** — instruct the model to cite sources for
  factual claims and surface those citations to users
- **Verification step** — use a second LLM call or deterministic check
  to verify claims before displaying them
- **Confidence communication** — design UI to communicate uncertainty
  rather than presenting all outputs as equally reliable

### Prompt injection

Malicious content in user input or retrieved documents can override
the system prompt's instructions. This is the LLM equivalent of SQL
injection: untrusted input is concatenated with trusted instructions
and interpreted as instructions.

Mitigations are imperfect: input sanitization, clear structural
separation between instructions and data, monitoring for anomalous
outputs, and limiting what actions an agent can take without human
confirmation.

### Bias and fairness

LLMs reflect patterns in their training data, including social biases
around gender, race, nationality, and other dimensions. This matters
more in some deployment contexts than others — a code completion tool
has different risk exposure than a hiring screening tool. Evaluation
should include fairness audits proportional to the stakes of the deployment.

### RLHF and its limits

RLHF improves helpfulness and reduces obvious harms but does not
eliminate them. The reward model is trained on human preferences, and
human preferences are inconsistent, gameable, and dependent on who the
annotators are. Models optimized for RLHF can learn to produce outputs
that score well on the reward model without actually being more truthful
or safe — a form of Goodhart's Law applied to alignment.

---

## AI-Assisted Development

LLMs have changed the daily workflow of software engineers.
The most widely used tools:

| Tool             | Primary use                                          |
|------------------|------------------------------------------------------|
| GitHub Copilot   | Inline code completion in the editor                 |
| Cursor           | AI-native editor with chat, edit, and agent modes    |
| Codeium          | Free alternative to Copilot                          |
| Aider            | LLM pair programmer in the terminal                  |
| ChatGPT / Claude | General-purpose chat for design, debugging, writing  |

### What works — and what needs care

```mermaid
flowchart LR
    subgraph GOOD["Works well ✓"]
        G1["Generating boilerplate<br/>test scaffolding · DTOs · CLIs"]
        G2["Explaining unfamiliar code<br/>trace through execution"]
        G3["First-draft documentation<br/>docstrings · README · API docs"]
        G4["Debugging with context<br/>error messages · stack traces"]
        G5["Cross-language translation<br/>working impl → another language"]
    end

    subgraph CARE["Needs care ⚠"]
        C1["Trusting output unread<br/>may be subtly wrong<br/>or use deprecated APIs"]
        C2["Security-sensitive code<br/>crypto · auth · input sanitization"]
        C3["Novel architecture<br/>LLMs optimize common patterns<br/>poor on genuinely new problems"]
    end

    GOOD ~~~ CARE

    style GOOD fill:#c8e6c9,color:#333333,stroke:#81c784
    style CARE fill:#ffe0b2,color:#333333,stroke:#ffb74d
```

The sustainable workflow treats the LLM as a fast but fallible
collaborator: its output always passes through the engineer's judgment,
tests, and review.

---

## Key Authors

| Author            | Contribution                                                      |
|-------------------|-------------------------------------------------------------------|
| Ashish Vaswani    | Co-author of Attention Is All You Need; transformer architecture  |
| Ilya Sutskever    | Co-founder OpenAI; GPT series; scaling insight                    |
| Jared Kaplan      | Scaling Laws for Neural Language Models (2020)                    |
| Tom Brown         | Lead author of GPT-3 paper (2020)                                 |
| Jason Wei         | Chain-of-thought prompting (2022); instruction tuning             |
| Long Ouyang       | InstructGPT / RLHF paper (2022)                                   |
| Andrej Karpathy   | Educator and practitioner; nanoGPT; LLM intuition building        |
| Lilian Weng       | Influential blog posts on agents, prompting, alignment            |

---

## Key Works

| Work                                            | Authors           | Year | Significance                                          |
|-------------------------------------------------|-------------------|------|-------------------------------------------------------|
| Attention Is All You Need                       | Vaswani et al.    | 2017 | Introduced the transformer architecture               |
| Language Models are Few-Shot Learners           | Brown et al.      | 2020 | GPT-3; demonstrated in-context learning at scale      |
| Scaling Laws for Neural Language Models         | Kaplan et al.     | 2020 | Power-law relationships between compute, data, size   |
| Training language models to follow instructions | Ouyang et al.     | 2022 | InstructGPT; RLHF pipeline for alignment              |
| Chain-of-Thought Prompting Elicits Reasoning    | Wei et al.        | 2022 | Step-by-step reasoning improves complex task accuracy |
| ReAct: Synergizing Reasoning and Acting         | Yao et al.        | 2022 | Interleaved reasoning and tool use in agents          |
| Lost in the Middle                              | Liu et al.        | 2023 | LLMs underuse context placed in the middle of prompts |
| Direct Preference Optimization                  | Rafailov et al.   | 2023 | Alignment without a separate reward model             |

---

## Reading Path

```mermaid
flowchart LR
    S1["1 · Start here<br/>Karpathy — Let's build GPT<br/>~2h video, builds transformer<br/>from scratch"]
    S2["2 · Architecture<br/>Transformer deep dive<br/>attention · tokens · context"]
    S3["3 · Scale<br/>Scaling Laws — Kaplan 2020<br/>why bigger works"]
    S4["4 · Alignment<br/>InstructGPT — Ouyang 2022<br/>RLHF pipeline"]
    S5["5 · Prompting<br/>zero-shot · few-shot<br/>chain-of-thought · structured"]
    S6["6 · Retrieval<br/>RAG patterns<br/>external knowledge"]
    S7["7 · Agents<br/>ReAct · function calling<br/>memory · reliability"]
    S8["8 · Production<br/>Evaluation + Safety<br/>quality & failure modes"]

    S1 --> S2 --> S3 --> S4 --> S5 --> S6 --> S7 --> S8

    style S1 fill:#e1f5fe,color:#333333,stroke:#90caf9
    style S2 fill:#e8f5e9,color:#333333,stroke:#90caf9
    style S3 fill:#e8f5e9,color:#333333,stroke:#90caf9
    style S4 fill:#e8f5e9,color:#333333,stroke:#90caf9
    style S5 fill:#fff3e0,color:#333333,stroke:#90caf9
    style S6 fill:#fff3e0,color:#333333,stroke:#90caf9
    style S7 fill:#fff3e0,color:#333333,stroke:#90caf9
    style S8 fill:#fff3e0,color:#333333,stroke:#90caf9
```

---

*LLMs connect to [Architecture](../architecture/index.md) through RAG and
agent patterns, to [Developer Tools](../dev-tools/index.md) through
AI-assisted coding, to [Databases](../databases/index.md) through vector
stores, and to [Distributed Systems](../distributed/index.md) through the
infrastructure needed to serve models at scale.*
