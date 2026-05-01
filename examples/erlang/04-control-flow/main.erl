%%% Control Flow in Erlang
%%% ======================
%%% Erlang has no loops. Control flow via pattern matching,
%%% case/if expressions, guards, and recursion.

-module(main).
-export([start/0]).

start() ->
    %% ── case expression ──────────────────────────

    io:format("--- case expression ---~n"),
    io:format("describe(42) = ~s~n", [describe(42)]),
    io:format("describe(ok) = ~s~n", [describe(ok)]),
    io:format("describe(\"hello\") = ~s~n", [describe("hello")]),

    %% ── if expression (guards only) ──────────────

    io:format("~n--- if expression ---~n"),
    io:format("grade(95) = ~s~n", [grade(95)]),
    io:format("grade(82) = ~s~n", [grade(82)]),
    io:format("grade(45) = ~s~n", [grade(45)]),

    %% ── Recursion (looping via recursion) ────────

    io:format("~n--- recursion (countdown) ---~n"),
    countdown(5),

    io:format("~n--- recursion (sum 1..10) ---~n"),
    io:format("sum(10) = ~p~n", [sum(10)]),

    io:format("~n--- recursion (factorial) ---~n"),
    io:format("factorial(5) = ~p~n", [factorial(5)]),

    %% ── Tail-recursive loop ──────────────────────

    io:format("~n--- tail recursion ---~n"),
    io:format("powers_of_two(8) = ~p~n", [powers_of_two(8)]),

    %% ── Higher-order iteration ───────────────────

    io:format("~n--- lists:foreach ---~n"),
    lists:foreach(fun(X) -> io:format("item: ~p~n", [X]) end, [1, 2, 3]),

    io:format("~n--- lists:map ---~n"),
    Squares = lists:map(fun(X) -> X * X end, [1, 2, 3, 4, 5]),
    io:format("squares = ~p~n", [Squares]),

    io:format("~n--- lists:filter ---~n"),
    Evens = lists:filter(fun(X) -> X rem 2 == 0 end, [1, 2, 3, 4, 5, 6]),
    io:format("evens = ~p~n", [Evens]),

    %% ── List comprehensions ──────────────────────

    io:format("~n--- list comprehensions ---~n"),
    Doubles = [X * 2 || X <- [1, 2, 3, 4, 5]],
    io:format("doubles = ~p~n", [Doubles]),

    Odds = [X || X <- [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], X rem 2 == 1],
    io:format("odds = ~p~n", [Odds]),

    %% ── Early exit via pattern matching ──────────

    io:format("~n--- early return (find first) ---~n"),
    io:format("find_even([1,3,5,8,9]) = ~p~n", [find_even([1, 3, 5, 8, 9])]),

    %% ── Summary ──────────────────────────────────

    io:format("~n--- Summary ---~n"),
    io:format("Erlang control flow:~n"),
    io:format("  - case: pattern matching on values~n"),
    io:format("  - if: guard-based branching~n"),
    io:format("  - Recursion: primary looping mechanism~n"),
    io:format("  - Tail recursion: constant stack space~n"),
    io:format("  - HOFs: lists:map, filter, foreach~n"),
    io:format("  - List comprehensions: [Expr || X <- List, Guard]~n"),
    ok.

%% ── case expression ────────────────────────────────

describe(X) when is_integer(X) -> "integer";
describe(X) when is_atom(X)    -> "atom";
describe(X) when is_list(X)    -> "string/list";
describe(_)                    -> "other".

%% ── if expression (guards only) ────────────────────

grade(Score) when Score >= 90 -> "A";
grade(Score) when Score >= 80 -> "B";
grade(Score) when Score >= 70 -> "C";
grade(Score) when Score >= 60 -> "D";
grade(_)                      -> "F".

%% ── Simple recursion ───────────────────────────────

countdown(0) ->
    io:format("blastoff!~n");
countdown(N) when N > 0 ->
    io:format("~p...~n", [N]),
    countdown(N - 1).

%% ── Tail-recursive sum ─────────────────────────────

sum(N) -> sum(N, 0).
sum(0, Acc) -> Acc;
sum(N, Acc) when N > 0 -> sum(N - 1, Acc + N).

factorial(0) -> 1;
factorial(N) when N > 0 -> N * factorial(N - 1).

%% ── Tail-recursive powers of two ───────────────────

powers_of_two(N) -> powers_of_two(N, 1, []).
powers_of_two(0, _, Acc) -> lists:reverse(Acc);
powers_of_two(N, Cur, Acc) when N > 0 ->
    powers_of_two(N - 1, Cur * 2, [Cur | Acc]).

%% ── Find first even (early exit via matching) ──────

find_even([]) -> none;
find_even([H | _]) when H rem 2 == 0 -> H;
find_even([_ | T]) -> find_even(T).
