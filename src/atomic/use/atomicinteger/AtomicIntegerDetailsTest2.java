package atomic.use.atomicinteger;

public class AtomicIntegerDetailsTest2 {

    private static final CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {
        for(int i = 0; i < 5;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        doSomething2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (GetLockException e){
                        System.out.println(Thread.currentThread().getName()+"-catch");
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
           /* try之后会首先执行finally然后是外部的catch!!! */
           System.out.println(Thread.currentThread().getName()+"-finally");
           lock.unlock();
       }
       /*
            你没有catch当然不会帮你运行余下的代码
        */
       System.out.println("ggg");
    }






}