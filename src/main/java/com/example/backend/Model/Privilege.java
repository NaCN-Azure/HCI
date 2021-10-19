package com.example.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Privilege")
public class Privilege {

    @Transient
    public static final String SEQUENCE_NAME = "privileges_sequence";

    @Id
    private long id;

    private long vid;

    private long bid;

    private String discount;

    private String checkout;

    private long breakfast;

    public Privilege() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVid() {
        return vid;
    }

    public void setVid(long vid) {
        this.vid = vid;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public long getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(long breakfast) {
        this.breakfast = breakfast;
    }
}
