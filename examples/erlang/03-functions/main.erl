%%% Functions in Erlang
%%% ====================
%%% Erlang functions are defined by pattern matching clauses.
%%% Anonymous functions (funs) are first-class values.
%%% Higher-order functions are idiomatic.

-module(main).
-export([start/0]).

start() ->
    %% ── Basic functions ──────────────────────────

    io:format("greet: ~s~n", [greet("Atlas")]),

    %% ── Pattern matching in functions ────────────

    io:format("~n--- Pattern matching ---~n"),
    io:format("factorial(10) = ~p~n", [factorial(10)]),
    io:format("fibonacci(10) = ~p~n", [fibonacci(10)]),

    io:format("describe(42) = ~s~n", [describe(42)]),
    io:format("describe(hello) = ~s~n", [describe(hello)]),
    io:format("describe(\"hi\") = ~s~n", [describe("hi")]),

    %% ── Guards ───────────────────────────────────

    io:format("~n--- Guards ---~n"),
    io:format("bmi(22.5) = ~s~n", [bmi(22.5)]),
    io:format("bmi(30.0) = ~s~n", [bmi(30.0)]),
    io:format("abs_val(-5) = ~p~n", [abs_val(-5)]),
    io:format("abs_val(5) = ~p~n", [abs_val(5)]),

    %% ── Anonymous functions (funs) ───────────────

    io:format("~n--- Anonymous functions (funs) ---~n"),

    Square = fun(X) -> X * X end,
    Double = fun(X) -> X * 2 end,
    io:format("Square(5) = ~p~n", [Square(5)]),
    io:format("Double(5) = ~p~n", [Double(5)]),

    %% Funs can have multiple clauses
    Describe = fun
        (X) when is_integer(X) -> "integer";
        (X) when is_float(X)   -> "float";
        (X) when is_atom(X)    -> "atom";
        (_)                    -> "other"
    end,
    io:format("Describe(42) = ~s~n", [Describe(42)]),
    io:format("Describe(hello) = ~s~n", [Describe(hello)]),

    %% ── Higher-order functions ───────────────────

    io:format("~n--- Higher-order functions ---~n"),

    Numbers = lists:seq(1, 10),

    %% map
    Squares = lists:map(fun(X) -> X * X end, Numbers),
    io:format("map(square) = ~p~n", [Squares]),

    %% filter
    Evens = lists:filter(fun(X) -> X rem 2 == 0 end, Numbers),
    io:format("filter(even) = ~p~n", [Evens]),

    %% foldl (reduce)
    Total = lists:foldl(fun(X, Acc) -> X + Acc end, 0, Numbers),
    io:format("foldl(+, 0) = ~p~n", [Total]),

    Product = lists:foldl(fun(X, Acc) -> X * Acc end, 1, Numbers),
    io:format("foldl(*, 1) = ~p~n", [Product]),

    %% any, all
    HasEven = lists:any(fun(X) -> X rem 2 == 0 end, Numbers),
    AllPositive = lists:all(fun(X) -> X > 0 end, Numbers),
    io:format("any(even) = ~p, all(positive) = ~p~n", [HasEven, AllPositive]),

    %% sort with custom comparator
    Words = ["banana", "apple", "cherry", "date"],
    ByLength = lists:sort(fun(A, B) -> length(A) =< length(B) end, Words),
    io:format("sort by length = ~p~n", [ByLength]),

    %% ── Closures ─────────────────────────────────

    io:format("~n--- Closures ---~n"),

    Factor = 3,
    Triple = fun(X) -> X * Factor end,  %% captures Factor
    io:format("Triple(5) = ~p~n", [Triple(5)]),

    MakeMultiplier = fun(F) -> fun(X) -> X * F end end,
    TimesFive = MakeMultiplier(5),
    TimesSeven = MakeMultiplier(7),
    io:format("TimesFive(4) = ~p~n", [TimesFive(4)]),
    io:format("TimesSeven(4) = ~p~n", [TimesSeven(4)]),

    %% ── Custom HOF ───────────────────────────────

    io:format("~n--- Custom HOF ---~n"),

    ApplyTwice = fun(F, X) -> F(F(X)) end,
    io:format("ApplyTwice(+1, 5) = ~p~n",
              [ApplyTwice(fun(X) -> X + 1 end, 5)]),
    io:format("ApplyTwice(*2, 3) = ~p~n",
              [ApplyTwice(fun(X) -> X * 2 end, 3)]),

    %% ── List comprehensions ──────────────────────

    io:format("~n--- List comprehensions ---~n"),

    SquaresLC = [X * X || X <- lists:seq(1, 10)],
    io:format("[X*X || X <- 1..10] = ~p~n", [SquaresLC]),

    EvensLC = [X || X <- lists:seq(1, 20), X rem 2 == 0],
    io:format("[X || X <- 1..20, even] = ~p~n", [EvensLC]),

    Pythag = [{A,B,C} || C <- lists:seq(1, 20),
                          B <- lists:seq(1, C),
                          A <- lists:seq(1, B),
                          A*A + B*B == C*C],
    io:format("Pythagorean triples = ~p~n", [Pythag]),

    %% ── Recursion (Erlang's primary looping) ─────

    io:format("~n--- Recursion ---~n"),

    io:format("my_length([1,2,3,4,5]) = ~p~n", [my_length([1,2,3,4,5])]),
    io:format("my_reverse([1,2,3]) = ~p~n", [my_reverse([1,2,3])]),
    io:format("my_sum([1..10]) = ~p~n", [my_sum(Numbers)]),

    %% ── Summary ──────────────────────────────────

    io:format("~n--- Summary ---~n"),
    io:format("Erlang functions:~n"),
    io:format("  - Defined by pattern matching clauses~n"),
    io:format("  - Guards for conditional logic~n"),
    io:format("  - Anonymous functions: fun(X) -> X end~n"),
    io:format("  - Closures capture enclosing scope~n"),
    io:format("  - lists:map, filter, foldl, foldr~n"),
    io:format("  - List comprehensions~n"),
    io:format("  - Recursion for all looping~n"),
    io:format("  - Tail-call optimisation for efficiency~n"),
    ok.

%% ── Named functions (must be at module level) ────────

greet(Name) ->
    "Hello, " ++ Name ++ "!".

factorial(0) -> 1;
factorial(N) when N > 0 -> N * factorial(N - 1).

fibonacci(0) -> 0;
fibonacci(1) -> 1;
fibonacci(N) when N > 1 -> fibonacci(N-1) + fibonacci(N-2).

describe(X) when is_integer(X) -> "integer";
describe(X) when is_float(X)   -> "float";
describe(X) when is_atom(X)    -> "atom";
describe(X) when is_list(X)    -> "list";
describe(_)                    -> "other".

bmi(X) when X < 18.5 -> "underweight";
bmi(X) when X < 25.0 -> "normal";
bmi(X) when X < 30.0 -> "overweight";
bmi(_)               -> "obese".

abs_val(X) when X < 0 -> -X;
abs_val(X)            -> X.

%% Tail-recursive versions
my_length(List) -> my_length(List, 0).
my_length([], Acc) -> Acc;
my_length([_|T], Acc) -> my_length(T, Acc + 1).

my_reverse(List) -> my_reverse(List, []).
my_reverse([], Acc) -> Acc;
my_reverse([H|T], Acc) -> my_reverse(T, [H|Acc]).

my_sum(List) -> my_sum(List, 0).
my_sum([], Acc) -> Acc;
my_sum([H|T], Acc) -> my_sum(T, Acc + H).
