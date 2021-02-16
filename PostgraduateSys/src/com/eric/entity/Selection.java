package com.eric.entity;

/**
 * @Date: 2020/6/7 9:21
 * @author: Eric
 */
public class Selection {
    private String sno;
    private String tno;

    public Selection(){
    }

    public Selection(String sno, String tno) {
        this.sno = sno;
        this.tno = tno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }
}
