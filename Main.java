import java.util.Scanner;
class Book{
    int id;
    String title;
    String author;
    boolean status;
    Book(int id,String title,String author){   /*Initializing a constructor. */
        this.id=id;
        this.title=title;
        this.author=author;
        this.status=false;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isIssued() {
        return status;
    }
    public void setIssued(boolean status) {
        this.status=status;
    }
    /*%10b is used for boolean datatype. */
    public void display(){
    System.out.printf("%-6d %-25s %-20s %-10b%n",id,title,author,status); /*Using format specifiers for alignment purpose. */
    }   
}
class Library{
    final int total_books=100; /*Using final keyword to prevent modifiction. */
    /*Creating an array of books*/
    Book[] books = new Book[total_books];
    /*Declare count variable and initialize it to zero. */
    int count=0;
    /*Function to add books */
    public void addBook(Book book) {
        if(count>=total_books) {
            System.out.println("Library is full!! Cannot add more books.");
            return;
        }
        if(findIndexById(book.id)!=-1) {
            System.out.println("A book with this ID already exists!");
            return;
        }
        books[count]=book;
        count++; /*Incrementing the count */
        System.out.println("Book is added successfully!");
    }
    /*Function to display books */
        public void displayBooks() {
        if (count==0) {
            System.out.println("No books available in the library.");
            return;
        }
        System.out.printf("%-6s %-25s %-20s %-10s%n", "ID", "Title", "Author", "Status");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0;i < count; i++) {
            books[i].display();
        }
    }
    /*Searching for a book by using id .*/
    public void searchBook(int id) {
        int index=findIndexById(id);
        if (index==-1) {
            System.out.println("Book not found!");
            return;
        }
        System.out.println("Book found:");
        books[index].display();
    }
    /*Issue book by using id. */
    public void issueBook(int id){
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("Book not found!");
            return;
        }
        if (books[index].isIssued()) {
            System.out.println("This book is already issued.");
            return;
        }
        books[index].setIssued(true);
        System.out.println("Book issued successfully!");
    }
    public void returnBook(int id) {
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("Book not found!");
            return;
        }
        if (!books[index].isIssued()) {
            System.out.println("This book was not issued.");
            return;
        }

        books[index].setIssued(false);
        System.out.println("Book returned successfully!");
    }
    public void deleteBook(int id) {
        int index = findIndexById(id);
        if (index == -1) {
            System.out.println("Book not found!");
            return;
        }
        for (int i = index; i < count - 1; i++) {
            books[i] = books[i + 1];
        }
        books[count - 1] =null;
        count--;
        System.out.println("Book deleted successfully!");
    }
    public int findIndexById(int id) {
        for (int i = 0;i<count;i++) {
            if (books[i].getId()==id) return i;
        }
        return -1;
    }
}
public class Main {
    public static void main(String[] args) {
        Library l=new Library();
        Scanner sc=new Scanner(System.in);
        System.out.println("-----Welcome to Library-----");
        System.out.println("Please go through with the following menu:");
        /*Creating a while loop which continues as long as the condition is true. */
        while(true){
            System.out.println("\n===LIBRARY MENU===");
            System.out.println("1.Add book");
            System.out.println("2.Display books");
            System.out.println("3.Search book");
            System.out.println("4.Issue book");
            System.out.println("5.Return book");
            System.out.println("6.Delete book");
            System.out.println("7.Exit");
            System.out.print("Choose an option from the above choices: ");
            int choice;
            /*try,catch to handle the exceptions. */
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            /*Use switch case to select choices. */
            switch (choice) {
                case 1 -> {
                    int id;
                     try {
                        System.out.print("Enter ID: ");
                        id = Integer.parseInt(sc.nextLine().trim());
                    } 
                    catch (NumberFormatException e){
                        System.out.println("Invalid ID! Please enter a number.");
                        break;
                    }
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    l.addBook(new Book(id,title,author));

                }
                case 2 -> {
                    l.displayBooks();
                }
                case 3 ->
                {
                   int id;
                    try {
                        System.out.print("Enter ID to search: ");
                        id = Integer.parseInt(sc.nextLine().trim());
                    } 
                    catch (NumberFormatException e) {
                        System.out.println("Invalid ID! Please enter a number.");
                        break;
                    }
                    l.searchBook(id);
                }
                case 4 -> 
                {
                   int id;
                   try {
                    System.out.print("Enter ID to issue: ");
                    id = Integer.parseInt(sc.nextLine().trim());
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid ID! Please enter a number.");
                     break;
                }
                    l.issueBook(id);
                }
                case 5 ->
                {
                    int id;
                     try {
                        System.out.print("Enter ID to return: ");
                         id = Integer.parseInt(sc.nextLine().trim());
                    }
                     catch (NumberFormatException e) {
                        System.out.println("Invalid ID! Please enter a number.");
                        break;
                    }
                    l.returnBook(id);
                }
                case 6 -> {
                    int id;
                    try {
                        System.out.print("Enter ID to delete: ");
                        id = Integer.parseInt(sc.nextLine().trim());
                    } 
                    catch (NumberFormatException e) {
                        System.out.println("Invalid ID! Please enter a number.");
                        break;
                    }
                    l.deleteBook(id);
                }
                case 7 -> {
                    System.out.println("===THANK YOU===");
                    sc.close();
                    return;
                }
                default ->{
                 System.out.println("Invalid option, try again.");
                }
            }
        }
    }
}
