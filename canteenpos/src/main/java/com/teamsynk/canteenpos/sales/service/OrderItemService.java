package com.teamsynk.canteenpos.sales.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.sales.dto.request.OrderItemRequestDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderItemResponseDto;
import com.teamsynk.canteenpos.sales.entity.Order;
import com.teamsynk.canteenpos.sales.entity.OrderItem;
import com.teamsynk.canteenpos.sales.mapper.OrderItemMapper;
import com.teamsynk.canteenpos.sales.repository.OrderItemRepository;
import com.teamsynk.canteenpos.sales.repository.OrderRepository;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderItemResponseDto createOrderItem(UUID orderId, OrderItemRequestDto dto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        OrderItem item = OrderItemMapper.toEntity(dto);
        order.addItem(item);

        return OrderItemMapper.toDto(orderItemRepository.save(item));
    }

    public OrderItemResponseDto getOrderItemById(UUID id) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem", id));
        return OrderItemMapper.toDto(item);
    }

    public List<OrderItemResponseDto> getItemsByOrder(UUID orderId) {
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
        return items.stream()
                    .map(OrderItemMapper::toDto)
                    .collect(Collectors.toList());
    }

}
