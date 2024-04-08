package learning_tests;

import org.junit.jupiter.api.Test;

public class InnerClassCallerTest {

    @Test
    void testingInnerClassTypes() {
        InnerClassTest.InnerClass inner = new InnerClassTest().new InnerClass(6);
        inner.printHi();

        InnerClassTest outer = new InnerClassTest();
        InnerClassTest.InnerClass inner2 = outer.new InnerClass(6);
        outer.printHi();
        inner2.printHi();

    }

    @Test
    void testingNestedStaticClass() {
        InnerClassTest.NestedStaticClass nested = new InnerClassTest.NestedStaticClass(5);
        nested.printHi();
    }
}
