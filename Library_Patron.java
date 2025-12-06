/*
Program name: Library_Patron.java
Creator: Valor Guzman, Jin-gyu Shim
Date: 12/03/2025
Input: requests user for library patron na,e patron ID and book titles
        for checkout and check in
Output: displays patron information and books that are checked out. 
Description: This class defines a library patron. It maintains name, ID,
        and a HashSet of books checked out.
*/

import java.util.HashSet;
import java.util.Set;

public class Library_Patron implements Comparable<Library_Patron>{
    private String PatName;
    private int PatID;
    private Set<String> Checkout = new HashSet<>();


    /*
        Default constructor
        precondition: none
        postcondition: initializes all data members to default values
     */
    public Library_Patron(){
        this.PatName = "";
        this.PatID = 0;
    }

    /*
        Overloaded constructor
        precondition: none
        postcondition: initializes all data members with given values
     */
    public Library_Patron(String PatName, int PatID){
        this.PatName = PatName;
        this.PatID = PatID;
    }

    /*
        Prints Patron's information
        precondition: data members contain valid values
        postcondition: prints patron's name and ID
     */
    public void PrintPat(){
        System.out.println("Patron Name: " + PatName );
        System.out.println("Patron ID: " + PatID);
    }

    /*
        Prints patron's name and books he checked out
        precondition: Checkout HashSet contains the book title if it was checked out
        postcondition: prints patron's name, and books that were checked out if any
     */
    public void PrintCheckout(){
        if(Checkout.isEmpty()){
            System.out.println(PatName + ": No books are checked out");
        }
        else{
            System.out.println(PatName + " has the books:");
            for (String book : Checkout){
                System.out.println(book);
            }
        }
    }

    /*
        Compares this patron to otherPatron by name
        precondition: otherPatron has PatName initialized
        postcondition: returns integer indicating alphabetical order
     */
    @Override
    public int compareTo(Library_Patron otherPatron){
        return this.PatName.compareTo(otherPatron.PatName);
    }

    /*
        Adds a book to the patron's Checkout HashSet
        precondition: book is not null
        postcondition: adds book to the Checkout HashSet.
     */
    public void CheckingOut(String book){ Checkout.add(book); }

    /*
        Removes a book from the patron's Checkout HashSet
        precondition: the patron's Checkout HashSet contains the book
        postcondition: removes book from the Checkout HashSet
     */
    public void CheckingIn(String book){ Checkout.remove(book); }

    //Getters and setters
    public Set<String> getCheckout(){ return Checkout; }
    public String GetPatName(){ return PatName; }
    public int GetPatID(){ return PatID; }
    public void SetPatName(String name) { PatName = name;}
    public void SetPatID(int ID){ PatID = ID; }
}
