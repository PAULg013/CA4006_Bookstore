
import java.util.ArrayList;
import java.util.HashMap;

public class Assistant {

    public final static int nbBookMax = 10;
    private int bookCarried;
    private HashMap<Main.bookSection, ArrayList<Book>> books;

    public Assistant() {
        this.bookCarried = 0;
        this.books = new HashMap<>();
        for (Main.bookSection type: Main.bookSection.values()){
            this.books.put(type, new ArrayList<>());
        }
    }

    public int getBookCarried() {
        return bookCarried;
    }

    public void pickUp(Book book){
        this.books.get(book.getType()).add(book);
        this.bookCarried++;
    }

    public boolean isbooks(Main.bookSection type){
        if (this.books.get(type).size() == 0){
            return false;
        }else {
            return true;
        }
    }
    public int nbBooks(Main.bookSection type){
       return this.books.get(type).size() ;
    }
    public ArrayList<Book> tidyUp(Main.bookSection type){
        ArrayList<Book> booksToBeReturned = new ArrayList<>();
        for (Book book : this.books.get(type)) {
            booksToBeReturned.add(book);
        }
        for (Book book : booksToBeReturned) {
            this.books.get(type).remove(book);
            this.bookCarried--;
        }
        return booksToBeReturned;
    }


}
