package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    public void tryLock() throws GetLockException {
        boolean success = value.compareAndSet(0, 1);//原子性
        if(!success)
            throw new GetLockException("Get the Lock failed");
    }

    public void unlock(){
        if(0 == value.get())
            return;
        value.compareAndSet(1,0);
    }


}