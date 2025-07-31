//Name: Jeslyn Ho Ka Yan
//Tutorial group:121T05
//JDK version: 11.016
//Declaration: This is my work and I have not copied anyone's work, I am willing to accept whatever penalty given to me.



import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

enum NumberType {
    One("1", "un", "satu", "uno"),
    Two("2", "deux", "dua", "dos"),
    Three("3", "trois", "tiga", "tres"),
    Four("4", "quatre", "empat", "cuatro"),
    Five("5", "cinq", "lima", "cinco"),
    Six("6", "six", "enam", "seis"),
    Seven("7", "sept", "tujuh", "siete"),
    Eight("8", "huit", "delapan", "ochu"),
    Nine("9", "neuf", "sembilan", "nueve"),
    Ten("10", "dix", "sepuluh", "diez");

    private final String number;
    private final String french;
    private final String bahasa;
    private final String spanish;

    NumberType(String number, String french, String bahasa, String spanish) {
        this.number = number;
        this.french = french;
        this.bahasa = bahasa;
        this.spanish = spanish;
    }

    public String getNumber() {
        return number;
    }

    public String getFrench() {
        return french;
    }

    public String getBahasa() {
        return bahasa;
    }

    public String getSpanish() {
        return spanish;
    }
}//============================================================================================================


class Set {//============================================================================================================

    private final ArrayList<NumberType> s;

    public Set() {
        this.s = new ArrayList<>(Arrays.asList(generateRandomSet()));

    }

    //---------------------------------------------------------------------------
    public Set(Set otherSet) { // deep copy
        this.s = new ArrayList<>(Arrays.asList(otherSet.generateRandomSet()));

    }

    //---------------------------------------------------------------------------

    private NumberType[] generateRandomSet() {
        Random random = new Random();//random class
        int maxSize = 10;
        int setSize = random.nextInt(maxSize) + 1;//get the random size of the Array List
        int numValues = NumberType.values().length;//the number of all enum constants
        NumberType[] randomSet = new NumberType[setSize];// new a NumberType array list with setsize number of elements
        boolean[] usedIndices = new boolean[numValues];

        if (setSize > numValues) {
            setSize = numValues;
        }

        for (int i = 0; i < setSize; i++) {
            int randomIndex;
            do {
                randomIndex = random.nextInt(numValues);//get a random integer between 0 and numValue i.e. 10 since there are 10 constants in enum
            } while (usedIndices[randomIndex]);// check if the element already exist in arraylist, if no, false.

            randomSet[i] = NumberType.values()[randomIndex];//only assign the element to the array list if it does not exist
            usedIndices[randomIndex] = true;//turn the boolean to true means that enum element has been used
        }

        return randomSet;
    }

    //---------------------------------------------------------------------------
    public int cardinality() {
        return s.size();
    }

    //---------------------------------------------------------------------------

    public boolean belongTo(NumberType element) {
        if (s.contains(element))
            return true;
        else
            return false;
    }
    //---------------------------------------------------------------------------

    public void addElement(NumberType element) {
        if (!s.contains(element)) {
            s.add(element);
        }
    }
    //---------------------------------------------------------------------------
    public void union(Set otherSet) {
        for (int i = 0; i < s.size(); i++) //add the missing element from setA1 into setB (otherSet)
            otherSet.addElement(s.get(i));

        //System.out.println("Union of A and B = {" + otherSet.getNumberInfoFormat(1) + "}");
    }

    public void intersect(Set otherSet) {

        ArrayList<NumberType> setIntersect = new ArrayList<>();
        for (int i = 0; i < s.size(); i++) {
            if (otherSet.belongTo(s.get(i))) {
                setIntersect.add(s.get(i));
            }
        }
        otherSet.s.clear();
        otherSet.s.addAll(setIntersect);
    }


    //---------------------------------------------------------------------------

    public boolean subset(Set otherSet) {
        for (NumberType element : s) {
            if (!otherSet.belongTo(element)) {
                return false;
            }
        }
        return true;
    } //---------------------------------------------------------------------------

    private ArrayList<NumberType> getArray() {
        return s;
    }

    public void difference(Set otherSet) {
        for (NumberType element : otherSet.getArray()) {
            if (belongTo(element)) { //check if setB element is found in setA1
                s.remove(element); //remove from setA1 i.e s
            }
        }
        System.out.println("\tDifference of A - B = {" + toString(s) + "}");

    } //---------------------------------------------------------------------------


