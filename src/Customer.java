
import java.util.ArrayList;

public class Customer {

    private ArrayList<Book> books;

    public Customer() {
        this.books = new ArrayList<>();
    }

    public void setBooks(Book book) {
        this.books.add(book);
    }
}
