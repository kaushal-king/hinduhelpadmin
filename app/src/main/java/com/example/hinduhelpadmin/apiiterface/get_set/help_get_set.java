package com.example.hinduhelpadmin.apiiterface.get_set;

public class help_get_set {
    int id;
    String fullname,datehelp,hdisc,helper,hmob;

    public help_get_set(int id, String fullname, String datehelp, String hdisc, String helper, String hmob) {
        this.id = id;
        this.fullname = fullname;
        this.datehelp = datehelp;
        this.hdisc = hdisc;
        this.helper = helper;
        this.hmob = hmob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDatehelp() {
        return datehelp;
    }

    public void setDatehelp(String datehelp) {
        this.datehelp = datehelp;
    }

    public String getHdisc() {
        return hdisc;
    }

    public void setHdisc(String hdisc) {
        this.hdisc = hdisc;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    public String getHmob() {
        return hmob;
    }

    public void setHmob(String hmob) {
        this.hmob = hmob;
    }
}
