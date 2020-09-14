package atomic.use.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    private Thread lockedThread;

    public void tryLock() throws GetLockException {
        boolean success = value.compareAndSet(0, 1);//原子性,unsafe里面有锁,cpu级别的锁性能高
        if(!success)
            throw new GetLockException("Get the Lock failed");
        else{
            lockedThread = Thread.currentThread();
        }
    }

    public void unlock(){
        if(0 == value.get())
            return;
        /* 自己加的锁自己释放  */
        if(lockedThread == Thread.currentThread()){
            value.compareAndSet(1,0);
        }
    }


}