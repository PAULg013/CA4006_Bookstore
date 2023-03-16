public class ServiceTick implements Runnable {
    public static int ticks = 0;

    public ServiceTick() {
        (new Thread(this)).start();
    }

    @Override
    public void run() {

        synchronized (this){
            while (ticks < Main.TICK_MAX) {
                try {
                    Thread.sleep(Main.TIME_TICK_SIZE);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ticks += 1;
                System.out.println("THREAD ID : "+ Thread.currentThread().getId() + ". TICK PASSED  |  " + ticks + " / " + Main.TICK_MAX+ " TICKS");
                notifyAll();
            }

        }
    }
}
