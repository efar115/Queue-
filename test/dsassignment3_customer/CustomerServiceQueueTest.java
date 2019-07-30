package dsassignment3_customer;

import Exceptions.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Perrin Mele
 * @version 1.0
 */
public class CustomerServiceQueueTest {

    /**
     * Test of enqueue method, of class CustomerServiceQueue.
     */
    @Test
    public void testEnqueue() {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        //Ensures the queue starts empty.
        assertEquals(0, instance.size());
        Customer myCustomer = new Customer("Some Individual", "Some problem");
        // enqueue to large collection
        for (int i = 0; i < 100; i++) {
            assertEquals("Incorrect size. Make sure to incrementwhen adding: ", i, instance.size());
            instance.enqueue(myCustomer);
        }

        try {
            for (int i = instance.size(); i > 0; i--) {
                assertEquals(i, instance.size());
                instance.dequeue();
            }
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                fail("NullPointerException. Remember to set back pointer when enqueueing.");
            }
        }
    }

    /**
     * Test of dequeue method, of class CustomerServiceQueue.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDequeue() throws Exception {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        try {
            instance.dequeue();
            fail("Shouldn't get her");
        } catch (EmptyCollectionException e) {
            System.out.println("Excepted: gets into exception");
        }
        Customer c = new Customer("Antimony Carver", "Too many wolves");
        instance.enqueue(c);
        instance.enqueue(new Customer("Amiri", "Missing six bears"));
        instance.enqueue(new Customer("Lini", "Too many wolves"));
        assertEquals(3, instance.size());
        Customer d = instance.dequeue();
        assertEquals(2, instance.size());
        assertEquals(c, d);
    }

    /**
     * Test of first method, of class CustomerServiceQueue.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFirst() throws Exception {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        try {
            instance.dequeue();
            fail("Shouldn't get her");
        } catch (EmptyCollectionException e) {
            System.out.println("Excepted: gets into exception");
        }
        Customer c = new Customer("Antimony Carver", "Too many wolves");
        instance.enqueue(c);
        instance.enqueue(new Customer("Amiri", "Missing six bears"));
        instance.enqueue(new Customer("Lini", "Too many wolves"));
        assertEquals(3, instance.size());
        Customer d = instance.first();
        assertEquals(3, instance.size());
        assertEquals(c, d);
    }

    /**
     * Test of size method, of class CustomerServiceQueue.
     */
    @Test
    public void testSize() {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        assertEquals(0, instance.size());
        assertTrue(instance.isEmpty());
        instance.enqueue(new Customer("Judy Hopps", "Underappreciated"));
        instance.enqueue(new Customer("Tartuccio", "Meddling kids"));
        instance.enqueue(new Customer("Sora", "Donald won't heal"));
        assertEquals(3, instance.size());

    }

    /**
     * Test of isEmpty method, of class CustomerServiceQueue.
     */
    @Test
    public void testIsEmpty() {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        assertEquals(0, instance.size());
    }

    /**
     * Test of help method, of class CustomerServiceQueue.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testHelp() throws Exception {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        assertEquals(0, instance.size());
        assertTrue(instance.isEmpty());
        instance.enqueue(new Customer("Judy Hopps", "Underappreciated"));
        instance.enqueue(new Customer("Tartuccio", "Meddling kids"));
        instance.enqueue(new Customer("Sora", "Donald won't heal"));
        instance.enqueue(new Customer("Jessica Jones", "Most things"));
        instance.enqueue(new Customer("Velwyn Benirus", "Haunted by ghosts"));
        instance.enqueue(new Customer("Jak", "Daxter"));
        boolean helped;
        int sizeTracker = instance.size();
        for (int x = 0; x < instance.size(); x++) {
            helped = instance.help();
            if (helped) {
                //Success (true) results in size decrementing
                assertEquals(sizeTracker - 1, instance.size());
                sizeTracker--;
            } else {
                //Failure (false) results in size remaining the same
                assertEquals(sizeTracker, instance.size());
            }
        }
        while (!instance.isEmpty()) {
            instance.help();
        }
        //Ensures that eventually, the queue empties
        assertEquals(0, instance.size());
    }

    /**
     * Test of increasePriority method, of class CustomerServiceQueue.
     */
    @Test
    public void testIncreasePriority() {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        Customer[] start = new Customer[16];
        //This represents what the queue will look like at first
        start[0] = new Customer("Rose Lalonde", "Stuck at home");
        start[1] = new Customer("Adrianne Avenicci", "Can't make enough armor");
        start[2] = new Customer("Amiri", "Missing six bears");
        start[3] = new Customer("John Egbert", "Stuck at home");
        start[4] = new Customer("J'zargo", "Needs more magicka");
        start[5] = new Customer("Tassadar", "Must construct additional pylons");
        start[6] = new Customer("Antimony Carver", "Too many wolves");
        start[7] = new Customer("Angela Ziegler", "Underappreciated");
        start[8] = new Customer("Estovian Lozarov", "Too many wolves");
        start[9] = new Customer("Lini", "Too many wolves");
        start[10] = new Customer("Eldor Olenvis", "Meddling kids");
        start[11] = new Customer("Cayden Cailean", "Impulsive gambling");
        start[12] = new Customer("Judy Hopps", "Underappreciated");
        start[13] = new Customer("Jessica Jones", "Most things");
        start[14] = new Customer("Jak", "Daxter");
        start[15] = new Customer("Farkas", "Too many wolves");

        Customer priority1 = new Customer("VIP", "Too many wolves");

        Customer[] aftP1 = new Customer[16];
        //This represents what the queue will look like after the method
        aftP1[0] = new Customer("Rose Lalonde", "Stuck at home");
        aftP1[1] = new Customer("Adrianne Avenicci", "Can't make enough armor");
        aftP1[2] = new Customer("Amiri", "Missing six bears");
        aftP1[3] = new Customer("John Egbert", "Stuck at home");
        aftP1[4] = new Customer("J'zargo", "Needs more magicka");
        aftP1[5] = new Customer("Antimony Carver", "Too many wolves");
        aftP1[6] = new Customer("Tassadar", "Must construct additional pylons");
        aftP1[7] = new Customer("Estovian Lozarov", "Too many wolves");
        aftP1[8] = new Customer("Lini", "Too many wolves");
        aftP1[9] = new Customer("Angela Ziegler", "Underappreciated");
        aftP1[10] = new Customer("Eldor Olenvis", "Meddling kids");
        aftP1[11] = new Customer("Cayden Cailean", "Impulsive gambling");
        aftP1[12] = new Customer("Judy Hopps", "Underappreciated");
        aftP1[13] = new Customer("Jessica Jones", "Most things");
        aftP1[14] = new Customer("Farkas", "Too many wolves");
        aftP1[15] = new Customer("Jak", "Daxter");

        for (int i = 0; i < start.length; i++) {
            instance.enqueue(start[i]);
        }

        assertEquals(16, instance.size());
        try {
            instance.increasePriority(priority1);
        } catch (EmptyCollectionException e) {
            fail("Should not throw exception");
        }
        //Size should not change
        assertEquals(16, instance.size());

        try {
            for (int i = 0; i < aftP1.length; i++) {
                //Ensures that it is now in the correct order.
                assertEquals(aftP1[i].getName(), instance.dequeue().getName());
            }
        } catch (Exception e) {
            fail("Should not throw exception.");
        }

        //The queue should be empty before this try block begins
        try {
            instance.increasePriority(priority1);
            fail("Should throw exception");
        } catch (Exception e) {
            //Ensures that an exception is succesfully thrown
            assertTrue(e instanceof EmptyCollectionException);
        }

    }

    /**
     * Test of add method, of class CustomerServiceQueue
     */
    @Test
    public void testAdd() {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        Customer myCustomer = new Customer("Some Individual", "Some problem");
        assertEquals(0, instance.size());
        instance.add(0, myCustomer);
        //Ensures that adding results in incremented size
        assertEquals(1, instance.size());
        Customer myCustomer2 = new Customer("Another Customer", "And issue");
        instance.add(0, myCustomer2);
        assertEquals(2, instance.size());

        try {
            //Ensures that insertion can work at any point, pushing other elems
            assertEquals(instance.dequeue(), myCustomer2);
            assertEquals(instance.first(), myCustomer);
        } catch (Exception e) {
            fail("Should not throw an exception");
        }

        try {
            instance.add(2, myCustomer2);
            fail("Should throw an exception");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }

    /**
     * Test of remove method, of class CustomerServiceQueue
     */
    @Test
    public void testRemove() {

        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        Customer d = null;
        try {
            instance.remove(0);
            fail("Shouldn't get her");
        } catch (EmptyCollectionException e) {
            System.out.println("Excepted: gets into exception");
        }
        Customer c = new Customer("Antimony Carver", "Too many wolves");
        instance.enqueue(c);
        instance.enqueue(new Customer("Amiri", "Missing six bears"));
        instance.enqueue(new Customer("Lini", "Too many wolves"));
        Customer f = new Customer("Foggy Nelson", "Debt from Law School");
        instance.enqueue(f);
        instance.enqueue(new Customer("J'zargo", "Needs more magicka"));
        instance.enqueue(new Customer("Antimony Carver", "Too many wolves"));
        Customer a = new Customer("Tassadar", "Must construct additional pylons");
        instance.enqueue(a);
        assertEquals(7, instance.size());
        try {
            d = instance.remove(6);
        } catch (EmptyCollectionException e) {
            System.out.println("Excepted: gets into exception");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Excepted: gets into exception");
        }
        assertEquals(6, instance.size());
        assertEquals(a, d);

    }

    /**
     * Test of toString method, of class CustomerServiceQueue.
     */
    @Test
    public void testToString() {
        CustomerServiceQueue<Customer> instance = new CustomerServiceQueue<>();
        //Ensures that something is returned both when empty and when populated
        assertNotNull(instance.toString());
        instance.enqueue(new Customer("Foggy Nelson", "Debt from Law School"));
        assertNotNull(instance.toString());
    }
}
