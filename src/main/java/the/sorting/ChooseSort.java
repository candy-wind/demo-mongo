package the.sorting;

/**
 * @Author candy-wind
 * @Data: 2020-06-01 14:35
 * @Version 1.0
 *
 * 选择排序
 * 基本思想
 *
 *将第一个看成最小值
 *
 * 然后和后续比较找出最小值和下标
 * 交换本次遍历的起始值和最小值
 *
 * 如何后一个比前一个大 则交换
 *
 * 每一次从带排序列中选择最小的元素放在排序好的元素末尾
 */

public class ChooseSort {
    public static void main(String[] args) {
        int arr[] = {8, 5, 3, 2, 4};

        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[index] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
