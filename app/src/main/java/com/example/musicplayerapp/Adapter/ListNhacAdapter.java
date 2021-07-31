package com.example.musicplayerapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayerapp.Interface.OnItemClick;
import com.example.musicplayerapp.Model.BaiHat;
import com.example.musicplayerapp.R;

import java.util.ArrayList;

public class ListNhacAdapter extends RecyclerView.Adapter<ListNhacAdapter.ListNhacHolder> {

    ArrayList<BaiHat> listBaiHat;
    OnItemClick listener;

    public ListNhacAdapter(ArrayList<BaiHat> listBaiHat, OnItemClick listener) {
        this.listBaiHat = listBaiHat;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ListNhacHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_nhac, parent, false);
        ListNhacHolder viewHolder = new ListNhacHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListNhacAdapter.ListNhacHolder holder, int position) {
        final BaiHat baiHat= listBaiHat.get(position);
        if(baiHat==null){
            return;
        }
        holder.imgAnhCaSi.setImageResource(baiHat.getAvtBaiHat());
        holder.tvTenBaiHat.setText(baiHat.getTenBaiHat());
        holder.tvTenCaSi.setText(baiHat.getTenCaSi());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(baiHat);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBaiHat.size();
    }

    public class ListNhacHolder extends RecyclerView.ViewHolder {
        ImageView imgAnhCaSi;
        TextView tvTenBaiHat,tvTenCaSi;
        LinearLayout linearLayout;

        public ListNhacHolder(View itemView) {
            super(itemView);
            imgAnhCaSi=itemView.findViewById(R.id.imgAnhCaSi);
            tvTenBaiHat=itemView.findViewById(R.id.txtName);
            tvTenCaSi=itemView.findViewById(R.id.txtCasi);
            linearLayout=itemView.findViewById(R.id.layout_item);
        }
    }
}
