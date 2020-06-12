package hashmaptest;

import java.util.HashMap;

/**
 * @Author candy-wind
 * @Data: 2020-06-08 10:40
 * @Version 1.0
 *
 * jdk1.7 hashmap
 */
public class TestHashMap {


    /**
     * put方法
     *
     * 初始化数组  为啥要是2的幂次方数  因为数组下标 取值是16
     * 计算hashcode
     * hash值计算数组下标
     * 遍历连表 判断连表是否有值 覆盖
     * 最后放进数组
     *
     * 扩容
     * 条件
     * 1 hashmap.size > capacity * loadFactor 扩容
     * 2 对应下标的数组不能为空
     *
     *
     *  1 得到hashcode
     *  2 hash % 数组长度
     *  3
     *      header=...
     *  3 new Enrty(key,value)
     *
     *
     *  ^=抑或运算 相同的为0
     * @param args
     */

    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("1","2");
        String value = hazshMap.put("1","3");

//        hashMap.hash(a);
//        hash(key);
        //  0000 0001
        //  0001 0000
        System.out.println(value);
    }
}
