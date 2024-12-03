class SharedResource {
    private int counter = 0;

    synchronized void increment() {
        counter++;
        notify();
    }

    synchronized void waitForValue(int value) throws InterruptedException {
        while (counter < value) {
            wait();
        }
    }
}

public class _11_ThreadCommunicationExample {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(() -> {
            try {
                sharedResource.waitForValue(5);
                System.out.println("Counter reached 5!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedResource.increment();
                System.out.println("Incremented: " + i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}