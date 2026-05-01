%% FP Features in Erlang
%% =====================
%% Pure functions, immutable data, recursion, pattern matching

-module(main).
-export([main/0]).

main() ->
    %% ── Pure functions ─────────────────────────────

    io:format("--- Pure functions ---~n"),

    %% Function as variable
    Add = fun(A, B) -> A + B,
    io:format("Add(5, 3) = ~p~n", [Add(5, 3)]),

    %% Higher-order function
    ApplyTwice = fun(F, X) -> F(F(X)),
    io:format("ApplyTwice(fun(X)->X+1, 5) = ~p~n", [ApplyTwice(fun(X)->X+1, 5)]),

    %% Named function
    Square = fun(X) -> X * X,
    io:format("Square(4) = ~p~n", [Square(4)]).


    %% ── Higher-order list functions ─────────────────────

    io:format("~n--- Higher-order list functions ---~n"),

    Numbers = lists:seq(1, 10),

    %% map (lists:map/2)
    Squares = lists:map(fun(X) -> X * X, Numbers),
    io:format("Squares: ~p~n", [Squares]),

    %% filter (lists:filter/2)
    Evens = lists:filter(fun(X) -> X rem 2 =:= 0, Numbers),
    io:format("Evens: ~p~n", [Evens]),

    %% foldl (lists:foldl/3)
    Sum = lists:foldl(fun(Acc, X) -> Acc + X, 0, Numbers),
    io:format("Sum: ~p~n", [Sum]),

    %% find (lists:find/2)
    FirstEven = lists:find(fun(X) -> X rem 2 =:= 0, Numbers),
    io:format("First even: ~p~n", [FirstEven]),

    %% any (lists:any/2)
    HasOdd = lists:any(fun(X) -> X rem 2 =/= 0, Numbers),
    io:format("Has odd number? ~p~n", [HasOdd]),

    %% all (lists:all/2)
    AllLessThan20 = lists:all(fun(X) -> X < 20, Numbers),
    io:format("All < 20? ~p~n", [AllLessThan20]).


    %% ── List comprehension ─────────────────────────────

    io:format("~n--- List comprehension ---~n"),

    %% [X*X || X <- Lists, X > 5]
    SquaresGT25 = [X*X || X <- Numbers, X*X > 25],
    io:format("Squares > 25: ~p~n", [SquaresGT25]).

    %% [X || X <- Lists, X rem 2 =:= 0, X < 10]
    EvensLT10 = [X || X <- Numbers, X rem 2 =:= 0, X < 10],
    io:format("Evens < 10: ~p~n", [EvensLT10]).

    %% List comprehension with multiple generators
    Pairs = [{X, Y} || X <- [1, 2, 3], Y <- [4, 5]],
    io:format("Pairs: ~p~n", [Pairs]).


    %% ── Closures (anonymous functions) ─────────────────

    io:format("~n--- Closures ---~n"),

    MakeMultiplier = fun(Factor) ->
        fun(X) -> X * Factor
    end,

    TimesThree = MakeMultiplier(3),
    TimesFive = MakeMultiplier(5),

    io:format("TimesThree(7) = ~p~n", [TimesThree(7)]),
    io:format("TimesFive(8) = ~p~n", [TimesFive(8)]).


    %% ── Pattern matching in functions ─────────────────────

    io:format("~n--- Pattern matching ---~n"),

    %% Match in function heads
    DescribeNumber = fun
        (1) -> "one";
        (2) -> "two";
        (3) -> "three";
        (_) -> "many"
    end,

    io:format("Number 2: ~s~n", [DescribeNumber(2)]),
    io:format("Number 5: ~s~n", [DescribeNumber(5)]),
    io:format("Number 99: ~s~n", [DescribeNumber(99)]).


    %% ── Recursion ─────────────────────────────────────

    io:format("~n--- Recursion ---~n"),

    %% Factorial (tail-recursive)
    Factorial = fun
        (0) -> 1;
        (N) when N > 0 -> N * Factorial(N - 1)
    end,

    io:format("Factorial(5) = ~p~n", [Factorial(5)]),
    io:format("Factorial(10) = ~p~n", [Factorial(10)]).


    %% ── Tail-recursive sum (accumulator) ─────────────

    SumList = fun
        SumList([], Acc) -> Acc;
        SumList([H|T], Acc) -> SumList(T, Acc + H)
    end,

    io:format("SumList([1,2,3]) = ~p~n", [SumList([1, 2, 3])]).


    %% ── Lazy evaluation (only when requested) ────────────

    io:format("~n--- Lazy evaluation ---~n"),

    %% Infinite list (not evaluated until needed)
    Naturals = fun() -> naturals() end,
    %% naturals/0 returns infinite list: [0,1,2,...]

    TakeTen = naturals(),
    TenItems = lists:sublist(TakeTen, 0, 10),
    io:format("First 10 natural numbers: ~p~n", [TenItems]).


    %% ── Summary ─────────────────────────────────────

    io:format("~n--- Summary ---~n"),
    io:format("Erlang FP features:~n"),
    io:format("  - Pure functions: no side effects~n"),
    io:format("  - Immutable data: cannot be mutated~n"),
    io:format("  - Pattern matching: function clauses by pattern~n"),
    io:format("  - Recursion: only iteration mechanism~n"),
    io:format("  - Tail recursion: optimized by BEAM~n"),
    io:format("  - Higher-order: map, filter, foldl~n"),
    io:format("  - List comprehensions: [X || X <- Lists]~n"),
    io:format("  - Anonymous functions (funs): closures~n"),
    io:format("  - Lazy evaluation: only when requested~n"),
    io:format("  - No loops: use recursion instead~n").
