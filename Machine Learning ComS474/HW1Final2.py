#!/usr/bin/env python
# coding: utf-8

# In[17]:


# 144

import sklearn.neural_network
import numpy
import matplotlib.pyplot

import itertools 

def test_NN(Ts, Hs, max_iter=200):
    NN = sklearn.neural_network.MLPRegressor(
        hidden_layer_sizes=(4,4), 
        activation='tanh', 
        random_state = 1, 
        max_iter=max_iter
        )
    Ts = Ts.reshape(-1, 1) # learned from error
    NN.fit(Ts, Hs)
#     predictions = NN.predict(Ts)
    score = NN.score(Ts, Hs)
    return score

def learning_curve(Ts, Hs, filename):
    max_iters, scores = None, None # place holder 
    #max_iter = 1
    NN = sklearn.neural_network.MLPRegressor(
        hidden_layer_sizes=(4,4),
        activation='tanh',
        random_state = 1,
        max_iter=50,
        warm_start=True
        )
    scores = []
    max_iters = []
    Ts = Ts.reshape(-1,1)
    #NN.fit(X,y)
    #score = NN.score(X,y)
    for i in range(50,2050,50):
        NN.fit(Ts,Hs)
        scorevar = NN.score(Ts,Hs)
        scores.append(scorevar)
        
        max_iters.append(i)
    matplotlib.pyplot.plot(max_iters,scores)
   # matplotlib.pyplot.savefig(filename + '.png')
    matplotlib.pyplot.savefig(filename)
    max_iters = numpy.asarray(max_iters)
    scores = numpy.asarray(scores)
    return max_iters, scores

def self_checker(*args): 
    X, y = learning_curve(*args)
    print (type(X), X)
    print (type(y), y)
    import hashlib
    print (hashlib.md5(open(args[2], "rb").read()).hexdigest())

def f(a, b, c):
    """
    a, b: 1-D numpy.ndarray
    c: str, placeholder
    """
    return a+b, a-b, a*b

if __name__ == "__main__":
    import warnings
    warnings.filterwarnings("ignore")

    self_checker(numpy.array([1,2]), numpy.array([3,4]), "test.png")
    print()
    self_checker(numpy.array([1,2,3,4]), numpy.array([-1,-1,-1,-1]), "test.pdf")

