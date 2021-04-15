:- ['airlines.pl'].

:-table trip/3.

trip(Origin,Dest,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Origin,Nxt,Cost,Time),
    member(Nxt,[Dest]),
    sum(Tax,Cost,TCCost),

    sum(Delay,Time, TTime),
    R = [TCCost,TTime,1,[[Origin,Fl,Dest]]]
    .

trip(Origin,Dest,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Nxt,Origin,Cost,Time),
    member(Nxt,[Dest]),
    sum(Tax,Cost,TCCost),

    sum(Delay,Time, TTime),
    R = [TCCost,TTime,1,[[Origin,Fl,Dest]]]

    .


trip(Origin,Dest,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Origin,Nxt,Cost,Time),

    not(member(Nxt,[Dest])),

    airport(Nxt,Tax1,Delay1),
    addTaxDelay([],Origin,Tax,Delay),
    sum(Tax,Tax1,Titem),
    sum(Titem,Cost,TCCost),
    sum(Delay,Delay1,TDelay),
    sum(TDelay,Time, TTTime),
    tripHelper(Nxt,[[Origin,Fl,Nxt]],[Origin|Nxt],[Fl],_,TCCost,TTTime,R,Dest)
    .
trip(Origin,Dest,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Nxt,Origin,Cost,Time),

    not(member(Nxt,[Dest])),

    airport(Nxt,Tax1,Delay1),
    addTaxDelay([],Origin,Tax,Delay),
	sum(Tax,Tax1,Titem),
    sum(Titem,Cost,TCCost),
    sum(Delay,Delay1,TDelay),
    sum(TDelay,Time, TTTime),

    tripHelper(Nxt,[[Origin,Fl,Nxt]],[Origin|Nxt],[Fl],_,TCCost,TTTime,R,Dest)
    .


:-table tripHelper/9.
tripHelper(FinalDest,Path,_,UsedAir,Count,TCCost,TTime,[TCCost,TTime,Count,Path],FinalDest):-
len(UsedAir,N),
    Count = N.


 tripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,FinalDest):-
	flight(Fl,Curr,FinalDest,Cost,Time),
	not(contains(Visited,FinalDest)),
	addToAir(UsedAir,Fl,NUsed),
    addTaxDelay([],Curr,Tax,_),
    sum(TCost,Tax,TTCost),


    sum(TTCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    len(NUsed,N),
    Count = N,
	append(Path, [[Curr,Fl,FinalDest]], NPath),
    Final = [TCCost,TTTime,Count,NPath]

.

tripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,FinalDest):-
	flight(Fl,FinalDest,Curr,Cost,Time),
	not(contains(Visited,FinalDest)),

	addToAir(UsedAir,Fl,NUsed),
    addTaxDelay([],Curr,Tax,_),

    sum(TCost,Tax,TTCost),


    sum(TTCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    len(NUsed,N),
    Count = N,
	append(Path, [[Curr,Fl,FinalDest]], NPath),
    Final = [TCCost,TTTime,Count,NPath]

.




tripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,FinalDest):-
    flight(Fl,Curr,Nxt,Cost,Time),
    not(contains(Visited,Nxt)),

    addToAir(UsedAir,Fl,NUsed),
    addTaxDelay(Visited,Nxt,Tax,Delay),

    sum(TCost,Tax,Titem),
    sum(Titem,Cost,TCCost),
    sum(TTime,Delay,TDelay),
    sum(TDelay,Time, TTTime),

    append(Path,[[Curr,Fl,Nxt]], NPath),


    tripHelper(Nxt,NPath,[Nxt|Visited],NUsed,Count,TCCost,TTTime,Final,FinalDest)
    .
tripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,FinalDest):-
    flight(Fl,Nxt,Curr,Cost,Time),
    not(contains(Visited,Nxt)),

    addToAir(UsedAir,Fl,NUsed),
    addTaxDelay(Visited,Nxt,Tax,Delay),
    sum(TCost,Tax,Titem),
    sum(Titem,Cost,TCCost),
    sum(TTime,Delay,TDelay),
    sum(TDelay,Time, TTTime),


    append(Path,[[Curr,Fl,Nxt]], NPath),

    tripHelper(Nxt,NPath,[Nxt|Visited],NUsed,Count,TCCost,TTTime,Final,FinalDest)
    .

:-table tripk/4.

tripk(Origin,Dest,K,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Origin,Nxt,Cost,Time),
    member(Nxt,[Dest]),
    sum(Tax,Cost,TCCost),

    sum(Delay,Time, TTime),
    gec(TTime,K),
    R = [TCCost,TTime,1,[[Origin,Fl,Dest]]]
    .

