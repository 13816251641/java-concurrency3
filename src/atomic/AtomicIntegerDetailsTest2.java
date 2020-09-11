package atomic;

public class AtomicIntegerDetailsTest2 {

    private static final CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {
        for(int i = 0; i < 2;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        doSomething2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (GetLockException e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }



    private static void doSomething() throws InterruptedException{
        synchronized (AtomicIntegerDetailsTest2.class){
            System.out.println(Thread.currentThread().getName()+" get the lock");
            Thread.sleep(100000);
        }
    }

    private static void doSomething2() throws InterruptedException,GetLockException{
       try{
           lock.tryLock();
           System.out.println(Thread.currentThread().getName()+" get the lock");
           Thread.sleep(100000);
       }finally {
           lock.unlock();
       }
    }






}