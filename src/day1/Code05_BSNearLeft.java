package day1;

import java.util.Arrays;
//排序中找到>=num最左的位置
public class Code05_BSNearLeft {
    public static int nearestIndex(int[] arr,int value){
        int L=0;
        int R=arr.length-1;
        int index=-1;//记录最右的对号
        while (L<=R){
            int mid=L+((R-L)>>1);
            if (arr[mid]<=value){
                index=mid;
                L=mid+1;
            }else {
                R=mid-1;
            }
        }
        return index;
    }

    public static int test(int[] arr,int value){
        for (int i=arr.length-1;i>=0;i--){
            if (arr[i]<=value){
                return i;
            }
        }
        return -1;
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

    public static void printArray(int[] arr){
        if (arr==null){
            return;
        }
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime=500000;
        int maxSize=10;
        int maxValue=100;
        boolean succeed=true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1 * Math.random())) - (int) (maxValue * Math.random());
            if (test(arr,value)!=nearestIndex(arr,value)){
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr,value));
                System.out.println(nearestIndex(arr,value));
                succeed=false;
                break;
            }
        }
        System.out.println(succeed?"Nice!":"Fucking fucked!");
    }
}
