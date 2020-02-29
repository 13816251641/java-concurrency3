package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther ljn
 * @Date 2020/2/25
 * 读写锁
 *
 * W  W  X
 * W  R  X
 * R  W  X
 * R  R  O
 */
public class TestReentrantReadWriteLock {

    private static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        new Thread(()->{
            read(lock);
        }).start();

        Thread.sleep(500);

        new Thread(()->{
            read(lock);
        }).start();

    }

    private static void write(ReentrantReadWriteLock lock){
        lock.writeLock().lock();//获取写锁,会堵塞
        try {
            list.add(System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName()+"插入完成");
            Thread.sleep(4_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

    private static void read(ReentrantReadWriteLock lock){
        lock.readLock().lock();//获取读锁,会阻塞
        try {
            if(list.size()>0){
                Long result = list.get(0);
            }
            System.out.println(Thread.currentThread().getName()+"取到值");
            Thread.sleep(4_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }

}
