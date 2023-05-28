package com.example.paseomodernobk.Service;

import com.example.paseomodernobk.Entity.Dto.OrderItemDTO;
import com.example.paseomodernobk.Entity.Dto.ProductDTO;
import com.example.paseomodernobk.Entity.OrderItemEntity;
import com.example.paseomodernobk.Exceptions.ResourceNotFoundException;
import com.example.paseomodernobk.Repository.OrderItemRepository;
import com.example.paseomodernobk.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<OrderItemEntity> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public List<OrderItemDTO> getOrderItemById(Long id) {
        List<OrderItemEntity> list = orderItemRepository.findByIdUser(id);
        List<OrderItemDTO> orderItemDtoList = new ArrayList<>();

        for (OrderItemEntity orderItemEntity : list) {
            OrderItemDTO orderItemDto = modelMapper.map(orderItemEntity, OrderItemDTO.class);
            if (orderItemEntity.getProduct() != null) {
                orderItemDto.setProduct(modelMapper.map(orderItemEntity.getProduct(), ProductDTO.class));
            }
            orderItemDtoList.add(orderItemDto);
        }

        return orderItemDtoList;
    }

    public List<OrderItemEntity> createOrderItem(List<OrderItemEntity> orderItems) {
        List<OrderItemEntity> savedOrderItems = new ArrayList<>();
        for (OrderItemEntity orderItem : orderItems) {
            OrderItemEntity savedOrderItem = orderItemRepository.save(orderItem);
            savedOrderItems.add(savedOrderItem);
        }
        return savedOrderItems;
    }


    public OrderItemEntity createOrders(OrderItemEntity orderItems) {
        return orderItemRepository.save(orderItems);
    }


    public void deleteOrderItem(Long id) {
        OrderItemEntity orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", id));

        orderItemRepository.delete(orderItem);
    }
}

