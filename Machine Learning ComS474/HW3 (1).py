#!/usr/bin/env python
# coding: utf-8

# In[5]:


import operator
import numpy, sklearn, sklearn.tree

def estimate_gini_impurity(feature_values, threshold, labels, polarity): 
    """Compute the gini impurity for comparing a feature value against a threshold under a given polarity
    feature_values: 1D numpy array, feature_values for samples on one feature dimension
    threshold: float
    labels: 1D numpy array, the label of samples, only +1 and -1. 
    polarity: operator type, only operator.gt or operator.le are allowed
    Examples
    -------------
    >>> feature_values = numpy.array([1,2,3,4,5,6,7,8])
    >>> labels = numpy.array([+1,+1,+1,+1, -1,-1,-1,-1])
    >>> for threshold in range(0,8): 
    ...     print("%.5f" % estimate_gini_impurity(feature_values, threshold, labels, operator.gt))
    0.50000
    0.48980
    0.44444
    0.32000
    0.00000
    0.00000
    0.00000
    0.00000
    >>> for threshold in range(0,8): 
    ...     print("%.5f" % estimate_gini_impurity(feature_values, threshold, labels, operator.le))
    1.00000
    0.00000
    0.00000
    0.00000
    0.00000
    0.32000
    0.44444
    0.48980
    """
    minus = numpy.logical_and(
        polarity(feature_values,threshold),
        labels == -1
        )
    plus = numpy.logical_and(
        polarity(feature_values,threshold),
        labels == +1
        )
    
    minussize = sum(minus)
    plussize = sum(plus)
    featuresize = minussize + plussize
    
    
    minusprob = 0.0
    plusprob = 0.0
    gini_impurity = 0.0
    

    if featuresize == 0:
        
        gini_impurity = 1.0
        
        
        
    elif minussize == 0 or plussize == 0:
        if minussize == 0:
            minusprob = numpy.finfo(float).eps
            
            plusprob = float(plussize/featuresize)
            
            gini_impurity = (minusprob)*(1-minusprob) + (plusprob)*(1-plusprob)
            
        elif plussize == 0:
            plusprob = numpy.finfo(float).eps
            
            minusprob = float(minussize/featuresize)
            gini_impurity = (minusprob)*(1-minusprob) + (plusprob)*(1-plusprob)
        
    else:
        minusprob = float(minussize/featuresize)
        plusprob = float(plussize/featuresize)
        gini_impurity = (minusprob)*(1-minusprob) + (plusprob)*(1-plusprob)
    return gini_impurity

