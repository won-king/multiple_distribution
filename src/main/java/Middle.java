/**
 * Created by wangke18 on 2018/8/31.
 */
public class Middle {
    public static void main(String[] args) {
        //int[] array=new int[]{1,2,3,3,4,5,6,7,8};
        //int[] array=new int[]{1,2,2,3,4,5,6,7,8};
        int[] array=new int[]{1,1,1,1,4,5,6,7,8};
        //int[] array=new int[]{1,1,1,1,1,1,1,1,2};
        int num=3;
        System.out.println(findNum(array, 0, array.length-1, num));

        //System.out.println(findNearest(array, 0, array.length-1, num, 0));
        System.out.println(findTarget(array, 0, array.length-1, num));
    }

    private static int findTarget(int[] nums, int lo, int hi, int target) {
        //如果只有一个元素可选，那就直接返回该元素
        if (lo == hi) {
            return lo;
        }

        int middle = 0;
        int count=0;
        while (lo < hi) {
            middle = (lo + hi) / 2;
            //原博主没有加这个等于的判断，所以导致有些情况会误判
            //他的缺陷在于，他认为只要中间值比其后一个值更接近目标，那么更接近的下标一定在start~middle-1之间，注意是middle-1
            //所以他漏了可能middle就是最匹配的情况

            //后来发现，其实加了这个等于的判断也不行，就比如说这个例子
            //int[] array=new int[]{1,1,1,1,4,5,6,7,8};
            //int num=3;
            //所以这个思路本身存在问题，他只简单判断了中位数和其后面的一位数，没有考虑前面的一位数
            //还是我的递归实现完美无缺
            if(Math.abs(target - nums[middle])==0){
                return middle;
            }
            if (Math.abs(target - nums[middle]) > Math.abs(target - nums[middle + 1])) {
                lo = middle + 1;
            } else {
                hi = middle - 1;
            }
        }
        System.out.println("target count->"+count);
        return Math.abs(target - nums[middle]) > Math.abs(target - nums[middle + 1]) ? (middle + 1) : middle;
    }

    //如果不允许调用取数组的子集的话，那么在设计递归函数的参数时，一般都需要带start,end参数来表示当前数组的子集
    private static int findNearest(int[] array, int start, int end, int num, int count){
        int midIndex=(start+end)/2;
        count++;
        //对比上下两个实现，可以发现，其实递归方法的终止条件，往往就是非递归while循环的结束条件
        if(start >= end){
            //System.out.println("count->"+count);
            return start;
        }
        int mid=array[midIndex];
        int left=array[(midIndex-1)<start? start:(midIndex-1)];
        int right=array[(midIndex+1)>end? end:(midIndex+1)];
        int sm=Math.abs(num-mid);
        int sl=Math.abs(num-left);
        int sr=Math.abs(num-right);
        //[3,3,3,3,3,3,3,3,4]
        if(sm < sl && sm < sr){
            //System.out.println("count->"+count);
            //System.out.println("start->"+start+"  end->"+end);
            return midIndex;
        }/*else if(miniSub >= leftMiniSub){
            return findNearest(array,start,midIndex-1, num, count);
        }else {
            return findNearest(array,midIndex+1,end, num, count);
        }*/
        else{
            int li=findNearest(array,start,midIndex-1, num, count);
            int ri=findNearest(array,midIndex+1, end, num, count);
            if(Math.abs(num-array[li]) < Math.abs(num-array[ri])){
                return li;
            }
            return ri;
        }
    }

    private static int findNum(int[] array, int start, int end, int num){
        if(start>end){
            return -1;
        }
        int mi=(start+end)/2;
        int m=array[mi];
        if(m==num){
            return mi;
        }else if(m<num){
            start=mi+1;
        }else {
            end=mi-1;
        }
        return findNum(array, start, end, num);
    }

    /*private static int middle(int[] array1, int[] array2){
        int start1=0, end1=array1.length-1;
        int start2=0, end2=array2.length-1;
        int toSkip=(array1.length+array2.length)/2;
        int leftStep=0, rightStep=0;
        while(leftStep!=rightStep || leftStep<toSkip){
            int m1=(start1+end1)/2;
            int m2=(start2+end2)/2;
            int a1=array1[m1];
            int a2=array2[m2];
            if(a1<a2){
                leftStep+=m1-start1;
                rightStep+=end2-m2;
                start1=m1;
                end2=m2;
            }else if(a1>a2){
                rightStep+=end1-m1;
                leftStep+=m2-start2;
                end1=m1;
                start2=m2;
            }else {
                leftStep+=m1-start1;
                rightStep+=end2-m2;
                start1=m1;
                end2=m2;
            }
        }
    }*/
}
