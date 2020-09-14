package com.example.hinduhelpadmin.apiiterface.responce;

import com.example.hinduhelpadmin.apiiterface.get_set.help_get_set;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class helpresponce {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("de")
    @Expose
    private List<help_get_set> de = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<help_get_set> getDe() {
        return de;
    }

    public void setDe(List<help_get_set> de) {
        this.de = de;
    }
}
