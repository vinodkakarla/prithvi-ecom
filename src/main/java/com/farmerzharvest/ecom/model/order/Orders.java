package com.farmerzharvest.ecom.model.order;

import com.farmerzharvest.ecom.model.BaseEntity;
import com.farmerzharvest.ecom.model.accounts.AccountAddress;
import com.farmerzharvest.ecom.model.accounts.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Orders extends BaseEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User account;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AccountAddress accountAddress;

    private float totalAmount;
    private LocalDateTime orderDate;
    private String status;
    private String pickUp;

    @OneToMany(mappedBy = "orderId")
    private List<OrderDetails> orderDetails;
}


