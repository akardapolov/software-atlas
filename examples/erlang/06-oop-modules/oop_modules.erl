%% OOP and Modules in Erlang
%% ==========================
%% Erlang uses modules as the primary unit for code organization.
%% Behaviours (lightweight processes) provide concurrency.
%% Records are used as data structures.

-module(oop_and_modules).

%% ── Modules ─────────────────────────────

-export([demonstrate_modules/0]).

demonstrate_modules() ->
    io:format("~n--- Modules ---~n"),
    io:format("Current module: ~p~n", [?MODULE]),
    io:format("Exported functions: ~p~n", [?MODULE:info/0]),
    io:format("Loaded applications: ~p~n", [?MODULE:loaded_applications/0]).


%% ── Behaviours (Actor Model) ─────────────────

-export([demonstrate_behaviours/0]).

demonstrate_behaviours() ->
    io:format("~n--- Behaviours (Actors) ---~n"),

    %% Spawn a behaviour
    io:format("Spawning actor...~n"),
    Pid = spawn(fun() -> loop
        timer:sleep(100),
        io:format("Actor ~p~n~n", [Pid])
        end),

    ActorPid ! {greet, "Alice"},

    io:format("Sending message to actor...~n"),
    ActorPid ! {greet, "Bob"},

    io:format("Received response: ~n"),
    receive
        {greet, From, Name} -> io:format("From ~s greeting ~s to ~s!~n", [From, Name])
    after 500 -> io:format("Timeout, stopping...~n")
    end,

    %% Stop the actor
    exit(whereis(Pid, actor)).



%% ── Records (Data Structures) ─────────────────────

-export([demonstrate_records/0]).

demonstrate_records() ->
    io:format("~n--- Records (Data Structures) ---~n"),

    %% Record definition
    -record(person, name, age, city, email).

    %% Create records
    Person1 = #person{name = "Ada", age = 36, city = "London", email = "ada@example.com"},
    Person2 = #person{name = "Bob", age = 42, city = "New York", email = "bob@example.com"},

    %% Access record fields
    io:format("Person1 name: ~p~n", [Person1#person.name]),
    io:format("Person1 age: ~p~n", [Person1#person.age]),
    io:format("Person2 name: ~p~n", [Person2#person.name]),
    io:format("Person2 age: ~p~n", [Person2#person.age]),

    %% Update record (creates new instance)
    Person2Updated = Person2#person{city = "Boston"},
    io:format("Person2Updated city: ~p~n", [Person2Updated#person.city]),

    %% Nested records
    -record(address, street, city, country).
    Address1 = #address{street = "123 Main St", city = "London", country = "UK"},
    Address2 = #address{street = "456 Oak Ave", city = "New York", country = "USA"},

    Person3 = #person{name = "Charlie", age = 28, address = Address1},
    io:format("Person3 name: ~p~n", [Person3#person.name]),
    io:format("Person3 address: ~p~n", [Person3#person.address]),

    %% Pattern matching on records
    get_city(#person{name = _Name, address = #address{} = _City}) ->
        case address of
            #address{city = City} -> City;
            _ -> "Unknown"

    io:format("Ada's city: ~p~n", [get_city(Person1, Address1)]),


    %% Default values for record fields
    DefaultPerson = #person{name = "Unknown", age = 0},
    io:format("Default: ~p~n", [DefaultPerson]),


    %% Named parameters in records (type specs)
    -spec_circle(radius, circumference).


    -record(circle, radius, circumference).
    Circle1 = #circle{radius = 2, circumference = fun math:pi() * 2 * radius},
    Circle2 = #circle{radius = 3, circumference = fun math:pi() * 2 * radius},

    io:format("Circle1 radius: ~p~n", [Circle1#circle.radius]),
    io:format("Circle1 circumference: ~.2f~n", [Circle1#circle.circumference]),

    io:format("Circle2 radius: ~p~n", [Circle2#circle.radius]),
    io:format("Circle2 circumference: ~.2f~n", [Circle2#circle.circumference]),

    %% Polymorphic function with pattern matching
    -spec_shape(shape).
    area(#rectangle{width = 3, height = 4}) -> 12,
    area(#circle{radius = 2}) -> fun math:pi() * 2 * 2.



%% ── Processes with State ─────────────────────

-export([demonstrate_state/0]).

demonstrate_state() ->
    io:format("~n--- Processes with State ---~n"),

    %% Stateful process
    spawn(fun() ->
        register(),
        loop
            timer:sleep(100),
            receive
                {counter, Pid} ->
                    Pid ! {get_counter, self()},
                    receive
                        {current_count, Pid} ->
                            io:format("Counter updated to ~p~n~n", [current_count])
                    end
            end
    end),

    %% Register name
    register() -> counter_server.

    %% Client request
    counter_server ! {get_counter, self()},

    %% Handle responses
    receive
        {counter, Pid} ->
            io:format("Current counter: ~p~n~n", [counter])
        after 1000 ->
            io:format("Stopping...~n")
            exit(whereis(Pid, actor))
    end.



%% ── Generics (Types and Behaviours) ─────────────

-export([demonstrate_generics/0]).

demonstrate_generics() ->
    io:format("~n--- Generics (Types and Behaviours) ---~n"),

    %% Generic behaviours using parametrised modules
    -behaviour_info(callbacks, timeout).
    -behaviour_info(calculate_area, {circle, rectangle}, 300}).

    %% Use generic behaviour
    generic_server:start_link(),
    generic_server ! {calculate_area, {circle, #circle{radius = 3}}, 300},

    %% Handle responses
    receive
        {calculate_area, From, Result} ->
            io:format("Area result: ~p~n", [Result]),
            From ! {done, self()},
            io:format("From: ~p~n", [From])
        end.



%% ── Application Behavior (Supervision Tree) ─────────────

-export([demonstrate_application/0]).

demonstrate_application() ->
    io:format("~n--- Application Behavior (Supervision Tree) ---~n"),

    %% Supervision tree
    supervisor:start_link(),
    supervisor ! {add_child, [supervisor, worker]},

    %% Worker process
    spawn(fun() ->
        loop
            timer:sleep(100),
            io:format("Worker running...~n")
        end,

    %% Stop worker
    timer:sleep(500),
    worker ! {stop, normal},
    io:format("Worker stopped normally~n"),

    %% Supervisor handles worker crash
    receive
        {'EXIT', From, Reason} ->
            io:format("Worker ~p~n exited: ~p~n", [From, Reason]).



%% ── Summary ───────────────────────────────────

main(_) ->
    io:format("OOP and Modules in Erlang~n"),
    io:format("========================~n"),

    demonstrate_modules(),
    demonstrate_behaviours(),
    demonstrate_records(),
    demonstrate_state(),
    demonstrate_generics(),
    demonstrate_application(),

    io:format("~n--- Summary ---~n"),
    io:format("Erlang OOP:~n"),
    io:format("  - Modules: erlang modules for code organization~n"),
    io:format("  - Behaviours: lightweight actors for concurrency~n"),
    io:format("  - Records: named tuples with pattern matching~n"),
    io:format("  - No classes: use records instead~n"),
    io:format("  - Generic behaviours: parametrised modules~n"),
    io:format("  - Application behavior: supervision trees~n"),
    io:format("  - Pattern matching: primary control flow mechanism~n"),
    io:format("  - ! operator: matches and creates variables~n"),
    io:format("  - Functional: emphasis on immutability~n").
