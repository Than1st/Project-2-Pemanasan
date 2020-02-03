package com.example.project2panas.Adapter;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2panas.Model.ModelData;
import com.example.project2panas.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems;
    private Context context;

    public AdapterData (Context context, List<ModelData> items){
        this.mItems = items;
        this.context = context;

    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        HolderData holderData = new HolderData(layout);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelData md = mItems.get(position);
        holder.tvnama.setText(md.getNama());
        holder.tvnim.setText(md.getNIM());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView tvnama,tvnim;

        public HolderData (View view){
            super(view);
            tvnama = (TextView) view.findViewById(R.id.nama);
            tvnim = (TextView) view.findViewById(R.id.nim);

        }
    }
}
