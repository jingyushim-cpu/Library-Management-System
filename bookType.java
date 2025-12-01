import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class bookType {

    private static Set<Library_Patron> patrons = new HashSet<>();
    //prints the options for main menu
    public static void printMainOptions() {
        System.out.println(" ");
        System.out.println("Welcome to the Private Library");
        System.out.println("To make a selection enter the number and press enter");
        System.out.println("1: Print a List of Books Currently in the Library");
        System.out.println("2: To See if a Book is in the Library (search)");
        System.out.println("3: To Update the Number of Copies of a Book");
        System.out.println("4: Print a List of ALL Books in the Library");
        System.out.println("5: Enter Library Patron Information");
        System.out.println("6: Library Patron Checkout");
        System.out.println("7: Library Patron Checkout Return");
        System.out.println("8: List ALL Patrons & Books Checked OUt");
        System.out.println("9: Exit the program");
    }

    //prints the menu for the search options
    public static void printSearchOptions() {
        System.out.println(" ");
        System.out.println("Enter");
        System.out.println("1: To search the book by ISBN");
        System.out.println("2: To search the book by Title");
        System.out.println("3: To search the book by Author");

    }

    //opens txt file
    public static File openFile(String fileName){
        try{
            File file = new File(fileName);
            if (!file.exists()){
                throw new FileNotFoundException();
            }
            return file;
        }
        catch (FileNotFoundException e){
            System.err.println("Could not open input file" + e.getMessage());
            return null;
        }
    }

    //loads data into class for usage in code
    public static int loadBookData(File file, book[] books) {
        int count = 0;
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine() && count < books.length) {
                String title = fileScanner.nextLine();
                String isbn = fileScanner.nextLine();
                String publisher = fileScanner.nextLine();
                int yearPublished = Integer.parseInt(fileScanner.nextLine());
                double cost = Double.parseDouble(fileScanner.nextLine());
                int numberInStock = Integer.parseInt(fileScanner.nextLine());
                String author = fileScanner.nextLine();

                books[count] = new book(title, isbn, publisher, yearPublished, cost, numberInStock, author, false);
                count++;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    //prints the entire list of books in the file
    public static void optionPrint(book[] books, int actualCount){
        System.out.println("------------------------------");
        for (int i = 0; i < actualCount; i++){
            System.out.println("Title: " + books[i].getTitle());
            System.out.println("ISBN: " + books[i].getIsbn());
            System.out.println("Publisher: " + books[i].getPublisher());
            System.out.println("Year Published: " + books[i].getYearPublished());
            System.out.println("Author: " + books[i].getAuthor());
            System.out.println("Cost: $" + books[i].getCost());
            System.out.println("Number In Stock: " + books[i].getNumberInStock());
            System.out.println("------------------------------");
        }

    }

    //prints ONLY books in stock, books that are 0 stock do not print
    public static void optionStockPrint(book[] books, int actualCount){
        System.out.println("------------------------------");
        boolean foundInStock = false;
        for (int i = 0; i < actualCount; i++){
            if (books[i].getNumberInStock() > 0) {
                foundInStock = true;
                System.out.println("Title: " + books[i].getTitle());
                System.out.println("ISBN: " + books[i].getIsbn());
                System.out.println("Publisher: " + books[i].getPublisher());
                System.out.println("Year Published: " + books[i].getYearPublished());
                System.out.println("Author: " + books[i].getAuthor());
                System.out.println("Cost: $" + books[i].getCost());
                System.out.println("Number In Stock: " + books[i].getNumberInStock());
                System.out.println("------------------------------");
            }
        }
        if (!foundInStock) {
            System.out.println("No books currently in stock");

        }

    }

    //searches the file based on input
    public static int searchBooks(book[] books, int searchOption) {
        Scanner userScnr = new Scanner(System.in);
        int bookID = -1;
        String userSearch;

        if (searchOption == 1){
            System.out.println("Input the ISBN: ");
            userSearch = userScnr.nextLine().toLowerCase();
            for (bookID = 0; bookID < books.length; bookID++) {
                if (Objects.equals(books[bookID].getIsbn().toLowerCase(), userSearch)) {
                    return bookID;
                }
            }

        }
        else if (searchOption == 2){
            System.out.println("Input the Title: ");
            userSearch = userScnr.nextLine().toLowerCase();
            for (bookID = 0; bookID < books.length; bookID++) {
                if (Objects.equals(books[bookID].getTitle().toLowerCase(), userSearch)) {
                    return bookID;
                }
            }
        }
        else if (searchOption == 3){
            System.out.println("Input the Author (Last, First): ");
            userSearch = userScnr.nextLine().toLowerCase();
            for (bookID = 0; bookID < books.length; bookID++) {
                if (Objects.equals(books[bookID].getAuthor().toLowerCase(), userSearch)) {
                    return bookID;
                }
            }
        }
        return bookID;
    }

    //only prints a single book file based on search function
    public static void printSingle(book[] books, int listNumber){
        System.out.println("------------------------------");
        System.out.println("Title: " + books[listNumber].getTitle());
        System.out.println("ISBN: " + books[listNumber].getIsbn());
        System.out.println("Publisher: " + books[listNumber].getPublisher());
        System.out.println("Year Published: " + books[listNumber].getYearPublished());
        System.out.println("Author: " + books[listNumber].getAuthor());
        System.out.println("Cost: $" + books[listNumber].getCost());
        System.out.println("Number In Stock: " + books[listNumber].getNumberInStock());
        System.out.println("------------------------------");
    }

    //adds to the copies using the setNumberInStock() function in book.java
    public static void changeCopies(book[] books, int listNumber){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the amounted restocked: ");
        int newCopies = userInput.nextInt();
        books[listNumber].setNumberInStock(newCopies);
    }

    //looks for patron
    public static Library_Patron findPatron(String name){
        for(Library_Patron p : patrons){
            if(p.GetPatName().equals(name)){
                return p;
            }
        }
    }

    public static void bookCheckout(book[] books, int count){}
    public static void bookReturn(book[] books, int count){}
    public static void displayPatrons(){
        for(Library_Patron p : patrons){
            p.PrintPat();
            p.PrintCheckout();
        }
    }

    //main code that calls all these functions
    public static void main(String[] args){
        book[] bookList = new book[50];
        int bookCount = 0;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter library book file name");
        String fileName = scnr.nextLine();
        boolean validInput = false;

        File bookFile = openFile(fileName);
        do {
            if (bookFile != null) {
                bookCount = loadBookData(bookFile, bookList);
                printMainOptions();
                try {
                    int userAction = scnr.nextInt();
                    validInput = true;
                    while (userAction != 9) {
                        if (userAction == 1) {
                            optionStockPrint(bookList, bookCount);
                            printMainOptions();
                            userAction = scnr.nextInt();
                        } else if (userAction == 2) {
                            int bookSearched1 = -1;
                            printSearchOptions();
                            userAction = scnr.nextInt();
                            if ((userAction == 1) || (userAction == 2) || (userAction == 3)) {
                                bookSearched1 = searchBooks(bookList, userAction);
                            } else {
                                System.out.println("Invalid Input, Try Again");
                                printSearchOptions();
                                userAction = scnr.nextInt();
                            }
                            printSingle(bookList, bookSearched1);
                            printMainOptions();
                            userAction = scnr.nextInt();

                        } else if (userAction == 3) {
                            int bookSearched2 = searchBooks(bookList, bookCount);
                            if ((userAction == 1) || (userAction == 2) || (userAction == 3)) {
                                bookSearched2 = searchBooks(bookList, userAction);
                            } else {
                                System.out.println("Invalid Input, Try Again");
                                printSearchOptions();
                                userAction = scnr.nextInt();
                            }
                            changeCopies(bookList, bookSearched2);
                            printMainOptions();
                            userAction = scnr.nextInt();
                        } else if (userAction == 4) {
                            optionPrint(bookList, bookCount);
                            printMainOptions();
                            userAction = scnr.nextInt();
                        } else if (userAction == 5) {
                            System.out.println("Enter library patron first and last name ");
                            String patName = scnr.nextLine();
                            System.out.println("Enter library patron ID ");
                            int patID = scnr.nextInt();
                            scnr.nextLine();
                            if(findPatron(patName) == null){
                                Library_Patron newPat = new  Library_Patron(patName, patID);
                                patrons.add(newPat);
                            }

                        } else if (userAction == 6) {
                            System.out.println("Enter library patron name ");
                            String patName = scnr.nextLine();
                            System.out.println("Enter book to check out ");
                            String bookName =  scnr.nextLine();


                        } else if (userAction == 7) {
                            System.out.println("Enter library patron name");
                            String patName = scnr.nextLine();
                            System.out.println("Enter book to check in");
                            String bookName =  scnr.nextLine();


                        } else if (userAction == 8) {
                            displayPatrons();
                        } else {
                            System.out.println("Invalid option input. Please enter a valid option.");
                            printMainOptions();
                            userAction = scnr.nextInt();
                        }
                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number from the menu.");
                    scnr.next();

                }
            }
        } while (!validInput);

        scnr.close();

    }

}
