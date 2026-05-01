%% Data Structures in Erlang
%% =========================
%% Erlang provides lists, tuples, maps, and records.
%% Immutable data structures designed for concurrent, fault-tolerant systems.

-module(data_structures).
-export([main/0]).

%% ── Lists (linked lists) ─────────────────────────────

demonstrate_lists() ->
  io:format("--- Lists (linked, immutable) ---~n"),

  %% List literal (syntactic sugar for cons cells)
  Fruits = [apple, banana, cherry],
  io:format("fruits = ~w~n", [Fruits]),

  %% Head and tail (pattern matching)
  [First | Rest] = Fruits,
  io:format("first = ~w~n", [First]),
  io:format("rest = ~w~n", [Rest]),

  %% Indexing (inefficient O(n))
  io:format("fruits[0] = ~w~n", [lists:nth(0, Fruits)]),
  io:format("fruits[2] = ~w~n", [lists:nth(2, Fruits)]),

  %% cons (prepend)
  Fruits2 = [date | Fruits],
  io:format("After cons date: ~w~n", [Fruits2]),

  %% length
  io:format("length = ~p~n", [length(Fruits)]),

  %% List comprehensions
  Squares = [X * X || X <- lists:seq(1, 10)],
  io:format("squares (1-10): ~w~n", [Squares]),

  Evens = [X || X <- lists:seq(1, 20), X rem 2 =:= 0],
  io:format("evens (1-20): ~w~n", [Evens]).

%% ── Tuples (fixed-size, heterogenous) ─────────────

