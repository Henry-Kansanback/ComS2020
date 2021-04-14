#!/usr/bin/env python
# coding: utf-8

# In[203]:


import numpy as np 
import matplotlib.pyplot as plt

import numpy # import again 
import matplotlib.pyplot # import again 

import numpy.linalg 
import numpy.random

def generate_data(Para1, Para2, seed=0):
    """Generate binary random data
    Para1, Para2: dict, {str:float} for each class, 
      keys are mx (center on x axis), my (center on y axis), 
               ux (sigma on x axis), ux (sigma on y axis), 
               y (label for this class)
    seed: int, seed for NUMPy's random number generator. Not Python's random.
    """
    numpy.random.seed(seed)
    X1 = numpy.vstack((numpy.random.normal(Para1['mx'], Para1['ux'], Para1['N']), 
                       numpy.random.normal(Para1['my'], Para1['uy'], Para1['N'])))
    X2 = numpy.vstack((numpy.random.normal(Para2['mx'], Para2['ux'], Para2['N']), 
                       numpy.random.normal(Para2['my'], Para2['uy'], Para2['N'])))
    Y = numpy.hstack(( Para1['y']*numpy.ones(Para1['N']), 
                       Para2['y']*numpy.ones(Para2['N'])  ))            
    X = numpy.hstack((X1, X2)) 
    X = numpy.transpose(X)
    return X, Y



def plot_mse(X, y, filename):
    
    """
    X: 2-D numpy array, each row is a sample, not augmented 
    y: 1-D numpy array
    Examples
    -----------------
    >>> X,y = generate_data(\
        {'mx':1,'my':2, 'ux':0.1, 'uy':1, 'y':1, 'N':20}, \
        {'mx':2,'my':4, 'ux':.1, 'uy':1, 'y':-1, 'N':50},\
        seed=10)
    >>> plot_mse(X, y, 'test1.png')
    array([-1.8650779 , -0.03934209,  2.91707992])
    >>> X,y = generate_data(\
    {'mx':1,'my':-2, 'ux':0.1, 'uy':1, 'y':1, 'N':20}, \
    {'mx':-1,'my':4, 'ux':.1, 'uy':1, 'y':-1, 'N':50},\
    seed=10)
    >>> # print (X, y)
    >>> plot_mse(X, y, 'test2.png')
    array([ 0.93061084, -0.01833983,  0.01127093])
    """
    w = np.array([0,0,0]) 
    
    minusarr = X[y == -1]
    plusarr = X[y == +1]
    N = int(X.shape[0]/2)
    Nlim = int(X.shape[0]/2)
    i = 0
    Nm = int(minusarr.size/2)
    Np = int(plusarr.size/2)
    for i in range(Nm):
        if y[i] == -1:
            plt.plot(minusarr[i][0],minusarr[i][1], '.r')
    i = 0
    for i in range(Np):
        if y[i] == 1:
            plt.plot(plusarr[i][0],plusarr[i][1], '.b')
    #for i in range(Nlim):
    #    if y[i] == -1:
    #        plt.plot(X[0][i],X[1][i],'.r')
    #    elif y[i] == 1:
    #        plt.plot(X[0][i],X[1][i],'.b')
        #if y[i+Nlim] == -1:
            #plt.plot(X[0][i+Nlim],X[1][i+Nlim],'.r')
        #elif y[i+Nlim] == 1:
            #plt.plot(X[0][i+Nlim],X[1][i+Nlim],'.b')
            

    
    
    X = np.hstack((X, np.transpose(np.array([np.ones(2*N)]))))
    
    
    compound = np.matmul(np.transpose(X), X)
    
    
    all_but_y = np.matmul(np.linalg.inv(compound), np.transpose(X))#X is given as transposed
    
    w = np.matmul(all_but_y,y )
    a,b,c=w[0], w[1], w[2]
    h = [0, -1*c/a]
    vd = [-1*c/b, 0]
    plt.plot(h,vd)
    matplotlib.pyplot.xlim(numpy.min(X[:,0]), numpy.max(X[:,0]))
    matplotlib.pyplot.ylim(numpy.min(X[:,1]), numpy.max(X[:,1]))
    matplotlib.pyplot.savefig(filename)
    matplotlib.pyplot.close('all') # it is important to always clear the plot
    return w

