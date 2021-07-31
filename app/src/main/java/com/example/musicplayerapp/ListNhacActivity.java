package com.example.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.musicplayerapp.Adapter.ListNhacAdapter;
import com.example.musicplayerapp.Interface.OnItemClick;
import com.example.musicplayerapp.Model.BaiHat;

import java.util.ArrayList;

public class ListNhacActivity extends AppCompatActivity {


    ListNhacAdapter adapter;
    RecyclerView recyclerView;
    public static final ArrayList<BaiHat> listBaiHat = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nhac);
        recyclerView = findViewById(R.id.rcv_list_nhac);
        addMusic();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new ListNhacAdapter(listBaiHat, new OnItemClick() {
            @Override
            public void onItemClick(BaiHat baiHat) {
                Intent intent = new Intent(ListNhacActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("baihat", baiHat);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void addMusic() {

        listBaiHat.add(new BaiHat(0,"Mình bước qua đời nhau", "Lê Bảo Bình", R.drawable.lebaobinh, R.raw.minhbuocquadoinhauremix));
        listBaiHat.add(new BaiHat(1,"Big city boy", "Lê Bảo Bình", R.drawable.binz, R.raw.bigcityboi));
        listBaiHat.add(new BaiHat(2,"Bỏ lỡ 1 người", "Lê Bảo Bình", R.mipmap.ic_launcher, R.raw.bolo1nguoiremix));
        listBaiHat.add(new BaiHat(3,"Lá xa lìa cành", "Lê Bảo Bình", R.drawable.lebaobinh, R.raw.laxaliacanhremix));
        listBaiHat.add(new BaiHat(4,"Thích thì đến", "Lê Bảo Bình", R.drawable.lebaobinh, R.raw.thichthidenremix));

    }


}