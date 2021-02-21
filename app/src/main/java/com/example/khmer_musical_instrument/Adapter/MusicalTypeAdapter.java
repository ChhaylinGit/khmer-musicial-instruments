package com.example.khmer_musical_instrument.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khmer_musical_instrument.Callback.OnRecyclerClickListener;
import com.example.khmer_musical_instrument.Class.Musical;
import com.example.khmer_musical_instrument.Class.MusicalType;
import com.example.khmer_musical_instrument.R;

import java.util.List;

public class MusicalTypeAdapter extends RecyclerView.Adapter<MusicalTypeAdapter.MusicalTypeViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    List<MusicalType> musicalTypeList;
    Context context;
    OnRecyclerClickListener onRecyclerClickListener;

    public MusicalTypeAdapter(List<MusicalType> musicalTypeList,Context context,OnRecyclerClickListener onRecyclerClickListener) {
        this.musicalTypeList = musicalTypeList;
        this.context = context;
        this.onRecyclerClickListener=onRecyclerClickListener;
    }

//    public void setMusicalTypeList(List<MusicalType> musicalTypeList){
//        this.musicalTypeList = musicalTypeList;
//    }

    @NonNull
    @Override
    public MusicalTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_musical_type, viewGroup, false);
        return new MusicalTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicalTypeViewHolder holder, int position) {
        MusicalType musicalType = musicalTypeList.get(position);
        holder.tvItemTitle.setText(musicalType.getType_name());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.rvSubItem.getContext(),2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.rvSubItem.getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setInitialPrefetchItemCount(musicalType.getMusicalListlist().size());


        MusicalAdapter subItemAdapter = new MusicalAdapter(musicalType.getMusicalListlist(),context,onRecyclerClickListener);

        Log.e("bbbbbbbbb",musicalType.getMusicalListlist().size()+"");

        holder.rvSubItem.setLayoutManager(gridLayoutManager);
        holder.rvSubItem.setAdapter(subItemAdapter);
        holder.rvSubItem.setRecycledViewPool(viewPool);

    }

    @Override
    public int getItemCount() {
        return musicalTypeList.size();
    }

    class MusicalTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemTitle;
        private RecyclerView rvSubItem;

        MusicalTypeViewHolder(View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tv_type_title);
            rvSubItem = itemView.findViewById(R.id.rv_musical);
        }

        public void bind(final Musical musical, final OnRecyclerClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(musical);
                }
            });
        }
    }


}
