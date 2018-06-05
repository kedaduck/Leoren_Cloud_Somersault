package com.leoren.liehu.MyView.MyWheel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.MyView.MyWheel.OtherView.OnWheelChangedListener;
import com.leoren.liehu.MyView.MyWheel.OtherView.OnWheelScrollListener;
import com.leoren.liehu.MyView.MyWheel.OtherView.WheelView;
import com.leoren.liehu.MyView.MyWheel.adapter.AbstractWheelTextAdapter;
import com.leoren.liehu.R;
import com.leoren.liehu.User.BaseUserInfo.UserFood.Food;

import java.util.ArrayList;

/**
 * 食物选择滑轮对话框
 * @Author Leoren
 * @Date 2018/5/26 16:12
 */
public class FoodChooseWheelDialog extends Dialog implements View.OnClickListener{



    /**
     * 食物选择滑轮
     */
    private WheelView foodWheelView;
    private WheelView weightWheelView;

    private TextView titleTextView;

    private Button sureButton;
    private Button closeButton;

    private Dialog dialog;

    //变量
    private ArrayList<String> food_date = new ArrayList<>();
    private ArrayList<String> weight_date = new ArrayList<>();

    private int nowFoodId = 0;
    private int nowWeightId = 0;
    private String foodStr;
    private String weightStr;


    //常量
    private final int MAX_TEXT_SIZE = 18;
    private final int MIN_TEXT_SIZE = 16;

    private Context context;
    private DateChooseInterface dateChooseInterface;

    private FoodTextAdapter foodTextAdapter;
    private FoodTextAdapter weightTextAdapter;

    public FoodChooseWheelDialog(Context context, DateChooseInterface dateChooseInterface, ArrayList<String> list){
        super(context);
        this.context = context;
        this.dateChooseInterface = dateChooseInterface;
        dialog = new Dialog(context, R.style.dialog);

        initView();
        initFoodContent(list);
        initWeightData();
        initListener();
    }

    public void initListener(){

        foodWheelView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) foodTextAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, foodTextAdapter);
                foodStr = food_date.get(wheel.getCurrentItem()) + "";
            }

        });

        foodWheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) foodTextAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, foodTextAdapter);
            }
        });

        weightWheelView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) weightTextAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, weightTextAdapter);
                weightStr = weight_date.get(wheel.getCurrentItem());
            }
        });

        weightWheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) weightTextAdapter.getItemText(wheel.getCurrentItem());
                setTextViewStyle(currentText, weightTextAdapter);
            }
        });

    }

    private void initFoodContent(ArrayList<String> list){
        food_date.clear();
        for(int i = 0 ; i < list.size(); i++){
            food_date.add(list.get(i));
            if(nowFoodId == i){
                nowFoodId = food_date.size() - 1;
            }
        }

        foodTextAdapter = new FoodTextAdapter(context, food_date, nowFoodId, MAX_TEXT_SIZE, MIN_TEXT_SIZE);
        foodWheelView.setVisibleItems(5);
        foodWheelView.setViewAdapter(foodTextAdapter);
        foodWheelView.setCurrentItem(nowFoodId);
        foodStr = food_date.get(nowFoodId) + "";
        setTextViewStyle(foodStr, foodTextAdapter);
    }

    private void initWeightData(){
        weight_date.clear();
        for(int i = 0; i <= 100; i++){
            weight_date.add(i*50 + "");
            if(nowWeightId == i){
                nowWeightId = weight_date.size() -1;
            }
        }

        weightTextAdapter = new FoodTextAdapter(context, weight_date, nowWeightId, MAX_TEXT_SIZE, MIN_TEXT_SIZE);
        weightWheelView.setVisibleItems(5);
        weightWheelView.setViewAdapter(weightTextAdapter);
        weightWheelView.setCurrentItem(nowWeightId);
        weightStr = weight_date.get(nowWeightId) + "";
        setTextViewStyle(weightStr, weightTextAdapter);
    }

    private void initView(){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_date_choose, null);
        dialog.setContentView(view);
        //foodWheelView = findViewById(R.id.food_wheel);
        //weightWheelView = findViewById(R.id.weight_wheel);
        titleTextView = findViewById(R.id.title_tv);
        sureButton = findViewById(R.id.sure_btn);
        closeButton = findViewById(R.id.date_choose_close_btn);

        sureButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);

    }

    public void setFoodDiaogTitle(String title){
        titleTextView.setText(title);
    }



/*
    public FoodChooseWheelDialog(Class<MainFunction> context, DateChooseInterface themeResId) {
        super(context, themeResId);
    }
*/
    /**
     * 设置文字大小
     */
    public void setTextViewStyle(String curritemText, FoodTextAdapter adapter){
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for(int i = 0 ; i < size; i++){
            TextView textView = (TextView) arrayList.get(i);
            currentText = textView.getText().toString();
            if(curritemText.equals(currentText)){
                textView.setTextSize(MAX_TEXT_SIZE);
                textView.setTextColor(context.getResources().getColor(R.color.text_10));
            }else{
                textView.setTextSize(MIN_TEXT_SIZE);
                textView.setTextColor(context.getResources().getColor(R.color.text_11));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_btn:
                dateChooseInterface.getFoodName(foodStr);
                dateChooseInterface.getWeight(weightStr);
                dismissDialog();
                break;
            case R.id.date_choose_close_btn:
                dismissDialog();
                break;
            default:
                break;
        }

    }

    private void dismissDialog(){

        if(Looper.myLooper() != Looper.getMainLooper()){
            return;
        }

        if(null == dialog || dialog.isShowing() || null == context || ((Activity) context).isFinishing()){
            return;
        }
        dialog.dismiss();
        this.dismiss();

    }

    private void showDateChooseDialog(){
        if(Looper.myLooper()  != Looper.getMainLooper()){
            return;
        }
        if(null == context || ((Activity) context).isFinishing()){
            return;
        }
        if(null != dialog){
            dialog.show();
            return;
        }
        if(null == dialog){
            return;
        }

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     * 滚轮的adapter
     */
    private class FoodTextAdapter extends AbstractWheelTextAdapter{

        ArrayList<String> list;

        protected FoodTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize){
            super(context, R.layout.item_birth_year,R.id.tempValue,currentItem,maxsize,minsize);
            this.list = list;
        }

        @Override
        public View getItem(int index, View convertView, ViewGroup parent) {
            View view = super.getItem(index, convertView, parent);
            return view;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            String str = list.get(index) + "";
            return str;
        }
    }

    public interface DateChooseInterface{
        void getFoodName(String foodName);
        void getWeight(String weight);
    }
}
