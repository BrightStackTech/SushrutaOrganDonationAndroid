package com.example.daandharma;

import java.io.Serializable;

public class HospitalsModel implements Serializable {
    String hname, hlocation, haddress, hstate, hnum, horgan;

    public HospitalsModel(String hname, String hlocation, String haddress, String hstate, String hnum, String horgan){
        this.hname = hname;
        this.hlocation = hlocation;
        this.haddress = haddress;
        this.hstate = hstate;
        this.hnum = hnum;
        this.horgan = horgan;
    }

    public String getHorgan(){
        return horgan;
    }

    public String getHname(){ return hname; }

    public String getHaddress(){
        return haddress;
    }

    public String getHstate(){
        return hstate;
    }

    public String getHnum(){
        return hnum;
    }

    public String getHlocation(){
        return hlocation;
    }

}
