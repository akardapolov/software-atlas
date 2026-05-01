%% Error Handling in Erlang
%% ========================
%% let it crash, supervisors, links, monitors, try...catch

-module(main).
-export([main/0]).

%% ── Custom exception (throw for errors) ───────────

custom_exception(Message) ->
    erlang:error(Message).

%% ── Division with error checking ───────────────────

safe_divide(_Numerator, 0) ->
    {error, division_by_zero};
safe_divide(Numerator, Denominator) ->
    Numerator div Denominator.

%% ── Process with link and monitor ─────────────────────

start_monitored_process(Parent) ->
    Pid = spawn_link(fun() ->
        receive
            stop -> ok;
            Msg -> io:format("Received: ~p~n", [Msg])
    end),
    io:format("Spawned process: ~p~n", [Pid]).

    %% Let the process crash
    Pid ! {crash_now, "die"},
    receive
        {'EXIT', Pid, Reason} ->
            io:format("Process exited: ~p~n", [Reason])
    end.

%% ── try...catch with specific error type ───────────

try_catch_demo() ->
    io:format("--- try...catch ---"),
    try
        custom_exception("This will be caught"),
        throw {custom_error, "Should not be caught"}
    catch
        {custom_error, Msg} ->
            io:format("Caught custom: ~p~n", [Msg])
    end,
        _ ->
            io:format("Other error~n").

%% ── catch/3 (catch specific error types) ─────────────

catch_three_demo() ->
    io:format("~n--- catch/3 ---"),
    try
        exit({bad_input, "Invalid input"}),
        throw {bad_data, "Data error"}
    catch
        {bad_input, _Reason} ->
            io:format("Caught bad_input~n");
        {bad_data, _Reason} ->
            io:format("Caught bad_data~n");
        _ ->
            io:format("Other error~n").

%% ── Supervisor behavior (OTP) ───────────────────

supervisor_behavior() ->
    io:format("~n--- Supervisor behavior ---"),
    io:format("Erlang/OTP supervisors provide:~n"),
    io:format("  - Automatic restart of child processes~n"),
    io:format("  - One-for-one strategy~n"),
    io:format("  - Error logging and telemetry~n"),
    io:format("  - Process trees for fault tolerance~n").

%% ── System processes (kernel errors) ─────────────

system_error_demo() ->
    io:format("~n--- System processes ---"),
    case sys:get_status() of
        {error, _} ->
            io:format("System error detected~n");
        {normal, _} ->
            io:format("System status: normal~n").

%% ── Summary ─────────────────────────────────────

main() ->
    io:format("--- Safe divide ---~n"),
    io:format("safe_divide(10, 2): ~p~n", [safe_divide(10, 2)]),
    io:format("safe_divide(10, 0): ~p~n", [safe_divide(10, 0)]),

    try_catch_demo(),
    catch_three_demo(),
    start_monitored_process(self),
    supervisor_behavior(),
    system_error_demo(),

    io:format("~n--- Summary ---~n"),
    io:format("Erlang error handling:~n"),
    io:format("  - let it crash: small processes are restarted~n"),
    io:format("  - Supervisors: OTP gen_server/gen_fsm for structured errors~n"),
    io:format("  - link/monitor: receive EXIT messages~n"),
    io:format("  - try...catch: structured error handling~n"),
    io:format("  - catch/3: catch specific error types~n"),
    io:format("  - System processes: sys:get_status() for kernel errors~n"),
    io:format("  - No shared exceptions: each process owns errors~n").
