%% Concurrency in Erlang
%% =====================
%% Actor model: spawn, message passing, receive

-module(main).
-export([main/0]).

main() ->
    %% ── Basic spawn and message ─────────────────────

    io:format("--- Basic spawn and message~n"),

    Pid = spawn(fun() ->
        receive
            hello -> io:format("Received: hello~n");
            {bye, Msg} -> io:format("Bye: ~s~n", [Msg])
        end
    end),

    Pid ! hello,
    timer:sleep(100),
    Pid ! {bye, "Erlang"},

    %% ── Worker pool pattern ───────────────────────────

    io:format("~n--- Worker pool~n"),

    WorkerCount = 3,
    Workers = [spawn_worker(Self, N) || N <- lists:seq(1, WorkerCount)],

    %% Send jobs to random workers
    lists:foreach(fun(N) ->
        Worker = lists:nth((N rem WorkerCount) + 1, Workers),
        Worker ! {job, N}
    end, lists:seq(1, 9)),

    %% Collect results
    collect_results(9),

    %% ── Producer-consumer with selective receive ───────

    io:format("~n--- Producer-consumer~n"),

    Pid2 = spawn(fun() -> consumer_loop([]) end),

    Producer = spawn(fun() -> producer(Pid2) end),

    timer:sleep(500),
    Producer ! stop,

    timer:sleep(200),
    Pid2 ! stop,

    %% ── Link and exit monitoring ─────────────────────

    io:format("~n--- Exit monitoring~n"),

    Monitored = spawn_link(fun() ->
        timer:sleep(100),
        exit(normal)
    end),

    %% This will trap exit from Monitored
    spawn(fun() ->
        link(Monitored),
        receive
            {'EXIT', _Pid, Reason} ->
                io:format("Process exited: ~p~n", [Reason])
        end
    end),

    timer:sleep(200),

    %% ── Summary ─────────────────────────────────────

    io:format("~n--- Summary ---~n"),
    io:format("Erlang concurrency:~n"),
    io:format("  - spawn: create lightweight process~n"),
    io:format("  - Pid ! Msg: asynchronous message send~n"),
    io:format("  - receive: pattern-matching message mailbox~n"),
    io:format("  - spawn_link: link processes for monitoring~n"),
    io:format("  - No shared state: everything via messaging~n"),
    io:format("  - Isolated failures: let it crash philosophy~n").

spawn_worker(Master, Id) ->
    spawn(fun() -> worker_loop(Master, Id) end).

worker_loop(Master, Id) ->
    receive
        {job, N} ->
            Result = N * 2,
            Master ! {result, Id, Result},
            worker_loop(Master, Id);
        stop ->
            ok
    end.

collect_results(0) -> ok;
collect_results(Count) ->
    receive
        {result, _, _} ->
            collect_results(Count - 1)
    end.

consumer_loop(Acc) ->
    receive
        {item, I} ->
            io:format("Consumed: ~p~n", [I]),
            consumer_loop([I | Acc]);
        stop ->
            io:format("Total consumed: ~p~n", [lists:reverse(Acc)])
    end.

producer(Consumer) ->
    producer_loop(Consumer, 1).

producer_loop(Consumer, N) ->
    receive
        stop -> ok
    after 0 ->
        Consumer ! {item, N},
        timer:sleep(50),
        producer_loop(Consumer, N + 1)
    end.
