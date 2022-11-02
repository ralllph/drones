package com.task.dronetask.repository;

import com.task.dronetask.entity.BatteryHistory;
import org.springframework.data.repository.CrudRepository;

public interface BatteryRepository extends CrudRepository<BatteryHistory, Long> {
}
