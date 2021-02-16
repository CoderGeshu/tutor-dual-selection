package com.eric.entity;

/**
 * @Date: 2020/5/21 20:22
 * @author: Eric
 */
public class Major {
    private String mno;
    private String mname;
    private String dno;

    public Major() {
    }

    public Major(String mno, String mname, String dno) {
        this.mno = mno;
        this.mname = mname;
        this.dno = dno;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }
}
