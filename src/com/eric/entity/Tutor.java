package com.eric.entity;

/**
 * @Date: 2020/5/21 20:17
 * @author: Eric
 */
public class Tutor {
    private String tno;
    private String password;
    private String tname;
    private String sex;
    private String birth;
    private String title;
    private String tel;
    private String email;
    private String description;
    private int ability;
    private String mno;

    public Tutor() {

    }

    public Tutor(String tno, String password, String tname, String sex,
                 String birth, String title, String tel, String email,
                 String description, int ability, String mno) {
        this.tno = tno;
        this.password = password;
        this.tname = tname;
        this.sex = sex;
        this.birth = birth;
        this.title = title;
        this.tel = tel;
        this.email = email;
        this.description = description;
        this.ability = ability;
        this.mno = mno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }
}
