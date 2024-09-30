package com.springboot.brokagefirm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    private String customerID;

    private String assetName;

    private int size;

    private BigDecimal usableSize;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public BigDecimal getUsableSize() {
        return usableSize;
    }

    public void setUsableSize(BigDecimal usableSize) {
        this.usableSize = usableSize;
    }
}
