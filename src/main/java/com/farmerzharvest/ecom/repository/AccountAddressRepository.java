package com.farmerzharvest.ecom.repository;

import com.farmerzharvest.ecom.model.accounts.AccountAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface AccountAddressRepository extends JpaRepository<AccountAddress, Long> {

    List<AccountAddress> findAllByAccount_id(long id);

}
