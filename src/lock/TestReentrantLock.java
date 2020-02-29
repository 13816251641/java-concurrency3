package lock;

import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @Auther ljn
 * @Date 2020/2/24
 */
public class TestReentrantLock {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
       // test01();
       // test02();
       // test03();
        test04();







    }

    /*
     * 2个线程同时启动,利用ReentrantLock锁
     */
    private static void test01(){
        IntStream.range(0,2).forEach(i->{
            new Thread(){
                @Override
                public void run() {
                    canLock();
                }
            }.start();
        });
    }

    /*
       lock.lock的确会阻塞线程
     */
    private static void canLock(){
        lock.lock();//会阻塞,要放到try外面
        try {
            System.out.println(Thread.currentThread().getName()+" get lock and will do working");
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /*
       利用lock.lockInterruptibly(),可以打断block的线程
     */
    private static void test02() throws Exception{
        new Thread(()->{
            canInterrupt();
        }).start();
        Thread.sleep(1000);
        Thread t =new Thread(()->{
            canInterrupt();
        });
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }


    /*
        阻塞线程可以被打断
    */
    private static void canInterrupt(){
        try {
            lock.lockInterruptibly();//等待锁的时候可以被别的线程打断
            while (true){
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /*
        使用tryLock可以尝试获取锁,得到为true,没得到为false,立即返回
     */
    private static void test03() throws Exception{
        new Thread(()->{
            canTryLock();
        }).start();
        Thread.sleep(1000);
        Thread t =new Thread(()->{
            canTryLock();
        });
        t.start();
        Thread.sleep(1000);
    }

    /*

     */
    private static void canTryLock(){
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName()+" get lock and will do work");
                while (true){
                }
            } finally {
              lock.unlock();
            }
        }
        else {
            // perform alternative actions
            System.out.println(Thread.currentThread().getName()+" will exit");
          }
    }

    /**
     * ReentrantLock锁可重入
     * @throws Exception
     */
    private static void test04() throws Exception{
        for (int i=1;i<=3;i++){
            lock.lock();
        }

        for (int i=1;i<=3;i++){
            lock.unlock();
        }

        System.out.println("over");
    }
}
