package com.example.foodapp.auth.controller.admin;

import com.example.foodapp._api.PageableRequest;
import com.example.foodapp._api.service.RedisService;
import com.example.foodapp.auth.dto.UserUpdatedRequest;
import com.example.foodapp.auth.service.admin.service.AdminUsersService;
import com.example.foodapp.auth.user.Addresses.AddressModel;
import com.example.foodapp.auth.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.example.foodapp._api.NumericCheck.isNumeric;
import static com.example.foodapp._api.NumericCheck.isPositiveLong;

@RestController
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Log4j2
public class AdminUsersController {
    private final AdminUsersService adminUsersService;
//    private final RedisService redisService;

    @GetMapping("/list")
    public ResponseEntity<Object> getAll (
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page ,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "order", required = false, defaultValue = "default") String order,
            Principal principal) {
        try {
            System.out.println(limit);
            PageableRequest request = new PageableRequest(page, limit, order);
            String users = adminUsersService.list(request, principal);
//            System.out.println(users);
//            redisService.setValue("sadlfasdf", "alsdnflksadnf");
//            System.out.println(redisService.getValue("sadlfasdf"));
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser (
            Principal principal, @PathVariable String id) {
        try {
            if (isPositiveLong(id)) {
                String user = adminUsersService.get(Integer.valueOf(id), principal);
                return ResponseEntity.ok().body(user);
            } else {
                return ResponseEntity.badRequest().body("Id is not valid");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> updateUser (
            @RequestBody UserUpdatedRequest userUpdatedRequest,
            Principal principal,
            @PathVariable String id) {
        try {
            if (isPositiveLong(id)) {
                adminUsersService.updateUser(Integer.valueOf(id), userUpdatedRequest, principal);
                return ResponseEntity.ok().body("User updated");
            } else {
                return ResponseEntity.badRequest().body("Id is not valid");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser (
            Principal principal, @PathVariable String id) {
        try {
            if (isNumeric(id)) {
                adminUsersService.deleteUser(Integer.valueOf(id), principal);
                return ResponseEntity.ok().body("User deleted.");
            } else {
                return ResponseEntity.badRequest().body("Id is not valid");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
