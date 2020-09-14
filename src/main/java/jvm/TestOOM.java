package jvm;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Author candy-wind
 * @Data: 2020-09-02 18:06
 * @Version 1.0
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3332)
 * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
 * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:448)
 * 	at java.lang.StringBuilder.append(StringBuilder.java:136)
 * 	at java.lang.StringBuilder.append(StringBuilder.java:131)
 * 	at jvm.TestOOM.main(TestOOM.java:16)
 */
public class TestOOM {

    public static void main(String[] args) {
//测试oom代码zhen shi

        //测试代码一：
        //改变堆大小：-Xms1024m -Xmx1024m -XX:+PrintGCDetails
//        String str = "zht097808080808";
//        while (true){
//            str +=str + new Random().nextInt(888888)+new Random().nextInt(88888888);
//        }
        byte[] array = new byte[1024 * 1024];

        //测试代码二
        //改变大小 -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
        ArrayList<TestOOM> list = new ArrayList<>();
        int count=0;
        try {
            while (true){
                list.add(new TestOOM());
                count=count+1;
            }
        } catch (Error e) {
            System.out.println(count);
            e.printStackTrace();
        }
    }






}
