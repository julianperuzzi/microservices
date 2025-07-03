package com.ucc.orders.model.mappers;


import com.ucc.orders.model.dto.OrderDTO;
import com.ucc.orders.model.entities.Order;
import com.ucc.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrdersMapper {

    private final OrderRepository orderRepository;

    public Order orderDTOToOrdersEntity(OrderDTO orderDTO){
        Order productEntity = new Order();
        productEntity.setName(orderDTO.getName());

//        Category categoryEntity = categoryRepository.findOneById(productDTO.getCategoryDTO().getId());
//        productEntity.setCategory(categoryEntity);
        return productEntity;
    }

    public OrderDTO OrdersEntityToOrderDTO(Order orderEntity){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setName(orderEntity.getName());
        return  orderDTO;
    }
}
