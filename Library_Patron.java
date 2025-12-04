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
public class Library_Patron implements Comparable<Library_Patron>{
    private String PatName;
    private int PatID;
    private Set<String> Checkout = new HashSet<>();

    public Library_Patron(){
        this.PatName = "";
        this.PatID = 0;
    }

    public Library_Patron(String PatName, int PatID){
        this.PatName = PatName;
        this.PatID = PatID;
    }

    //Printing Patron Info
    public void PrintPat(){
        System.out.println("Patron Name:" + PatName );
        System.out.println("Patron ID:" + PatID);
    }

    //Print Books in Checkout HashSet list
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

    @Override
    public int compareTo(Library_Patron otherPatron){
        return this.PatName.compareTo(otherPatron.PatName);
    }

    //Add book to Checkout HashSet
    public void CheckingOut(String book){ Checkout.add(book); }

    //Check in a book removing it from the Checkout HashSet
    public void CheckingIn(String book){ Checkout.remove(book); }

    //Getters and setters
    public Set<String> getCheckout(){ return Checkout; }
    public String GetPatName(){ return PatName; }
    public int GetPatID(){ return PatID; }
    public void SetPatName(String name) { PatName = name;}
    public void SetPatID(int ID){ PatID = ID; }
}
