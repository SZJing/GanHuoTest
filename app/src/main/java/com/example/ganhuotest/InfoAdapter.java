package com.example.ganhuotest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ganhuotest.bean.InfoGson;

import java.util.List;
import java.util.Random;

/**
 * Created by sl on 2018/2/11.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.viewHolder> {

    private Context context;
    private List<InfoGson.Info> List;
    private String Url;
    private int[] image = {R.drawable.image_1,R.drawable.image_2,R.drawable.image_3,
    R.drawable.image_4,R.drawable.image_5_2,R.drawable.image_6,R.drawable.image_7,
    R.drawable.image_8,R.drawable.image_9,R.drawable.image_10,
    R.drawable.image_11,R.drawable.image_12_1,R.drawable.image_13,R.drawable.image_14,R.drawable.image_15,
    R.drawable.image_17,R.drawable.image_18,R.drawable.image_19,R.drawable.image_20,
            R.drawable.image_21,R.drawable.image_22,R.drawable.image_24,R.drawable.image_25,
            R.drawable.image_26,R.drawable.image_27,R.drawable.image_28,R.drawable.image_29,R.drawable.image_30,
            R.drawable.image_31,R.drawable.image_32,R.drawable.image_33,};

    public InfoAdapter(Context context,List<InfoGson.Info> list){
        this.context = context;
        this.List = list;
    }

    @Override
    public InfoAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_info_item,parent,false);
        final viewHolder holder = new viewHolder(view);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Url = List.get(position).getUrl();
                Intent intent = new Intent(context,InfoActivity.class);
                intent.putExtra("Url",Url);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(InfoAdapter.viewHolder holder, int position) {
        holder.title.setText(List.get(position).getDesc());
        if (List.get(position).getWho() == null){
            holder.author.setText("佚名");
        }else {
            holder.author.setText(List.get(position).getWho());
        }
            Random random = new Random();
            int index = random.nextInt(image.length);
            Glide.with(context).load(image[index]).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView title;
        private TextView author;
        private LinearLayout item;

        public viewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
            author = (TextView) itemView.findViewById(R.id.author);
            item = (LinearLayout) itemView;
        }
    }
}