def estimate_gini_impurity_expectation(feature_values, threshold, labels):
    """Compute the expectation of gini impurity given the feature values on one  feature dimension and a threshold 
    feature_values: 1D numpy array, feature_values for samples on one feature dimension
    threshold: float
    labels: 1D numpy array, the label of samples, only +1 and -1. 
    Examples 
    ---------------
    >>> feature_values = numpy.array([1,2,3,4,5,6,7,8])
    >>> labels = numpy.array([+1,+1,+1,+1, -1,-1,-1,-1])
    >>> for threshold in range(0,9): 
    ...     print("%.5f" % estimate_gini_impurity_expectation(feature_values, threshold, labels))
    0.50000
    0.42857
    0.33333
    0.20000
    0.00000
    0.20000
    0.33333
    0.42857
    0.50000
    """
    minusgt = numpy.logical_and(
        feature_values > threshold,
        labels == -1
        )
    plusgt = numpy.logical_and(
        feature_values > threshold,
        labels == +1
        )

    minusle = numpy.logical_and(
        feature_values <= threshold,
        labels == -1
        )
    plusle = numpy.logical_and(
        feature_values <= threshold,
        labels == +1
        )


    minussizegt = sum(minusgt)
    minussizele = sum(minusle)
    plussizegt = sum(plusgt)
    plussizele = sum(plusle)
    featuresizegt = minussizegt +plussizegt
    featuresizele = minussizele + plussizele
    
    featuresize = 0
    gini_impurityle = 0.0
    gini_impuritygt = 0.0
    #minusprob = 0.0
    #plusprob = 0.0
     

    expectation = 0.0
    plusproble = 0.0
    plusprobgt = 0.0
    minusprobgt = 0.0
    minusproble = 0.0
    


    if minussizele == 0 and plussizele == 0:
        minusproble = numpy.finfo(float).eps
        plusproble = numpy.finfo(float).eps
        
        gini_impurityle = 1.0
    
        
    elif gini_impurityle != 1.0 and (minussizele == 0 or plussizele == 0):
        gini_impurityle = 0.0
    else:
        minusproble = float(minussizele/featuresizele)
        plusproble = float(plussizele/featuresizele)
        gini_impurityle = (minusproble)*(1-minusproble) + (plusproble)*(1-plusproble)
        
    if minussizegt == 0 and plussizegt == 0:
        minusprobgt = numpy.finfo(float).eps
        plusprobgt = numpy.finfo(float).eps
    
        gini_impuritygt = 1.0
    elif gini_impuritygt != 1.0 and (minussizegt == 0 or plussizegt == 0):
        gini_impuritygt = 0.0
    
    else:
        minusprobgt = float(minussizegt/featuresizegt)
        plusprobgt = float(plussizegt/featuresizegt)
        gini_impuritygt = (minusprobgt)*(1-minusprobgt) + (plusprobgt)*(1-plusprobgt)
        
        
        
    if gini_impuritygt == 1.0 and gini_impurityle == 1.0:
        expectation = gini_impuritygt*(numpy.finfo(float).eps)+gini_impurityle*(numpy.finfo(float).eps)
    elif gini_impuritygt == 1.0:
        expectation = gini_impuritygt*(numpy.finfo(float).eps)+gini_impurityle*((plussizele+minussizele)/feature_values.size)
    elif gini_impurityle == 1.0:
        expectation = gini_impuritygt*((plussizegt+minussizegt)/feature_values.size)+gini_impurityle*(numpy.finfo(float).eps)
    else:
        expectation = gini_impuritygt*((minussizegt+plussizegt)/feature_values.size)+gini_impurityle*((plussizele+minussizele)/feature_values.size)
    
    return expectation

def midpoint(x):
    """Given a sequqence of numbers, return the middle points between every two consecutive ones. 
    >>> x= numpy.array([1,2,3,4,5])
    >>> (x[1:] + x[:-1]) / 2
    array([1.5, 2.5, 3.5, 4.5])
    """
    return (x[1:] + x[:-1]) / 2

