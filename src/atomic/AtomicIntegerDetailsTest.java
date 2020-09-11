package atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDetailsTest {

    public static void main(String[] args) {



    }

    @Test
    public void create(){
        /**
         * create
         */
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());//0
        i = new AtomicInteger(10);
        System.out.println(i.get());//10
    }

    @Test
    public void set(){
        AtomicInteger i = new AtomicInteger();
        i.set(12);
        System.out.println(i.get());//12
    }

    @Test
    public void getAndADD(){
        AtomicInteger getAndSet = new AtomicInteger(10);
        int result = getAndSet.getAndAdd(10);
        System.out.println(result);//10
        System.out.println(getAndSet.get());//20
    }

    /**
     * 测试互加
     */
    @Test
    public void testHuJia(){
        /**
         * 测试互加
         */
        AtomicInteger a = new AtomicInteger(0);
        new Thread(()->{
            for(int index = 0;index < 10;index++){
                int v = a.addAndGet(1);//它的api是原子性的,放心使用即可
                System.out.println(Thread.currentThread().getName()+":"+v);
            }
        }).start();

        new Thread(()->{
            for(int index = 0;index < 10;index++){
                int v = a.addAndGet(1);
                System.out.println(Thread.currentThread().getName()+":"+v);
            }
        }).start();
    }

    /**
     *
     */
    @Test
    public void test(){
        AtomicInteger i = new AtomicInteger(10);
        boolean b = i.compareAndSet(15, 20);
        System.out.println(b);
        System.out.println(i.get());
    }




}