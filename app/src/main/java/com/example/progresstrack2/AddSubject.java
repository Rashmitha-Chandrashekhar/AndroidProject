package com.example.progresstrack2;

public class AddSubject {
    String sub,phn;

    public AddSubject() {}

    public AddSubject(String phn, String sub) {
        this.phn = phn;
        this.sub = sub;
    }


/* public AddSubject(String sub, String phn)
    {
        this.sub=sub;
        this.phn=phn;
    }*/

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

}
