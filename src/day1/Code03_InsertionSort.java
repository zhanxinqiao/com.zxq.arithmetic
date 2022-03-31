package day1;

import java.util.Arrays;

public class Code03_InsertionSort{
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //当个数大于1时
        /**
         * 只能从第二个数开始，然后开始排序，将第一个与后面的比较，选择出最大的，然后向后交换
         */
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j+1);
            }
        }
    }


    private static void swap(int[] arr, int i, int j) {
        //low方法
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        //炫技方法
//        arr[i]=arr[i]^arr[j];
//        arr[j]=arr[i]^arr[j];
//        arr[i]=arr[i]^arr[j];
    }

    /**
     * 用数组自带的排序方法进行排序
     *
     * @param arr
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    //for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        //个人理解
        /**
         * 随机生成0~1间的数，左闭右开区间
         * Math.random() [0,1)
         * 乘以N之后，生成了0~N之间左闭右开的区间
         * Math.random() * N [0,N)
         * 强转化为0~N-1之间的整数
         * (int)(Math.random() * N) [0,N-1]
         */

        //左神理解
        /**
         *Math.random()->[0,1)所有的小数，等概率返回一个
         * Math.random()*N->[0,N)所有的小数，等概率返回一个
         * (int)(Math.random()*N)->[0,N-1]所有的整数，等概率返回一个
         *
         */
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            //生成[-N-1，N-1]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * @param arr
     * @return 对arr数组进行复制
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * @param arr1
     * @param arr2
     * @return 重写isEqual方法, 对两个数组对象内的元素进行一对一比较
     * 完全为null或者完全相同才返回true,否则返回false
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || arr1 != null && arr2 == null) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param arr 遍历输出数组内的每一个元素
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            System.out.println();
        }
    }

    /**
     * 只能从第二个数开始，然后开始排序，将第一个与后面的比较，选择出最大的，然后向后交换
     * 期间该算法会与Arrays.sort()排序结果作比较,若不同则报错比较不同的位置，否则，则输出排序前后的结果
     * @param args
     */
    public static void main(String[] args) {
        int testTime=500000;
        int maxSize=100;
        int maxValues=100;
        boolean succeed=true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValues);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            insertionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1,arr2)){
                succeed=false;
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j]+" ");
                }
                System.out.println();
                break;
            }
        }
        System.out.println(succeed?"Nice!":"Fucking fucked");
        int[] arr = generateRandomArray(maxSize, maxValues);
        printArray(arr);
        insertionSort(arr);
        System.out.println("___________________________");
        printArray(arr);
    }
}
