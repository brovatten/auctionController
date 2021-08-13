package com.example.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

class PostBidTemplate {

    private final Integer auctionId;
    private final Integer amount;
    private final Integer bidId;


    PostBidTemplate(@JsonProperty("auctionId") Integer auctionId,
                    @JsonProperty("amount") Integer amount,
                    @JsonProperty("bidId") Integer bidId){
        this.auctionId = auctionId;
        this.amount = amount;
        this.bidId = bidId;
    }

    public Integer getBidId() {
        return bidId;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public Integer getAmount() {
        return amount;
    }
}
