package com.example.foodapp.auth.user.UserProfiles;


import com.example.foodapp.auth.user.Addresses.Address;
import com.example.foodapp.auth.user.ERole;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.order.model.OrderO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class Customer {
    private ERole EROLE = ERole.CUSTOMER;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_addresses",
            joinColumns = @JoinColumn(name = "address"),
            inverseJoinColumns = @JoinColumn(name = "user"))

    private Address addresses;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany
    private List<OrderO> orderList;

    public Map<String, Object> getCustomerBasicDetail() {
        Map<String, Object> detail = new HashMap<>();
        detail.put("id", this.id);
        detail.put("username", this.username);
        detail.put("addresses", this.addresses);
        detail.put("dateCreated", this.dateCreated);
        detail.put("dateUpdated", this.dateUpdated);
        detail.put("user", this.user.getBasicUserDetail());
        return detail;
    }

}
