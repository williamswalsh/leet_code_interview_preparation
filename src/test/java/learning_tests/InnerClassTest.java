package learning_tests;

public class InnerClassTest {


    public void printHi() {
        System.out.println("Hi - Outer class");

//        Only accessible within this method.
        class LocalInnerClass {
            private void printHi() {
                System.out.println("Hi - Local inner class");
            }
        }

        LocalInnerClass lic = new LocalInnerClass();
        lic.printHi();
    }

//    Is tied to an object of the outer class.
//    Must create outer object then create inner object.
    public class InnerClass {
        int x;

        public InnerClass(int x) {
            this.x = x;
        }

        public void printHi() {
            System.out.println("Hi - Inner non-static class");
        }
    }

//    Not tied to an object of the outer class.
    public static class NestedStaticClass {
        int x;

        public NestedStaticClass(int x) {
            this.x = x;
        }

        public void printHi() {
            System.out.println("Hi - Inner static class");
        }
    }
}