def grid_search_split_midpoint(X, y): 
    """Given a dataset, compute the gini impurity expectation for all pairs of features and thresholds. 
    Inputs
    ----------
        X: 2-D numpy array, axis 0 or row is a sample, and axis 1 or column is a feature
        y: 1-D numpy array, the labels, +1 or -1
    Returns
    ---------
        grid: 2-D numpy array, axis 0 or row is a threshold, and axis 1 or column is a feature
    Examples 
    -------------
    >>> numpy.random.seed(1) # fix random number generation starting point
    >>> X = numpy.random.randint(1, 10, (8,3)) # generate training samples
    >>> y = numpy.array([+1,+1,+1,+1, -1,-1,-1,-1])
    >>> grid, feature_id, bts = grid_search_split_midpoint(X, y)
    >>> numpy.set_printoptions(precision=5)
    >>> print (grid)
    [[0.42857 0.5     0.46667]
     [0.46667 0.5     0.46667]
     [0.46667 0.46667 0.46667]
     [0.375   0.5     0.46667]
     [0.5     0.5     0.46667]
     [0.5     0.5     0.5    ]
     [0.5     0.42857 0.42857]]
    >>> clf = sklearn.tree.DecisionTreeClassifier(max_depth=1)
    >>> clf = clf.fit(X,y)
    >>> print (clf.tree_.feature[0], clf.tree_.threshold[0], feature_id, bts)
    0 7.0 0 7.0
    >>> print(clf.tree_.feature[0] == feature_id)
    True
    >>> print( clf.tree_.threshold[0] == bts)
    True
    >>> # Antoher test case 
    >>> numpy.random.seed(2) # fix random number generation starting point
    >>> X = numpy.random.randint(1, 30, (8,3)) # generate training samples
    >>> grid, feature_id, bts = grid_search_split_midpoint(X, y)
    >>> numpy.set_printoptions(precision=5)
    >>> print (grid)
    [[0.42857 0.42857 0.42857]
     [0.5     0.5     0.33333]
     [0.375   0.46667 0.46667]
     [0.375   0.5     0.5    ]
     [0.46667 0.46667 0.46667]
     [0.33333 0.5     0.5    ]
     [0.42857 0.42857 0.42857]]
    >>> clf = clf.fit(X,y) # return the sklearn DT
    >>> print (clf.tree_.feature[0], clf.tree_.threshold[0], feature_id, bts)
    2 8.5 2 8.5
    >>> print(clf.tree_.feature[0] == feature_id)
    True
    >>> print( clf.tree_.threshold[0] == bts)
    True
    >>> # yet antoher test case 
    >>> numpy.random.seed(4) # fix random number generation starting point
    >>> X = numpy.random.randint(1, 100, (8,3)) # generate training samples
    >>> grid, feature_id, bts = grid_search_split_midpoint(X, y)
    >>> numpy.set_printoptions(precision=5)
    >>> print (grid)
    [[0.42857 0.42857 0.42857]
     [0.5     0.5     0.33333]
     [0.46667 0.46667 0.375  ]
     [0.375   0.375   0.375  ]
     [0.46667 0.2     0.46667]
     [0.5     0.42857 0.5    ]
     [0.42857 0.42857 0.42857]]
    >>> clf = clf.fit(X,y) # return the sklearn DT
    >>> print (clf.tree_.feature[0], clf.tree_.threshold[0], feature_id, bts)
    1 47.5 1 47.5
    >>> print(clf.tree_.feature[0] == feature_id)
    True
    >>> print( clf.tree_.threshold[0] == bts)
    True
    """

    X_sorted = numpy.sort(X, axis=0)
    thresholds = numpy.apply_along_axis(midpoint, 0, X_sorted)
    finmin = estimate_gini_impurity_expectation(X[:,0], thresholds[0][0],y)
    grid = numpy.empty((thresholds.shape))
    minindex = 0
    
    fthreshold = thresholds[0][0]
    finthres = fthreshold
    

    for q in range(0,thresholds[0].size):
        for j in range(0,thresholds[:,0].size):
            fthreshold = thresholds[j][q]

            festimate = estimate_gini_impurity_expectation(X[:,q], fthreshold, y)
            
            
            grid[j][q] = festimate

            if festimate <= finmin:
                finmin = festimate
                finthres = fthreshold
                minindex = q
    
    #(best_threshold, best_feature) = \
    #    numpy.unravel_index(numpy.argmin(grid, axis=None), grid.shape)
    
    best_threshold = finthres
    best_feature = minindex
    return grid, best_feature, best_threshold 

def you_rock(N, R, d):
    """
    N: int, number of samples, e.g., 1000. 
    R: int, maximum feature value, e.g., 100. 
    d: int, number of features, e.g., 3. 
    """
    numpy.random.seed() # re-random the seed 
    hits = 0
    for _ in range(N):
        X = numpy.random.randint(1, R, (8,d)) # generate training samples
        y = numpy.array([+1,+1,+1,+1, -1,-1,-1,-1])
        _, feature_id, bts = grid_search_split_midpoint(X, y)
        clf = sklearn.tree.DecisionTreeClassifier(max_depth=1)
        clf = clf.fit(X,y)
        
        if clf.tree_.feature[0] == feature_id and clf.tree_.threshold[0] == bts:
            hits += 1 
    print ("your Decision tree is {:2.2%} consistent with Scikit-learn's result.".format(hits/N))

if __name__ == "__main__":
    import doctest
    doctest.testmod()
    you_rock(1000, 100, 3)


# In[ ]:




