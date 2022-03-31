package day1;

import java.util.Arrays;


public class Code01_SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //0~N-1 找到最小值，在哪儿，然后放到0位置上
        //1~N-1 找到最小值，在哪儿，然后放到1位置上
        //2~N-1 找到最小值，在哪儿，然后放到2位置上
        //......
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        //low方法
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
        //炫技方法
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
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
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; //长度随机
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
     * 程序完整含义：
     * 对一段随机生成的数字进行选择排序（asc）,排序前对数组进行处理,使得数组是[-N,N],
     * 排序前先验证算法的正确性,用Arrays.sort()方法也对其进行排序,然后用重写的isEqual()方法比较,
     * 若完全一样,则输出Nice!,然后对排序前后数组进行输出,若不一样，则输出selectionSort()排序后的结果与 Arrays.sort()排序后的结果，
     * 两者进行比较
     */
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            selectionSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                System.out.println("______________");
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        System.out.println("______________");
        selectionSort(arr);
        printArray(arr);
    }

}
