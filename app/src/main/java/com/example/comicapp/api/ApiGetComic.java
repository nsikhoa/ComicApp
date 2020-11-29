package com.example.comicapp.api;

import android.os.AsyncTask;

import com.example.comicapp.interfaces.LayTruyenVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiGetComic extends AsyncTask<Void, Void, Void> {
    String data;

    public ApiGetComic(LayTruyenVe layTruyenVe) {
        this.layTruyenVe = layTruyenVe;
        this.layTruyenVe.start();
    }

    LayTruyenVe layTruyenVe;
    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://jsonkeeper.com/b/8BTG")
                .build();
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch(IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      if (data == null){
          this.layTruyenVe.errored();
      }else {
          this.layTruyenVe.finish(data);
      }
    }
}
