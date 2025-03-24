package com.hellFire.Order_Service.service;


import com.hellFire.Order_Service.dto.OrderLineItemsDto;
import com.hellFire.Order_Service.dto.OrderRequest;
import com.hellFire.Order_Service.model.Order;
import com.hellFire.Order_Service.model.OrderLineItems;
import com.hellFire.Order_Service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setOrderNo(UUID.randomUUID().toString());

        List<OrderLineItems> list = orderRequest.getOrderLineItemsDtosList().stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(list);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
