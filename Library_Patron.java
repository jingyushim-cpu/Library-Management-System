import java.util.HashSet;
import java.util.Set;

/*
Creator: Valor Guzman
Due: Dec 3rd
Inputs include: String for Patron Name and Books being Checked out
Outputs include: Patron Name, Patron ID, and Books being Checked out
Uses include: Creating a Library Patron's profile including their Name and ID as well as creating the HashSet that
    holds which books they checked out.
*/

//Class for Patron Information
public class Library_Patron {
    private String PatName;
    private int PatID;
    private Set<String> Checkout = new HashSet<>();
//Setting and Getting Patron name
    public void SetPatName(String name) { PatName = name;}

    public String GetPatName(){ return PatName; }
//Setting and Getting Patron ID
    public void SetPatID(int ID){ PatID = ID; }

    public int GetPatID(){ return PatID; }
//Printing Patron Info
    public void PrintPat(){
        System.out.println("Patron Name:" + PatName );
        System.out.println("Patron ID:" + PatID);
    }
//Print Books in Checkout HashSet list
    public void PrintCheckout(){ System.out.println(Checkout); }
//Add book to Checkout HashSet
    public void CheckingOut(String book){ Checkout.add(book); }
//Check in a book removing it from the Checkout HashSet
    public void CheckingIn(String book){ Checkout.remove(book); }
}
