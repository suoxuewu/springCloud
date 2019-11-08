package test;

public class TestThreadLocal {
    public static class MyRunnable implements Runnable{

        private ThreadLocal threadLocal = new ThreadLocal();
        @Override
        public void run() {
            threadLocal.set((int)(Math.random()*100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread th = new Thread(myRunnable);
        Thread th2 = new Thread(myRunnable);
        th.start();
        th2.start();
    }
}
