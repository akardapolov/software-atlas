%% Testing in Erlang
%% =====================
%% EUnit macros, test generators

-module(main).
-export([main/0]).

-include_lib("eunit/include/eunit.hrl").

%% ── Basic test ─────────────────────────────────────

addition_test() ->
    ?assertEqual(8, 5 + 3).

string_length_test() ->
    ?assertEqual(5, length("hello")).

%% ── Exception testing ─────────────────────────────

divide_test() ->
    ?assertEqual(5, divide(10, 2)),
    ?assertError(division_by_zero, divide(10, 0)).

divide(_Numerator, 0) -> erlang:error(division_by_zero);
divide(Numerator, Denominator) -> Numerator div Denominator.

%% ── Test generator ─────────────────────────────────

square_test_() ->
    [ {1, 1},
      {2, 4},
      {3, 9},
      {-3, 9}
    ].

square_test_({Input, Expected}) ->
    ?assertEqual(Expected, Input * Input).

%% ── Fixture-based test ──────────────────────────────

with_fixture_test_() ->
    [{setup, [1, 2, 3], 6},
      {setup, [10, 20], 30}
    ].

with_fixture_test_({Setup, List, Expected}) ->
    ?assertEqual(Expected, sum_list(List)).

sum_list(List) -> lists:foldl(fun(X, Acc) -> X + Acc end, 0, List).

setup(List) -> List.

%% ── Subtest organization ─────────────────────────────

math_suite_test_() ->
    [
      {addition_test_},
      {subtraction_test_}
    ].

addition_test_() ->
    [{positive, [1, 2], 3},
      {negative, [-1, -2], -3}
    ].

addition_test_({Type, A, B, Expected}) ->
    ?assertEqual(Expected, lists:sum(A ++ B)).

subtraction_test_() ->
    [{basic, [10, 3], 7}
    ].

subtraction_test_({Type, A, B, Expected}) ->
    ?assertEqual(Expected, hd(A) - hd(B)).

%% ── Test with state ───────────────────────────────

counter_test() ->
    ?assertEqual(1, increment_counter(0)).

increment_counter(Count) -> Count + 1.

%% ── Summary ─────────────────────────────────────

summary_test() ->
    ?assertEqual(true, true).

%% ── Main ───────────────────────────────────────────

main() ->
    eunit:test(main, [verbose]).

%% Note: Run with:
%% erlc main.erl && erl -noshell -s main -s init stop
%% Or with rebar3: rebar3 eunit
