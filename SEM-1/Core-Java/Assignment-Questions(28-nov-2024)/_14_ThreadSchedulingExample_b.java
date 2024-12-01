class MyThread_b extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " with priority " + Thread.currentThread().getPriority());
    }
}

public class _14_ThreadSchedulingExample_b {
    public static void main(String[] args) {
        MyThread_b thread1 = new MyThread_b();
        MyThread_b thread2 = new MyThread_b();

        thread1.setPriority(9); // Set high priority
        thread2.setPriority(2); // Set low priority

        thread1.start();
        thread2.start();
    }
}