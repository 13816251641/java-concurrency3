package condition;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther ljn
 * @Date 2020/2/29
 * 多生产 多消费者模式 一定要用while和notifyAll
 */
public class ConditionExample2 {

    private Lock lock = new ReentrantLock();

    private Condition p = lock.newCondition();

    private Condition c = lock.newCondition();

    private LinkedList<Long> list = new LinkedList<>();

    private void produce(){
        lock.lock();
        try{
            while(list.size()>100){
                try {
                    p.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long time = new Random().nextInt();
            System.out.println(Thread.currentThread().getName()+":"+time);
            list.addFirst(time);
            c.signalAll();
        }finally {
            lock.unlock();
        }
    }

    private void consume(){
        lock.lock();
        try{
            while(list.isEmpty()){
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Long l = list.removeFirst();
            System.out.println(Thread.currentThread().getName()+":"+l);
            p.signalAll();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ConditionExample2 example2 = new ConditionExample2();
        new Thread(()->{
            while (true){
                example2.produce();
            }
        },"p1").start();
        new Thread(()->{
            while (true){
                example2.produce();
            }
        },"p2").start();
        new Thread(()->{
            while (true){
                example2.consume();
            }
        },"c1").start();
        new Thread(()->{
            while (true){
                example2.consume();
            }
        },"c2").start();

    }



}
