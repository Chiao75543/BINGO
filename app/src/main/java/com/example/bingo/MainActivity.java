package com.example.bingo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bingo.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity  {
    private ActivityMainBinding binding;
    private ViewModel viewModel = new ViewModel();
    //mark來判斷是否達成bingo條件
    private Boolean mark1,mark2,mark3,mark4,mark5,mark6,mark7,mark8,mark9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setViewModel(viewModel);
        mark1 = mark2 = mark3 = mark4 =mark5 = mark6 =mark7 =mark8 =mark9 = false;

        //遊戲開始鍵
        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更新九宮格數字,呼叫ViewModel的refresh
                viewModel.refresh();
            }
        });
        //下一個數字鍵
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果不按遊戲開始就不能按下一個數字
                if(binding.text1.getText()==""){
                    Toast.makeText(MainActivity.this,"請先按下遊戲開始鍵",Toast.LENGTH_SHORT).show();
                }
                else {
                    //change是判斷哪一格需要轉換顏色
                    int change = viewModel.updateNumber();
                    ChangeColor(change);
                    IsBingo();
                }
            }
        });
        //重新開始鍵
        binding.restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.initValue();
                initBackground();
            }
        });
    }
    //改變文字顏色背景
    private void ChangeColor(int num) {
        switch (num) {
            case 1:
                binding.text1.setBackgroundColor(Color.YELLOW);
                mark1 = true;
                break;
            case 2:
                binding.text2.setBackgroundColor(Color.YELLOW);
                mark2 = true;
                break;
            case 3:
                binding.text3.setBackgroundColor(Color.YELLOW);
                mark3 = true;
                break;
            case 4:
                binding.text4.setBackgroundColor(Color.YELLOW);
                mark4 = true;
                break;
            case 5:
                binding.text5.setBackgroundColor(Color.YELLOW);
                mark5 = true;
                break;
            case 6:
                binding.text6.setBackgroundColor(Color.YELLOW);
                mark6 = true;
                break;
            case 7:
                binding.text7.setBackgroundColor(Color.YELLOW);
                mark7 = true;
                break;
            case 8:
                binding.text8.setBackgroundColor(Color.YELLOW);
                mark8 = true;
                break;
            case 9:
                binding.text9.setBackgroundColor(Color.YELLOW);
                mark9 = true;
                break;
        }
    }
    //判斷是否bingo
    private void IsBingo(){
       if(mark1&&mark2&&mark3||mark4&&mark5&&mark6||mark7&&mark8&&mark9||mark1&&mark4&&mark7
          ||mark2&&mark5&&mark8||mark3&&mark6&&mark9||mark1&&mark5&&mark9||mark7&&mark5&&mark3){
           CreateDialog();
       }
    }
    //初始化mark
    private void initBackground(){
        mark1 = mark2 = mark3 = mark4 =mark5 = mark6 =mark7 =mark8 =mark9 = false;
        binding.text1.setBackgroundColor(Color.WHITE);
        binding.text2.setBackgroundColor(Color.WHITE);
        binding.text3.setBackgroundColor(Color.WHITE);
        binding.text4.setBackgroundColor(Color.WHITE);
        binding.text5.setBackgroundColor(Color.WHITE);
        binding.text6.setBackgroundColor(Color.WHITE);
        binding.text7.setBackgroundColor(Color.WHITE);
        binding.text8.setBackgroundColor(Color.WHITE);
        binding.text9.setBackgroundColor(Color.WHITE);
    }
    //創建對話框
    private void CreateDialog(){
        AlertDialog.Builder alertDialog =
                new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("你Bingo了!");
        alertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            //按確定就初始化所有介面跟值
            public void onClick(DialogInterface dialog, int which) {
                viewModel.initValue();
                initBackground();
            }
        });
        alertDialog.show();
    }
}