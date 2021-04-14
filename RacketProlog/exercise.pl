/*

  %  Single line comment
  /* Multi-line comment */

  Windows: click the executable to obtain a prolog prompt. 
           You can use the terminal menu to create new files in 
           the folder of your choice.
  ?- ["full-path-to-exercise.pl"]. %% this will load the predicates from this file.

  Command-line: executable 
            For unix-based systems here is what I have done. 
            I have a softlink to the executable swipl in the folder where I have
            my practice problems or test files. You can do the same by adding swipl
            in your search-path for executables (e.g., bash-profile).
 
  ./swipl %% run in the folder where exercise.pl is placed.
  
  You can use the text-editor of your choice to write the Prolog predicates. 
  
  ?- [exercise].   %% this will load the predicates from this file.
  ?- <write-queries>.
  ?- rev([1, 2, 3], [3, 2, 1]).
  ...

  If you want to trace a query. 
  ?- trace.
  true.

   [trace]  ?- rev([1, 2, 3], [3, 2, 1]).
   Call: (8) rev([1, 2, 3], [3, 2, 1]) ? creep
   Call: (9) rev([2, 3], _4986) ? creep
   Call: (10) rev([3], _4986) ? skip  %% PRESS s to "skip" - this will not trace the predicate call.
   Exit: (10) rev([3], [3]) ? creep
   Call: (10) lists:append([3], [2], _5000) ? creep
   Exit: (10) lists:append([3], [2], [3, 2]) ? creep
   Exit: (9) rev([2, 3], [3, 2]) ? creep
   Call: (9) lists:append([3, 2], [1], [3, 2, 1]) ? skip
   Exit: (9) lists:append([3, 2], [1], [3, 2, 1]) ? creep
   Exit: (8) rev([1, 2, 3], [3, 2, 1]) ? creep
true.

  %% to get out of trace environment
  [trace]  ?- notrace.
  true.
  [debug]  ?- nodebug.
  true.

   %% to get out of the prolog environment 
   ?- halt. 

*/


/*
  Write a predicate that relates a list to the number of elements
  in the list. Assume the list elements are not lists themselves.
*/
len([], 0).
len([_X|Xs], K) :-
	len(Xs, K1),
	K is K1 + 1.
% the underscore X is used as the first argument of second rule of len
% as we are not interested in its value.


/*
  Write the predicate that relates two lists such that
  one is the reverse of the other. Assume the elements
  in the lists are not lists themselves.
*/
 
% query: rev([a, b], [b, a]) or rev([a, b], L)
% rev(L, [a, b]) has may go into infinite loop.
rev([], []).
rev([X|L1], L) :-
	rev(L1, L2),
	append(L2, [X], L).


/*
  Rewrite the predicate using three lists, where
  the last argument-list will act as an "accumulator"
  (following the strategy of tail-recursion we learned
   in Racket; this will not use append). 
*/

rev1([], L, L).
rev1([X|Xs], L, R) :-
	rev1(Xs, [X|L], R).
myrev(L, R) :-
	rev1(L, [], R).

/*
  Given a list of numbers, write a predicate which relates the list to
  the sum of the numbers in the list. 
  (Write a new version of sum that uses accumulator).
*/

sum([], 0).
sum([X|Xs], Result) :-
	sum(Xs, Result1),
	Result is X + Result1.


mysum([], N, N).
mysum([X|Xs], N, Result) :-
	N1 is N + X,
	mysum(Xs, N1, Result).


/*
  Write a predicate which verifies whether a list is
  is a palindrome (assume the list elements are not lists
  themselves).
*/
palindrom(X) :-
	myrev(X, X).


/* 
   Write a predicate that relates two lists such that
   one is the reverse of the other. Elements in the list
   can be lists themselves.
*/
revc([], L, L).
revc([X|Xs], L, R) :-
    is_list(X), !,  %% if it is list
    myrevc(X, XR),
    revc(Xs, [XR|L], R).
revc([X|Xs], L, R) :-
    revc(Xs, [X|L], R).


myrevc(L, LR) :-
    revc(L, [], LR).


