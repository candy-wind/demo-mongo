package jvm;

/**
 * @Author candy-wind
 * @Data: 2020-08-28 17:52
 * @Version 1.0
 *
 *
 *
 *bootstrap classloader 启动类加载器
 *extension classloader 扩展类加载器
 *应用程序类加载器 application classloader
 * jvm
 */
public class Car {

    public static void main(String[] args) {
        Car car =new Car();
        Car car1 =new Car();
        Car car2 =new Car();

        System.out.println(car.hashCode());
        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());

        Class<?> class1=car.getClass();
        Class<?> class12=car1.getClass();
        Class<?> class3=car2.getClass();

        System.out.println(class1.hashCode());
        System.out.println(class12.hashCode());
        System.out.println(class3.hashCode());

        ClassLoader classLoader = class1.getClassLoader();
        System.out.println( classLoader);
        System.out.println( classLoader.getParent());
        System.out.println( classLoader.getParent().getParent());

        String cls = System.getProperty("java.class.path");
        System.out.println(cls);

        new Thread().start();

    }
}
