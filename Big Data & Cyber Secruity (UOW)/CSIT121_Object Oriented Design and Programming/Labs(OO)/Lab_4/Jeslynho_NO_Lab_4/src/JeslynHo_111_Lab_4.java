//Name: Jeslyn Ho Ka Yan
//Tutorial group:121T05
//JDK version: 11.016
//Declaration: This is my work and I have not copied anyone's work, I am willing to accept whatever penalty given to me.

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class JeslynHo_111_Lab_4 {
    static Scanner console = new Scanner(System.in);

    public static void main(String args[]) {
        int hr = getHours();
        int min = getMinutes();
        int sec = getSeconds();
        String str = getAmPm();

        print24HourTime(hr, min, sec, str);
    }

    public static int getHours() {
        do {
            try {

                int hour = 0;

                System.out.print("Enter hours: ");
                try {
                    hour = console.nextInt();
                } catch (Exception ex) {
                    throw new InvalidHrException();// Throw a custom exception for invalid input such as String
                }

                if (hour > 0 && hour <= 12) {//12 hours notation, only accept integer 1 to 12
                    return hour;
                }
                else {
                    throw new InvalidHrException();// Throw a custom exception for invalid input
                }
            } catch (InvalidHrException ex) {
                System.out.println(ex); //Display the error message
                console.nextLine();
            }
        } while (true);
    }

    public static int getMinutes() {
        do {
            try {
                int minute = 0;

                System.out.print("Enter minutes: ");
                try {
                    minute = console.nextInt();
                } catch (Exception ex) {
                    throw new InvalidMinException();// Throw a custom exception for invalid input such as String
                }

                if (minute >= 0 && minute < 60) {//12 hours notation, only accept integer from 0 to 59 minutes
                    return minute;
                } else {
                    throw new InvalidMinException();// Throw a custom exception for invalid input
                }
            } catch (InvalidMinException ex) {
                System.out.println(ex);//Display the error message
                console.nextLine();
            }
        } while (true);
    }

    public static int getSeconds() {
        do {
            try {
                int seconds = 0;

                System.out.print("Enter seconds: ");

                try {
                    seconds = console.nextInt();
                } catch (Exception ex) {
                    throw new InvalidSecException();// Throw a custom exception for invalid input such as String
                }

                if (seconds >= 0 && seconds < 60) { //12 hours notation, only accept integer from 0 to 59 seconds
                    return seconds;
                } else {
                    throw new InvalidSecException();// Throw a custom exception for invalid input
                }
            } catch (InvalidSecException ex) {
                System.out.println(ex);//Display the error message
                console.nextLine();
            }
        } while (true);
    }

    public static String getAmPm() {
        do {
            System.out.print("Enter AM or PM: ");

            String amPm = console.next();


            if ("AM".equalsIgnoreCase(amPm) ||  "PM".equalsIgnoreCase(amPm)) { //12 hours notation, only accept String AM or PM (ignorecase)
                return amPm;
            } else {
                System.out.println("The value must be AM or PM.");//Display the error message
                console.nextLine();
            }

        } while (true);
    }

    public static void print24HourTime(int hr, int min, int sec, String str) {
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ss a");//12 hours notation.
            Date date = parseFormat.parse(String.format("%02d:%02d:%02d %s", hr, min, sec, str));

            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");//24 hours notation.

            System.out.println(String.format("\n24 hour clock time: %s", displayFormat.format(date)));
        } catch (ParseException e) {
            System.out.println("Invalid time");
        }
    }

}

//====================================================
class InvalidHrException extends Exception {
    private String message;

    public InvalidHrException() {// Default constructor

    }
    public InvalidHrException(String str) {
        super(str);
        this.message = str;
    }

    public String getMessage() {
        return String.format("The value of hours must be between 0 and 12.");
    }

    public String toString() {
        return "The value of " + getMessage();
    }
}
//====================================================
class InvalidMinException extends Exception {
    private String message;

    public InvalidMinException() {// Default constructor

    }
    public InvalidMinException(String str) {
        super(str);
        this.message = str;
    }

    public String getMessage() {
        return String.format("The value of minutes must be between 0 and 60.");
    }

    public String toString() {
        return "The value of " + getMessage();
    }
}
//====================================================
class InvalidSecException extends Exception {
    private String message;

    public InvalidSecException() {// Default constructor
    }

    public InvalidSecException(String str) {
        super(str);
        this.message = str;
    }

    public String getMessage() {
        return String.format("The value of seconds must be between 0 and 60.");
    }

    public String toString() {
        return "The value of " + getMessage();
    }
}