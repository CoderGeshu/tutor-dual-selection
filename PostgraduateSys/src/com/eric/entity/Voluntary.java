package com.eric.entity;

/**
 * @Date: 2020/5/21 20:26
 * @author: Eric
 */
public class Voluntary {
    private String sno;
    private String tno;
    private int status;

    public Voluntary() {
    }

    public Voluntary(String sno, String tno, int status) {
        this.sno = sno;
        this.tno = tno;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
