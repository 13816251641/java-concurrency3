package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    private static volatile int value = 0;

    public static void main(String[] args) throws Exception {
//
//        Thread t1 = new Thread() {
//            @Override
//            public void run() {
//                int x = 0;
//                while ( x < 500){
//                    int temp = value;
//                    System.out.println(Thread.currentThread().getName()+":"+temp);
//                    value += 1;
//                    /**
//                     * value = value + 1;
//                     * 1) get value from main memory to local memory
//                     * 2) add 1 => x
//                     * 3) assign the x to value
//                     * 4) flush to main memory
//                     */
//                    x++;
//                }
//            }
//        };
//
//        Thread t2 = new Thread() {
//            @Override
//            public void run() {
//                int x = 0;
//                while ( x < 500){
//                    int temp = value;
//                    System.out.println(Thread.currentThread().getName()+":"+temp);
//                    value += 1;
//                    x++;
//                }
//            }
//        };
//
//        Thread t3 = new Thread() {
//            @Override
//            public void run() {
//                int x = 0;
//                while ( x < 500){
//                    int temp = value;
//                    System.out.println(Thread.currentThread().getName()+":"+temp);
//                    value += 1;
//                    x++;
//                }
//            }
//        };
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t1.join();
//        t2.join();
//        t3.join();
//
//        /*
//            可能出现1498 1499 1500,原因是因为:value += 1 不是原子性的
//         */
//        System.out.println(value);


        final AtomicInteger value = new AtomicInteger();//默认为0
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int x = 0;//内存局部变量
                while ( x < 500){
                    int v = value.getAndIncrement();//先得到再增加,原子操作
                    System.out.println(Thread.currentThread().getName()+":"+v);
                    x++;
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while ( x < 500){
                    int v = value.getAndIncrement();//先得到再增加,原子操作
                    System.out.println(Thread.currentThread().getName()+":"+v);
                    x++;
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int x = 0;
                while ( x < 500){
                    int v = value.getAndIncrement();//先得到再增加,原子操作
                    System.out.println(Thread.currentThread().getName()+":"+v);
                    x++;
                }
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println(value);

    }

}