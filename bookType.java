/*
    Program Name: bookType.java
    Author: Jin-gyu Shim
    Date: 12/03/2025
    Input: Requests user for a file name, a number for menu selection, book information,
           and patron information
    Output: displays menu selection, book information, search results, books in stock,
            patron information, books checked out
    Description: This class loads books from a file, allows user to search for books,
            update stocks, check out/in books, display all books, display all books in stock,
            display patron information, and display books checked out.

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class bookType {

    //An arrayList for all patrons in the library
    private static List<Library_Patron> patrons = new ArrayList<>();

    /*
        Prints the options for main menu
        precondition: none
        postcondition: outputs menu selection
     */
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
        System.out.println("8: List ALL Patrons & Books Checked Out");
        System.out.println("9: Exit the program");
    }

    /*
        prints the menu for the search options
        precondition: none
        postcondition: outputs submenu for search options
     */
    public static void printSearchOptions() {
        System.out.println(" ");
        System.out.println("Enter");
        System.out.println("1: To search the book by ISBN");
        System.out.println("2: To search the book by Title");
        System.out.println("3: To search the book by Author");

    }

    /*
        opens txt file
        precondition: fileName contains the right string value
        postcondition: file is opened and returned if file exits, if not, exception is thrown
                        and exception message is displayed
     */
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

    /*
        loads data into class for usage in code
        precondition: file is valid
        postcondition: returns the number of books, books array is
                    filled with objects with all data member initialized
     */
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

    /*
        prints the entire list of books in the file
        precondition: books array is filled with book objects.
        postcondition: outputs entire list of books, as well as checked out message if checked out
     */
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
            if(books[i].getCheckStatus()) System.out.println("----------Checked out---------");
            System.out.println("------------------------------");
        }

    }

    /*
        prints ONLY books in stock, books that are 0 stock or checked out do not print
        precondition: books array is filled with book objects
        postcondition: outputs book objects that are available for check out.
     */
    public static void optionStockPrint(book[] books, int actualCount){
        System.out.println("------------------------------");
        boolean foundInStock = false;
        for (int i = 0; i < actualCount; i++){
            if (books[i].getNumberInStock() > 0 && !books[i].getCheckStatus()) {
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

    /*
        searches for the book based on input
        precondition: books array is filled with book objects, searchOption contains a valid integer
        postcondition: if the book is found, bookID is returned, if not, returns -1
     */
    public static int searchBooks(book[] books, int searchOption) {
        Scanner userScnr = new Scanner(System.in);
        int bookID = -1;
        String userSearch;

        if (searchOption == 1){
            System.out.println("Input the ISBN: ");
            userSearch = userScnr.nextLine().toLowerCase();
            for (bookID = 0; bookID < books.length; bookID++) {
                if (Objects.equals(books[bookID].getIsbn().toLowerCase(), userSearch)
                && !books[bookID].getCheckStatus()) {
                    return bookID;
                }
            }

        }
        else if (searchOption == 2){
            System.out.println("Input the Title: ");
            userSearch = userScnr.nextLine().toLowerCase();
            for (bookID = 0; bookID < books.length; bookID++) {
                if (Objects.equals(books[bookID].getTitle().toLowerCase(), userSearch)
                && !books[bookID].getCheckStatus()) {
                    return bookID;
                }
            }
        }
        else if (searchOption == 3){
            System.out.println("Input the Author (Last, First): ");
            userSearch = userScnr.nextLine().toLowerCase();
            for (bookID = 0; bookID < books.length; bookID++) {
                if (Objects.equals(books[bookID].getAuthor().toLowerCase(), userSearch)
                && !books[bookID].getCheckStatus()) {
                    return bookID;
                }
            }
        }
        return -1;
    }

    /*
        only prints a single book file based on search function
        precondition: books[listNumber] is valid
        postcondition: outputs books[listNumber] information
     */
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

    /*
        adds to the copies using the setNumberInStock() function in book.java
        precondition: books[listNumber] exists
        postcondition: numberInStock of books[listNumber] is added by the input number from the user
     */
    public static void changeCopies(book[] books, int listNumber){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the amounted restocked: ");
        int newCopies = userInput.nextInt();
        books[listNumber].setNumberInStock(newCopies);
    }

    /*
        looks for patron
        precondition: parameter name is valid
        postcondition: returns Library_Patron object if found, returns null otherwise
     */
    public static Library_Patron findPatron(String name){
        for(Library_Patron p : patrons){
            if(p.GetPatName().equals(name)){
                return p;
            }
        }
        return null;
    }

    /*
        adds patron to patrons ArrayList if not already in the list
        precondition: newPat is valid
        postcondition: newPat is added to patrons ArrayList if not alreay, the list is sorted
     */
    public static void addPatron(Library_Patron newPat){
        if(findPatron(newPat.GetPatName()) == null){
            patrons.add(newPat);
            Collections.sort(patrons);
        }
    }

    /*
        checks out book if book is available
        if patron doesn't exist, adds patron to patrons ArrayList
        precondition: books array is filled with book objects
        postcondition: if book is available, book is checked out, if not, outputs the message
                       if patron doesn't exist, adds patron to patrons ArrayList.
     */
    public static void bookCheckout(book[] books, int count){
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter library patron name ");
        String patName = scnr.nextLine();
        System.out.println("Enter book to check out ");
        String bookName =  scnr.nextLine();

        Library_Patron pat = findPatron(patName);

        int bookID = 0;
        boolean sucess = false;
        //for loop to find the book
        for(int i = 0; i < count; i++){
            if(books[i].getTitle().equals(bookName)){
                System.out.println("Found the book");
            }
            if(books[i].getTitle().equals(bookName) && !books[i].getCheckStatus() && books[i].getNumberInStock() > 0){
                sucess = true;
                bookID = i;
                System.out.println("The book is in stock");
                break;
            }
        }

        //book is not available
        if(!sucess){
            System.out.println("The book is not in stock or not available...cannot complete action at this time");
            return;
        }

        //patron is not in the list
        if(pat == null){
            System.out.println("Enter library patron ID: ");
            int id = scnr.nextInt();
            scnr.nextLine();
            pat = new Library_Patron(patName, id);
            addPatron(pat);
        }
        //patron already exists
        else{
            System.out.println("Library Patron " + patName + " exists");
        }
        System.out.println("-------------------------------------");

        //book is available for checkout
        if(sucess){
            pat.CheckingOut(bookName);
            books[bookID].setCheckStatus(true);
            //books[bookID].setNumberInStock(-1);
        }
    }

    /*
        book is returned if it was checked out
        if patron doesn't exist or patron doesn't have the book, exits the function
        precondition: books array is filled with book objects
        postcondition: book is checked in if the patron had the book
                       if patron doesn't exist or doesn't have the book, outputs the message
     */
    public static void bookReturn(book[] books, int count){
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter library patron name ");
        String patName = scnr.nextLine();
        System.out.println("Enter book to check in ");
        String bookName =  scnr.nextLine();

        Library_Patron pat = findPatron(patName);

        //if patron doesn't exist
        if(pat == null){
            System.out.println("Library Patron " + patName + " does not exist");
            return;
        }
        else{
            System.out.println("Library Patron " + patName + " exists");
            //if patron doesn't have the book
            if(!pat.getCheckout().contains(bookName)){
                System.out.println("Patron does not have this book");
                return;
            }
            pat.CheckingIn(bookName);
            for(int i = 0; i < count; i++){
                if(books[i].getTitle().equals(bookName)){
                    books[i].setCheckStatus(false);
                    //books[i].setNumberInStock(1);
                    System.out.println(bookName + " checked in");
                    break;
                }
            }
        }
    }


    /*
        displays all patron using iterator
        precondition: patrons ArrayList has patrons
        postcondition: outputs patrons' names and book checked out
     */
    public static void displayPatrons(){
        Iterator<Library_Patron> iter = patrons.iterator();
        while(iter.hasNext()){
            Library_Patron p = iter.next();
            p.PrintPat();
            p.PrintCheckout();
            System.out.println();
        }
    }

    //main
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
                    scnr.nextLine();
                    validInput = true;
                    while (userAction != 9) {
                        if (userAction == 1) {
                            optionStockPrint(bookList, bookCount);
                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();
                        } else if (userAction == 2) {
                            int bookSearched1 = -1;
                            printSearchOptions();
                            int searchChoice = scnr.nextInt();
                            scnr.nextLine();
                            if(searchChoice < 1 || searchChoice > 3){
                                System.out.println("Invalid input");
                            }
                            else{
                                bookSearched1 = searchBooks(bookList, searchChoice);
                                if(bookSearched1 == -1){
                                    System.out.println("Book not found or checked out");
                                }
                                else{
                                    printSingle(bookList, bookSearched1);
                                }
                            }
                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();

                        } else if (userAction == 3) {
                            printSearchOptions();
                            int searchChoice = scnr.nextInt();
                            scnr.nextLine();

                            if(searchChoice < 1 || searchChoice > 3){
                                System.out.println("Invalid input");
                            }
                            else{
                                int bookSearched2 = searchBooks(bookList, searchChoice);
                                if(bookSearched2 == -1){
                                    System.out.println("Book not found or checked out");
                                }
                                else{
                                    changeCopies(bookList, bookSearched2);
                                }
                            }

                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();
                        } else if (userAction == 4) {
                            optionPrint(bookList, bookCount);
                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();
                        } else if (userAction == 5) {
                            System.out.println("Enter library patron first and last name ");
                            String patName = scnr.nextLine();
                            System.out.println("Enter library patron ID ");
                            int patID = scnr.nextInt();
                            scnr.nextLine();
                            if(findPatron(patName) == null){
                                Library_Patron newPat = new  Library_Patron(patName, patID);
                                addPatron(newPat);
                            }
                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();

                        } else if (userAction == 6) {
                            bookCheckout(bookList, bookCount);
                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();


                        } else if (userAction == 7) {
                            bookReturn(bookList, bookCount);
                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();


                        } else if (userAction == 8) {
                            displayPatrons();
                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();
                        } else {
                            System.out.println("Invalid option input. Please enter a valid option.");
                            printMainOptions();
                            userAction = scnr.nextInt();
                            scnr.nextLine();
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
