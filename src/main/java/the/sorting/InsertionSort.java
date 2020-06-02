package the.sorting;

/**
 * @Author candy-wind
 * @Data: 2020-06-01 15:06
 * @Version 1.0
 *
 *
 * 插入排序
 *
 * 基本思想
 *     a、默认从第二个数据开始比较。
 *
 * 　　b、如果第二个数据比第一个小，则交换。然后在用第三个数据比较，如果比前面小，则插入（狡猾）。否则，退出循环
 *
 * 　　c、说明：默认将第一数据看成有序列表，后面无序的列表循环每一个数据，如果比前面的数据小则插入（交换）。否则退出。
 *
 * 　　d、代码实现
 */
public class InsertionSort {

    public static void main(String[] args) {
        int arr[] = {7, 5, 3, 2, 4};
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                //内存循环，与前面排好序的数据比较，如果后面的数据小于前面的则交换
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    //如果不小于，说明插入完毕，退出内层循环
                    break;
                }
            }

        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

}
