/* Creator: Madison Bryant
Co-Creator: Valor Guzman
12/--/2025
Notes: Adapted Programming Assignment 1 Code for Program 3
Creates the ability to store each book file when read from the txt.file, take Patron
information, store information in Hash Sets, and use of a menu to call information stored
in the Hash Set.

Assumed left to Impliment:
5. Create a hash set to represent all books currently checked out
7. Add basic functions and menu choices:
    a. Add a library patron. If the library patron already exists, display a message and
do not add the duplicate
    b. Check out a book:
▪ Request the library patron name wishing to check out the book. If the
library patron is not in the existing list, add the patron. Request a book
title and search for the book to check out. If the book is found and is not
checked out by another library patron, allow check out by the current
library patron. Also add this book to a general set of books checked out
    c . Check in a book:
▪ Request the library patron name wishing to check in the book. If the
library patron is not in the existing list, display a message. Request the
book title to check in, remove the book from the library patron’s checked
out list and the general checked out list
    d. Print a list of all library patrons and all books checked out

*/

public class book {
    private String title;
    private String isbn;
    private String publisher;
    private int yearPublished;
    private double cost;
    private int numberInStock;
    private String author;
    private Boolean checkStatus;

    public book(String title, String isbn, String publisher, int yearPublished, double cost, int numberInStock, String author, Boolean checkStatus) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.cost = cost;
        this.numberInStock = numberInStock;
        this.author = author;
        this.checkStatus = checkStatus;
    }

    public book() {
        this.title = "";
        this.isbn = "";
        this.publisher = "";
        this.yearPublished = 0;
        this.cost = 0.0;
        this.numberInStock = 0;
        this.author = "";
        this.checkStatus = false;
    }

    public void setCheckStatus(boolean status){
        this.checkStatus = status;
    }
    //Used for the setNumberInStock to increase stock
    public void setNumberInStock(int newStockValue) {
        this.numberInStock += newStockValue;
    }

    //Getters
    public String getTitle(){
        return title;
    }
    public String getIsbn(){
        return isbn;
    }
    public String getPublisher(){
        return publisher;
    }
    public int getYearPublished(){
        return yearPublished;
    }
    public double getCost(){
        return cost;
    }
    public int getNumberInStock(){
        return numberInStock;
    }
    public String getAuthor(){
        return author;
    }
    public Boolean getCheckStatus(){
        return checkStatus;
    }
}
