package test;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConTest2 {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConTest2 conTest2 = new ConTest2();
        Consume consume = conTest2.new Consume();
        Porducer porducer = conTest2.new Porducer();
        consume.start();
        porducer.start();
        consume.interrupt();
        porducer.interrupt();
    }

    class Consume extends Thread{
        @Override
        public void run() {
            consume();
        }
        volatile boolean flag = true;
        private void consume() {
            while (true){
                lock.lock();
                try {
                    try {
                        while (queue.isEmpty()){
                            System.out.println("对列空等待数据");
                            notEmpty.await();
                        }
                        queue.poll();
                        notFull.signal();
                        System.out.println("从对列移走一个元素，对列剩余"+queue.size()+"个元素");
                    } catch (Exception e) {
                        e.printStackTrace();
                        flag = false;
                    }
                } finally {
                    lock.unlock();
                }

            }
        }
    }
    private class Porducer extends Thread{
        @Override
        public void run() {
            producer();
        }
        boolean flag = true;
        private void producer() {
            while (flag){
                lock.lock();
                try {
                    try {
                        while (queue.size() == queueSize){
                            System.out.println("对列已满等待有剩余空间");
                            notFull.await();
                        }
                        queue.offer(1);
                        notEmpty.signal();
                        System.out.println("向对列中插入一个元素，剩余对列空间"+(queueSize - queue.size()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        flag = false;
                    }
                } finally {
                    lock.unlock();
                }

            }

        }
    }

}
