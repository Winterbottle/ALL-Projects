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
   // private ArrayList<NumberType> s;
  //  private static NumberType[] randomSet;
    private final ArrayList<NumberType> randomSet;

    public Set() {
        this.randomSet = new ArrayList<>(Arrays.asList(generateRandomSet(10)));

    }
    //---------------------------------------------------------------------------




    private NumberType[] generateRandomSet(int maxSize) {
        Random random = new Random();//random class
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


    public void printAndAddRandomSet() {
        for (int i = 0; i < randomSet.size(); i++) {
            System.out.print(randomSet.get(i).name());
            if (i < randomSet.size() - 1) {
                System.out.print(", ");// Add a comma and space for all elements except the last one
            }
            addNumberTypeElement(randomSet.get(i)); //a random set that is display in the displaySubMenu
        }
}





    //---------------------------------------------------------------------------


    public void addNumberTypeElement(NumberType element) {
        if (!randomSet.contains(element)) {
            randomSet.add(element);
        }
    }

    //public ArrayList<NumberType> getSetContents()
    public NumberType[] getSetContents() {
       // return randomSet;
        return randomSet.toArray(new NumberType[0]);
    }
}//============================================================================================================

public class A1_d {//============================================================================================================
    private static Scanner scanner = new Scanner(System.in);
    private static Set yourSet = new Set();

    public static void main(String[] args) {
        displayNumberTypeInfo();

        while (true) {
            displayMenu();
            String input = scanner.next();
            switch (input) {
                case "0":
                    System.out.println("=========================");
                    System.out.println("Properties of set");
                    displaySubMenu();
                    break;
                case "1":
                    System.out.println("=========================");
                    System.out.println("Union example");
                    break;
                case "2":
                    System.out.println("=========================");
                    System.out.println("Intersection example");
                    break;
                case "3":
                    System.out.println("=========================");
                    System.out.println("Subset example");
                    break;
                case "4":
                    System.out.println("=========================");
                    System.out.println("Difference example");
                    break;
                case "5":
                    System.out.println("=========================");
                    System.out.println("Complement example");
                    break;
                case "6":
                    System.out.println("=========================");
                    System.out.print("Sets equality example");
                    break;
                case "7":
                    System.out.println("=========================");
                    System.out.println("Distributive Law 1");
                    break;
                case "8":
                    System.out.println("=========================");
                    System.out.println("Distributive Law 2");
                    break;
                case "9":
                    System.out.println("Quit");
                    System.exit(0);//shutdown hook
                    break;
                default:
                    System.out.println("Unrecognized option");
                    break;
            }
        }
    }
//---------------------------------------------------------------------------


    private static void displayNumberTypeInfo() {
        System.out.printf("Universal set Info\nNumberType   Number   French   Bahasa   Spanish\n");
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
        System.out.println("Here is an example of set");
        System.out.print("A = {");
        yourSet.printAndAddRandomSet();
        System.out.println(" }");

        System.out.println("All elements in set are distinct and in random order");
        System.out.println();

        while (true) {
            System.out.println("1. Add an element");
            System.out.println("2. Check an element");
            System.out.println("3. Cardinality");
            System.out.println("4. Number Info format");
            System.out.println("9. Go back to Main Menu");
            System.out.print("Your option:");
            String input2 = scanner.next();

            switch (input2) {
                case "1":
                    System.out.println("Add an element");
                    getAnElement();
                    break;
                case "2":
                    System.out.println("Check an element");
                    break;
                case "3":
                    System.out.println("Cardinality");
                    break;
                case "4":
                    System.out.println("Number Info format");
                    break;
                case "9":
                    System.out.println("Back to the main menu");
                    return;
                default:
                    System.out.println("Unrecognized option");
                    break;
            }
        }
    }
    //---------------------------------------------------------------------------

    private static void getAnElement() {
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
            yourSet.addNumberTypeElement(numberType);
            System.out.println("Element added to the set: " + numberType.getNumber());
            System.out.println("Set contents: " + Arrays.toString(yourSet.getSetContents())); //Array.toString convert an array to string, more readable for userm and solve issue of the cryptic
            System.out.println("===============");
        } else {
            System.out.println("Invalid input");
        }
    }
    //---------------------------------------------------------------------------




}//============================================================================================================



