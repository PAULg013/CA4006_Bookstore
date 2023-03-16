import java.util.ArrayList;
import java.util.Random;

public class ServiceAssistant implements Runnable {

    private final Assistant assistant;

    public ServiceAssistant(Assistant assistant) {
        this.assistant = assistant;
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        Deliverybox deliverybox = Deliverybox.getInstance();
        int time_to_wait = 0;
        while (ServiceTick.ticks < Main.TICK_MAX) {
            synchronized (deliverybox) {
                //pick up books from deliverybox
                while (deliverybox.isEmpty() == false && (assistant.getBookCarried() < Assistant.nbBookMax)) {
                    Book book = deliverybox.removeBook();
                    this.assistant.pickUp(book);
                    System.out.println("THREAD ID : "+ Thread.currentThread().getId() + ". assistant " + this.assistant + ")  took " + book + " from deliverybox");
                }
            }
            // if assistant carry books
            if (this.assistant.getBookCarried() != 0) {
                //tidy books
                for (Main.bookSection type : Main.bookSection.values()) {
                    if (assistant.isbooks(type) == true) {
                        // walk until section
                        System.out.println("THREAD ID : "+ Thread.currentThread().getId() + ". Assistant (" + this.assistant + ")  walk until " + type + " section");
                        time_to_wait = 10 * Main.TIME_TICK_SIZE +  Main.TIME_TICK_SIZE  * assistant.getBookCarried();
                        try {
                            Thread.sleep(time_to_wait);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        // tidy up books
                        System.out.println("THREAD ID : "+ Thread.currentThread().getId() + ". Assistant (" + this.assistant + ")  tidy books in  " + type + " section");
                        ArrayList<Book> books = assistant.tidyUp(type);
                        synchronized (Main.bookstore){
                            Main.bookstore.setBooks(type, books);
                        }
                        time_to_wait = 1 * books.size();
                        try {
                            Thread.sleep(time_to_wait);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                System.out.println("THREAD ID : "+ Thread.currentThread().getId() + ". Assistant (" + this.assistant + ")  return to delivery");
                time_to_wait = 10;
                try {
                    Thread.sleep(time_to_wait);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("THREAD ID : "+ Thread.currentThread().getId() + ". Assistant (" + this.assistant + ") is waiting for a delivery");
                try {
                    Thread.sleep(Main.TIME_TICK_SIZE*100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
