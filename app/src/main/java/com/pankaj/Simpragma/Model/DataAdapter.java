package com.pankaj.Simpragma.Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pankaj.Simpragma.R;
import com.pankaj.Simpragma.View.DetailsAtivity;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    List<SearchModel.Hits> dataList;
    Context context;

    public DataAdapter(Context context, List<SearchModel.Hits> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_single, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(dataList.get(i).recipe.label);
        viewHolder.rating.setText(dataList.get(i).recipe.rate);
        Glide.with(context).load(dataList.get(i).recipe.image).into(viewHolder.thumble);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void clearList() {
        if (dataList != null) {
            dataList.clear();
            notifyDataSetChanged();
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, rating, tv_api_level;
        private ImageView thumble;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.nameTextView);
            rating = (TextView) view.findViewById(R.id.ratingTextView);
            thumble = (ImageView) view.findViewById(R.id.thumbImageView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  String url=  dataList.get(getAdapterPosition()).recipe.url;
                    Intent intent = new Intent(context, DetailsAtivity.class);
                    intent.putExtra("url", url);
                    context.startActivity(intent);
                }
            });

        }
    }

}