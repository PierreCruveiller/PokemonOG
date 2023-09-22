package com.og.pokemonog;

public class Pokemon {
    private int order;
    private String name;
    private String height;
    private String weight;
    private int frontResource;
    private String desc;
    private POKEMON_TYPE type1;
    private POKEMON_TYPE type2;
    public Pokemon() {
        order = 1;
        name = "Unknown";
        frontResource = R.drawable.p1;
        type1 = POKEMON_TYPE.Grass;
    }
    public Pokemon(int order, String name, int frontResource,
                   POKEMON_TYPE type1, POKEMON_TYPE type2, String height, String weight) {
        this.order = order;
        this.name = name;
        this.frontResource = frontResource;
        this.type1 = type1;
        this.type2 = type2;
        this.height = height;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getFrontResource() {
        return frontResource;
    }
    public void setFrontResource(int frontResource) {
        this.frontResource = frontResource;
    }
    public POKEMON_TYPE getType1() {
        return type1;
    }
    public void setType1(POKEMON_TYPE type1) {
        this.type1 = type1;
    }
    public POKEMON_TYPE getType2() {
        return type2;
    }
    public void setType2(POKEMON_TYPE type2) {
        this.type2 = type2;
    }
    public String getType1String() {
        return type1.name();
    }
    public String getType2String() {
        return type2.name();
    }
}

