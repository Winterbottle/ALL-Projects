#====================================================
class Student:
	def _init_(self,userid,name,classNum,gender,password):
		self.userid = userid
		self.name = name
		self.classNum = classNum
		self.gender = gender
		self.password = password
#========================MAIN==========================================
def main():

    s1 = Student()
    print("Welcome to CucKoo Learn")
    option = input("Login or Register (Login,Reg): ")
    status = access(option,s1)                
    if status== False:                           #login fail
        return                               #exit from program\


   # showMainMenu()
    while True:
        option= showMainMenu()
        if option == '1':
            startQuiz(s1)
        elif option == '2':
            displayResult(s1)
        elif option == '3':
            displayRanking()
        elif option =='4':
            print("Thanks for using CucKoo Learn, bye")
            return
        else:
            print("Wrong input")

#=============================================================


def showMainMenu():
    print('''
    ==================================================================
    ===================== CUCKOO LEARN MAIN MENU =====================
    ==================================================================
    1. Start Quiz
    2. Show Results
    3. Show ranking
    4. Quit
   ''' )

    option = input("Please enter option:")
    return option





#=========================LOGIN============================
def login(s1):
    success = False                         #(False) login not successful
    file = open("students_info.txt","r")
    for line in file:
        a,b,c,d,e = line.split(",")
        if(s1.userid==a and s1.password==b):
            s1.userid = a
            s1.password = b
            s1.name = c
            s1.classNum = d
            s1.gender = e
            success = True                 #(True) login successful
            break

    file.close()
    if(success):
        print("Login Successful")
        return True                #(True) means login pass, return a,b (go to access)
    else:
        print("wrong user name or password")
        return False               #(False) means login fail, retun a,b (go to access)
#==============================================================



def validInput(input):
    if input.find(",") != -1:                   #cannot find (-1)
        return False
    else:
        return True

#========================Registeration=================================================
def registerStu(s1):
    if isUserIDFound(s1.userid) == False :   
        file = open("students_info.txt","a")
        file.write(s1.userid+","+s1.password+ ","  +s1.name+","  +s1.classNum+"," +s1.gender+"\n")
        file.close()
        return True                          # (True), registration successful, userid is now in the file (go to access)
    else :                                  
        print("You have already registered please login.")
        return False                         # (False), regsitration not successful, already in file (go to access)

def isUserIDFound(userid):                  #vaildation comes First
    found = False                           #User have not registered, not in the file (False)
    file = open("students_info.txt","r")
    for line in file:
        a,b,c,d,e = line.split(",")
        if(userid==a):
             found = True                   #user have registered, in the file (True) 
             break
    file.close()
    return found
#===============================================================================================


#=================================ACCESS=============================================(begin > access >def > access > begin > access > def ...)
def access(option,s1):
    if(option=="login"):    #login
        s1.userid = input("Enter your user id: ")      
        s1.password = input("Enter your password: ")
        return login(s1)                #a = login status True/False, b=userid 

    elif (option=="reg"):                 #registration
        print("Enter your name and password to register")   

        s1.userid = input("Enter your userid: ")
        if validInput(str(s1.userid)) == False:
            print("User ID cannot contain ','")
            print("Please register again.")
            return False

        s1.password = input("Enter your password: ")
        s1.name = input ("Enter your name:")
        s1.classNum = input("Enter your Class Number: ")
        s1.gender = input("Enter your gender (f/m): ")
        
        if registerStu(s1) == False:                                 # (False), regsitration not successful, already in file
            s1.userid = input("Enter your user id: ")      
            s1.password = input("Enter your password: ")
            return login(s1)                          
        else:
            return True                                               # (True), registration successful, userid is now in the file
    else:
         print("Invalid option. Please try again")   
         return False                                         
#=============================================================================================


#============================QUIZ============================================

def startQuiz(s1): 
    displayQuizMenu()
    proceed = input("Do you want to proceed?(y/n)")
    if proceed=='n' or proceed=='N':
        return                              #exit from program

    print ("=============Start Basic Quiz ===============")
    b1,b2 = quizGen('B') #a=score, b=qnAnsw
    bP = round(b1*100/b2, 2)
    print ("You have score "+str(b1)+" out of "+str(b2)+" questions for Basic level. You have achieved " + "%s%%"%bP+" correctness")
    print ("==============================================") 

    print ("=============Start Normal Quiz ===============")
    n1,n2 =quizGen('N')
    nP = round(n1*100/n2,2)
    print ("You have score "+str(n1)+" out of "+str(n2)+" questions for Advance level. You have achieved " + "%s%%"%nP+" correctness")
    print ("==============================================")

    print ("=============Start Advance Quiz ===============")
    a1,a2 =quizGen('A')
    aP = round(a1*100/a2,2)
    print ("You have score "+str(a1)+" out of "+str(a2)+" questions for Advance level. You have achieved " + "%s%%"%aP+" correctness")
    print ("==============================================")

    print("You have completed all 3 quizes")
    overallP = round((b1+n1+a1)*100/(b2+n2+a2),2)
    print("You have achieved "+ "%s%%"%overallP +" correctness for all 3 quizes")

    storeResult(s1,b1,b2,n1,n2,a1,a2,bP,nP,aP,overallP)
    
    input("Press ENTER to return to main menu")



