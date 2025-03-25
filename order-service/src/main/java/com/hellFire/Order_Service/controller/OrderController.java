package com.hellFire.Order_Service.controller;


import com.hellFire.Order_Service.dto.OrderRequest;
import com.hellFire.Order_Service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  String placeOrder(@RequestBody OrderRequest orderRequest)  {
        try {
            orderService.placeOrder(orderRequest);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "Order Placed";
    }
}
