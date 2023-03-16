import java.util.ArrayList;
import java.util.HashMap;

public final class Bookstore{
    // Attributes
    private static Bookstore instance;
    private static Deliverybox deliveryBox;
    private HashMap<Main.bookSection, Section> sections;


    // Methods
    public static Bookstore getInstance() {
        if (instance == null) {
            deliveryBox = Deliverybox.getInstance();
            instance = new Bookstore ();
        }
        return instance;
    }
    public void setSection(HashMap<Main.bookSection,Section> section) {
        this.sections = section;
    }
    public void setBooks(Main.bookSection type, ArrayList <Book> books) {
        this.sections.get(type).setBooks(books);
    }

    public HashMap<Main.bookSection, Section> getSections() {
        return sections;
    }
    public static  Deliverybox getDeliveryBoxInstance() {
        return deliveryBox;
    }

    public Book getSpecificBook (Main.bookSection type) {
        Section section = sections.get(type);
        return section.getBook();
    }
}
