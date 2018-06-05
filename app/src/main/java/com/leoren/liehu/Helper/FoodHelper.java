package com.leoren.liehu.Helper;

import android.view.ContextThemeWrapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * 三餐模块的帮助类
 * @Author Leoren
 * @Date 2018/5/29 10:51
 */
public class FoodHelper {

    public static List<FoodEnergy> initFoodKinds(InputStream input){

        List<FoodEnergy> list = null;
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input,"UTF-8"));
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            Gson gson = new Gson();
            list = gson.fromJson(sb.toString(),new TypeToken<List<FoodEnergy>>(){}.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }



    //食物能量对照类
    public class FoodEnergy{

        private String name;

        private int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public FoodEnergy() {
        }

        public FoodEnergy(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }



}
