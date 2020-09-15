package com.example.hinduhelpadmin.apiiterface.get_set;

public class help_get_set {
    int id;
    String fullname,datehelp,hdisc,helper,hmob,mobileno,typehelp,helpmode,subhelp;

    public help_get_set(int id, String fullname, String datehelp, String hdisc, String helper, String hmob,String mobileno,String typehelp,String helpmode,String subhelp) {
        this.id = id;
        this.fullname = fullname;
        this.datehelp = datehelp;
        this.hdisc = hdisc;
        this.helper = helper;
        this.hmob = hmob;
        this.mobileno=mobileno;
        this.typehelp=typehelp;
        this.helpmode=helpmode;
        this.subhelp=subhelp;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }


    public String getTypehelp() {
        return typehelp;
    }

    public void setTypehelp(String typehelp) {
        this.typehelp = typehelp;
    }

    public String getHelpmode() {
        return helpmode;
    }

    public void setHelpmode(String helpmode) {
        this.helpmode = helpmode;
    }

    public String getSubhelp() {
        return subhelp;
    }

    public void setSubhelp(String subhelp) {
        this.subhelp = subhelp;
    }
}
