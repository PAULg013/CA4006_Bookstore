import java.util.ArrayList;
import java.util.Random;

public class Section {
    // Attributes
    private Main.bookSection type;
    private ArrayList<Book> books;

    // Constructor
    public Section(Main.bookSection type, ArrayList<Book> books) {
        this.type = type;
        this.books = books;
    }

    public Book getBook() {
        if (books.size() == 0)
            return null;
        int randomInt = new Random().nextInt(books.size());
        Book selectedBook = this.books.get(randomInt);
        this.books.remove(selectedBook);
        return selectedBook;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
