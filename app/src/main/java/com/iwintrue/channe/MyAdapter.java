package com.iwintrue.channe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import static com.iwintrue.channe.DataUtils.leftStepList;
import static com.iwintrue.channe.DataUtils.rightStepList;

/**
 * Created by zhoukai on 2017/7/3.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements TouchInterface {

    private Context context;
    //是否显示delete
    public boolean isShow;

    public List<DataBean> getList() {
        return list;
    }

    public void setList(List<DataBean> list) {
        this.list = list;
    }

    private List<DataBean> list;

    public MyAdapter(Context context, List<DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_des.setText(list.get(position).name);
        holder.tv_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBean bean = list.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context,"删除了"+bean.name+"频道",Toast.LENGTH_SHORT).show();
            }
        });

        holder.tv_des.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isShow = true;
                notifyDataSetChanged();
                return true;
            }
        });

        if (isShow) {
            holder.iv_icon.setVisibility(View.VISIBLE);
        } else {
            holder.iv_icon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onMove(int currentPosition, int targetPosition) {

        Collections.swap(list, currentPosition, targetPosition);
        if (targetPosition < currentPosition) {
            List<DataBean> subList = list.subList(targetPosition + 1, currentPosition + 1);
            //向右移一位
            rightStepList(0, subList);
        } else {
            List<DataBean> subList = list.subList(currentPosition, targetPosition);
            //向左移一位
            leftStepList(0, subList);
        }
//        Log.i("log","-----------toArray------------" + Arrays.toString(list.toArray()));
        notifyItemMoved(currentPosition, targetPosition);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    public ImageView iv_icon;
    public TextView tv_des;

    public MyViewHolder(View itemView) {
        super(itemView);
        iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        tv_des = (TextView) itemView.findViewById(R.id.tv_des);
    }
}