tripk(Origin,Dest,K,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Nxt,Origin,Cost,Time),
    member(Nxt,[Dest]),


    sum(Tax,Cost,TCCost),

    sum(Delay,Time, TTime),
    gec(TTime,K),
    R = [TCCost,TTime,1,[[Origin,Fl,Dest]]]

    .


tripk(Origin,Dest,K,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Origin,Nxt,Cost,Time),

    not(member(Nxt,[Dest])),

    airport(Nxt,Tax1,Delay1),
    addTaxDelay([],Origin,Tax,Delay),
    sum(Tax,Tax1,Titem),
    sum(Titem,Cost,TCCost),
    sum(Delay,Delay1,TDelay),
    sum(TDelay,Time, TTTime),
    gec(TTTime,K),
    tripHelperk(Nxt,[[Origin,Fl,Nxt]],[Origin|Nxt],[Fl],_,K,TCCost,TTTime,R,Dest)
    .
tripk(Origin,Dest, K,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Nxt,Origin,Cost,Time),

    not(member(Nxt,[Dest])),

    airport(Nxt,Tax1,Delay1),
    addTaxDelay([],Origin,Tax,Delay),
	sum(Tax,Tax1,Titem),
    sum(Titem,Cost,TCCost),
    sum(Delay,Delay1,TDelay),
    sum(TDelay,Time, TTTime),
    gec(TTTime,K),
    tripHelperk(Nxt,[[Origin,Fl,Nxt]],[Origin|Nxt],[Fl],_,K,TCCost,TTTime,R,Dest)
    .


