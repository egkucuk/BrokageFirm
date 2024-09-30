package com.springboot.brokagefirm.repository;

import com.springboot.brokagefirm.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, String> {
        List<Asset> findByCustomerID(String customerID);
}