/*
   Find the k-th element in a list, where each 
   element in the list may be lists themselves.
   (First you may need to flatten the list).
*/
flatten([], []).
flatten([X], Result) :-
	(   (atom(X); integer(X))
	->  Result = [X]
	;   flatten(X, Result)
	).
flatten([X|Xs], Result) :-
	Xs \= [],
	flatten([X], Result1),
	flatten(Xs, Result2),
	append(Result1, Result2, Result).


/*
   Write a predicate that relates a list to 
   the permutation of the elements in the list.

?- permute([1, 2, 3, 4], L).
L = [[4, 3, 2, 1], [4, 3, 1, 2], [4, 1, 3, 2], [1, 4, 3, 2], [4, 2, 3, 1], [4, 2, 1|...], [4, 1|...], [1|...], [...|...]|...]
To avoid ... in long lists, type w. and will be get
L = [[4, 3, 2, 1], [4, 3, 1, 2], [4, 1, 3, 2], [1, 4, 3, 2], [4, 2, 3, 1], [4, 2, 1, 3], [4, 1, 2, 3], [1, 4, 2, 3], [2, 4, 3, 1], [2, 4, 1, 3], [2, 1, 4, 3], [1, 2, 4, 3], [3, 4, 2, 1], [3, 4, 1, 2], [3, 1, 4, 2], [1, 3, 4, 2], [3, 2, 4, 1], [3, 2, 1, 4], [3, 1, 2, 4], [1, 3, 2, 4], [2, 3, 4, 1], [2, 3, 1, 4], [2, 1, 3, 4], [1, 2, 3, 4]] ;

The method is discussed in class when we did the same exercise using Racket. 

*/

permute([X], [[X]]).
permute([X|Xs], L) :-
	permute(Xs, LstofLst),
	addineveryposofall(X, LstofLst, L).

addineveryposofall(_, [], []).
addineveryposofall(X, [L|Ls], Res) :-
	addineverypos(L, X, Res1),
	addineveryposofall(X, Ls, Res2),
	append(Res1, Res2, Res).

addineverypos(Lst, X, LstofLst) :-
	len(Lst, K),
	addatpositions(Lst, X, LstofLst, K).

addatpositions(Lst, X, [Lst1], 0) :-
	addat(Lst, X, Lst1, 0).
addatpositions(Lst, X, [Lst1|LstofLst], K) :-
	K > 0,
	addat(Lst, X, Lst1, K),
	K1 is K - 1,
	addatpositions(Lst, X, LstofLst, K1).

addat(L, X, [X|L], 0).
addat([Y|Ys], X, [Y|Y1s], K) :-
	K > 0,
	K1 is K - 1,
	addat(Ys, X, Y1s, K1).


/*
   Write a predicate relates the following elements
   L1: a list 
   L2: first k elements of L1
   L3: append L2 to L3 results in L1
   k:  a number

   Write one definition of the predicate that uses
   append, and one that does not use append.
*/
divide(L, [], L, K) :- K =< 0.
divide([X|Xs], [X|L1], L2, K) :-
	K > 0,
	K1 is K - 1, % arithmetic operation using is. immutable variables
	divide(Xs, L1, L2, K1).

newdivide(L1, L2, L3, K) :-
	append(L2, L3, L1),
	len(L2, K).


/*
   Write a predicate that relates a list to its
   sublist. 
*/
prefix1(P,L) :- append(P,_,L).
suffix1(S,L) :- append(_,S,L).
sublist(SubL,L) :- suffix1(S,L), prefix1(SubL,S).



/*
   Write a predicate that relates the following:
   L1: given as a sorted list of integers
   L2: given a sorted list of integers
   L3: contains all elements of L1 and L2 in sorted order.
   The sorted order is ascending.
*/
merge1([], L, L).
merge1(L, [], L).

merge1([X|Xs], [Y|Ys], [Z|Zs]) :-
	(   X < Y
	->  Z = X,  % unification of Z and X
	    merge1(Xs, [Y|Ys], Zs)
	;   Z = Y,
	    merge1([X|Xs], Ys, Zs)
	).

