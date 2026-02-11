package com.teamsynk.canteenpos.event;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.teamsynk.canteenpos.sales.service.FinancialSnapshotService;

@Component
public class FinancialSnapshotListener {

    private final FinancialSnapshotService snapshotService;

    public FinancialSnapshotListener(FinancialSnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderCancelledEvent event) {
        snapshotService.captureSnapshot(event.orderId());
    }
}