    public void complement() {
        ArrayList<NumberType> setComplement = new ArrayList<>();
        for (NumberType number : NumberType.values()) {//full list
            if (!belongTo(number))
                setComplement.add(number);
        }
        System.out.println("\tA' = {" + toString(setComplement) + "}");
    }

    //---------------------------------------------------------------------------
    public boolean equality(Set otherSet) {
        if (s.size() != otherSet.s.size()) {
            return false;
        }
        return subset(otherSet);
    }


    //---------------------------------------------------------------------------
    private String toString(ArrayList<NumberType> setList) {
        String result = null;
        for (int j = 0; j < setList.size(); j++) {
            if (result == null)
                result = setList.get(j).name();
            else
                result = result + ", " + setList.get(j).name();
        }
        return result;
    }


    //---------------------------------------------------------------------------
    public String getNumberInfoFormat(int format) {
        String numberInfoFormat = "";

        switch (format) {

            case 2: //number

                for (int i = 0; i < s.size(); i++) {

                    numberInfoFormat = numberInfoFormat.concat(s.get(i).getNumber());
                    if (i < s.size() - 1) {
                        numberInfoFormat = numberInfoFormat.concat(", ");
                    }
                }
                break;

            case 3: //French
                for (int i = 0; i < s.size(); i++) {
                    numberInfoFormat = numberInfoFormat.concat(s.get(i).getFrench());
                    if (i < s.size() - 1) {
                        numberInfoFormat = numberInfoFormat.concat(", ");
                    }
                }
                break;

            case 4: //Bahasa
                for (int i = 0; i < s.size(); i++) {
                    numberInfoFormat = numberInfoFormat.concat(s.get(i).getBahasa());
                    if (i < s.size() - 1) {
                        numberInfoFormat = numberInfoFormat.concat(", ");
                    }
                }
                break;

            case 5: //Spanish

                for (int i = 0; i < s.size(); i++) {
                    numberInfoFormat = numberInfoFormat.concat(s.get(i).getSpanish());
                    if (i < s.size() - 1) {
                        numberInfoFormat = numberInfoFormat.concat(", ");
                    }
                }
                break;
            case 1: //name

            default: //name

                for (int i = 0; i < s.size(); i++) {
                    numberInfoFormat = numberInfoFormat.concat(s.get(i).name());
                    if (i < s.size() - 1) {
                        numberInfoFormat = numberInfoFormat.concat(", ");
                    }
                }

                break;
        }
        return numberInfoFormat;

    }
    //---------------------------------------------------------------------------

    public void copySet(Set cSet) {//for distributiveLaw_2 and 1
        s.clear();
        s.addAll(cSet.s);
    }

}//============================================================================================================

public class Jeslynho_A1 {//============================================================================================================
    private static final Scanner scanner = new Scanner(System.in);
    private static final Set setA = new Set(); // new random set

    public static void main(String[] args) {
        displayNumberTypeInfo();

        while (true) {
            displayMenu();
            String input = scanner.next();
            System.out.print(" ");
            switch (input) {
                case "0" -> {
                    System.out.println("=========================");
                    System.out.println("Properties of set");
                    displaySubMenu();
                }
                case "1" -> {
                    System.out.println("=========================");
                    System.out.println("Union example");
                    unionExample();
                }
                case "2" -> {
                    System.out.println("=========================");
                    System.out.println("Intersection example");
                    intersectExample();
                }
                case "3" -> {
                    System.out.println("=========================");
                    System.out.println("Subset example");
                    subSetExample();
                }
                case "4" -> {
                    System.out.println("=========================");
                    System.out.println("Difference example");
                    differenceExample();
                }
                case "5" -> {
                    System.out.println("=========================");
                    System.out.println("Complement example");
                    complementExample();
                }
                case "6" -> {
                    System.out.println("=========================");
                    System.out.print("Sets equality example");
                    equalityExample();
                }
                case "7" -> {
                    System.out.println("=========================");
                    System.out.println("Distributive Law 1");
                    distributiveLaw_1();
                }
                case "8" -> {
                    System.out.println("=========================");
                    System.out.println("Distributive Law 2");
                    distributiveLaw_2();
                }
                case "9" -> {
                    System.out.println("Quit");
                    System.exit(0);//shutdown hook
                }
                default -> System.out.println("Unrecognized option");
            }
        }
    }
//---------------------------------------------------------------------------