/*
   Write a predicate msort that relates the first
   list to a second list such that the second list
   contains all the elements of the first list
   arranged in ascending order. (use the merge relation).
*/
msort([], []).
msort([X], [X]).

msort(L1, Sort) :-
	len(L1, K),
	K > 1,
	K1 is K/2,
	divide(L1, Lst1, Lst2, K1), % evaluation does not happen without "is"
	msort(Lst1, Sort1),
	msort(Lst2, Sort2),
	merge1(Sort1, Sort2, Sort).



/*
   Given the following grammar for lambda expressions, write a relation
   lsem with relates one lambda expression E to another E' where
   E' is obtained by applying beta-reductions to E and E' cannot be
   beta-reduced any further. 

   \begin{verbatim}
   e ->  x | lambda(X, e) | (e1, e2)
   \end{verbatim}

   Example lambda expresions:
   \begin{verbatim}
   e1( ( (  lambda(x, lambda(y, (x, y))), z1 ), z2 )  ).
   e2( ( lambda(y, (x, y)), z1) ).
   \end{verbatim}
   where e1 and e2 are predicates describing different lambda expressions.

*/

% ?- e1(E), lsem(E, R).
% E =  ((lambda(x, lambda(y,  (x, y))), z1), z2),
% R =  (z1, z2) ;
e1(((lambda(x, lambda(y, (x, y))), z1), z2)).

% ?- e2(E), lsem(E, R).
% E =  (lambda(y,  (x, y)), z1),
% R =  (x, z1) ;
e2((lambda(y, (x, y)), z1)).

/* semantics of a variable is the variable itself */
lsem(X, X) :- atom(X).

/* semantics of lambda abstraction is the lambda abstraction
   where the definition of the abstraction is beta-reduced 
*/
lsem(lambda(X, E), lambda(X, E1)) :-
	lsem(E, E1).

/*
   semantics of lambda application is the result of beta-reduction
   after the each element in the pair is beta-reduced. 
*/
lsem((E1, E2), R) :-
	lsem(E1, R1),
	lsem(E2, R2),
	lsemlow(R1, R2, R).

/*
   lsemlow: beta-reduces the pair (E1, E2) and recursively 
            relates the result to its beta-reduction.
*/
lsemlow(E1, E2, R) :-
	(   E1 = lambda(_, _)
	->  beta(E1, E2, E12),
	    lsem(E12, R)
	;   R = (E1, E2)
	).

/*
   beta-reduction: replace every free occurrence of X in E1
   with E2. 
*/
beta(lambda(X, E1), E2, E) :-
	replace(X, E1, E2, E).

replace(X, lambda(X, E), _, lambda(X, E)). % X is not free in lambda(X, E).

replace(X, X, E, E).                       % replace X with E.  

replace(X, Y, _, Y) :- atom(Y), X \= Y.    % X and Y are not the same

replace(X, lambda(Y, E1), E2, lambda(Y, E)) :-  
	X \= Y,             
	replace(X, E1, E2, E).             % recursively find free occurrence of X in abstraction

replace(X, (E11, E12), E2, (R1, R2)) :-    % recursively find free occurrence of X in application
	replace(X, E11, E2, R1),          
	replace(X, E12, E2, R2).


% simple reachability discussed in the lecture.
edge1(a, b).
edge1(b, c).
edge1(c, a).

:- table reach/2.
reach(X, Y) :- edge1(X, Y).
reach(X, Y) :- edge1(X, Z), reach(Z, Y).




:- table adjacent/2.

adjacent(a, b).
adjacent(e, f).
adjacent(X, Y) :- adjacent(Y, X).


/*
   Consider the following grammar: (similar to sequential language with variables+if-then)

   \begin{verbatim}
    Stmt -> assign(x, AExpr) | decl(x) | if(CondExpr, StmtList)
            x is a variable represented by some atom - lowercase
    AExpr -> plus(AExpr, AExpr) | minus(AExpr, AExpr) | X | Numerical
    CondExpr -> gt(AExpr, AExpr) | ... | and(CondExpr, CondExpr) | ...

     Program -> StmtList
    \end{verbatim}

    Write an predicate sem, which relates a program written following
    the above grammar and an environment to another environment, where
    the second environment is the result of interpreting the program
    in the context of the first environment. Follow the interpretation
    rules from homework assignments.

    Incorporate the syntactic construct for while-do and implement its
    semantics.
*/

