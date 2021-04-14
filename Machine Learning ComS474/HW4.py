#!/usr/bin/env python
# coding: utf-8

# In[1]:


import numpy
import sklearn, sklearn.datasets, sklearn.utils, sklearn.model_selection, sklearn.svm

def study_C_fix_split(C_range): 
    """
    Examples 
    -----------
    >>> study_C_fix_split([1,10,100,1000])
    10
    >>> study_C_fix_split([10**p for p in range(-5,5)])
    10
    """
    best_C = 0 # placeholder

    # load the data
    data = sklearn.datasets.load_breast_cancer()
    X, y = data["data"], data["target"]

    # prepare the training and testing data
    X_train, X_test, y_train, y_test = sklearn.model_selection.train_test_split(X, y, test_size=0.2, random_state=1)
    tofit = sklearn.svm.SVC(C_range[0],kernel='linear',random_state=1)
    tofit.fit(X_train, y_train)
    best_C = C_range[0]
    best_Cv = tofit.score(X_test, y_test)

    for Citem in C_range:
    
        tofit = sklearn.svm.SVC(Citem,kernel='linear',random_state=1)
        tofit.fit(X_train, y_train)
        best_Cp = tofit.score(X_test, y_test)
        if best_Cp > best_Cv:
            best_C = Citem
            best_Cv = best_Cp
    
    
    return best_C

def study_C_gridCV(C_range):
    """
    C_range: 1-D list of floats or integers 
    Examples
    --------------
    >>> study_C_gridCV([1,2,3,4,5])
    2
    >>> study_C_gridCV(numpy.array([0.1, 1, 10]))
    10.0
    """
    best_C = 0  #placeholder

    # load the data
    data = sklearn.datasets.load_breast_cancer()
    X, y = data["data"], data["target"]

    # shuffle the data
    X, y = sklearn.utils.shuffle(X, y, random_state=1)
        

    model = sklearn.svm.SVC(
            kernel='linear',
            random_state=1)

    paras = {'C':C_range}
    
    # your code here
    
    clf = sklearn.model_selection.GridSearchCV(model, paras)
    clf.fit(X,y)
    
    best_C = clf.best_params_['C']
    
    
    return best_C

def study_C_and_sigma_gridCV(C_range, sigma_range):
    """
    Examples 
    ------------
    >>> study_C_and_sigma_gridCV([0.1, 0.5, 1, 3, 9, 100], numpy.array([0.1, 1, 10]))
    (0.1, 0.1)
    >>> study_C_and_sigma_gridCV([10**p for p in range(-5, 5, 1)], [10**p for p in range(-5, 5, 1)])
    (1000, 1e-05)
    """
    best_C, best_sigma = 0, 0 # placeholders

    # load the data
    data = sklearn.datasets.load_breast_cancer()
    X, y = data["data"], data["target"]

    # shuffle the data
    X, y = sklearn.utils.shuffle(X, y, random_state=1)

    # your code here
    model = sklearn.svm.SVC(kernel='rbf',random_state=1)
    paras = {'C':C_range, 'gamma':sigma_range}
    
    clf = sklearn.model_selection.GridSearchCV(model,paras)
    clf.fit(X,y)
    best_C = clf.best_params_['C']
    best_sigma = clf.best_params_['gamma']
    
    
    return best_C, best_sigma



if __name__ == "__main__":
    import doctest
    doctest.testmod()
    doctest.run_docstring_examples(study_C_and_sigma_gridCV, globals())


# In[ ]:




