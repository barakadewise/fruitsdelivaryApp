package com.example.fruitsapp.Helper;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.fruitsapp.Activities.ShowDetailsActivity;
import com.example.fruitsapp.Domain.Popular;
import com.example.fruitsapp.Interface.ChangeNumerItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDb tinyDb;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDb = new  TinyDb(context);
    }

    public  void insertPopular(Popular items){
        ArrayList<Popular>popularList=getListCart();
                boolean existAlready =false;
                int n =0;
                for (int i =0;i<popularList.size();i++){
                    if(popularList.get(i).getTitle().equals(items.getTitle())){
                        existAlready=true;
                        n=i;
                        break;
                    }
                }
                if (existAlready){
                    popularList.get(n).setNumberIncart(items.getNumberIncart());
                }
                else {
                    popularList.add(items);
                }
                tinyDb.putListObject("CartList",popularList);
                Toast.makeText(context, "Successfully added to you Cart!", Toast.LENGTH_SHORT).show();


    }
    public ArrayList<Popular>getListCart(){
        return tinyDb.getListObject("CartList");
    }
    public void  plusNumberItems(ArrayList<Popular> listPopular , int position, ChangeNumerItemsListener changeNumerItemsListener){
        listPopular.get(position).setNumberIncart(listPopular.get(position).getNumberIncart()+1);
        tinyDb.putListObject("CartList",listPopular);
        changeNumerItemsListener.change();
    }
    public void  minusNumberItems(ArrayList<Popular> listPopular , int position, ChangeNumerItemsListener changeNumerItemsListener){
        if (listPopular.get(position).getNumberIncart()==1){
            listPopular.remove(position);
        }else {
            listPopular.get(position).setNumberIncart(listPopular.get(position).getNumberIncart()-1);

        }
        tinyDb.putListObject("CartList",listPopular);
        changeNumerItemsListener.change();

    }
    public double getTotalFee(){
        ArrayList<Popular>listPopular =getListCart();
        double fee =0;
        for (int i=0;i<listPopular.size();i++){
            fee=fee+(listPopular.get(i).getFee()*listPopular.get(i).getNumberIncart());
        }
        return fee;
    }


}
