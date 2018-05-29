package com.leoren.liehu.util.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoren.liehu.R;
import com.leoren.liehu.User.BaseUserInfo.UserFood.ChooseFoodItem;

import java.util.List;

/**
 * 食物选择适配器
 * @Author Leoren
 * @Date 2018/5/27 9:10
 */
public class ChooseFoodKindAdapter extends RecyclerView.Adapter<ChooseFoodKindAdapter.ViewHolder> {

    private Context context;

    private List<ChooseFoodItem> chooseFoodItemList;



    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout chooseFoodKindList;
        TextView foodKindName;

        public ViewHolder(View itemView) {
            super(itemView);
            chooseFoodKindList = itemView.findViewById(R.id.item_choose_food_kind);
            foodKindName = itemView.findViewById(R.id.chooseFood_text);
        }
    }

    public ChooseFoodKindAdapter(List<ChooseFoodItem> list){
        chooseFoodItemList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_choose_foodkind, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChooseFoodItem item = chooseFoodItemList.get(position);
        holder.foodKindName.setText(item.getFoodKindName());


    }

    @Override
    public int getItemCount() {
        return chooseFoodItemList.size();
    }

}