#array([-1.8650779 , -0.03934209,  2.91707992])
#array([ 0.93061084, -0.01833983,  0.01127093])

def plot_fisher(X, y, filename):
    """
    X: 2-D numpy array, each row is a sample, not augmented 
    y: 1-D numpy array
    Examples
    -----------------
    >>> X,y = generate_data(\
        {'mx':1,'my':2, 'ux':0.1, 'uy':1, 'y':1, 'N':20}, \
        {'mx':2,'my':4, 'ux':.1, 'uy':1, 'y':-1, 'N':50},\
        seed=10)
    >>> plot_fisher(X, y, 'test3.png')
    array([-1.61707972, -0.0341108 ,  2.54419773])
    >>> X,y = generate_data(\
        {'mx':-1.5,'my':2, 'ux':0.1, 'uy':2, 'y':1, 'N':200}, \
        {'mx':2,'my':-4, 'ux':.1, 'uy':1, 'y':-1, 'N':50},\
        seed=1)
    >>> plot_fisher(X, y, 'test4.png')
    array([-1.54593468,  0.00366625,  0.40890079])
    """
    w = np.array([0,0,0]) 
    
    
    #for Fisher's linear discriminant, we want to max(w^T*x_1 - w^T*x_2)^2
    minusarr = X[y == -1]
    plusarr = X[y == +1]
    

    i = 0


    Nm = int(minusarr.size/2)
    Np = int(plusarr.size/2)
    for i in range(Nm):
        if y[i] == -1:
            plt.plot(minusarr[i][0],minusarr[i][1], '.r')
    i = 0
    for i in range(Np):
        if y[i] == 1:
            plt.plot(plusarr[i][0],plusarr[i][1], '.b')

    m1 = numpy.mean(plusarr, axis=0)
    m2 = numpy.mean(minusarr, axis=0) 
    sb = np.matmul(np.subtract(m1,m2), np.transpose(np.array([np.subtract(m1, m2)])))
    

    
    #alt comp for s1 and s2
    M1 = np.array([m1]*Np)
    M2 = np.array([m2]*Nm)
    S1 = np.matmul(np.transpose(np.subtract(plusarr,M1)),np.array(np.subtract(plusarr,M1)))
    S2 = np.matmul(np.transpose(np.subtract(minusarr,M2)),np.array(np.subtract(minusarr,M2)))

    sw = S1 + S2
    swi = np.linalg.inv(sw)

    w = np.matmul(swi,(np.subtract(m1,m2)))
    #[-1.61707972 -0.0341108   1.50636074  3.1747229 ]
    
    
    c = np.add(m1,m2)/2
    c = -1*np.matmul(np.transpose(w),c)
    a,b=w[0], w[1]
 
    x_ticks = numpy.array([numpy.min(X[:,0]), numpy.max(X[:,0])])
    
    y_ticks = (x_ticks * w[0] + c)/w[1]
    matplotlib.pyplot.plot(x_ticks, y_ticks)



    # limit the range of plot to the dataset only
    matplotlib.pyplot.xlim(numpy.min(X[:,0]), numpy.max(X[:,0]))
    matplotlib.pyplot.ylim(numpy.min(X[:,1]), numpy.max(X[:,1]))
    matplotlib.pyplot.savefig(filename)
    matplotlib.pyplot.close('all') # it is important to always clear the plot
    w = np.append(w,c)
    #array([-1.61707972, -0.0341108 ,  2.54419773])
    #array([-1.54593468,  0.00366625,  0.40890079])
    
    return w




if __name__ == "__main__":
    import doctest
    doctest.testmod()


# In[ ]:




