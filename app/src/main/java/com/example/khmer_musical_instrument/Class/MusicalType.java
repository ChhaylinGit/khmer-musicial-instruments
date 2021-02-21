package com.example.khmer_musical_instrument.Class;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MusicalType {
    int type_id;
    String type_name;
    List<Musical> musicalListlist;

    public MusicalType() {
    }

    public MusicalType(int type_id, String type_name,List<Musical> musicalListlist) {
        this.type_id = type_id;
        this.type_name = type_name;
        this.musicalListlist = musicalListlist;
    }

    public void addList(List<Musical> musicalListlist)
    {
        this.musicalListlist = musicalListlist;
        Log.e("wwwwwww",musicalListlist.size()+"");
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<Musical> getMusicalListlist() {
        return musicalListlist;
    }

    public void setMusicalListlist(List<Musical> musicalListlist) {
        this.musicalListlist = musicalListlist;
    }

    @Override
    public String toString() {
        return "MusicalType{" +
                "type_id='" + type_id + '\'' +
                ", type_name='" + type_name + '\'' +
                ", musicalListlist=" + musicalListlist +
                '}';
    }
}