    private static void displayNumberTypeInfo() {
        System.out.print("Universal set Info\nNumberType   Number   French   Bahasa   Spanish\n");
        for (NumberType number : NumberType.values()) {
            System.out.printf("%-12.5s %-8.5s %-8s %-8s %-10s \n", number, number.getNumber(), number.getFrench(), number.getBahasa(), number.getSpanish());
        }
        System.out.println();
        System.out.println("==========================================================");
    }

    private static void displayMenu() {//]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
        System.out.printf("Welcome to SIM set Theory lesson\n");
        System.out.println("0: Properties of set");
        System.out.println("1: Union example");
        System.out.println("2: Intersection example");
        System.out.println("3: Subset example");
        System.out.println("4: Difference example");
        System.out.println("5: Complement example");
        System.out.println("6: Sets equality example");
        System.out.println("7: Distributive Law 1");
        System.out.println("8: Distributive Law 2");
        System.out.println("9: Quit");
        System.out.print("Your option:");
    }

    public static void displaySubMenu() {
        System.out.println("\nHere is an example of set");
        int format = 1;

        System.out.println("\tA = {" + setA.getNumberInfoFormat(format) + "}");
        format++;
        System.out.println("\tAll elements in set are distinct and in random order");
        System.out.println();

        while (true) {
            System.out.println("\t1. Add an element");
            System.out.println("\t2. Check an element");
            System.out.println("\t3. Cardinality");
            System.out.println("\t4. Number Info format");
            System.out.println("\t9. Go back to Main Menu");
            System.out.print("\tYour option:");
            String input2 = scanner.next();


            switch (input2) {
                case "1":
                    System.out.println("\nAdd an element");
                    addAnElement();
                    break;
                case "2":
                    System.out.println("\nCheck an element");
                    String userInput = scanner.next();

                    NumberType numberType = null;
                    for (NumberType type : NumberType.values()) {// Search for the same enum based on user input
                        if (type.name().equalsIgnoreCase(userInput)) {
                            numberType = type;
                            break;
                        }
                    }

                    if (numberType != null) {
                        if (setA.belongTo(numberType))
                            System.out.println("Element " + numberType.name() + " is in set");
                        else
                            System.out.println("Element " + numberType.name() + " is not in set");
                    }
                    break;
                case "3":
                    System.out.println("\nCardinality");
                    System.out.println("==> No of elements in set is " + setA.cardinality());

                    break;
                case "4":
                    System.out.println("\nNumber Info format");
                    System.out.println("A = {" + setA.getNumberInfoFormat(format) + "}");
                    if (format < 5)
                        format++;
                    else format = 1;

                    break;
                case "9":
                    System.out.println("\nBack to the main menu");
                    return;
                default:
                    System.out.println("\nUnrecognized option");
                    break;
            }
        }
    }
    //---------------------------------------------------------------------------

    private static void addAnElement() {
        System.out.println("Enter an element:");
        String userInput = scanner.next();

        NumberType numberType = null;
        for (NumberType type : NumberType.values()) {// Search for the same enum based on user input
            if (type.name().equalsIgnoreCase(userInput)) {
                numberType = type;
                break;
            }
        }

        if (numberType != null) {
            setA.addElement(numberType);

            System.out.println("==> A = { " + setA.getNumberInfoFormat(1) + "}");
            System.out.println("===============");
        } else {
            System.out.println("Invalid input");
        }
    }

    //---------------------------------------------------------------------------
    private static void unionExample() {
        Set setA1 = new Set();
        Set setB = new Set(setA1);

        System.out.println("\nGiven sets");
        System.out.println("\tA = {" + setA1.getNumberInfoFormat(1) + "}");
        System.out.println("\tB = {" + setB.getNumberInfoFormat(1) + "}");

        setA1.union(setB);
        System.out.println("\tUnion of A and B = {" + setB.getNumberInfoFormat(1) + "}");

    }

    //---------------------------------------------------------------------------
    private static void intersectExample() {
        Set setA1 = new Set();
        Set setB = new Set(setA1);

        System.out.println("\nGiven sets");
        System.out.println("\tA = {" + setA1.getNumberInfoFormat(1) + "}");
        System.out.println("\tB = {" + setB.getNumberInfoFormat(1) + "}");

        setA1.intersect(setB);
        System.out.println("\n\tIntersection of A and B = {" + setB.getNumberInfoFormat(1) + "}");
    }
    //---------------------------------------------------------------------------

