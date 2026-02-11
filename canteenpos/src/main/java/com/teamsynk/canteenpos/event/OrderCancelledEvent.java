package com.teamsynk.canteenpos.event;

import java.util.UUID;

public record OrderCancelledEvent(
        UUID orderId,
        String reason
) {}