demonstrate_tuples() ->
  io:format("~n--- Tuples (fixed-size) ---~n"),

  %% Tuple literal
  Point = {10, 20},
  io:format("point = ~w~n", [Point]),

  %% Element access by index
  io:format("point[0] = ~p~n", [element(1, Point)]),
  io:format("point[1] = ~p~n", [element(2, Point)]),

  %% Pattern matching (destructuring)
  {X, Y} = Point,
  io:format("X = ~p, Y = ~p~n", [X, [Y]),

  %% Tuple with many elements
  RGB = {255, 0, 128},
  io:format("RGB = ~w~n", [RGB]).

%% ── Maps (key-value) ─────────────────────────────

demonstrate_maps() ->
  io:format("~n--- Maps (key-value) ---~n"),

  %% Map literal
  Person = #{name => "Ada", age => 36, city => "London"},
  io:format("person = ~w~n", [Person]),

  %% Access
  io:format("name = ~s~n", [maps:get(name, Person)]),
  io:format("age = ~p~n", [maps:get(age, Person)]),

  %% Check existence
  io:format("has email? ~p~n", [maps:is_key(email, Person)]),

  %% Update (add or modify)
  Person2 = maps:put(email, "ada@example.com", Person),
  io:format("After add email: ~w~n", [Person2]),

  Person3 = maps:remove(city, Person2),
  io:format("After remove city: ~w~n", [Person3]),

  %% Get all keys and values
  io:format("keys: ~w~n", [maps:keys(Person)]),
  io:format("values: ~w~n", [maps:values(Person)]),

  %% Fold (reduce)
  Sum = lists:foldl(fun(X, Acc) -> X + Acc end, 0, [1, 2, 3, 4, 5]),
  io:format("fold sum = ~p~n", [Sum]),

  %% Map (transform)
  Doubled = maps:map(fun(_K, V) -> V * 2 end, #{a => 1, b => 2, c => 3}),
  io:format("doubled map: ~w~n", [Doubled]).

%% ── Sets ───────────────────────────────────────────

demonstrate_sets() ->
  io:format("~n--- Sets (unique elements) ---~n"),

  %% Set literal (duplicates removed)
  Tags = sets:from_list([python, typing, python, data]),
  io:format("tags = ~w~n", [Tags]),
  io:format("size = ~p~n", [sets:size(Tags)]),

  %% Add element
  Tags2 = sets:add_element(algorithms, Tags),
  io:format("After add 'algorithms': ~w~n", [Tags2]),

  %% Remove element
  Tags3 = sets:del_element(data, Tags2),
  io:format("After remove 'data': ~w~n", [Tags3]),

  %% Check membership
  io:format("is_member 'typing'? ~p~n", [sets:is_element(typing, Tags)]),

  %% Set operations
  SetA = sets:from_list([1, 2, 3, 4, 5]),
  SetB = sets:from_list([4, 5, 6, 7, 8]),
  Union = sets:union(SetA, SetB),
  Intersection = sets:intersection(SetA, SetB),
  Difference = sets:subtract(SetA, SetB),

  io:format("union: ~w~n", [Union]),
  io:format("intersection: ~w~n", [Intersection]),
  io:format("difference A-B: ~w~n", [Difference]),

  %% Subset/superset
  io:format("subset? {2,3,4} of {1,2,3,4,5,6}? ~p~n",
    [sets:is_subset(sets:from_list([2, 3, 4]), SetA)]),
  io:format("superset? {1,2,3,4,5,6} of {2,3,4}? ~p~n",
    [sets:is_superset(SetA, sets:from_list([2, 3, 4]))]).

%% ── Records (named tuples) ─────────────────────────

-record(point, {x, y}).
-record(person, {name, age}).

demonstrate_records() ->
  io:format("~n--- Records (named tuples) ---~n"),

  %% Record creation
  P = #point{x = 10, y = 20},
  io:format("Point = ~w~n", [P]),

  %% Access fields (use #record.field)
  io:format("x = ~p~n", [P#point.x]),
  io:format("y = ~p~n", [P#point.y]),

  %% Pattern matching records
  #point{x = X, y = Y} = P,
  io:format("X = ~p, Y = ~p~n", [X, [Y]),

  %% Update records
  P2 = P#point{x = 30},
  io:format("After update x: ~w~n", [P2]),
  io:format("Original unchanged: ~w~n", [P]),

  %% Person record with multiple fields
  Ada = #person{name = "Ada", age = 36},
  io:format("Person = ~w~n", [Ada]),
  io:format("name = ~s~n", [Ada#person.name]),
  io:format("age = ~p~n", [Ada#person.age]).

%% ── Binary (binary data) ─────────────────────────

demonstrate_binary() ->
  io:format("~n--- Binaries (binary data) ---~n"),

  %% << for building binaries
  Byte1 = 16#FF,
  Byte2 = 16#AA,

  %% Concatenate binaries
  Combined = <<Byte1, Byte2>>,
  io:format("Combined = ~w~n", [Combined]),

  %% Size in bytes
  io:format("size = ~p~n", [byte_size(Combined)]),

  %% Extract segment
  <<First:8, _Last:8>> = Combined,
  io:format("First 8 bits = ~w~n", [First]),
  io:format("Last 8 bits = ~w~n", [_Last]),

  %% Bit syntax
  <<Bit1:1, _:7>> = Combined,
  io:format("First bit = ~p~n", [Bit1]).

%% ── Processes (lightweight actors) ─────────────────

demonstrate_processes() ->
  io:format("~n--- Processes (actors) ---~n"),

  %% Spawn process
  Pid = spawn(fun() ->
    receive
      hello -> io:format("Received: hello~n")
    end
  end),

  %% Send message
  Pid ! hello,

  %% Self-pid
  io:format("Process ID: ~w~n", [self()]),
  io:format("Spawned PID: ~w~n", [Pid]).

%% ── ETS (Erlang Term Storage) ─────────────

demonstrate_ets() ->
  io:format("~n--- ETS (Term Storage) ---~n"),

  %% Create named ETS table
  TableId = ets:new(counter_table, [set, named_table, public]),

  %% Insert
  ets:insert(TableId, {counter, 0}),

  %% Lookup
  case ets:lookup(TableId, counter) of
    [{counter, Value}] -> io:format("counter value = ~p~n", [Value]);
    [] -> io:format("counter not found~n")
  end,

  %% Update
  ets:update_counter(TableId, counter, {1}),
  io:format("After increment: ~p~n", [ets:lookup_element(2, TableId, counter)]),

  %% Delete table
  ets:delete(TableId).

%% ── Summary ───────────────────────────────────────────

main(_) ->
  io:format("Data Structures in Erlang~n"),
  io:format("========================~n~n"),

  demonstrate_lists(),
  demonstrate_tuples(),
  demonstrate_maps(),
  demonstrate_sets(),
  demonstrate_records(),
  demonstrate_binary(),
  demonstrate_processes(),
  demonstrate_ets(),

  io:format("~n--- Summary ---~n"),
  io:format("Erlang data structures:~n"),
  io:format("  - List: linked, head|tail, O(n) access~n"),
  io:format("  - Tuple: fixed-size, element() access~n"),
  io:format("  - Map: key-value, hash-based~n"),
  io:format("  - Set: unique elements~n"),
  io:format("  - Record: named tuples with field access~n"),
  io:format("  - Binary: packed binary data~n"),
  io:format("  - Process: lightweight actor model~n"),
  io:format("  - ETS: in-memory term storage~n"),
  io:format("  - Immutability: all data is immutable~n"),
  io:format("  - Pattern matching: primary control flow~n"),
  io:format("  - Let it crash: fault-tolerance philosophy~n").
