package com.example.comicapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.comicapp.R;
import com.example.comicapp.object.Comic;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends ArrayAdapter<Comic> {
    private Context ct;
    private ArrayList<Comic> arr;
    public ComicAdapter(@NonNull Context context, int resource, @NonNull List<Comic> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    public void sortTruyen(String s){
        s = s.toUpperCase();
        int k = 0;
        for (int i = 0; i < arr.size(); i++){
            Comic comic = arr.get(i);
            String ten = comic.getNameComic().toUpperCase();
            if(ten.indexOf(s) >= 0){
                arr.set(i, arr.get(k));
                arr.set(k, comic);
                k++;
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_comic, null);
        }
        if(arr.size() > 0) {
            Comic comic = this.arr.get(position);
            TextView comicName = convertView.findViewById(R.id.txvComicName);
            TextView comicChap = convertView.findViewById(R.id.txvChapName);
            ImageView imgAnhTruyen = convertView.findViewById(R.id.imgAnh);

            comicName.setText(comic.getNameComic());
            comicChap.setText(comic.getNameChap());
            Glide.with(this.ct).load(comic.getLinkImage()).into(imgAnhTruyen);
        }
        return convertView;
    }
}
