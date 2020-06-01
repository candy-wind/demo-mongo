package the.sorting;

import java.lang.reflect.Array;
import java.sql.SQLOutput;

/**
 * @Author candy-wind
 * @Data: 2020-05-29 14:18
 * @Version 1.0
 *
 * 冒泡排序
 * O(n2)
 * 基本思想
 * 两个数比较 较大的数下沉，较小的数浮起来
 * 比较相邻的两个数大小 第二个数小 交换位置
 *
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {12,3,4,8,5,6,7,9};
        int temp;

        System.out.println(System.getProperty("java.system.class.loader"));

        for (int i = 0; i < array.length; i++) {
//            System.out.println(i);
            for (int j = 0; j < array.length - 1  -i; j++) {
                if(array[j] < array[j-1]){
                    temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }


    }


}
