package com.teamsynk.canteenpos.sales.service;

import java.util.Collection;
import java.util.UUID;

import com.teamsynk.canteenpos.sales.entity.FinancialSnapshot;

public interface FinancialSnapshotService {

    FinancialSnapshot captureSnapshot(UUID orderId);

    FinancialSnapshot getLatestSnapshot(UUID orderId);

    Collection<FinancialSnapshot> findAll(UUID orderId);
}