def displayQuizMenu():
    print("Welcome to Cuckoo Learn Mathematics Test for Primary 1")
    print('''
            You will be taking 3 tests

            "Basic"
            No. of questions: 5
            Type of questions (Addtional/Subtraction)
    
            "Normal"
            No. of questions: 8
            Type of questions (Muliplication/Division)
    
            "Advanced"
            No. of questions: 10
            Type of questions (Addtional/Subtraction/Muliplication/Division)
        ''')

def quizGen(level):
    from random import randint
    from random import choice
    qnAnsw= 0 
    score= 0  

    if level=='B':
        qnNum = 5
        randList = ['+','-']
    elif level=='N':
        qnNum = 8
        randList = ['*','/']
    else:  #Advance
        qnNum = 10
        randList = ['+','-','*','/']
      
    while qnAnsw <qnNum:
        randomNum1 = randint(1,9)
        randomNum2 = randint(1,9)
        eq = choice(randList)
        print("Question", qnAnsw)

        if eq == '+' or eq == '-' or eq == '*':
            print(randomNum1, eq, randomNum2, "=")
        else:
            print(randomNum1 * randomNum2,eq, randomNum1, "=")
        if eq == '+':
            final = randomNum1 + randomNum2
        elif eq == '-':
            final = randomNum1 - randomNum2
        elif eq == '*':
            final = randomNum1 * randomNum2
        elif eq == '/':
            final = round((randomNum1*randomNum2)/randomNum1)

        print(final)

        ans = input("Answer: ") #cannot cast as a float because user may input alphabet wrongly
        if ans == str(final): #convert final to string for comparison as user may input characters i.e. wrong answer
            result = 1
            print("Correct!")
        else:
            result = 0
            print("Incorrect!")

        print("--------------------------------")

        if result == 1:
            score += 1
        else:
            score = score
        qnAnsw += 1
    return score,qnAnsw
#==================================================================================

#==========================Delete existing record ==============================================
import os
def deleteExistingRecord(s1, file1):
    with open(file1,"r") as input:
        with open ("temp.txt","w") as output:
            for line in input:
                if not line.strip("\n").startswith(s1.userid+","):
                    output.write(line)
    os.replace('temp.txt',file1)




#==========================STORE RESULT==============================================
def storeResult(s1,b1,b2,n1,n2,a1,a2,bP,nP,aP,overallP):

    deleteExistingRecord(s1, "Result_Breakdown.txt")

    file = open("Result_Breakdown.txt","a")
    file.write(s1.userid +","+"Basic" +","+ str(b1) +"/"+str(b2)  +","+"%s%%"%bP+"\n")
    file.write(s1.userid +","+ "Normal" +","+ str(n1)+"/"+str(n2)  +","+ "%s%%"%nP+"\n")
    file.write(s1.userid +","+ "Advance" +","+ str(a1)+"/"+str(a2)  +","+ "%s%%"%aP+"\n")
    file.close()

    deleteExistingRecord(s1, "Result_Overall.txt")

    file=open("Result_Overall.txt", "a")
    file.write(s1.userid + ","+ "%s%%"%overallP+","+s1.classNum +"\n")
    file.close()


#====================DISPLAY RESULT=============================
def displayFile(s1,file1):
    with open(file1,"r") as input:
        for line in input:
            if line.strip("\n").startswith(s1.userid+","):
                print(line)


def displayResult(s1):
    print("Result Breakdown")
    displayFile (s1, "Result_Breakdown.txt")

    print("Result Overall")
    displayFile(s1, "Result_Overall.txt")

    input("Press ENTER to return to main menu")
#============================================================================

class reversor:
        def __init__(self,obj) :
            self.obj =obj
        def __eq__(self,other):
            return other.obj == self.obj
        def __lt__(self,other):
            return other.obj < self.obj

#=====================DISPLAY RANKING=====================================

def displayRanking():
    infile=open("Result_Overall.txt", "r")
    allStudentScore=[]
    for line in infile:
        userid, overallP, classNum= line.split(',')
        studentScore = [userid, overallP, classNum]
        allStudentScore.append(studentScore)

    infile.close()

    print("-----------------Ranking by Class --------------------")
    result = sorted (allStudentScore, key=lambda x:(x[2],reversor (float(x[1][:-1]))))                            #sort by 2 first(class), then sort by 1(percentage)

    currentClass=""
    newClass=""
    
    for i in result:
        newClass = i[2].strip("\n")
        if (newClass != currentClass):
            print("\n-----------Ranking for Class "+i[2].strip("\n") +"----------")
            print("UserID, Overall %, Class")
            print("------------------------")
            currentClass = newClass
        print (i[0],",",i[1],",",i[2].strip("\n"))

    print("\n-----------------Ranking all student --------------------")
    result = sorted (allStudentScore, key=lambda x:float(x[1][:-1]), reverse= True)                #[-1] removes the percentage sign, convert it to number so it can be sorted
    print("UserID, Overall %, Class")
    print("------------------------")
    for i in result:
        print (i[0],",",i[1],",",i[2].strip("\n"))

    input("Press ENTER to return to main menu")


main()
