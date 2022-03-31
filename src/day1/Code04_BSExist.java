package day1;

import java.util.Arrays;

/**
 * 二分法查找元素
 */
public class Code04_BSExist {
    public static boolean exist(int[] sortedArr,int num){
        if (sortedArr==null||sortedArr.length==0){
            return false;
        }
        int L=0;
        int R=sortedArr.length-1;
        int mid=0;
        while (L<R){
            // mid=(L+R)/2
            //L 10亿  R 18亿
            //mid=L+(R-L)/2
            //  N/2  <=>  N>>1
            /**
             * 防止超出整数类型范围，这样的写法，最小的代价找到中值
             */

            mid=L+((R-L)>>1);
            if (sortedArr[mid]==num){
                return true;
            }else if (sortedArr[mid]>num){
                R=mid-1;
            }else {
                L=mid+1;
            }
        }
        return sortedArr[L]==num;
    }

    /**
     * 循环查找  找出这个数是否在数组中
     * @param sortedArr
     * @param num
     * @return
     */
    public static boolean test(int[] sortedArr,int num){
        for (int i : sortedArr) {
            if (i==num){
                return true;
            }
        }
        return false;
    }

    public static int[] generateRandomArray(int maxSize,int maxValue){
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
        int[] arr=new int[(int) ((maxSize+1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)((maxValue+1)*Math.random())-(int)(maxValue*Math.random());
        }
        return arr;
    }

    /**
     * 验证二分法查找元素，在主函数中，先对随机生成的数组做了排序
     * 然后用循环遍历的方法验证了需要查找的数存在于数组中，最后用二分法找出
     * @param args
     */
    public static void main(String[] args) {
        int testTime=500000;
        int maxSize=10;
        int maxValue=100;
        boolean succeed=true;
        for (int i = 0; i < testTime; i++) {
            int[] arr=generateRandomArray(maxSize,maxValue);
            Arrays.sort(arr);
            int value=(int)((maxValue+1)*Math.random())-(int)(maxValue*Math.random());
            if (test(arr,value)!=exist(arr,value)){
                succeed=false;
                break;
            }
        }
        System.out.println(succeed?"Nice!":"Fucking fucked!");
    }
}
