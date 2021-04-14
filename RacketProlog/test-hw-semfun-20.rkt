#lang racket ;; uncomment this line
;(require "sol-hw5-1-20.rkt") ;; include your solution here
(require racket/trace)
(require "program.rkt")
(provide (all-defined-out)) 


(define-namespace-anchor anc)
(define ns (namespace-anchor->namespace anc))

(define (myequal l1 l2)
  (if (not (equal? (length l1) (length l2)))
      false
      (myeqlow l1 l2)))

(define (myeqlow l1 l2)
  (if (null? l1)
      true
      (and (present (car l1) l2)
          (myeqlow (cdr l1) l2))))

;; does some specific course present in the course list
(define (present x lst)
  (if (null? lst)
      false
      (if (equal? x (car lst))
          true
          (present x (cdr lst)))))



(define totalpoints 0) ;; total points for this assignment
(define cnt 0)  ;; test counts

(define (utest testcnt testname testfun testpoints)
  (begin
    (write testcnt)
    (write testname)
    (write ':)
    (write testpoints)
    (writeln 'pts)
    (with-handlers ([exn:fail? (lambda (exn)
                                 (begin
                                   (writeln exn)
                                   (writeln "Exception")
                                   (writeln "incorrect")
                                   ;(set! totalpoints (- totalpoints testpoints))
                                   ))])
      (if (eval testfun ns)
          (begin
            (writeln "correct")
            (set! totalpoints (+ totalpoints testpoints)))
          (begin
            (writeln "incorrect output")
            ;(set! totalpoints (- totalpoints testpoints))
            ))
    )
    ))

(define (hw5)
  (begin

    (writeln '************************************************)
    (writeln 'Tests-on-Q1)
    (writeln '************************************************)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q0 '(myequal (sem q0 '())
                                  '((y 10) ((f (x)) ((assign y x))))) 2)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q1 '(myequal (sem q1 '())
                                  '((z2 3) (z1 2) (y 5) ((f (x)) ((assign y x))))) 2)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q2 '(myequal (sem q2 '())
                                  '((z2 100)
                                    (z1 10) (y 110)
                                    ((f (x)) ((assign y x)))
                                    ((f (x1 x2)) ((assign y (* x1 x2)))))) 2)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q3 '(myequal (sem q3 '((z 0)))
                                  '((y 4) ((f (x)) ((assign y x))) (z 0))) 2)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q4 '(myequal (sem q4 '((z 0)))
                                  '((y 2) ((f (x)) ((assign y x))) (z 0))) 2)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q5 '(myequal (sem q5 '((z 5)))
                                  '((y 120)
                                    ((f (x))
                                     ((if (eq x 1)
                                          ((assign y x)))
                                      (if (gt x 1)
                                          ((call (f ((- x 1))) 1)
                                           (assign y (* x y))))))
                                    (z 5)))
           4)


    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q6 '(myequal (sem q6 '((z 5)))
                                  '((y 120)
                                    ((g (x))
                                     ((if (eq x 1) ((assign y x))) (if (gt x 1) ((call (f ((- x 1))) 1) (assign y (* x y))))))
                                    ((f (x))
                                     ((if (eq x 1) ((assign y x))) (if (gt x 1) ((call (g ((- x 1))) 1) (assign y (* x y))))))
                                    (z 5)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q7 '(myequal (sem q7 '())
                                  '((y 40)
                                    ((f (x)) ((fundecl (g (x)) ((assign y (* 2 x)))) (call (g (x)) 1)))
                                    ((g (x)) ((assign y (* 3 x))))))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q8 '(myequal (sem q8 '())
                                  '((y 400)
                                    ((f (x)) ((fundecl (g (x)) ((assign y (* 2 x)))) (call (g ((+ x y) (- x y))) 1)))
                                    ((g (z1 z2)) ((assign y (* z1 z2))))))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q9 '(myequal (sem q9 '((z 5)))
                                  '((y 120)
                                    ((f (x))
                                     ((fundecl
                                       (g (x))
                                       ((if (eq x 1) ((assign y 1)))
                                        (if (gt x 1) ((call (f ((- x 1))) 1)
                                                      (assign y (* x y))))))
                                      (call (g (x)) 1)))
                                    (z 5)))
           4)


    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q10 '(myequal (sem q10 '())
                                   '((z f) (y 10) ((f (x)) ((assign y x)))))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q11 '(myequal (sem q11 '())
                                   '((z f) (y 10) ((g (x1 x2))
                                                   ((call (x1 (x2)) 1)))
                                           ((f (x)) ((assign y x)))))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q12 '(myequal (sem q12 '((w 5)))
                                   '((z f) (y 120)
                                           ((f (x1 x2))
                                            ((if (eq x1 1) ((assign y x1)))
                                             (if (gt x1 1) ((call (x2 ((- x1 1) x2)) 1)
                                                            (assign y (* x1 y)))))) (w 5)))
           5)
    (writeln '-------------)
    (set! cnt (+ cnt 1))
    (utest cnt ':sem-q13 '(myequal (sem q13 '((w 5)))
                                   '((z f)
                                     (y 120)
                                     ((f (x1)) ((assign y x1)))
                                     ((f (x1 x2)) ((if (eq x1 1) ((assign y x1)))
                                                   (if (gt x1 1) ((call (x2 ((- x1 1) x2)) 1)
                                                                  (assign y (* x1 y)))))) (w 5)))
           5)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q14 '(myequal (sem q14 '())
                                   '((y 20) ((f (x)) ((assign y x))) (x 0)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q15 '(myequal (sem q15 '())
                                   '((x 2)))
           2)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q16 '(myequal (sem q16 '((w 10)))
                                   '((w 10)(x 102)))
           2)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q17 '(myequal (sem q17 '())
                                   '((x 10)))
           2)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q18 '(myequal (sem q18 '())
                                   '((x 18)))
           4)  

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-q19 '(myequal (sem q19 '())
                                   '(((f (z)) ((assign x (anonf ((y) (+ y y)) (z))))) (x 2)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s0 '(myequal (sem s0 '())
                                   '(((g (w)) ((decl x) (call (f ((+ x w))) 0))) ((f (z)) ((assign x (* x z)))) (x 20)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s1 '(myequal (sem s1 '())
                                   '(((g (w)) ((decl x) (call (f ((+ x w))) 1))) ((f (z)) ((assign x (* x z)))) (x 10)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s2 '(myequal (sem s2 '())
                                   '(((g (w)) ((decl x) (fundecl (f (z)) ((assign x (* x z)))) (call (f ((+ x w))) 0))) (x 10)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s3 '(myequal (sem s3 '())
                                   '(((g (w)) ((decl x) (fundecl (f (z)) ((assign x (* x z)))) (call (f ((+ x w))) 0))) ((f (z)) ((assign x 100))) (x 10)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s4 '(myequal (sem s4 '())
                                   '(((f3 (z3)) ((decl x) (assign x 2) (call (f2 ((* x z3))) 1)))
                                     ((f2 (z2)) ((call (f1 ((* x z2))) 0)))
                                     ((f1 (z1)) ((assign x (+ x z1))))
                                     (x 405)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s5 '(myequal (sem s5 '())
                                   '(((f3 (z3)) ((decl x) (assign x 2) (call (f2 ((* x z3))) 0)))
                                     ((f2 (z2)) ((call (f1 ((* x z2))) 1)))
                                     ((f1 (z1)) ((assign x (+ x z1))))
                                     (x 1005)))
           3)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s6 '(myequal (sem s6 '())
                                   '(((f4 (z4)) ((decl x) (assign x 3) (call (f3 ((+ x z4))) 0)))
                                     ((f3 (z3)) ((call (f2 ((+ x z3))) 0)))
                                     ((f2 (z2)) ((call (f1 ((+ x z2))) 0)))
                                     ((f1 (z1)) ((assign x (+ x z1))))
                                     (x 28)))
           3)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s7 '(myequal (sem s7 '())
                                   '(((f4 (z4)) ((decl x) (assign x 3) (call (f3 ((+ x z4))) 1)))
                                     ((f3 (z3)) ((decl y) (call (f2 ((+ x z3))) 0)))
                                     ((f2 (z2)) ((call (f1 ((+ y z2))) 1)))
                                     ((f1 (z1)) ((assign x (+ x (+ y z1)))))
                                     (y 10)
                                     (x 41)))
           4)

    (writeln '-------------)
    (set! cnt (+ cnt 1))

    (utest cnt ':sem-s8 '(myequal (sem s8 '((x 100) (y 2000)))
                                   '(((f4 (z4)) ((decl x) (assign x 3) (call (f3 ((+ x z4))) 1)))
                                     ((f3 (z3)) ((decl y) (call (f2 ((+ x z3))) 0)))
                                     ((f2 (z2)) ((call (f1 ((+ y z2))) 1)))
                                     ((f1 (z1)) ((assign x (+ x (+ y z1)))))
                                     (y 10)
                                     (x 41)))
           4)
    
    (writeln '---------------------------------------)
    (write "                      Total Points: ")
    (writeln totalpoints)

    
    )
)

(hw5)

