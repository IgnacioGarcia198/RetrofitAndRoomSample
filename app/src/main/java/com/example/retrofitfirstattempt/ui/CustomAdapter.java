package com.example.retrofitfirstattempt.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofitfirstattempt.R;
import com.example.retrofitfirstattempt.databinding.CustomRowBinding;
import com.example.retrofitfirstattempt.model.RetroPhoto;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<RetroPhoto> dataList;
    private Context context;

    public CustomAdapter(Context context){
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CustomRowBinding dataBinding;
        //private TextView txtTitle;
        //private ImageView coverImage;

        CustomViewHolder(CustomRowBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
            itemView.setOnClickListener(this);
            //txtTitle = itemView.findViewById(R.id.title);
            //coverImage = itemView.findViewById(R.id.coverImage);
        }

        public void bind(RetroPhoto retroPhoto) {
            if(retroPhoto != null) {
                dataBinding.setRetroPhoto(retroPhoto);
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round);
                Glide.with(context).load(dataList.get(getAdapterPosition()).getThumbnailUrl()).apply(options).into(dataBinding.coverImage);
            }
            else {
                dataBinding.title.setText("Not data yet");
                dataBinding.coverImage.setImageResource(R.mipmap.ic_launcher_round);
            }
        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomRowBinding binding = CustomRowBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        if(dataList == null) return 0;
        return dataList.size();
    }

    public void setDataList(List<RetroPhoto> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }
}
