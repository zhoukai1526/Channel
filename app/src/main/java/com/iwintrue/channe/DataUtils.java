package com.iwintrue.channe;

import java.util.List;

/**
 * Created by zhoukai on 2017/7/14.
 */

public class DataUtils {


    /**
     * 利用反转的思想对数据进行排序
     * 例如：list{0,1,2,3,4,5,6,7} 左移一位
     * 第一步:第一位先反转{0,1,2,3,4,5,6,7}
     * 第二部:剩下的在反转{0,7,6,5,4,3,2,1}
     * 第三步:全部反转{1,2,3,4,5,6,7,0}
     *
     *  例如：list{0,1,2,3,4,5,6,7} 右移一位
     * 第一步:最右边一位先反转{1,2,3,4,5,6,7}
     * 第二部:剩下的在反转{6,5,4,3,2,1,0,7}
     * 第三步:全部反转{7,6,5,4,3,2,1,0}
     *
     * 因为list的index是从0开始的，step要相应的-1
     * 优点:少创建对象，优化内存
     *
     * @param start
     * @param end
     * @param list
     */

    public static  void reverseList(int start,int end,List list){

        int count = (end+1-start)/2 ;
        for(int i = 0;i< count;i++){
            Object temp = list.get(start+i);
            list.set(start+i,list.get(end-i));
            list.set(end-i,temp);
        }
    }
    public static void leftStepList(int step,List list){

        int size = list.size() -1;
        //左移
        reverseList(0,step,list);
        reverseList(step+1,size,list);
        reverseList(0,size,list);

    }

    public static void rightStepList(int step,List list){

        int size = list.size() -1;
        //右移
        reverseList(size-step,size,list);
        reverseList(0,size-step-1,list);
        reverseList(0,size,list);
    }
}
