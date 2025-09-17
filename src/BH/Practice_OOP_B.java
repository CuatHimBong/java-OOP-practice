package BH;
import java.util.ArrayList;

public class Practice_OOP_B {
    static class Book {
        private String title;
        private String author;
        private int year;
        private Boolean isBorrowed;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public Boolean getIsBorrowed() {
            return isBorrowed;
        }

        public void setIsBorrowed(Boolean isBorrowed) {
            this.isBorrowed = isBorrowed;
        }

        Book(String title, String author, int year) {
            setTitle(title);
            setAuthor(author);
            setYear(year);
            setIsBorrowed(false);
        }

        public void getInfo() {
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Release year: " + year);
            System.out.println("Is borrowed: " + isBorrowed);
        }
    }

    interface Library {
        void addBook(Book book);
        void borrowBook(String title) throws LibraryException;
        void returnBook(String title) throws LibraryException;
        void listAvailableBooks();
    }

    static class LibraryException extends Exception {
        public LibraryException(String msg) {
            super(msg);
        }
    }

    static class LibraryManager implements Library {
        ArrayList<Book> books;

        LibraryManager() {
            books = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public void borrowBook(String title) throws LibraryException {
            Boolean isFound = false;

            for (int i = books.size() - 1; i >= 0; i--) {
                if (books.get(i).getTitle().equals(title)) {
                    isFound = true;
                    if (books.get(i).getIsBorrowed()) {
                        throw new LibraryException("This book is borrowed.");
                    }
                    else {
                        books.get(i).setIsBorrowed(true);
                        return;
                    }
                }
            }
            if (!isFound) {
                throw new LibraryException("This book isn't found.");
            }
        }

        public void returnBook(String title) throws LibraryException {
            Boolean isFound = false;

            for (int i = books.size() - 1; i >= 0; i--) {
                if (books.get(i).getTitle().equals(title)) {
                    isFound = true;
                    if (books.get(i).getIsBorrowed()) {
                        books.get(i).setIsBorrowed(false);
                        return;
                    }
                    else {
                        throw new LibraryException("This book wasn't borrowed.");
                    }
                }
            }
            if (!isFound) {
                throw new LibraryException("This book isn't found.");
            }
        }

        public void listAvailableBooks() {
            for (int i = 0; i < books.size(); i++) {
                books.get(i).getInfo();
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) {
        Library myLibrary = new LibraryManager();

        myLibrary.addBook(new Book("My life", "John Smitt", 1995));
        myLibrary.addBook(new Book("Harry Potter", "J. K. Rowling", 1997));
        myLibrary.addBook(new Book("My Little Prince", "Antoine de Saint-Exupery", 1943));
        myLibrary.listAvailableBooks();

        try {
            myLibrary.borrowBook("Harry Potter");
            myLibrary.borrowBook("My Little Prince");
            myLibrary.returnBook("Harry Potter");
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("");
        }

        myLibrary.listAvailableBooks();
    }
}