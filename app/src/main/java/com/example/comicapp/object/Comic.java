package com.example.comicapp.object;

import org.json.JSONException;
import org.json.JSONObject;

public class Comic {
    private String nameComic, nameChap, LinkImage;

    public Comic(){

    }
    public Comic(JSONObject o) throws JSONException {
        nameComic = o.getString("nameComic");
        nameChap = o.getString("nameChap");
        LinkImage = o.getString("linkImage");
    }
    public Comic(String nameComic, String nameChap, String linkImage) {
        this.nameComic = nameComic;
        this.nameChap = nameChap;
        LinkImage = linkImage;
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getNameChap() {
        return nameChap;
    }

    public void setNameChap(String nameChap) {
        this.nameChap = nameChap;
    }

    public String getLinkImage() {
        return LinkImage;
    }

    public void setLinkImage(String linkImage) {
        LinkImage = linkImage;
    }
}
