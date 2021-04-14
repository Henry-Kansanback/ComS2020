/*
A Farmer, a goat, a wolf and a cabbage(??) are standing on the bank of
a river. They all want to cross the river. They got a boat; only the
farmer can row the boat (what with having thumbs). The problem is that
the boat is so small, the farmer can take along with him one other
entity: the goat, the wolf or the cabbage (it is a pretty big
cabbage).  If the goat is with the cabbage without the farmer's
supervison, the cabbage will end up in goat's stomach. If the wolf is
left alone with the goat (without the farmer around) the the goat will
end up in wolf's stomach. No one is eating the farmer and he is not
eating anything (Phew!!).

Our objective is to find out a way to get them all on the other side
of the river. So, we wrote a logic program that explores all possible
situations reachable from the start position and identifies a valid
path where no one is eaten and everyone ends up on the other side
of the bank.

Notice how loop free paths are constructed.

*/

/*
  The position of the entites is represented by a list
  of four variables: the variables can be either e (east bank)
  or w (west bank).  Assume we want to go from w to e.
  Element 1 in the list: farmer's position
  Element 2 in the list: goat's position
  Element 3 in the list: cabbage's position
  Element 4 in the list: wolf's position
*/

% usafe predicate is true if the goat and wolf have the same position
% and the farmer is not there; or the goat and cabbage have the same
% position and the farmer is not ther. 
unsafe([F, G, _, G]) :- F \= G.
unsafe([F, G, G, _]) :- F \= G.

% move: relates the change in position with the action that
% resulted in the change. 
move([G, G, W, C], move-with-goat-G-G1, [G1, G1, W, C]) :-
    change(G, G1).

move([W, G, W, C], move-with-wolf-W-W1, [W1, G, W1, C]) :-
    change(W, W1).

move([C, G, W, C], move-with-cabbage-C-C1, [C1, G, W, C1]) :-
    change(C, C1).

move([F, G, W, C], move-alone-F-F1, [F1, G, W, C]) :-
    change(F, F1).

% solution predicate relates the current position with the
% sequence of moves that will take everyone to the east bank
% without anyone getting eaten.

% The predicate also keeps track of positions visited, so 
% that the same positions are not visited multiple times.

% All are in the east side - no move necessary
solution([e, e, e, e], _, []). 

% Otherwise
solution([F, G, W, C], Visited, [Move|RestofMoves]) :-
    move([F, G, W, C], Move, [F1, G1, W1, C1]), % make a move
    not(member([F1, G1, W1, C1], Visited)),     % check whether position is new
    not(unsafe([F1, G1, W1, C1])),              % check whether position is safe
    solution([F1, G1, W1, C1], [[F1, G1, W1, C1]|Visited], RestofMoves).
                                                % continue recursively

% predicate change: toggles the position: used in move predicate
change(e, w).
change(w, e).


/*
Invoke: (* if you don't see the full list of the solution then press w *)
and you will see two solutions

?- solution([w,w,w,w], [], Moves).
Moves = [move-with-goat-w-e, move-alone-e-w, move-with-wolf-w-e, move-with-goat-e-w, move-with-cabbage-w-e, move-alone-e-w, move-with-goat-w-e] ;
Moves = [move-with-goat-w-e, move-alone-e-w, move-with-cabbage-w-e, move-with-goat-e-w, move-with-wolf-w-e, move-alone-e-w, move-with-goat-w-e] ;
false
*/
