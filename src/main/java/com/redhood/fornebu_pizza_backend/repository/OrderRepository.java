package com.redhood.fornebu_pizza_backend.repository;

import com.redhood.fornebu_pizza_backend.entity.Order;
import com.redhood.fornebu_pizza_backend.entity.OrderProduct;
import com.redhood.fornebu_pizza_backend.resources.OrderSimpleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findById(Long id);


    @Query("SELECT new com.redhood.fornebu_pizza_backend.resources.OrderSimpleResource(o.id, o.uid, o.paymentMethod, " +
            "o.isPaid, o.createdAt, o.estimatedTime, o.orderStatus, o.total, size(o.products), o.userName) " +
            "FROM Order o WHERE o.uid = :uid ORDER BY o.createdAt DESC")
    List<OrderSimpleResource> findSimpleOrdersByUid(String uid);


    @Query("SELECT new com.redhood.fornebu_pizza_backend.resources.OrderSimpleResource(o.id, o.uid, o.paymentMethod, " +
            "o.isPaid, o.createdAt, o.estimatedTime, o.orderStatus, o.total, size(o.products), o.userName) " +
            "FROM Order o WHERE o.orderStatus = :orderStatus ORDER BY o.createdAt DESC")
    List<OrderSimpleResource> findSimpleOrdersByOrderStatus(String orderStatus);


    @Query("SELECT new com.redhood.fornebu_pizza_backend.resources.OrderSimpleResource(o.id, o.uid, o.paymentMethod, " +
            "o.isPaid, o.createdAt, o.estimatedTime, o.orderStatus, o.total, size(o.products), o.userName) " +
            "FROM Order o ORDER BY o.createdAt DESC")
    List<OrderSimpleResource> findAllSimpleOrders();

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus = :status WHERE o.id = :orderId")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("status") String status);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.isPaid = :isPaid WHERE o.id = :orderId")
    int updateOrderPaymentStatus(@Param("orderId") Long orderId, @Param("isPaid") boolean isPaid);

}
