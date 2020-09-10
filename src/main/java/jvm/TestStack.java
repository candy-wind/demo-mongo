package jvm;


/**
 * @Author candy-wind
 * @Data: 2020-09-01 18:18
 * @Version 1.0
 *
 * 栈溢出
 * Exception in thread "main" java.lang.StackOverflowError
 * 	at jvm.TestStack.test(TestStack.java:19)
 * 	at jvm.TestStack.a(TestStack.java:25)
 * 	at jvm.TestStack.test(TestStack.java:19)
 *
 */
public class TestStack {

    public static void main(String[] args) {
        test();
    }

    private static void test(){
        a();
    }

    private static void a(){
       test();
    }
}
