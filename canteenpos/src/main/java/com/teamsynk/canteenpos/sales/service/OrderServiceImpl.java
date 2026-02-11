package com.teamsynk.canteenpos.sales.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamsynk.canteenpos.common.enums.OrderStatus;
import com.teamsynk.canteenpos.common.exception.ResourceNotFoundException;
import com.teamsynk.canteenpos.event.OrderCancelledEvent;
import com.teamsynk.canteenpos.sales.dto.request.OrderItemRequestDto;
import com.teamsynk.canteenpos.sales.dto.request.OrderRequestDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderResponseDto;
import com.teamsynk.canteenpos.sales.entity.Order;
import com.teamsynk.canteenpos.sales.entity.OrderCancellation;
import com.teamsynk.canteenpos.sales.entity.OrderItem;
import com.teamsynk.canteenpos.sales.mapper.OrderItemMapper;
import com.teamsynk.canteenpos.sales.mapper.OrderMapper;
import com.teamsynk.canteenpos.sales.repository.OrderCancellationRepository;
import com.teamsynk.canteenpos.sales.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderCancellationRepository orderCancellationRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderServiceImpl(OrderRepository orderRepository,
    						OrderMapper orderMapper,
    						OrderCancellationRepository orderCancellationRepository,
    						ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderCancellationRepository = orderCancellationRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto request, UUID companyId, UUID branchId) {
        Order order = orderMapper.toEntity(request);
        order.setCompanyId(companyId);
        order.setBranchId(branchId);
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrdersByBranch(UUID branchId) {
        List<Order> orders = orderRepository.findByBranchId(branchId);
        return orders.stream()
                     .map(orderMapper::toDto)
                     .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrdersPaginated(UUID companyId, UUID branchId, Pageable pageable) {
        Page<Order> page = orderRepository.findByCompanyIdAndBranchIdAndSalesStatus(
                companyId, branchId, com.teamsynk.canteenpos.common.enums.OrderStatus.CREATED, pageable);
        return page.map(orderMapper::toDto);
    }
    
    @Transactional
    @Override
    public void cancelOrderFully(UUID orderId, String cancelledBy, String reason) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        order.cancelFully(cancelledBy, reason);
        orderRepository.save(order);

        orderCancellationRepository.save(
                OrderCancellation.cancelOrder(order, cancelledBy, reason)
        );

        applicationEventPublisher.publishEvent(
                new OrderCancelledEvent(orderId, reason)
        );
    }

    
    @Override
    @Transactional
    public void cancelOrderItems(
            UUID orderId,
            List<UUID> orderItemIds,
            String cancelledBy,
            String reason
    ) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        order.cancelItems(orderItemIds, cancelledBy, reason);

        orderRepository.save(order);

        OrderCancellation cancellation =
                OrderCancellation.cancelOrder(order, cancelledBy, reason);
        orderCancellationRepository.save(cancellation);
    }

    @Transactional
    public OrderResponseDto addItemToOrder(
            UUID orderId,
            OrderItemRequestDto dto
    ) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));

        if (order.getSalesStatus() != OrderStatus.CREATED) {
            throw new IllegalStateException("Cannot add items after payment started");
        }

        OrderItem item = OrderItemMapper.toEntity(dto);
        order.addItem(item);

        orderRepository.save(order);

        return orderMapper.toDto(order);
    }


}
