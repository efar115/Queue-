/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsassignment3_customer;

/**
 *
 * @author pmele
 * @version 1.0
 */
public class Customer implements Comparable<Customer> {
    private String name;
    private String issueID;
    
    /**
     *
     * @param name for names
     * @param issueID 
     */
    public Customer(String name, String issueID) {
        this.name = name;
        this.issueID = issueID;
    }
    
    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name , for the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return issueID 
     */
    public String getIssueID() {
        return issueID;
    }

    /**
     *
     * @param issueID , new issueID
     */
    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    @Override
    public int compareTo(Customer o) {
        return issueID.compareTo(o.getIssueID());
    }
    
    @Override
    public String toString() {
        return "Name: " + name + " \tIssue: " + issueID;
    }
}
