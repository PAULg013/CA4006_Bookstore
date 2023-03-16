import java.util.ArrayDeque;
public final class Deliverybox {
    // Attributes
    private static ArrayDeque<Book> booklist;
    private static Deliverybox instance;
    public static Deliverybox getInstance() {
        if (instance == null) {
            instance = new Deliverybox();
            booklist = new ArrayDeque<>();
        }
        return instance;
    }
    // Methods
    public void addBook(Book book) {
        this.booklist.push(book);
    }

    public boolean isEmpty() {
        if (booklist.size() == 0)
            return true;
        else
            return false;
    }

    public Book removeBook() {
        return this.booklist.pop();
    }

    public int getStock(){
        return this.booklist.size();
    }
    @Override
    public String toString() {
        return booklist.toString();
    }
}
