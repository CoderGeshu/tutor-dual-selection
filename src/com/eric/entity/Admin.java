package com.eric.entity;

/**
 * 管理员实体类
 *
 * @Date: 2020/5/20 22:09
 * @author: Eric
 */
public class Admin {
    private String ano;
    private String password;
    private String aname;
    private String sex;
    private String tel;
    private int typeno;

    public Admin(){
    }

    public Admin(String ano, String password, String aname, String sex,
                 String tel, int typeno) {
        this.ano = ano;
        this.password = password;
        this.aname = aname;
        this.sex = sex;
        this.tel = tel;
        this.typeno = typeno;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getTypeno() {
        return typeno;
    }

    public void setTypeno(int typeno) {
        this.typeno = typeno;
    }
}
