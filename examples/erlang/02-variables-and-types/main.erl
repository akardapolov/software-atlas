%%% Variables and Types in Erlang
%%% ==============================
%%% Erlang uses dynamic, strong typing.
%%% Variables start with Uppercase and are single-assignment (immutable).

-module(main).
-export([start/0]).

start() ->
    %% ── Basic types ──────────────────────────────────

    %% Integers — arbitrary precision (no overflow)
    Count = 42,
    Big = math:pow(10, 100),  % returns float for large numbers
    io:format("Count = ~p~n", [Count]),
    io:format("Big = ~p~n", [Big]),

    %% Floats — IEEE 754 double precision
    Pi = 3.14159,
    io:format("Pi = ~p~n", [Pi]),

    %% Atoms — symbolic constants (like symbols in Ruby/Lisp)
    Status = ok,
    Color = red,
    io:format("Status = ~p, Color = ~p~n", [Status, Color]),

    %% Booleans — atoms 'true' and 'false'
    Active = true,
    io:format("Active = ~p~n", [Active]),

    %% Strings — actually lists of integers (Unicode code points)
    Name = "Software Atlas",
    io:format("Name = ~p~n", [Name]),
    io:format("Name as list = ~w~n", [Name]),  % shows integer list

    %% ── Single assignment (immutability) ─────────────

    io:format("~n--- Single assignment ---~n"),

    X = 42,
    %% X = 43,  % ERROR: no match of right hand side value 43
    %% Once X is bound to 42, it cannot be rebound!

    io:format("X = ~p (cannot change!)~n", [X]),

    %% But you can use different variable names
    Y = X + 1,
    io:format("Y = X + 1 = ~p~n", [Y]),

    %% ── Pattern matching ─────────────────────────────

    io:format("~n--- Pattern matching ---~n"),

    %% = is the MATCH operator, not assignment!
    %% It binds unbound variables AND checks equality for bound ones

    {ok, Value} = {ok, 42},
    io:format("{ok, Value} = {ok, 42} → Value = ~p~n", [Value]),

    %% Pattern matching on structure
    {point, PX, PY} = {point, 3.0, 4.0},
    io:format("Point = (~p, ~p)~n", [PX, PY]),

    %% Match failure raises an error
    %% {ok, _} = {error, "oops"},  % ERROR: no match

    %% ── Tuples ───────────────────────────────────────

    io:format("~n--- Tuples ---~n"),

    %% Tuples are fixed-size, heterogeneous collections
    %% Convention: first element is an atom tag
    Point = {point, 3.0, 4.0},
    Person = {person, "Ada", 36},
    Result = {ok, "success"},
    Error = {error, not_found},

    io:format("Point = ~p~n", [Point]),
    io:format("Person = ~p~n", [Person]),
    io:format("Result = ~p~n", [Result]),
    io:format("Error = ~p~n", [Error]),

    %% Tuple size and element access
    io:format("tuple_size(Point) = ~p~n", [tuple_size(Point)]),
    io:format("element(2, Point) = ~p~n", [element(2, Point)]),

    %% ── Lists ────────────────────────────────────────

    io:format("~n--- Lists ---~n"),

    %% Lists are variable-length linked lists
    Nums = [1, 2, 3, 4, 5],
    Fruits = ["apple", "banana", "cherry"],

    io:format("Nums = ~p~n", [Nums]),
    io:format("Fruits = ~p~n", [Fruits]),
    io:format("length(Nums) = ~p~n", [length(Nums)]),

    %% Head and tail
    [H | T] = Nums,
    io:format("[H | T] = ~p → H = ~p, T = ~p~n", [Nums, H, T]),

    %% Prepend (O(1))
    Extended = [0 | Nums],
    io:format("[0 | Nums] = ~p~n", [Extended]),

    %% List concatenation
    Joined = [1, 2] ++ [3, 4],
    io:format("[1,2] ++ [3,4] = ~p~n", [Joined]),

    %% List comprehension
    Evens = [N || N <- lists:seq(1, 20), N rem 2 == 0],
    io:format("Evens 1..20 = ~p~n", [Evens]),

    %% ── Maps (Erlang 17+) ────────────────────────────

    io:format("~n--- Maps ---~n"),

    Ages = #{ada => 36, grace => 85},
    io:format("Ages = ~p~n", [Ages]),

    %% Access
    AdaAge = maps:get(ada, Ages),
    io:format("Ages[ada] = ~p~n", [AdaAge]),

    %% Missing key → error (use maps:get/3 for default)
    Default = maps:get(alan, Ages, 0),
    io:format("Ages[alan] (default 0) = ~p~n", [Default]),

    %% Update (returns new map — immutable!)
    Updated = Ages#{ada := 37},
    io:format("Updated = ~p~n", [Updated]),
    io:format("Original = ~p (unchanged!)~n", [Ages]),

    %% ── Strong typing (no implicit conversions) ──────

    io:format("~n--- Strong typing ---~n"),

    %% Erlang does NOT implicitly convert between types
    %% 1 + "2" → ERROR: bad argument in an arithmetic expression

    %% Explicit conversions
    IntVal = 42,
    StrVal = integer_to_list(IntVal),
    io:format("integer_to_list(42) = ~p~n", [StrVal]),

    FloatVal = float(IntVal),
    io:format("float(42) = ~p~n", [FloatVal]),

    %% ── Type checking with guards ────────────────────

    io:format("~n--- Type checking (guards) ---~n"),

    describe(42),
    describe(3.14),
    describe(hello),
    describe("text"),
    describe({point, 1, 2}),

    %% ── No null ──────────────────────────────────────

    io:format("~n--- No null ---~n"),

    %% Erlang has no null. Use atoms or tagged tuples:
    Found = {ok, 42},
    Missing = {error, not_found},
    Undefined = undefined,

    io:format("Found = ~p~n", [Found]),
    io:format("Missing = ~p~n", [Missing]),
    io:format("Undefined = ~p~n", [Undefined]),

    %% Pattern match to handle each case
    handle_result(Found),
    handle_result(Missing),

    %% ── Summary ──────────────────────────────────────

    io:format("~n--- Summary ---~n"),
    io:format("Erlang: dynamic + strong typing~n"),
    io:format("  - Types checked at runtime (dynamic)~n"),
    io:format("  - No implicit conversions (strong)~n"),
    io:format("  - Single assignment (immutable variables)~n"),
    io:format("  - Pattern matching instead of assignment~n"),
    io:format("  - Atoms for symbolic constants~n"),
    io:format("  - Tagged tuples: {ok, V}, {error, R}~n"),
    io:format("  - Arbitrary-precision integers~n"),
    io:format("  - No null~n"),
    ok.

%% ── Helper functions ─────────────────────────────────

describe(V) when is_integer(V) ->
    io:format("  ~p is an integer~n", [V]);
describe(V) when is_float(V) ->
    io:format("  ~p is a float~n", [V]);
describe(V) when is_atom(V) ->
    io:format("  ~p is an atom~n", [V]);
describe(V) when is_list(V) ->
    io:format("  ~p is a list~n", [V]);
describe(V) when is_tuple(V) ->
    io:format("  ~p is a tuple~n", [V]);
describe(V) ->
    io:format("  ~p is something else~n", [V]).

handle_result({ok, V}) ->
    io:format("  Result: got ~p~n", [V]);
handle_result({error, Reason}) ->
    io:format("  Result: error ~p~n", [Reason]).
