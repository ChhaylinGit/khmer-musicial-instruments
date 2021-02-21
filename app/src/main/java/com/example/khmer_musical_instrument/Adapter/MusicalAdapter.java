package com.example.khmer_musical_instrument.Adapter;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khmer_musical_instrument.Callback.OnRecyclerClickListener;
import com.example.khmer_musical_instrument.Class.Musical;
import com.example.khmer_musical_instrument.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicalAdapter extends RecyclerView.Adapter<MusicalAdapter.MusicalViewHolder>  {
    private List<Musical> musicalList;
    private Context context;
    private OnRecyclerClickListener onRecyclerClickListener;

    public MusicalAdapter(List<Musical> musicalList,Context context,OnRecyclerClickListener onRecyclerClickListener) {
        this.musicalList = musicalList;
        this.context = context;
        this.onRecyclerClickListener = onRecyclerClickListener;
    }

    @NonNull
    @Override
    public MusicalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_musical, viewGroup, false);
        return new MusicalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicalViewHolder holder, int position) {
        Musical musical = musicalList.get(position);
        Log.e("rrrrrrrrrr","Ad "+musical.getName());
        holder.tv_musical_title.setText(musical.getName());
        Picasso.with(context).load(musical.getImage()).into(holder.im_musical);
        holder.bind(musical,onRecyclerClickListener);
    }

    @Override
    public int getItemCount() {
        return musicalList.size();
    }

    class MusicalViewHolder extends RecyclerView.ViewHolder {
        private ImageView im_musical;
        private TextView tv_musical_title;
        private TextView tv_musical_desc;
        private CardView cardView;

        MusicalViewHolder(View itemView) {
            super(itemView);
            tv_musical_title = itemView.findViewById(R.id.tv_musical_title);
            tv_musical_desc = itemView.findViewById(R.id.tv_musical_desc);
            im_musical = itemView.findViewById(R.id.img_musical);
            cardView = itemView.findViewById(R.id.cardViewItem);
        }

        public void bind(final Musical musical, final OnRecyclerClickListener listener) {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(musical);
                }
            });
        }
    }
}
