package com.example.demo.examination;

import java.util.*;

/**
 * Created by Administrator on 2020/4/17.
 */
public class ThePandaDuDu {



    /*首先输入一个正整数 N（N <= 50），接下来输入 N 个数表示每顶帽子的价格（价格均是正整数，且小于等于 1000）

    输出描述:

    如果存在第三便宜的帽子，请输出这个价格是多少，否则输出-1*/


    public static void buyGreenCap() {
        int[] target={10,10,10,10,10,10,20,20,80,66,44,14,878,46,34,97,41,496,154,33,12,3,4,79,6,4,5,1,6,100};
        Set utilSet=new TreeSet();
        for(Integer i :target){
            utilSet.add(i);
        }
        List utilList=new ArrayList(utilSet);
        if(utilSet.size()<3){
            System.out.println(-1);
        }else{
            System.out.println(utilList.get(2));
        }
    }



   /*斗地主模拟发牌*/

    public static void killRichMan(){
        int[] target={3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,12,13,13,13,13,14,14,14,14,15,15,15,15,16,17};
        int[] allCards=new int[54];

        Random r1 = new Random();
        int utilInt;
        Set allRandomSet=new LinkedHashSet();
        while(allRandomSet.size()<54){
            utilInt =r1.nextInt(54);
            allRandomSet.add(utilInt);
        }

        Iterator<Integer> it=allRandomSet.iterator();
        int i=0;
        while (it.hasNext()){
            allCards[i]=target[it.next()];
            i++;
        }

        System.out.println(allRandomSet);

        int[] play1 = Arrays.copyOfRange(allCards, 0, 17);
        int[] play3 = Arrays.copyOfRange(allCards, 17, 34);
        int[] play2 = Arrays.copyOfRange(allCards, 34, 51);

        List playOne=new ArrayList();
        for(int l:play1){
            playOne.add(l);
        }

        Collections.sort(playOne);
        System.out.println(playOne);

    }


    public static void main(String[] args) {
        killRichMan();
    }

}




