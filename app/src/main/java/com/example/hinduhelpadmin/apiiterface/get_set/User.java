package com.example.hinduhelpadmin.apiiterface.get_set;

public class User {
    int id;
    String fname,lname,mobileno,email,state,dist,pass;

    public User(int id, String fname, String lname, String mobileno, String email, String state, String dist, String pass) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.mobileno = mobileno;
        this.email = email;

        this.state = state;
        this.dist = dist;
        this.pass = pass;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
