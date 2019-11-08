package TestCas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestCountDownLatch {
    public static void main(String[] args) throws Exception{
        CountDownLatch downLatch = new CountDownLatch(8);
        downLatch.await();
        downLatch.countDown();
        Lock lock= new ReentrantLock();
    }

}
