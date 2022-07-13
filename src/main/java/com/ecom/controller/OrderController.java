package com.ecom.controller;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.OrderDto;
import com.ecom.payload.OrderRequest;
import com.ecom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //for create order
    @PostMapping("/orders")
    public ResponseEntity<ApiResponse> createOrder(
            @RequestBody OrderRequest orderRequest,
            Principal principal
    ) {
        //let suppose order is create success
        String username = principal.getName();
        this.orderService.createOrder(orderRequest, username);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Order is created successfully !!");
        apiResponse.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(apiResponse);
    }

    // get orders

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getOrders() {
        List<OrderDto> orders = this.orderService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    //single order:by id




    // delete orders


}
