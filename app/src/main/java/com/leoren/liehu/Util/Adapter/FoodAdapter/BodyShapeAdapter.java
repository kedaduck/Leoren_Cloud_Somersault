package com.leoren.liehu.Util.Adapter.FoodAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoren.liehu.Function.Food.MyBody.BodyShapeShow;
import com.leoren.liehu.R;

import java.util.List;

/**
 * @Author Leoren
 * @Date 2018/6/20 15:03
 * 形体展示页面的Adapter
 */
public class BodyShapeAdapter extends RecyclerView.Adapter<BodyShapeAdapter.ViewHolder>{

    private Context context;
    private List<BodyShapeShow> showList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout bodyShapeShowItem;
        TextView photeTime;
        ImageView photeImg;

        public ViewHolder(View itemView) {
            super(itemView);
            bodyShapeShowItem = itemView.findViewById(R.id.bodyshape_item);
            photeTime = itemView.findViewById(R.id.body_photo_time);
            photeImg = itemView.findViewById(R.id.body_shape_phote);
        }
    }

    public BodyShapeAdapter(List<BodyShapeShow> list){
        showList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.bodyshapeitem, parent, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BodyShapeShow shapeShow = showList.get(position);
        holder.photeTime.setText(shapeShow.getPhotoTime());
        //holder.photeImg.setImageResource(R.drawable.head_icon);
        //Glide.with(context).load(shapeShow.getImgUrl()).into(holder.photeImg);
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }


}
