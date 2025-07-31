//Name: Jeslyn Ho Ka Yan
//Tutorial group:121T05
//JDK version: 11.016
//Declaration: This is my work and I have not copied anyone's work


import java.util.ArrayList;

//Class to manage a list of wishes
class arrayWish {
    public static void wishes (){
        ArrayList<String> wish = new ArrayList<String>(); //an ArrayList that stores wishes
        wish.add("1. Academic Success");
        wish.add("2. Money");
        wish.add("3. More Money");
        System.out.println("I have "+ wish.size()+" wishes:");
        for (int i =0; i< wish.size();i++) {
            System.out.println(wish.get(i));
        }
    }

}

//Class to manage a list of hobbies
class arrayHobbies {
    public static void hobbies (){
        ArrayList<String> hobbies = new ArrayList<String>();//an ArrayList that stores hobbies
        hobbies.add("1. Listening to music");
        hobbies.add("2. Reading Weebtoon, a manga/comic app");
        hobbies.add("3. Sleeping");
        System.out.println("I have "+ hobbies.size()+" hobbies:");
        for (int i =0; i< hobbies.size();i++) {
            System.out.println(hobbies.get(i));
        }
    }

}
//Class to manage personal information
class PersonInfo{
    private String name;
    private String nationality;
    private String dateOfBirth;

    public PersonInfo(String name, String nationality, String dateOfBirth){
        this.name = name;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Nationality: " + nationality);
        System.out.println("Date of Birth: " + dateOfBirth);
    }

}


public class JeslynHo_Lab_1 {
    public static void main (String [ ] args){
        //Create a PersonInfo class instance and fill it with personal data.
        PersonInfo person = new PersonInfo("Jeslyn Ho Ka Yan", "Singaporean", "30 Dec 2004");

        person.displayInfo(); //Display the personal information

        arrayHobbies.hobbies(); //Call the hobbies method, which will display the list hobbies
        System.out.println(" ");

        arrayWish.wishes();//Call the hobbies method, which will display the list hobbies


    }


}




