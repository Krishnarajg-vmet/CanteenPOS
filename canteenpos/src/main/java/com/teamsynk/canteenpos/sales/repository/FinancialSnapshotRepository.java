package com.teamsynk.canteenpos.sales.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.sales.entity.FinancialSnapshot;

@Repository
public interface FinancialSnapshotRepository extends JpaRepository<FinancialSnapshot, UUID> {

    List<FinancialSnapshot> findByOrderIdOrderByCapturedAtDesc(UUID orderId);

    Optional<FinancialSnapshot> findTopByOrderIdOrderByCapturedAtDesc(UUID orderId);
}
