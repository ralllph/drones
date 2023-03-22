package com.task.dronetask.repository;

import com.task.dronetask.entity.BatteryHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends CrudRepository<BatteryHistory, Long> {
}
