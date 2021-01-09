package com.kanagalatech.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kanagalatech.newsapp.NewsWebView;
import com.kanagalatech.newsapp.R;
import com.kanagalatech.newsapp.Response.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<Result> arrayList;

    private LayoutInflater mInflater;
    Context context;

    public NewsAdapter(Context context, List<Result> arrayList) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.news_list, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, final int position) {
        holder.myTextView.setText(arrayList.get(position).getTitle());
        final String webUl = arrayList.get(position).getUrl();

        // Picasso.get().load(arrayList.get(position).getUrl()).into(holder.img);
        Glide.with(context)
                .load(arrayList.get(position))
                .override(300, 200)
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent().setClass(context, NewsWebView.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                i.putExtra("webUrl", webUl);
                context.startActivity(i);
            }
        });

    }

    // total number of rows


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tital_tv);
            img = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }

        public int getItemCount() {
            return arrayList.size();
        }


        @Override
        public void onClick(View v) {

        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }
}