package hashmap;

import java.util.HashMap;

/**
 * @Author candy-wind
 * @Data: 2020-05-18 15:49
 * @Version 1.0
 */
public class HashMapTest {


    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("a",2);
        hashMap.put("a",3);
        for (Object iterable:hashMap.keySet()){
            System.out.println(iterable);
        }

    }
}
