:-['airlines.pl'].

% reach(A,B,Path):- reachhelp(A,B,Path,[A]).
%
% reachhelp(A,B,Path,Visited):-
% Path =[A,B],
% edge(A,B),
% not(contains(Visited,B)).
%
% reachhelp(A,B,Path,Visited):-
% Path =[A|T],
% edge(A,C),
% not(contains(Visited,C)),
% reachhelp(C,B,T,[C|Visited]).
%
% contains(Array,Elt):- Array = [H|_], H = Elt.
% contains(Array,Elt):- Array = [_|T], contains(T,Elt).
%
%Result is [Total Price, Duration of flights, Number of airlines, Path]
%Path is [[Origin, Airline, Dest]...]
%Flight is [Airline,Origin,Dest,Cost,Time of Flight]
%Airport is [Airport or City, Tax, Delay]


% tripHelper will take
% Curr,Path,Visited,UsedAir,TCost,TTime,Final,FinalDest
trip(Origin,Dest,R):-
    airport(Origin,Tax,Delay),

    tripHelper(Origin,[],_,[Origin],[],Tax,Delay,R,Dest)
    .

%tripHelper(FinalDest,FinalDest,_,_,_,_,_,[FinalDest],FinalDest).
 tripHelper(Curr,_,Path,Visited,UsedAir,TCost,TTime,Final,FinalDest):-

not(contains(Visited,FinalDest)),
flight(Fl,Curr,FinalDest,Cost,Time),
addToAir(UsedAir,Fl,NUsed),
    %TCost is TCost + Cost,
    %TTime is TTime + Time,
    sum(TCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    len(NUsed,N),

    Final = [TCCost,TTTime,N,[Path,[Curr,Fl,FinalDest]]]
    %tripHelper(Final,Final,_,_,_,_,_,[Final],Final)
.
tripHelper(Curr,_,Path,Visited,UsedAir,TCost,TTime,Final,FinalDest):-
    flight(Fl,Curr,Nxt,Cost,Time),
    not(contains(Visited,Nxt)),

    addToAir(UsedAir,Fl,NUsed),
    addTaxDelay(Visited,Nxt,Tax,Delay),
    sum(TCost,Tax,Titem),
    sum(Titem,Cost,TCCost),
    sum(TTime,Delay,TDelay),
    sum(TDelay,Time, TTTime),
    %TCost is TCost + Tax,
    %TCost is TCost + Cost,
    %TTime is TTime + Delay,
    %TTime is TTime + Time,
    %flight(Fl1,Nxt,_,Cost1,Time1),
    append(Path,[Curr,Fl,Nxt], NPath),
    tripHelper(Nxt,_,NPath,[Nxt|Visited],NUsed,TCCost,TTTime,Final,FinalDest)
    .
tripHelper(Curr,_,Path,Visited,UsedAir,TCost,TTime,Final,FinalDest):-
    flight(Fl,Nxt,Curr,Cost,Time),
    not(contains(Visited,Nxt)),

    addToAir(UsedAir,Fl,NUsed),
    addTaxDelay(Visited,Nxt,Tax,Delay),
    sum(TCost,Tax,Titem),
    sum(Titem,Cost,TCCost),
    sum(TTime,Delay,TDelay),
    sum(TDelay,Time, TTTime),
    %TCost = TCost + Tax,
    %TCost = TCost + Cost,
    %TTime = TTime + Delay,
    %TTime = TTime + Time,
    %flight(Fl1,Nxt,_,Cost1,Time1),
    MyPath is [Path,[Curr,Fl,Nxt]],
    append(Path,[Curr,Fl,Nxt], NPath),
    %[Path,[Curr,Fl,Nxt]]
    tripHelper(Nxt,_,NPath,[Nxt|Visited],[NUsed|UsedAir],TCCost,TTTime,Final,FinalDest)
    .

%not(contains(Visited,T))

contains(Array,Elt):- Array = [H|_], H = Elt.
 contains(Array,Elt):- Array = [_|T], contains(T,Elt).


addToAir(UsedAir,Fl, NUsedAir):-
    (not(contains(UsedAir,Fl))
    -> NUsedAir = Fl
    ;   NUsedAir = UsedAir
                                ).

addTaxDelay(Visited,Airport, Tax,Delay):-
    (not(contains(Visited,Airport))
    -> airport(Airport,T,D), Delay is D, Tax is T
                                         ;   Delay is 0, Tax is 0

    ).

sum(Item1,Item2, Sum):- Sum is Item1 + Item2.

len([], 0).
len([_X|Xs], K) :-len(Xs, K1),K is K1 + 1.