:-table tripHelperk/10.
tripHelperk(FinalDest,Path,_,UsedAir,Count,_,TCCost,TTime,[TCCost,TTime,Count,Path],FinalDest):-
    len(UsedAir,N),
    Count = N.


 tripHelperk(Curr,Path,Visited,UsedAir,Count,K,TCost,TTime,Final,FinalDest):-
	flight(Fl,Curr,FinalDest,Cost,Time),
	not(contains(Visited,FinalDest)),
	addToAir(UsedAir,Fl,NUsed),
    addTaxDelay([],Curr,Tax,_),

    sum(TCost,Tax,TTCost),


    sum(TTCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    gec(TTTime,K),
    len(NUsed,Count),
	append(Path, [[Curr,Fl,FinalDest]], NPath),
    Final = [TCCost,TTTime,Count,NPath]

.

tripHelperk(Curr,Path,Visited,UsedAir,Count,K,TCost,TTime,Final,FinalDest):-
	flight(Fl,FinalDest,Curr,Cost,Time),
	not(contains(Visited,FinalDest)),

	addToAir(UsedAir,Fl,NUsed),
    addTaxDelay([],Curr,Tax,_),

    sum(TCost,Tax,TTCost),


    sum(TTCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    gec(TTTime,K),
    len(NUsed,Count),
	append(Path, [[Curr,Fl,FinalDest]], NPath),
    Final = [TCCost,TTTime,Count,NPath]

.




tripHelperk(Curr,Path,Visited,UsedAir,Count,K,TCost,TTime,Final,FinalDest):-
    flight(Fl,Curr,Nxt,Cost,Time),
    not(contains(Visited,Nxt)),

    addToAir(UsedAir,Fl,NUsed),
    addTaxDelay(Visited,Nxt,Tax,Delay),

    sum(TCost,Tax,Titem),
    sum(Titem,Cost,TCCost),
    sum(TTime,Delay,TDelay),
    sum(TDelay,Time, TTTime),
    gec(TTTime,K),

    append(Path,[[Curr,Fl,Nxt]], NPath),
    tripHelperk(Nxt,NPath,[Nxt|Visited],NUsed,Count,K,TCCost,TTTime,Final,FinalDest)
    .
tripHelperk(Curr,Path,Visited,UsedAir,Count,K,TCost,TTime,Final,FinalDest):-
    flight(Fl,Nxt,Curr,Cost,Time),
    not(contains(Visited,Nxt)),

    addToAir(UsedAir,Fl,NUsed),
    addTaxDelay(Visited,Nxt,Tax,Delay),
    sum(TCost,Tax,Titem),
    sum(Titem,Cost,TCCost),
    sum(TTime,Delay,TDelay),
    sum(TDelay,Time, TTTime),
	gec(TTTime,K),
    append(Path,[[Curr,Fl,Nxt]], NPath),
    tripHelperk(Nxt,NPath,[Nxt|Visited],NUsed,Count,K,TCCost,TTTime,Final,FinalDest)
    .




:-table multicitytrip/4.

multicitytrip(Origin,Dest,Inter,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Origin,Nxt,Cost,Time),
    member(Nxt,[Dest]),
    member(Nxt,[Inter]),
    sum(Tax,Cost,TCCost),

    sum(Delay,Time, TTime),
    R = [TCCost,TTime,1,[[Origin,Fl,Dest]]]
    .

multicitytrip(Origin,Dest,Inter,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Nxt,Origin,Cost,Time),
    member(Nxt,[Dest]),
    member(Nxt,[Inter]),
    sum(Tax,Cost,TCCost),

    sum(Delay,Time, TTime),
    R = [TCCost,TTime,1,[[Origin,Fl,Dest]]]

    .

multicitytrip(Origin,Dest,Inter,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Origin,Nxt,Cost,Time),
    member(Nxt,[Inter]),

    airport(Nxt,Tax1,Delay1),
    addTaxDelay([],Origin,Tax,Delay),
    sum(Tax,Tax1,Titem),
    sum(Titem,Cost,TCCost),
    sum(Delay,Delay1,TDelay),
    sum(TDelay,Time, TTime),

    multicitytripHelper(Inter,[[Origin,Fl,Nxt]],[Origin|Nxt],[Fl],_,TCCost,TTime,R,Inter,Dest)
    .

multicitytrip(Origin,Dest,Inter,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Nxt,Origin,Cost,Time),
    member(Nxt,[Inter]),

    airport(Nxt,Tax1,Delay1),
    addTaxDelay([],Origin,Tax,Delay),
    sum(Tax,Tax1,Titem),
    sum(Titem,Cost,TCCost),
    sum(Delay,Delay1,TDelay),
    sum(TDelay,Time, TTime),

	multicitytripHelper(Inter,[[Origin,Fl,Nxt]],[Origin|Nxt],[Fl],_,TCCost,TTime,R,Inter,Dest)
    .

multicitytrip(Origin,Dest,Inter,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Origin,Nxt,Cost,Time),

    not(member(Nxt,[Dest])),

    airport(Nxt,Tax1,Delay1),
    addTaxDelay([],Origin,Tax,Delay),
    sum(Tax,Tax1,Titem),
    sum(Titem,Cost,TCCost),
    sum(Delay,Delay1,TDelay),
    sum(TDelay,Time, TTTime),
    multicitytripHelper(Nxt,[[Origin,Fl,Nxt]],[Origin|Nxt],[Fl],_,TCCost,TTTime,R,Inter,Dest)
    .
multicitytrip(Origin,Dest,Inter,R):-
    airport(Origin,Tax,Delay),

    flight(Fl,Nxt,Origin,Cost,Time),

    not(member(Nxt,[Dest])),

    airport(Nxt,Tax1,Delay1),
    addTaxDelay([],Origin,Tax,Delay),
	sum(Tax,Tax1,Titem),
    sum(Titem,Cost,TCCost),
    sum(Delay,Delay1,TDelay),
    sum(TDelay,Time, TTTime),

    multicitytripHelper(Nxt,[[Origin,Fl,Nxt]],[Origin|Nxt],[Fl],_,TCCost,TTTime,R,Inter,Dest)
    .


:-table multicitytripHelper/10.
%multicitytripHelper(FinalDest,Path,Visited,UsedAir,Count,TCCost,TTime,[TCCost,TTime,Count,Path],Inter,FinalDest):-
%	not(member(Inter,Visited)),
 %   len(UsedAir,N),
  %  Count = N,
   % multicitytripHelper(Nxt,Path,[Origin|Nxt],[Fl],_,TCCost,TTime,R,Inter,Dest).


multicitytripHelper(FinalDest,Path,Visited,UsedAir,Count,TCCost,TTime,[TCCost,TTime,Count,Path],Inter,FinalDest):-
	member(Inter,Visited),
    len(UsedAir,N),
    Count = N.


 multicitytripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,Inter,FinalDest):-
	flight(Fl,Curr,Inter,Cost,Time),
    not(contains(Visited,Inter)),
	not(contains(Visited,FinalDest)),
	addToAir(UsedAir,Fl,NUsed),
    addTaxDelay([],Curr,Tax,_),
    sum(TCost,Tax,TTCost),


    sum(TTCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    len(NUsed,N),
    Count = N,
	append(Path, [[Curr,Fl,Inter]], NPath),

   multicitytripHelper(Inter,NPath,[Inter|Visited],NUsed,Count,TCCost,TTTime,Final,Inter,FinalDest)
.


multicitytripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,Inter,FinalDest):-
	flight(Fl,Inter,Curr,Cost,Time),
    not(contains(Visited,Inter)),
	not(contains(Visited,FinalDest)),

	addToAir(UsedAir,Fl,NUsed),
    addTaxDelay([],Curr,Tax,_),

    sum(TCost,Tax,TTCost),


    sum(TTCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    len(NUsed,N),
    Count = N,
	append(Path, [[Curr,Fl,Inter]], NPath),

 multicitytripHelper(Inter,NPath,[Inter|Visited],NUsed,Count,TCCost,TTTime,Final,Inter,FinalDest)
.





 multicitytripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,Inter,FinalDest):-
	flight(Fl,Curr,FinalDest,Cost,Time),
	not(contains(Visited,FinalDest)),
    contains(Visited,Inter),
	addToAir(UsedAir,Fl,NUsed),
    addTaxDelay([],Curr,Tax,_),
    sum(TCost,Tax,TTCost),


    sum(TTCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    len(NUsed,N),
    Count = N,
	append(Path, [[Curr,Fl,FinalDest]], NPath),
    Final = [TCCost,TTTime,Count,NPath]

.


multicitytripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,Inter,FinalDest):-
	flight(Fl,FinalDest,Curr,Cost,Time),
	not(contains(Visited,FinalDest)),
    contains(Visited,Inter),
	addToAir(UsedAir,Fl,NUsed),
    addTaxDelay([],Curr,Tax,_),

    sum(TCost,Tax,TTCost),


    sum(TTCost,Cost,TCCost),
    sum(TTime,Time,TTTime),
    len(NUsed,N),
    Count = N,
	append(Path, [[Curr,Fl,FinalDest]], NPath),
    Final = [TCCost,TTTime,Count,NPath]

.




multicitytripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,Inter,FinalDest):-
    flight(Fl,Curr,Nxt,Cost,Time),
    not(contains(Visited,Nxt)),

    addToAir(UsedAir,Fl,NUsed),
    addTaxDelay(Visited,Nxt,Tax,Delay),

    sum(TCost,Tax,Titem),
    sum(Titem,Cost,TCCost),
    sum(TTime,Delay,TDelay),
    sum(TDelay,Time, TTTime),

    append(Path,[[Curr,Fl,Nxt]], NPath),


    multicitytripHelper(Nxt,NPath,[Nxt|Visited],NUsed,Count,TCCost,TTTime,Final,Inter,FinalDest)
    .