% semantics of sequence of statements
sem([Stmt], InEnv, OutEnv) :-
        semstmt(Stmt, InEnv, OutEnv).

sem([S|Ss], InEnv, OutEnv) :-
        semstmt(S, InEnv, Env),
        sem(Ss, Env, OutEnv).

% semantics of three types of statements
semstmt(decl(X), InEnv, [(X, 0)|InEnv]).

/*
   review the order in which the predicates
   are "conjuncted" that replicates the sequence
   of operations necessary to realize/implement
   the interpreter.  
 
*/
semstmt(assign(X, AExpr), InEnv, OutEnv) :-
        semarith(AExpr, InEnv, Val),
        updateval(X, Val, InEnv, OutEnv).

semstmt(if(Cond, StmtList), InEnv, OutEnv) :-
        (   semcond(Cond, InEnv)
        ->  sem(StmtList, [($m, 0)|InEnv], Out),
            removemarker(Out, OutEnv)
        ;   OutEnv = InEnv
        ).

% predicate to manage block context in conditional statement
removemarker([($m, 0)|Rest], Rest).
removemarker([(X, _V)|Rest], Rest1) :-
        X \= $m,
        removemarker(Rest, Rest1).

% semantics of conditional expressions
semcond(gt(E1, E2), Env) :-
        semarith(E1, Env, V1),
        semarith(E2, Env, V2),
        V1 > V2.
semcond(lt(E1, E2), Env) :-
        semarith(E1, Env, V1),
        semarith(E2, Env, V2),
        V1 < V2.
semcond(eq(E1, E2), Env) :-
        semarith(E1, Env, V1),
        semarith(E2, Env, V2),
        V1 == V2.
semcond(or(C1, C2), Env) :-
        (   semcond(C1, Env)
        ->  true
        ;   semcond(C2, Env)).
semcond(and(C1, C2), Env) :-
        semcond(C1, Env),
        semcond(C2, Env).
semcond(not(C), Env) :-
        not(semcond(C, Env)).

% smantics of arith-expressions
semarith(X, Env, Val) :-
        atom(X),
        findvalue(X, Env, Val).
semarith(N, _Env, N) :-
        number(N).
semarith(plus(E1, E2), Env, Val) :-
        semarith(E1, Env, V1),
        semarith(E2, Env, V2),
        Val is V1 + V2.
semarith(minus(E1, E2), Env, Val) :-
        semarith(E1, Env, V1),
        semarith(E2, Env, V2),
        Val is V1 - V2.

% update valuation of variable due to assignment statement
updateval(X, Val, [(X, _)|Rest], [(X, Val)|Rest]).
updateval(X, Val, [(Y, V)|Rest], [(Y, V)|Rest1]) :-
        X \= Y,
        updateval(X, Val, Rest, Rest1).

% read valuation of variables for arith-expressions
findvalue(X, [(X, V)|_], V).
findvalue(X, [(Y, V)|Rest], V) :-
        X \= Y,
        findvalue(X, Rest, V).


/*
   Some example programs
*/

% ?- prog1(P), sem(P, [], E).
% P = [decl(a), assign(a, 1), assign(a, plus(a, 2))],
% E = [(a, 3)] ;
prog1(
       [
          decl(a),  
          assign(a, 1),  
          assign(a, plus(a, 2))
       ]
     ).



% ?- prog2(P), sem(P, [], E).
% P = [decl(a), assign(a, 1), if(gt(a, 0), [decl(z), assign(z, 10), assign(a, z)])],
% E = [(a, 10)] ;
prog2([
         decl(a),
         assign(a, 1),
         if(gt(a, 0),
                      [
                        decl(z),
                        assign(z, 10),
                        assign(a, z)
                       ]
           )
       ]
      ).
