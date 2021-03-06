package atomic.use.atomicreference;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference<Simple> atomicReference = new AtomicReference<>(new Simple("Alex",12));
        System.out.println(atomicReference);

        boolean result = atomicReference.compareAndSet(new Simple("Alex", 12), new Simple("sdfd", 234));
        System.out.println(result);
    }

    static class Simple{
        private String name;
        private int age;

        public Simple(String name,int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}