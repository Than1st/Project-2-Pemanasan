package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.R;

import org.w3c.dom.Text;

import java.util.List;

import data.MyData;

public class recyclerviewadapter extends RecyclerView.Adapter<recyclerviewadapter.MyViewHolder> {

    private Context mContext;
    private List<MyData> mData;

    public recyclerviewadapter(Context mContext, List<MyData> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public recyclerviewadapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.list_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( recyclerviewadapter.MyViewHolder holder, int position) {
        holder.txt_nim.setText(mData.get(position).getNim());
        holder.txt_nama.setText(mData.get(position).getNama());
        holder.txt_alamat.setText(mData.get(position).getAlamat());
        holder.txt_email.setText(mData.get(position).getEmail());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_nim;
        TextView txt_nama;
        TextView txt_alamat;
        TextView txt_email;
        public MyViewHolder(View view){
            super(view);

            txt_nim = view.findViewById(R.id.txt_nim);
            txt_nama = view.findViewById(R.id.txt_nama);
            txt_alamat = view.findViewById(R.id.txt_alamat);
            txt_email = view.findViewById(R.id.txt_email);
        }
    }
}