multicitytripHelper(Curr,Path,Visited,UsedAir,Count,TCost,TTime,Final,Inter,FinalDest):-
    flight(Fl,Nxt,Curr,Cost,Time),
    not(contains(Visited,Nxt)),

    addToAir(UsedAir,Fl,NUsed),
    addTaxDelay(Visited,Nxt,Tax,Delay),
    sum(TCost,Tax,Titem),
    sum(Titem,Cost,TCCost),
    sum(TTime,Delay,TDelay),
    sum(TDelay,Time, TTTime),


    append(Path,[[Curr,Fl,Nxt]], NPath),

    multicitytripHelper(Nxt,NPath,[Nxt|Visited],NUsed,Count,TCCost,TTTime,Final,Inter,FinalDest)
    .



contains(Array,Elt):- Array = [H|_], H = Elt.
 contains(Array,Elt):- Array = [_|T], contains(T,Elt).

gec(Item1,Item2):- (Item1 =< Item2
->  true
                   ;   false
                   ).

addToAir(UsedAir,Fl, NUsedAir):-
    (not(contains(UsedAir,Fl))
    -> append(UsedAir,[Fl],NUsedAir)%
    ;   NUsedAir = UsedAir
                                ).

addTaxDelay(Visited,Airport, Tax,Delay):-
    (not(contains(Visited,Airport))
    -> airport(Airport,T,D), Delay is D, Tax is T
                                         ;   Delay is 0, Tax is 0

    ).

addToDelayFin(Visited, Airport,Delay,L):-
    (L == 1
    ->  addTaxDelay(Visited,Airport,_,D), Delay is D
    ;   Delay = 0
    ).

sum(Item1,Item2, Sum):- Sum is Item1 + Item2.

len([], 0).
len([_X|Xs], K) :-len(Xs, K1),K is K1 + 1.
