package com.hellFire.Order_Service.service;


import com.hellFire.Order_Service.dto.InventoryResponse;
import com.hellFire.Order_Service.dto.OrderLineItemsDto;
import com.hellFire.Order_Service.dto.OrderRequest;
import com.hellFire.Order_Service.model.Order;
import com.hellFire.Order_Service.model.OrderLineItems;
import com.hellFire.Order_Service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;


    public void placeOrder(OrderRequest orderRequest) throws Exception {
        Order order = new Order();

        order.setOrderNo(UUID.randomUUID().toString());

        List<OrderLineItems> list = orderRequest.getOrderLineItemsDtosList().stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(list);

        List<String> skuCodes=order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

//
        InventoryResponse[] inventoryResponses  = webClient.get()
                .uri("http://localhost:8082/api/inventory",uriBuilder -> uriBuilder.queryParam("skuCodes",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        assert inventoryResponses != null;
        boolean allProduceInStock= Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if(allProduceInStock) {
            orderRepository.save(order);
        }else{
            throw new Exception("Item not in stock");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
