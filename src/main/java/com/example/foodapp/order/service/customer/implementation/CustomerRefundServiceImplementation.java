package com.example.foodapp.order.service.customer.implementation;


import com.example.foodapp.auth.repo.UserRepository;
import com.example.foodapp.auth.service._UserProfileService;
import com.example.foodapp.auth.user.User;
import com.example.foodapp.auth.user.UserProfiles.Customer;
import com.example.foodapp.order.enumeration.RefundStatus;
import com.example.foodapp.order.model.OrderO;
import com.example.foodapp.order.model.Refund;
import com.example.foodapp.order.model.Request.RefundRequest;
import com.example.foodapp.order.repo.OrderRepo;
import com.example.foodapp.order.repo.RefundRepo;
import com.example.foodapp.order.service.customer.CustomerRefundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class CustomerRefundServiceImplementation implements CustomerRefundService {
    private final UserRepository userRepo;
    private final _UserProfileService userProfileService;
    private final RefundRepo refundRepo;
    private final OrderRepo orderRepo;

    @Override
    public Refund create(RefundRequest refundRequest, Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        Customer customer = userProfileService.returnCustomer(user.getUserProfile());
        OrderO orderO = orderRepo.findById(refundRequest.getOrderId())
                .orElseThrow(() -> new Exception("This order does not exists"));

        if (orderO.getCustomer() != customer) {
            throw new Exception("This order does not belong to you");
        }

        if (refundRequest.getEmail() == null) {
            throw new Exception("You need to specify email");
        }

        if (refundRequest.getReason() == null) {
            throw new Exception("You need to specify reason for refund");
        }

        if (refundRequest.getOrderId() == null) {
            throw new Exception("You need to specify order");
        }

        Refund refund = new Refund();

        refund.setReason(refundRequest.getReason());
        refund.setEmail(refund.getEmail());
        refund.setOrderO(orderO);
        refund.setCustomer(customer);
        refund.setRefundStatus(RefundStatus.IN_PROGRESS);
        refund.setDateSent(LocalDateTime.now());
        refundRepo.save(refund);

        return refund;
    }

    @Override
    public Refund get(Long id, Principal principal) throws Exception {
        Refund refund = refundRepo.findById(id)
                .orElseThrow(() -> new Exception("Refund does not exits"));
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        if(refund.getCustomer() == userProfileService.returnCustomer(user.getUserProfile())){
            return refund;
        }
        throw new Exception("This refund does not belong to you");
    }

    @Override
    public List<Refund> list(Principal principal) throws Exception {
        User user = userRepo.findByEmail(principal.getName()).orElseThrow();
        List<Refund> refundList = refundRepo.findRefundsByCustomer(
                userProfileService.returnCustomer(user.getUserProfile()));

        if(refundList.isEmpty()){
            throw new Exception("You do not have refunds");
        }

        return refundList;
    }

    @Override
    public Refund update(Long id) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(Long id) throws Exception {
        return null;
    }
}
