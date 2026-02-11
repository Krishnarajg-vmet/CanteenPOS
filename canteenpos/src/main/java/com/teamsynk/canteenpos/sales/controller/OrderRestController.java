package com.teamsynk.canteenpos.sales.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamsynk.canteenpos.sales.dto.request.OrderRequestDto;
import com.teamsynk.canteenpos.sales.dto.response.OrderResponseDto;
import com.teamsynk.canteenpos.sales.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderRequestDto request,
            @RequestParam UUID companyId,
            @RequestParam UUID branchId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(request, companyId, branchId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByBranch(@PathVariable UUID branchId) {
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> getOrdersPaginated(
            @RequestParam UUID companyId,
            @RequestParam UUID branchId,
            Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrdersPaginated(companyId, branchId, pageable));
    }
}
