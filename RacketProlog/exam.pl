p(X, Y) :- q(X), !, r(X, Y).
p(X, X) :- s(X).
q(a).
q(b).
r(W1, W2) :- W1 = b, !.
r(a, c).
r(b, d).
s(e).
