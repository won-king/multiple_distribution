/**
 * Created by wangke18 on 2018/9/3.
 */
public class TwoMiddle {
    public static void main(String[] args) {
        int[] array1=new int[]{};
        int[] array2=new int[]{1};

        //test1();
        //test2();
        //test3();
        test4();
    }

    private static void test1(){
        int[] array1=new int[]{};
        int[] array2=new int[]{1};
        System.out.println(findMiddle(array1, array2));
    }
    private static void test2(){
        int[] array1=new int[]{-1,1,2};
        int[] array2=new int[]{};
        System.out.println(findMiddle(array1, array2));
    }
    private static void test3(){
        int[] array1=new int[]{-1,5};
        int[] array2=new int[]{-3,1};
        System.out.println(findMiddle(array1, array2));
    }
    private static void test4(){
        int[] array1=new int[]{1,1,3,3};
        int[] array2=new int[]{1,1,3,3};
        System.out.println(findMiddle(array1, array2));
    }

    private static double findMiddle(int[] array1, int[] array2){
        int totalLength=array1.length+array2.length;
        if(totalLength==0){
            return -1;
        }
        //屏蔽奇偶差异，当是奇数时，k1，k2找到的是同一个中位数；当是偶数时，k1，k2找到的是两个中位数
        int k1=(totalLength+1)/2;
        int k2=(totalLength+2)/2;
        int mid1, mid2;
        if(array1.length==0){
            mid1=array2[k1-1];
            mid2=array2[k2-1];
        }else if(array2.length==0){
            mid1=array1[k1-1];
            mid2=array1[k2-1];
        }else {
            mid1=findKth(array1, 0, array1.length-1, array2, 0, array2.length-1, k1);
            mid2=findKth(array1, 0, array1.length-1, array2, 0, array2.length-1, k2);
        }
        return (double) (mid1+mid2)/2;
    }

    //不能用跳过多少个的思想，因为你当你真正跳过那么多个时，最后还需要判断中位数是落在哪个数据上，还需要比较一次大小
    //其实还可以把此算法进一步泛化，要找到的并不是中位数，而是两个有序数组，第k个最小的数

    //后来又有了一个想法，就是先在两个数组中直接找到各自的中位数，取较大的那一个，先假设它是中位数，再反过来证明它就是中位数
    //条件就是，有且仅有k-1个数可以排在它的左边
    //对于上面那个条件，我们还是无法编程实现，细化到能用编程语言实现的条件是
    // len(left)=k-1 && max(left)<=min(right)，即，如果左边部分刚好有k-1个，并且左边的最大值，小于等于右边的最小值，命题得证
    //这个想法没有更进一步细化，感觉细化了之后，跟leetcode上面的那个算法思想差不多
    private static int findKth(int[] array1, int start1, int end1, int[] array2, int start2, int end2, int k){
        if(start1>end1){
            return array2[start2+k-1];
        }
        if(start2>end2){
            return array1[start1+k-1];
        }
        if(k==1){
            return Math.min(array1[start1], array2[start2]);
        }
        int mid1=Integer.MAX_VALUE;
        int mid2=Integer.MAX_VALUE;
        //这里的思想，其实不是每次移动整个end-start的一半，而是移动k的一半
        if(start1+k/2-1<=end1){
            mid1=array1[start1+k/2-1];
        }
        if(start2+k/2-1<=end2){
            mid2=array2[start2+k/2-1];
        }
        if(mid1<mid2){
            return findKth(array1, start1+k/2, end1, array2, start2, end2, k-k/2);
        }
        return findKth(array1, start1, end1, array2, start2+k/2, end2, k-k/2);

        //的确，这里的二分思路有问题，不能以整个数组的长度来进行二分，而应该用K来二分
        //这个例子告诉我们，二分的时候，首先要确定，要对哪个值进行二分，选择不当，可能使你走入一个误区，
        //而如果你又是那种自以为是的人的话, 你很有可能就会钻里面出不来了
        /*int mid1=(start1+end1)/2;
        int mid2=(start2+end2)/2;
        int skip;
        if(array1[mid1] > array2[mid2]){
            skip=mid2-start2;
            if(skip==0){
                skip=1;
            }
            return findKth(array1, start1, end1, array2, start2+skip, end2, k-(skip));
        }else if(array1[mid1] < array2[mid2]){
            skip=mid1-start1;
            if(skip==0){
                skip=1;
            }
            return findKth(array1, start1+skip, end1, array2, start2, end2, k-(skip));
        }else {
            int skip1=mid1-start1;
            int skip2=mid2-start2;
            if(skip1==skip2){
                if(k-skip1*2 <1){}
                return findKth(array1, start1+skip1, end1, array2, start2+skip2, end2, k-(skip1*2));
            }else if(skip1>skip2){
                return findKth(array1, start1+skip1, end1, array2, start2, end2, k-skip1);
            }else {
                return findKth(array1, start1, end1, array2, start2+skip2, end2, k-skip2);
            }
        }*/
    }
}
