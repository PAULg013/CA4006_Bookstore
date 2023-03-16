import java.util.Random;

public class ServiceCustomer implements Runnable {

    private final Customer customer;

    public ServiceCustomer(Customer customer) {
        this.customer = customer;
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        Book book;
        while (ServiceTick.ticks < Main.TICK_MAX) {
            int randomInt = new Random().nextInt(Main.bookSection.values().length);
            Main.bookSection randomSection = Main.bookSection.values()[randomInt];
            synchronized (Main.bookstore) {
                 book = Main.bookstore.getSpecificBook(randomSection);
            }
            if (book == null) {
                System.out.println("THREAD ID :" + Thread.currentThread().getId() + ". Customer(" + this.customer + ") is waiting for a book in " + randomSection);
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                customer.setBooks(book);
                System.out.println("THREAD ID :" + Thread.currentThread().getId() + ". Customer(" + this.customer + ") took " + randomSection);
            }
            try {
                Thread.sleep(Main.TIME_TICK_SIZE * 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
