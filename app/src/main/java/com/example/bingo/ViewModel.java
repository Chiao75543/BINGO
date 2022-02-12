package com.example.bingo;

import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;

public class ViewModel {

    //用Data Binding中的Observable來讓View和ViewModel溝通
    public ObservableField<String> Number = new ObservableField<>();
    public ObservableField<String> data1= new ObservableField<>();
    public ObservableField<String> data2= new ObservableField<>();
    public ObservableField<String> data3= new ObservableField<>();
    public ObservableField<String> data4= new ObservableField<>();
    public ObservableField<String> data5= new ObservableField<>();
    public ObservableField<String> data6= new ObservableField<>();
    public ObservableField<String> data7= new ObservableField<>();
    public ObservableField<String> data8= new ObservableField<>();
    public ObservableField<String> data9= new ObservableField<>();
    public ObservableField<String> MarkNumber= new ObservableField<>();
    public int ch;


    private Model model = new Model();
    public void refresh() {
        model.GetRandom(new Model.onDataReadyCallback() {
            @Override
            public void onDataReady(String text1, String text2, String text3, String text4,
            String text5, String text6, String text7, String text8, String text9) {
                data1.set(text1);data2.set(text2);data3.set(text3);data4.set(text4);
                data5.set(text5);data6.set(text6);data7.set(text7);data8.set(text8);
                data9.set(text9);
            }
        });
    }

    //呼叫Model的NextNumber
    public int updateNumber(){
        model.getNextNum(new Model.onNumberCallback() {
            @Override
            public void NextNumber(String num,int mark) {
                Number.set("下一個數字:"+num);
                MarkNumber.set(String.valueOf(mark));
                ch = mark;
            }
        });
        return ch;
    }
    //將observable的變數設回初值
    public void initValue(){
        Number.set("");
        MarkNumber.set("");
        data1.set("");data2.set("");data3.set("");data4.set("");
        data5.set("");data6.set("");data7.set("");data8.set("");
        data9.set("");
    }
}
