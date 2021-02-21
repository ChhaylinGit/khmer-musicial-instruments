package com.example.khmer_musical_instrument.Class;

public class Musical {
    int type_id;
    int id;
    String name;
    String image;
    String desc;
    boolean status;

    public Musical(int type_id, int id, String name, String image, String desc, boolean status) {
        this.type_id = type_id;
        this.id = id;
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.status = status;
    }

    public Musical(String name) {
        this.name = name;
    }

    public Musical() {
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