    private static void subSetExample() {
        Set setA1 = new Set();
        Set setB = new Set(setA1);

        System.out.println("\nGiven sets");
        System.out.println("\tA = {" + setA1.getNumberInfoFormat(1) + "}");
        System.out.println("\tB = {" + setB.getNumberInfoFormat(1) + "}");

        System.out.println("Conclusion");
        System.out.println("\tA subset of B: " + setA1.subset(setB));
        System.out.println("\tB subset of A: " + setB.subset(setA1));


    } //---------------------------------------------------------------------------


    private static void differenceExample() {
        Set setA1 = new Set();
        Set setB = new Set(setA1);

        System.out.println("\nGiven sets");
        System.out.println("\tA = {" + setA1.getNumberInfoFormat(1) + "}");
        System.out.println("\tB = {" + setB.getNumberInfoFormat(1) + "}");

        setA1.difference(setB);
    }

    //---------------------------------------------------------------------------
    private static void complementExample() {
        Set setA1 = new Set();
        System.out.println("\nGiven sets");
        System.out.println("\tA = {" + setA1.getNumberInfoFormat(1) + "}");

        setA1.complement();
    }

    //---------------------------------------------------------------------------
    private static void equalityExample() {
        Set setA1 = new Set();
        Set setB = new Set(setA1);
        // Set setB = new Set(); //testing
        // setB.copySet(setA1);

        System.out.println("\nGiven sets");
        System.out.println("\tA = {" + setA1.getNumberInfoFormat(1) + "}");
        System.out.println("\tB = {" + setB.getNumberInfoFormat(1) + "}");

        System.out.println("Analysis");
        System.out.println("\tA subset of B: " + setA1.subset(setB));
        System.out.println("\tB subset of A: " + setB.subset(setA1));

        System.out.println("Conclusion");
        System.out.println("\tA is equal to B: " + (setA1.equality(setB)));
    }
    //---------------------------------------------------------------------------

    private static void distributiveLaw_1() {
        Set setA1 = new Set();
        Set setB = new Set(setA1);
        Set setC = new Set(setA1);

        Set setC1 = new Set();
        setC1.copySet(setC); // need this because setC will change after LHS operation

        System.out.println("\nWe wish to prove: A U (B I C) = (A U B) I (A U C)");
        System.out.println("\nGiven sets");
        System.out.println("\tA = {" + setA1.getNumberInfoFormat(1) + "}");
        System.out.println("\tB = {" + setB.getNumberInfoFormat(1) + "}");
        System.out.println("\tC = {" + setC.getNumberInfoFormat(1) + "}");

        // LHS
        //1st: B I C
        setB.intersect(setC);
        //2nd: A U setC
        setA1.union(setC);
        System.out.println("\nLHS analysis\n \tLHS = {" + setC.getNumberInfoFormat(1) + "}");

        // RHS
        //1st: A U B
        setA1.union(setB);
        //2nd: A U C
        setA1.union(setC1);
        //3rd: setB I setC1
        setB.intersect(setC1);

        System.out.println("\nRHS analysis\n \tRHS = {" + setC1.getNumberInfoFormat(1) + "}");

        System.out.println("\nConclusion\n \tLHS = RHS is " + (setC.equality(setC1)));
        System.out.println();
    }


    private static void distributiveLaw_2() {
        Set setA1 = new Set();
        Set setB = new Set(setA1);
        Set setC = new Set(setA1);

        Set setC1 = new Set();
        setC1.copySet(setC); // need this because setC will change after LHS operation

        System.out.println("\nWe wish to prove: A I (B U C) = (A I B) U (A I C)");
        System.out.println("\nGiven sets");
        System.out.println("\tA = {" + setA1.getNumberInfoFormat(1) + "}");
        System.out.println("\tB = {" + setB.getNumberInfoFormat(1) + "}");
        System.out.println("\tC = {" + setC.getNumberInfoFormat(1) + "}");

        // LHS
        //1st: B U C
        setB.union(setC);
        //2nd: A I setC
        setA1.intersect(setC);
        System.out.println("\nLHS analysis\n \tLHS = {" + setC.getNumberInfoFormat(1) + "}");

        // RHS
        //1st: A I B
        setA1.intersect(setB);
        //2nd: A IC
        setA1.intersect(setC1);
        //3rd: setB U setC1
        setB.union(setC1);

        System.out.println("\nRHS analysis\n \tRHS = {" + setC1.getNumberInfoFormat(1) + "}");

        System.out.println("\nConclusion\n \tLHS = RHS is " + (setC.equality(setC1)));
        System.out.println();
    }

}
