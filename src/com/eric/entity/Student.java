package com.eric.entity;

/**
 * @Date: 2020/5/21 20:17
 * @author: Eric
 */
public class Student {
    private String sno;
    private String password;
    private String sname;
    private String sex;
    private String birth;
    private float gpa;
    private String tel;
    private String email;
    private String description;
    private String mno;
    private String tno;

    public Student() {
    }

    public Student(String sno, String password, String sname, String sex,
                   String birth, float gpa, String tel, String email,
                   String description, String mno, String tno) {
        this.sno = sno;
        this.password = password;
        this.sname = sname;
        this.sex = sex;
        this.birth = birth;
        this.gpa = gpa;
        this.tel = tel;
        this.email = email;
        this.description = description;
        this.mno = mno;
        this.tno = tno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
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

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
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

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }
}
