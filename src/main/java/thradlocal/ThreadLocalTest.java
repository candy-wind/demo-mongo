package thradlocal;


/**
 * @Author candy-wind
 * @Data: 2020-05-29 10:30
 * @Version 1.0
 */
public class ThreadLocalTest {


//
//
    //本地线程变量
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("1");
        threadLocal.remove();
        threadLocal.get();
    }

}
