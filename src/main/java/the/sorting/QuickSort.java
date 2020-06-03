package the.sorting;

/**
 * @Author candy-wind
 * @Data: 2020-06-01 18:49
 * @Version 1.0
 *
 *
 *
 *快速排序  ----
 * 通过一趟排序将带排记录分割成独立的两部分，其中一部分记录的关键字均比另一部分关键字小，则分别对着两部分排序，达到整个序列有序
 *
 *
 * 分治法的思想
 *
 * 选择一个基准 小于基准数放左边 大于基准数放右边
 */
public class QuickSort {


    public static void main(String[] args) {

        int[] arr = {7,10,2,4,7,1,8,5,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    private static void quickSort(int[] sort,int left,int right){
        int i,j,temp,t;
        if(left > right){
            return;
        }
        i = left;
        j = right;
        temp = sort[i];

        while (i < j){
            //先从右边开始
            while(temp <= sort[j] && i<j){
                j--;
            }
            while(temp >= sort[i] && i<j){
                i++;
            }
            if(i<j){
                t = sort[j];
                sort[j] = sort[i];
                sort[i] = t;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        sort[left] = sort[i];
        sort[i] = temp;
        //递归调用左半数组
        quickSort(sort, left, j-1);
        //递归调用右半数组
        quickSort(sort, j+1, right);

    }
}
