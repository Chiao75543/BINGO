package com.example.bingo;

import android.sax.TextElementListener;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    public String text1,text2,text3,text4,text5,text6,text7,text8,text9;

    public void GetRandom(final onDataReadyCallback callback){
        Random random = new Random(); //產生Random物件
        List<Integer> randomList=new ArrayList<>();
        int max = 9;
        int i=0;
        //做出沒重複數字的array
        while (i<max){
            int r=random.nextInt(9)+1;
            if (!randomList.contains(r)){
                randomList.add(r);
                i++;
            }
        }
        //將數字放到個別的text裡
        text1= String.valueOf(randomList.get(0));text2= String.valueOf(randomList.get(1));
        text3= String.valueOf(randomList.get(2));text4= String.valueOf(randomList.get(3));
        text5= String.valueOf(randomList.get(4));text6= String.valueOf(randomList.get(5));
        text7= String.valueOf(randomList.get(6));text8= String.valueOf(randomList.get(7));
        text9= String.valueOf(randomList.get(8));

        callback.onDataReady(text1,text2,text3,text4,text5,text6,text7,text8,text9);
    }

    //獲取下一個數字
    public void getNextNum(final onNumberCallback onNumberCallback){
        Random random = new Random(); //產生Random物件
        int r=random.nextInt(9)+1;
        // 用來紀錄數字
        int mark = 0;
        String text[] = {text1,text2,text3,text4,text5,text6,text7,text8,text9};
        //判斷下一個數字是否等於九宮格內的任何數字
        for (int i=0;i<text.length;i++){
            if (String.valueOf(r) == text[i]){
                mark = i+1;
            }
        }
        onNumberCallback.NextNumber(String.valueOf(r),mark);
    }

    //當取得資料時透過onDataReady將9宮格返回
    interface onDataReadyCallback {
        void onDataReady(String text1,String text2,String text3,String text4
                ,String text5,String text6,String text7,String text8,String text9);
    }
    //當取得資料時透過onNumberCallback將下一個數字返回
    interface onNumberCallback{
        void NextNumber(String num,int mark);
    }
}

