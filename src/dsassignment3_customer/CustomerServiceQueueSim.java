/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsassignment3_customer;

import Exceptions.EmptyCollectionException;
import java.util.Random;

/**
 * A simulation class for the CustomerServiceQueue data structure.
 * This won't work until CustomerServiceQueue is finished!
 * @author Perrin Mele
 * @version 9-29-2018
 */
public class CustomerServiceQueueSim {

    public static void main(String[] args) {
        try {
            runSim();
        } catch (EmptyCollectionException ex) {
            System.out.println("SIMULATION FAILED: EmptyCollectionException");
        }
    }

    private static void runSim() throws EmptyCollectionException {
        Customer[] customers = createCustomers();
        CustomerServiceQueue<Customer> myQueue = new CustomerServiceQueue<>();
        Random ran = new Random();

        System.out.println("Adding random customers to the queue...");
        boolean used[] = new boolean[30];
        for (int i = 0; i < 20; i++) {
            int index = ran.nextInt(30);
            if (!(used[index])) {
                myQueue.enqueue(customers[index]);
                used[index] = true;
            }
        }
        System.out.println("Serving " + myQueue.size() + " customers.");
        //print out the playlist.
        System.out.println("The queue: \n");
        System.out.println(myQueue.toString());

        //handle some customers in order
        int timesHelped = 0;
        while (!myQueue.isEmpty()) {
            if (timesHelped >= 150) {
                System.out.println("Clearly this service isn't working...");
                System.out.println("(Unlikely result - check the randomness!)");
                break;
            }
            timesHelped++;
            System.out.print("Now helping: " + myQueue.first().getName()
                    + " with the issue: " + myQueue.first().getIssueID() + ".");
            Customer c = myQueue.first();
            if (myQueue.help()) {
                System.out.println(" We were able to solve the issue!");
            } else {
                System.out.println(" Uh oh, our help didn't work!"
                                 + " They got back in line!"
                                 + " Let's increase the priority...");
                myQueue.increasePriority(c);
            }
        }

        //clear out the queue
        System.out.println("Clearing out queue...");
        for (int i = 0; i < myQueue.size(); i++) {
            myQueue.dequeue();
        }

        if (myQueue.isEmpty()) {
            System.out.println("The queue is empty.");
        } else {
            System.out.println(myQueue.toString());
        }
    }

    private static Customer[] createCustomers() {
        Customer[] c = new Customer[30];
        c[0] = new Customer("Rose Lalonde", "Stuck at home");
        c[1] = new Customer("Adrianne Avenicci", "Can't make enough armor");
        c[2] = new Customer("Amiri", "Missing six bears");
        c[3] = new Customer("John Egbert", "Stuck at home");
        c[4] = new Customer("J'zargo", "Needs more magicka");
        c[5] = new Customer("Tassadar", "Must construct additional pylons");
        c[6] = new Customer("Antimony Carver", "Too many wolves");
        c[7] = new Customer("Dimitri", "Haunted by ghosts");
        c[8] = new Customer("Angela Ziegler", "Underappreciated");
        c[9] = new Customer("Estovian Lozarov", "Too many wolves");
        c[10] = new Customer("Gabriel Reyes", "Turned into a monster");
        c[11] = new Customer("Maxine Caulfield", "Time travel shenanigans");
        c[12] = new Customer("Sandra North", "Unprecedented racoon invasion");
        c[13] = new Customer("Lini", "Too many wolves");
        c[14] = new Customer("Eldor Olenvis", "Meddling kids");
        c[15] = new Customer("Sarah Kerrigan", "Turned into a monster");
        c[16] = new Customer("Cayden Cailean", "Impulsive gambling");
        c[17] = new Customer("Judy Hopps", "Underappreciated");
        c[18] = new Customer("Dave Strider", "Time travel shenanigans");
        c[19] = new Customer("Tartuccio", "Meddling kids");
        c[20] = new Customer("Sora", "Donald won't heal");
        c[21] = new Customer("Jessica Jones", "Most things");
        c[22] = new Customer("Velwyn Benirus", "Haunted by ghosts");
        c[23] = new Customer("Jak", "Daxter");
        c[24] = new Customer("Farkas", "Too many wolves");
        c[25] = new Customer("Talion", "Haunted by ghosts");
        c[26] = new Customer("Nepeta Leijon", "Not enough paint");
        c[27] = new Customer("Nick Wilde", "Accidental felony tax evasion");
        c[28] = new Customer("Matthew Murdock", "Far too selfless");
        c[29] = new Customer("The author", "Ran out of references");
        
        return c;
    }

}
