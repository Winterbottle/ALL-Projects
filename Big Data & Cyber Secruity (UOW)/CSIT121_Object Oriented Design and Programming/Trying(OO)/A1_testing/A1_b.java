//File: A1_b.java
// RandomSet generator is in the Class A1_b.
// set is in integer not String
// When enter in a number, it shows an int, not String. Eg. 1, One
// NUmber in set repeats

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


class A1_a {

    private static Scanner scanner = new Scanner(System.in);
    private static Set yourSet = new Set(); // Create an instance of the Set class
    private static ArrayList<NumberType> contents = yourSet.getSetContents();
    private static NumberType[] randomSet;


    public static void main(String[] args) {

        displayNumberTypeInfo();
        generateRandomSet(3);

        while(true){
            displayMenu();
            String input = scanner.next();
            switch(input){
                case"0":
                    System.out.println("=========================");
                    System.out.println("Properties of set");
                    displaySubMenu();
                    break;
                case"1":
                    System.out.println("=========================");
                    System.out.println("Union example");
                    break;
                case"2":
                    System.out.println("=========================");
                    System.out.println("Intersection example");
                    break;
                case"3":
                    System.out.println("=========================");
                    System.out.println("Subset example");
                    break;
                case"4":
                    System.out.println("=========================");
                    System.out.println("Difference example");
                    break;
                case"5":
                    System.out.println("=========================");
                    System.out.println("Complement example");
                    break;
                case"6":
                    System.out.println("=========================");
                    System.out.print("Sets equality example");
                    break;
                case"7":
                    System.out.println("=========================");
                    System.out.println("Distributive Law 1");
                    break;
                case"8":
                    System.out.println("=========================");
                    System.out.println("Distributive Law 2");
                    break;
                case"9":
                    System.out.println("Quit");
                    System.exit(0); //shutdown hook
                    break;
                default:
                    System.out.println("Unrecognized option");
                    break;


            }

        }//====================================================================================


    }



    private static void displayNumberTypeInfo(){
        System.out.printf("Universal set Info\nNumberType   Number   French   Bahasa   Spanish\n" );
        for (NumberType number : NumberType.values()) {
            System.out.printf("%-12.5s %-8.5s %-8s %-8s %-10s \n",number,number.getNumber(),number.getFrench(), number.getBahasa(), number.getSpanish());
        }
        System.out.println();
        System.out.println("==========================================================");
    }//====================================================================================


    private static void displayMenu(){
        System.out.printf("Welcome to SIM set Theory lesson\n");
        System.out.println("0: Properties of set");
        System.out.println("1: Union example");
        System.out.println("2. Intersection example");
        System.out.println("3. Subset example");
        System.out.println("4. Difference example");
        System.out.println("5. Complement example");
        System.out.println("6. Sets equality example");
        System.out.println("7. Distributive Law 1");
        System.out.println("8. Distributive Law 2");
        System.out.println("9. Quit");

        System.out.print("Your option:");
    }//====================================================================================

    public static void displaySubMenu(){
        System.out.println("Here is an example of set");
        System.out.print("A = {");
        for (int i = 0; i < randomSet.length; i++) {
            System.out.print(randomSet[i].getNumber());
            if (i < randomSet.length - 1) {
                System.out.print(", "); // Add a comma and space for all elements except the last one
            }
            yourSet.addNumberTypeElement(randomSet[i]);       // Add the current element to yourSet
        }
        System.out.println(" }");


        System.out.println("All elements in set are distinct and random order");
        System.out.println( );


        while (true) {
            System.out.println("1. Add an element");
            System.out.println("2. Check an element");
            System.out.println("3. Cardinality");
            System.out.println("4. Number Info format");
            System.out.println("9. GO back to Main Menu");
            System.out.print("Your option:");
            String input2 = scanner.next();


            switch(input2){
                case"1":
                    System.out.println("Add an element");
                       getAnElement();
                    break;
                case"2":
                    System.out.println("Check an element");
                    break;
                case"3":
                    System.out.println("Cardinality");
                    break;
                case"4":
                    System.out.println("Number Info format");
                    break;
                case"9":
                    System.out.println("Back to main menu");
                    return;

                default:
                    System.out.println("Unrecognized option");
                    break;

            }
        }

    }//====================================================================================
    private static void generateRandomSet(int size) {
        NumberType[] allValues = NumberType.values(); //create an array 'allvalues' that contains number type from enum
        randomSet = new NumberType[size];// intialialize an array "randomSet', with a specific size
        Random random = new Random(); // Initialize the Random class

        for (int i = 0; i < size; i++) {
            randomSet[i] = allValues[random.nextInt(allValues.length)];//generate int in each loop
        }
    }

    private static NumberType getAnElement() {
        System.out.println("Enter an element:");
        String userInput = scanner.next();

        NumberType numberType = null;
        for (NumberType type : NumberType.values()) {     // Search for the same enum based on user input
            if (type.name().equalsIgnoreCase(userInput)) {
                numberType = type;
                break;
            }
        }
//==========================
        System.out.println("Enter an element:");
        scanner.nextLine();
        for (NumberType type : NumberType.values()) {// Iterate through all enum values to find a match
            if (type.name().equalsIgnoreCase(userInput)) {
                numberType = type;// If a match is found, store the enum value
                break;
            }
        }

        if (numberType != null) {
            yourSet.addNumberTypeElement(numberType);// Add the enum value to the set
            System.out.println("Element added to the set: " + numberType.getNumber());
            System.out.println("Set contents: " + yourSet.getSetContents());
            System.out.println("===============");
        } else {
            System.out.println("Invalid input");
        }
        return null;
    }//====================================================================================





}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

class Set{
    //Constructor
    private ArrayList<NumberType> s;

    public Set(){
        this.s=new ArrayList<>();
    }
//copy Constructor
    /*public Set(Set otherSet){
        this.s = new ArrayList<>(otherSet.s);

    }*/

    public void addNumberTypeElement(NumberType element) {
        s.add(element);
        //System.out.println("Element added to the set: " + element.getNumber());


    }

    public ArrayList<NumberType> getSetContents() {
        return s;
    }

}



//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
enum NumberType {
    One("1","un","satu","uno"),
    Two("2","deux","dua","dos"),
    Three("3","trois","tiga","tres"),
    Four("4","quatre","empat","cuatro"),
    Five("5","cinq","lima","cinco"),
    Six("6","six","enam","seis"),
    Seven("7","sept","tujuh","siete"),
    Eight("8","huit","delapan","ochu"),
    Nine("9","neuf","sembilan","nueve"),
    Ten("10","dix","sepuluh","diez");

    // Some private instance variables
    private String number;
    private String french;
    private String bahasa;
    private String spanish;
    // constructior ==> NO public
    NumberType(String number, String french, String bahasa, String spanish) {
        this.number = number;
        this.french = french;
        this.bahasa = bahasa;
        this.spanish = spanish;
    }
    //Accessor methods
    public String getNumber(){
        return number;
    }
    public String getFrench(){
        return french;
    }
    public String getBahasa(){
        return bahasa;
    }
    public String getSpanish(){
        return spanish;
    }

   /* public static NumberType getRandom() {
        NumberType[] allValues = NumberType.values();
        return allValues[(int) (Math.random() * allValues.length)];
    }*/

}
//========================================================================================



