package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private volatile static int i = 1;


    public static void main(String[] args) throws Exception {

        AtomicInteger a = new AtomicInteger(1);

        new Thread(()->{
            int result = a.getAndAdd(1);
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }).start();

        Thread.sleep(500);

        new Thread(()->{
            a.getAndAdd(1);
        }).start();
    }
}