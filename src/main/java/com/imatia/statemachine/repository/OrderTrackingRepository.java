package com.imatia.statemachine.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.imatia.statemachine.model.OrderTracking;

@Repository
public interface OrderTrackingRepository extends CrudRepository<OrderTracking, Long> {


	OrderTracking findFirst1ByOrderIdOrderByOrderTrackingIdDesc(Long orderId);

}
