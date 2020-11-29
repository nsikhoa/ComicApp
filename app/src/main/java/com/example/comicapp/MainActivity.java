package com.example.comicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.comicapp.adapter.ComicAdapter;
import com.example.comicapp.api.ApiGetComic;
import com.example.comicapp.interfaces.LayTruyenVe;
import com.example.comicapp.object.Comic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
GridView gdvDSTruyen;
ComicAdapter adapter;
ArrayList<Comic> comicArrayList;
EditText edtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiGetComic(this).execute();
    }
    private void init(){
        comicArrayList = new ArrayList<>();
//        comicArrayList.add(new Comic("Võ Đạo Độc Tôn", "Chapter 214", "http://st.truyenchon.com/data/comics/228/vo-dao-doc-ton.jpg"));
//        comicArrayList.add(new Comic("Yêu Thần Ký", "Chapter 304", "http://st.truyenchon.com/data/comics/85/yeu-than-ky.jpg"));
//        comicArrayList.add(new Comic("Hoàng tử Tennis", "Chapter 361", "http://st.truyenchon.com/data/comics/229/hoang-tu-tennis.jpg"));
//        comicArrayList.add(new Comic("Tây Du", "Chapter 219", "http://st.truyenchon.com/data/comics/156/tay-du.jpg"));
//        comicArrayList.add(new Comic("Dị Tộc Trùng Sinh", "Chapter 142", "http://st.truyenchon.com/data/comics/80/di-toc-trung-sinh.jpg"));
//        comicArrayList.add(new Comic("Mairimashita! Iruma-kun", "Chapter 182", "http://st.truyenchon.com/data/comics/113/mairimashita-iruma-kun.jpg"));

        adapter = new ComicAdapter(this,0, comicArrayList);
    }
    private void anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
    }
    private void setUp(){
        gdvDSTruyen.setAdapter(adapter);
    }
    private void setClick(){
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTimKiem.getText().toString();
                adapter.sortTruyen(s);
            }
        });
    }

    @Override
    public void start() {
        Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish(String data) {
        try {
            comicArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++){
                JSONObject o = arr.getJSONObject(i);
                comicArrayList.add(new Comic(o));
            }
            adapter = new ComicAdapter(this,0, comicArrayList);
            gdvDSTruyen.setAdapter(adapter);
        }catch(JSONException e){

        }
    }

    @Override
    public void errored() {
        Toast.makeText(this, "Error connection", Toast.LENGTH_SHORT).show();
    }
}