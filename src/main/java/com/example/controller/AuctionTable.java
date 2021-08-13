
package com.example.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuctionTable {

    @Id
    private Integer auctionId;

    private Integer highestBidId;

    private Integer auctioneer;


    public AuctionTable(@JsonProperty("auctionId") int auctionId, @JsonProperty("auctioneer") int auctioneer) {
        this.auctioneer = auctioneer;
        this.auctionId = auctionId;
        this.highestBidId = -1;
    }

    public AuctionTable() {

    }

    public void setAuctioneer(int auctioneer) {
        this.auctioneer = auctioneer;
    }

    public int getAuctioneer() {
        return auctioneer;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public int getHighestBidId() {
        return highestBidId;
    }

    public void setHighestBidId(int highestBidId) {
        this.highestBidId = highestBidId;
    }
}
