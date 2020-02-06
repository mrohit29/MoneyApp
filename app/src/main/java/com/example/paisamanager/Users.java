package com.example.paisamanager;

import android.widget.Button;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Users implements Serializable {
    private String name;
    private Integer amount;
    private Boolean credit;
    private Date transDate;
    private Boolean status;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public LocalDateTime getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(LocalDateTime updatedat) {
        this.updatedat = updatedat;
    }

    private LocalDateTime updatedat;

    public Boolean getCredit() {
        return credit;
    }

    public void setCredit(Boolean credit) {
        this.credit = credit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
