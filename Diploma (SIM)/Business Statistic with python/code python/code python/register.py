student_lst = []

def main():
    while (True):
        option = menu()
    
        if option == 1:
            add_user()
        elif option == 2:
            search_user()
        elif option == 3:
            enter_score()
        elif option == 4:
            import_users()
        elif option == 5:
            export_users()
        elif option == 6:
            print(student_lst)
        elif option == 0:
            print("Bye bye~")
            break
        else:
            print("wrong input")

def menu():
    print("User Management")
    print("===============")
    print('''
          1. Add User
          2. Search User
          3. Enter User Score
          4. Import Users from data file
          5. Export Users to data file
          6. List all users
          0. End program
          ''')
    option = int(input("Please enter option: "))
    return option

def add_user():
    student = {}
    
    name = input("Name: ")
    gender = input("Gender: ")
    stu_class = input("Class: ")
    
    student['name'] = name
    student['gender'] = gender
    student['stu_class'] = stu_class
    student['score'] = 0
    
    print(student)
    student_lst.append(student)
    print("added to student list")

def search_user():
    name = input("Name: ")
    for stu in student_lst:
        if stu['name'] == name:
            print(stu)
        else:
            print("please registered.")

def enter_score():
    name = input("Name: ")
    score = int(input("Score: "))
    for stu in student_lst:
        if stu['name'] == name:
            stu['score'] = score

def import_users():
    infile = open("users.txt","r")
    
    for line in infile:
        user = line.split(',')

        student = {}
        student['name'] = user[0]
        student['gender'] = user[1]
        student['stu_class'] = user[2]
        student['score'] = int(user[3])
        
        student_lst.append(student)
    
    infile.close()
    print("users data loaded")

def export_users():
    outfile = open("users.txt","w")
    
    for stu in student_lst:
        outfile.write(stu['name']+","+ \
                      stu['gender']+","+ \
                      stu['stu_class']+","+ \
                      str(stu['score'])+"\n")
    
    outfile.close()
    print("users data saved")

main()