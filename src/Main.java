import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {
    public static Bookstore bookstore = Bookstore.getInstance();
    public final static int assistantCapacity = 1;
    public final static int customerCapacity = 1;

    public final static int TIME_TICK_SIZE = 2000;

    public final static int TICK_MAX = 10;
    public enum bookSection {
        FICTION, HORROR, ROMANCE, FANTASY, POETRY, HISTORY
    }
    public static ArrayList<Assistant> assistants =new ArrayList<>();
    public static ArrayList<Customer> customers= new ArrayList<>();

    public static void main(String[] args) {
        buildLibrary(bookstore);

        employAssistant();

        attractCustomer();

        System.out.println("BOOKSTORE init with :"+ bookstore.getSections() + "!!!");

        new ServiceTick();

        new ServiceDelivery(Bookstore.getDeliveryBoxInstance());

        for(Assistant assistant: assistants){
            new ServiceAssistant(assistant);
        }
        for(Customer customer : customers){
            new ServiceCustomer(customer);
        }
    }
    public static void buildLibrary(Bookstore bookstore){
            //create type for books
            HashMap< bookSection, ArrayList<Book>> books = new HashMap<>();
            for (Main.bookSection type: Main.bookSection.values()){
                books.put(type, new ArrayList<>());
            }

            //create books
            int cpt;
            for(cpt=0;cpt<10;cpt++){
                int randomInt = new Random().nextInt(bookSection.values().length);
                bookSection randomSection = bookSection.values()[randomInt];
                books.get(randomSection).add(new Book(randomSection));
            }
            //create sections
            HashMap<Main.bookSection, Section>  sections = new HashMap<>();
            for (bookSection type: bookSection.values()){
                sections.put(type,(new Section(type, books.get(type))));
            }
            bookstore.setSection(sections);
        }
        public static void employAssistant(){
            for(int cpt=0;cpt<assistantCapacity;cpt++){
                assistants.add(new Assistant());
            }
        }
        public static void attractCustomer(){
            for(int cpt=0;cpt<customerCapacity;cpt++){
                customers.add(new Customer());
            }
        }
    }

