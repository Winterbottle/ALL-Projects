//Name: Ho Ka Yan Jeslyn
//UOW ID: 8535383
//Assignment 2 PartB


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Queue<T> {
    private final Object[] elements;
    private int front, rear;
    int size;

    public Queue(int max) {
        elements = new Object[max]; //an array with specified max capacity
        front = rear = size = 0;
    }

    public void enqueue(T item) {
        if (!isFull()) {
            elements[rear] = item; //insert items at the rear index of the array
            rear = (rear + 1) % elements.length; //update the rear pointer
            size++;
        }
    }

    public T dequeue() {
        if (!isEmpty()) {
            T item = (T) elements[front]; //retrieve the item at the front index of array
            front = (front + 1) % elements.length; //update the front pointer
            size--;
            return item;
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }
}

public class QueueSim {
    static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        int simulationMinutes;//=15;

        System.out.print("Please enter the total minutes to run: ");
        simulationMinutes = console.nextInt();
        System.out.println(" ");

        int processingMailAveRate = 20;
        int msgArrivalAveRate = 30;

        Queue<String> mailboxQueue = new Queue<>(simulationMinutes * msgArrivalAveRate);    //// building a queue to mimic an email mailbox with a starting capacity that will allow it to hold the expected number of messages
        Random random = new Random();

        int totalMsgProcessed = 0;
        int totalMsgSent = 0;
        int totalMsgRequeued = 0;
        int totalMsgInQueue = 0;
        
        Map<String, Integer> emailMsgSentStatistic = new HashMap<>();
        Map<String, Integer> emailMsgSentFail = new HashMap<>();

        for (int minute = 1; minute <= simulationMinutes; minute++) {
            //Email arrival
            for (int i = 0; i < msgArrivalAveRate; i++) {
                if (!mailboxQueue.isFull() && random.nextDouble() < 0.5) { //50% sent 50% fail	//random.nextDouble() return 0.1 to 1
                    mailboxQueue.enqueue("Message " + (totalMsgProcessed + 1));// Enqueueing a new message
                    totalMsgProcessed++;
                }
            }

            //Processing mail
            for (int i = 0; i < processingMailAveRate && !mailboxQueue.isEmpty(); i++) {
                String msg = mailboxQueue.dequeue();//dequeue up to 20 messages and send them
                if (random.nextDouble() < 0.25) { //25% of the messages in the queue cannot be sent

                    mailboxQueue.enqueue(msg);//Cannot be sent, put it back at the end of the queue or enqueue it.
                    totalMsgRequeued++;
                    Integer numSentAttempt = emailMsgSentFail.get(msg); // Getting the number of failed attempts
                    if(numSentAttempt != null)
                    	numSentAttempt++;
                    else
                    	numSentAttempt = 1; //First time fail

                    emailMsgSentFail.put(msg, numSentAttempt);
                }
                else {
                    Integer numSentAttempt = emailMsgSentFail.get(msg);

                    if(numSentAttempt != null)
                    	numSentAttempt++;
                    else
                    	numSentAttempt = 1;//Sent out in first attempt

                    emailMsgSentStatistic.put(msg, numSentAttempt);// Updating the successful attempts map
                    totalMsgSent++;
                }
            }
            totalMsgInQueue +=  mailboxQueue.size;
        }

        //Print the statistics
        System.out.println("Total number of messages processed                     : " + totalMsgProcessed);	//Answer 1
        
        System.out.printf("Average arrival rate                                   : %.2f\n", (double) totalMsgProcessed / simulationMinutes);//Answer 2
        System.out.printf("Average number of messages sent per minute             : %.1f\n", (double) totalMsgSent / simulationMinutes);//Answer 3
        System.out.printf("Average messages in the queue per minute               : %.2f\n", (double) totalMsgInQueue / simulationMinutes);//Answer 4
        
        //Answer 5, store the number of messages sent on each attempt
        Map<Integer, Integer> attempttatistics = new HashMap<>();

        for (int numSentAttempt : emailMsgSentStatistic.values()) {
            // Update the attemptStatistics map that shows the number of unsuccessful attempts
            attempttatistics.put(numSentAttempt, attempttatistics.getOrDefault(numSentAttempt, 0) + 1);
        }

        //Calculate the number of unique attempt
        for (int attempt : attempttatistics.keySet()) {
            int count = attempttatistics.get(attempt);

            if(attempt == 1)
                System.out.println("Number of messages sent on " + attempt +"st attempt                 : " + count);
            else if(attempt == 2)
                System.out.println("Number of messages sent on " + attempt +"nd attempt                 : " + count);
            else if(attempt == 3)
                System.out.println("Number of messages sent on " + attempt +"rd attempt                 : " + count);
            else
                System.out.println("Number of messages sent on " + attempt +"th attempt                 : " + count);
        }


        System.out.printf("Average number of times messages had to be requeued    :  %.2f", (double) totalMsgRequeued / totalMsgProcessed); //Answer 6
    }
}

