package com.example.ourfirst.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourfirst.R;
import com.example.ourfirst.bean.ImageBean;

import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class ImageAdapter extends BannerAdapter<ImageBean, ImageAdapter.ImageHolder> {
    Context context;

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageHolder(LayoutInflater.from(context).inflate(R.layout.banner_image, parent, false));
    }

    @Override
    public void onBindView(ImageHolder holder, ImageBean data, int position, int size) {
        holder.imageView.setImageResource(data.getImageViewID());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ImageHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view_item);
        }

    }

    public ImageAdapter(Context context, List<ImageBean> mList) {
        super(mList);
        this.context = context;
    }


}
