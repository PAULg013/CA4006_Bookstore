public class Book {
    // Attributes
    private Main.bookSection type;

    // Constructor
    public Book(Main.bookSection type) {
        this.type = type;
    }

    public Main.bookSection getType() {
        return type;
    }

    @Override
    public String toString() {
        return "book " + type;
    }
}
