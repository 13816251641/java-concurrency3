package atomic;

public class JITTest {

    private static boolean init = false;

    public static void main(String[] args) throws Exception {
        /*
            如下线程会一直执行程序,即使init=false
            解决方法是将init设置为volatile
         */
        new Thread(()->{
            while(!init){
                /*
                    这里加了system打印后即使init没设置为volatile也会中断,
                    但为了避免这样的问题强烈建议init设置为volatile
                 */
                //System.out.println("aaaa");
            }
        }).start();

        Thread.sleep(1000);

        new Thread(()->{
            init = true;
            System.out.println("Set init to true");
        }).start();

    }


}