import sys
from util import PriorityQueue
sys.setrecursionlimit(100000)

# Model

class ElevatorProblem(object):
    def __init__(self, N):
        # N = number of levels
        self.N = N
    def startState(self):
        return 1
    def isEnd(self, state):
        return state == self.N
    def succAndCost(self, state):
        # return a list of (action, newState, cost) triples
        result = []
        if state+1 <= self.N:
            result.append(('walk', state+1, 1.0))
        if state*2 <= self.N:
            result.append(('elevator', 2*state, 1.5))
        return result

# Algorithms

def printSolution(solution):
    totalCost, history = solution
    print('Total cost: {}'.format(totalCost))
    for step in history:
        print(step)

