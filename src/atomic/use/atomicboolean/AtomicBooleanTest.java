package atomic.use.atomicboolean;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanTest {

    @Test
    public void testCreateWithoutArguments(){
        AtomicBoolean bool = new AtomicBoolean();
        System.out.println(bool.get());//false
    }

    @Test
    public void testCreateWithArguments(){
        AtomicBoolean bool = new AtomicBoolean(true);
        System.out.println(bool.get());//true
    }

    @Test
    public void testGetAndSet(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.getAndSet(false);
        System.out.println(result);//true
        System.out.println(bool.get());//false
    }

    @Test
    public void testCompareAndSet(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(true, false);
        System.out.println(result);//true
        System.out.println(bool.get());//false
    }

    @Test
    public void testCompareAndSetFailed(){
        AtomicBoolean bool = new AtomicBoolean(true);
        boolean result = bool.compareAndSet(false, false);
        System.out.println(result);//false
        System.out.println(bool.get());//true
    }







}