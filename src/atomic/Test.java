package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private volatile static int i = 1;


    public static void main(String[] args) throws Exception {

        AtomicInteger a = new AtomicInteger(1);

        new Thread(()->{
             i = i + 1;//2
            try {
                Thread.sleep(2_000);//睡眠2秒的时候,别的线程已经将i的值改变了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);//3 因为它会读取main memory中的最新的值
        }).start();

        Thread.sleep(500);

        new Thread(()->{
            i = i + 1;
        }).start();
    }
}