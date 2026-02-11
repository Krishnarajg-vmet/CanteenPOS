package com.teamsynk.canteenpos.sales.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.sales.dto.request.OrderItemRequestDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderItemResponseDto;
import com.teamsynk.canteenpos.sales.service.OrderItemService;

@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemRestController {

    private final OrderItemService orderItemService;

    public OrderItemRestController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<OrderItemResponseDto> createOrderItem(
            @PathVariable UUID orderId,
            @RequestBody OrderItemRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderItemService.createOrderItem(orderId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> getOrderItemById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemResponseDto>> getItemsByOrder(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderItemService.getItemsByOrder(orderId));
    }

}
