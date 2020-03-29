package com.itany.nmms.constant;

public enum ProductTypeConstant {
    ENABLE(1,"启用"),DISABLE(0,"禁用");
    private int val;
    private  String txt;

    ProductTypeConstant(int val, String txt) {
        this.val = val;
        this.txt = txt;
    }

    public int getVal() {
        return val;
    }

    public String getTxt() {
        return txt;
    }
}
