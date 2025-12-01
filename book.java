// Creator: Madison Bryant
// 12/--/2025
// Notes: Adapted Programming Assignment 1 Code
//Creates the ability to store each book file when read from the txt.file

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
