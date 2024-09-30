package com.springboot.brokagefirm.service;

import com.springboot.brokagefirm.entity.Asset;
import com.springboot.brokagefirm.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> listAssets(String customerID) {
        return assetRepository.findByCustomerID(customerID);
    }

    public void depositMoney(String customerID, BigDecimal amount) {
        Optional<Asset> asset = assetRepository.findById(customerID);
        if(asset.isPresent()) {
            Asset customerAsset = asset.get();
            customerAsset.setUsableSize(customerAsset.getUsableSize().add(amount));
            assetRepository.save(customerAsset);
        } else {
            throw new RuntimeException("The asset does not exist for customer " + customerID);
        }
    }

    public void withdrawMoney(String customerID, BigDecimal amount, String iban) {
        Optional<Asset> asset = assetRepository.findById(customerID);
        if(asset.isPresent()) {
            Asset customerAsset = asset.get();
            if(customerAsset.getUsableSize().compareTo(amount) >= 0) {
                customerAsset.setUsableSize(customerAsset.getUsableSize().subtract(amount));
                assetRepository.save(customerAsset);
            } else {
                throw new RuntimeException("");
            }
        } else {
            throw new RuntimeException("The asset does not exist for the customer " + customerID);
        }
    }
}
