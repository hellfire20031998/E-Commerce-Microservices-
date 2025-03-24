package com.hellFire.Order_Service.dto;


import com.hellFire.Order_Service.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDtosList;
}
