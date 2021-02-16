package com.eric.entity;

/**
 * @Date: 2020/5/21 20:21
 * @author: Eric
 */
public class Dept {
    private String dno;
    private String dname;
    private String ano;

    public Dept() {

    }

    public Dept(String dno, String dname, String ano) {
        this.dno = dno;
        this.dname = dname;
        this.ano = ano;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
