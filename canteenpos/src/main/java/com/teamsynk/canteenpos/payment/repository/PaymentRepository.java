package com.teamsynk.canteenpos.payment.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teamsynk.canteenpos.common.enums.PaymentStatus;
import com.teamsynk.canteenpos.payment.entity.Payment;
import com.teamsynk.canteenpos.sales.entity.OrderItem;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Optional<Payment> findByIdAndCompanyIdAndBranchId(
        UUID id,
        UUID companyId,
        UUID branchId
    );

    Optional<Payment> findByTransactionRefAndCompanyIdAndBranchId(
        String transactionRef,
        UUID companyId,
        UUID branchId
    );

    boolean existsByTransactionRefAndCompanyIdAndBranchId(
        String transactionRef,
        UUID companyId,
        UUID branchId
    );

    boolean existsByOrderIdAndPaymentStatusAndCompanyIdAndBranchId(
        UUID orderId,
        PaymentStatus paymentStatus,
        UUID companyId,
        UUID branchId
    );

    List<Payment> findByOrderIdAndCompanyIdAndBranchIdOrderByPaymentTimeAsc(
        UUID orderId,
        UUID companyId,
        UUID branchId
    );

    Optional<Payment> findTopByOrderIdAndCompanyIdAndBranchIdOrderByPaymentTimeDesc(
        UUID orderId,
        UUID companyId,
        UUID branchId
    );

    List<Payment> findByOrderIdInAndCompanyIdAndBranchId(
        List<UUID> orderIds,
        UUID companyId,
        UUID branchId
    );

    List<Payment> findByPaymentStatusAndCompanyIdAndBranchId(
        PaymentStatus paymentStatus,
        UUID companyId,
        UUID branchId
    );

    Page<Payment> findByPaymentStatusAndCompanyIdAndBranchId(
        PaymentStatus paymentStatus,
        UUID companyId,
        UUID branchId,
        Pageable pageable
    );

    List<Payment> findByPaymentStatusAndPaymentTimeBetweenAndCompanyIdAndBranchId(
        PaymentStatus paymentStatus,
        LocalDateTime from,
        LocalDateTime to,
        UUID companyId,
        UUID branchId
    );

    List<Payment> findByPaymentTimeBetweenAndCompanyIdAndBranchId(
        LocalDateTime from,
        LocalDateTime to,
        UUID companyId,
        UUID branchId
    );

    @Query("""
    	    SELECT COALESCE(SUM(p.amount), 0)
    	    FROM Payment p
    	    WHERE p.order.id = :orderId
    	      AND p.paymentStatus = :status
    	""")
    	BigDecimal sumAmountByOrderIdAndPaymentStatus(
    	    @Param("orderId") UUID orderId,
    	    @Param("status") PaymentStatus status
    	);


    @Query("""
    	    SELECT COALESCE(SUM(p.amount), 0)
    	    FROM Payment p
    	    WHERE p.order.id IN :orderIds
    	      AND p.paymentStatus = :status
    	""")
    	BigDecimal sumAmountByOrderIdsAndPaymentStatus(
    	    @Param("orderIds") List<UUID> orderIds,
    	    @Param("status") PaymentStatus status
    	);


    @Query("""
    	    SELECT p
    	    FROM Payment p
    	    WHERE p.order.id = :orderId
    	      AND p.paymentStatus = com.teamsynk.canteenpos.common.enums.PaymentStatus.COMPLETED
    	      AND NOT EXISTS (
    	          SELECT 1
    	          FROM Refund r
    	          WHERE r.payment = p
    	      )
    	""")
    	List<Payment> findNonRefundedPaymentsByOrderId(
    	    @Param("orderId") UUID orderId
    	);


    @Query("SELECT p FROM Payment p WHERE p.paymentStatus = :status AND p.paymentTime BETWEEN :from AND :to " +
           "AND p.companyId = :companyId AND p.branchId = :branchId")
    Page<Payment> findByStatusAndDateRangePaged(
        @Param("status") PaymentStatus status,
        @Param("from") LocalDateTime from,
        @Param("to") LocalDateTime to,
        @Param("companyId") UUID companyId,
        @Param("branchId") UUID branchId,
        Pageable pageable
    );

    List<Payment> findByOrderIdAndPaymentStatus(
    	    UUID orderId,
    	    PaymentStatus paymentStatus
    	);


}
