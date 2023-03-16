import java.awt.*;
import java.util.Random;

public class ServiceDelivery implements Runnable {
    private final Deliverybox deliverybox;

    private final int deliveryProbs =90;


    public ServiceDelivery(Deliverybox deliverybox) {
        this.deliverybox = deliverybox;
        (new Thread(this)).start();
    }

    @Override
    public void run() {

            while (ServiceTick.ticks < Main.TICK_MAX) {

                //System.out.println("1 TICK PASSED  |  " + ticks + " / 1000 TICKS");

                // add 10 books
                int rdm = new Random().nextInt(100);

                if (rdm > deliveryProbs) {
                    int cpt;
                    synchronized (deliverybox) {
                    Book book;
                    for (cpt = 0; cpt < 10; cpt++) {
                        int randomInt = new Random().nextInt(Main.bookSection.values().length);
                        Main.bookSection randomSection = Main.bookSection.values()[randomInt];
                        book = new Book(randomSection);
                        this.deliverybox.addBook(book);
                    }
                    System.out.println("THREAD ID :"+ Thread.currentThread().getId() + ". Delivery of 10 books arrived !");
                    System.out.println("THREAD ID :"+ Thread.currentThread().getId() +". Stock count : " + this.deliverybox.getStock());
                    deliverybox.notifyAll();
                }
                try {
                    Thread.sleep(Main.TIME_TICK_SIZE * 10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
