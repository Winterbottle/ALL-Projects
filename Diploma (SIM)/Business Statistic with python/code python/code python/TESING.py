from random import randint
from random import choice


randList = ['+', '-', '*', '/']
endRand = 0
qnNum = 1
score = 0

while endRand < 5:
    if qnNum < 6:
        randomNum1 = randint(1, 9)
        randomNum2 = randint(1, 9)
        eq = choice(randList)
        print("Question", qnNum)
        print(randomNum1, eq, randomNum2, "=")
        if eq == '+':
            final = randomNum1 + randomNum2
        elif eq == '-':
            final = randomNum1 - randomNum2
        elif eq == '*':
            final = randomNum1 * randomNum2
        elif eq == '/':
            final = randomNum1 / randomNum2

        ans = float(input("Answer: "))
        if ans == final:
            result = 1
            print("Correct!")
        else:
            result = 0
            print("Incorrect!")

            if result == 1:
                score += 1
            else:
                score = score

        endRand += 1
        qnNum += 1
    
    else:
        break

