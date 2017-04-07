package com.tonyj.myweb.po;

import com.tonyj.frame.orm.BaseEntity;

public class BsDictionary extends BaseEntity {

    private String diccode;

    private String dicname;

    private String dicnum;

    private String dickey;

    private String dicvalue;


    public String getDiccode() {
        return diccode;
    }

    public void setDiccode(String diccode) {
        this.diccode = diccode == null ? null : diccode.trim();
    }

    public String getDicname() {
        return dicname;
    }

    public void setDicname(String dicname) {
        this.dicname = dicname == null ? null : dicname.trim();
    }

    public String getDicnum() {
        return dicnum;
    }

    public void setDicnum(String dicnum) {
        this.dicnum = dicnum == null ? null : dicnum.trim();
    }

    public String getDickey() {
        return dickey;
    }

    public void setDickey(String dickey) {
        this.dickey = dickey == null ? null : dickey.trim();
    }

    public String getDicvalue() {
        return dicvalue;
    }

    public void setDicvalue(String dicvalue) {
        this.dicvalue = dicvalue == null ? null : dicvalue.trim();
    }

}