package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther ljn
 * @Date 2020/2/29
 * 使用Condition和ReentrantLock实现生产者和消费者
 * 单生产单消费使用if是没有任何问题的
 */
public class ConditionExample {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();//2个线程共用一个Condition

    private int value = 0;

    private boolean flag = false;//一开始未生产

    private void produce(){
        lock.lock();
        try {
            if (flag) //如果已经生产了就等待
                condition.await();//会释放锁,但不会调用finally
            value++;
            flag = true;
            System.out.println("生产:"+value);
            condition.signal();//notify
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    private void consume(){
        lock.lock();
        try {
            if (!flag) //还没生产就等待
                condition.await();//会释放锁,但不会调用finally
            flag = false;
            System.out.println("消费:"+value);
            condition.signal();
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample example = new ConditionExample();
        new Thread(()->{
            while (true){
                example.consume();
            }
        }).start();
        new Thread(()->{
            while (true){
                example.produce();
            }
        }).start();
    }

}
