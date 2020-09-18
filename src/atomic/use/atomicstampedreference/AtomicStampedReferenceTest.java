package atomic.use.atomicstampedreference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> atomicRef = new AtomicStampedReference<>(100,0);

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.SECONDS.sleep(1);
                    boolean success = atomicRef.compareAndSet(100,101,atomicRef.getStamp(),atomicRef.getStamp()+1);
                    System.out.println(success);
                    success = atomicRef.compareAndSet(101,100,atomicRef.getStamp(),atomicRef.getStamp()+1);
                    System.out.println(success);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    int stamp = atomicRef.getStamp();
                    System.out.println("Before sleep:stamp="+stamp);
                    TimeUnit.SECONDS.sleep(2);
                    boolean success = atomicRef.compareAndSet(100,101,stamp,stamp+1);
                    System.out.println(success);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();


    }

}