#!/usr/bin/env python
# coding: utf-8

# In[ ]:


#Answer to Problem 1 is 144 different expressions


import sklearn.neural_network
import matplotlib.pyplot

def learning_curve(X, y, filename):
    max_iter = 2000
    NN = sklearn.neural_network.MLPRegressor(
        hidden_layer_sizes=(4,4),
        activation='tanh',
        max_iter=max_iter
        )
    scoref = []
    max = []
    X = X.reshape(-1,1)
    #NN.fit(X,y)
    #score = NN.score(X,y)
    for max_i in range(50,2050,50):
        NN.fit(X,y)
        scorevar = NN.score(X,y)
        scoref.append(scorevar)
        max.append(max_i)
    matplotlib.pyplot.plot(max,scoref)
    matplotlib.pyplot.savefig(filename + '.png')
    return max, scoref

