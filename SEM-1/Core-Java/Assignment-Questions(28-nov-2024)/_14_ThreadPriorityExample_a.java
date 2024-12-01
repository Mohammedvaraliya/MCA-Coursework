class MyThread_a extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running.");
    }
}

public class _14_ThreadPriorityExample_a {
    public static void main(String[] args) {
        MyThread_a thread1 = new MyThread_a();
        MyThread_a thread2 = new MyThread_a();

        // Set priorities
        thread1.setPriority(Thread.MIN_PRIORITY); // Lowest priority
        thread2.setPriority(Thread.MAX_PRIORITY); // Highest priority

        thread1.start();
        thread2.start();
    }
}