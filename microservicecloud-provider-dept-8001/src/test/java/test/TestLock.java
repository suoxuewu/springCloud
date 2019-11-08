package test;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        ForkJoinTask task = new RecursiveAction() {
            @Override
            protected void compute() {

            }
        };
        ForkJoinTask t = new RecursiveTask() {
            @Override
            protected Object compute() {
                return null;
            }
        };
        TestLock testLock = new TestLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.meth(Thread.currentThread());
            }
        },"t1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.meth(Thread.currentThread());
            }
        },"t2");
    }
    Lock ck = new ReentrantLock();
    private void meth(Thread thread){
        try {
            if(ck.tryLock()){
                ck.lock();
                System.out.println(thread.getName()+"获取了锁");
            }
        } catch (Exception e) {
            ck.unlock();
            e.printStackTrace();
        }
    }
    Lock lock = new Lock() {
        @Override
        public void lock() {

        }

        //用该锁的获得方式，如果线程在获取锁的阶段进入了等待，那么可以中断此线程，先去做别的事
        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {

        }

        @Override
        public Condition newCondition() {
            return null;
        }
    };
}
