package com.task.dronetask.repository;

import com.task.dronetask.entity.Drones;
import org.springframework.data.repository.CrudRepository;

//This repo is an interface because spring would create this droneRepo as a bean/object in spring container
//the repo now inherits methods like save,findByid from CRUDrepo
//this allows you autowire this bean where you need it and allows you use all of CRud repo methods
//now the first parameter is the entity you want to perform crud on which in this case is Drone
//the second parameter is referring to the data type of what you use to id your entity which is Long id
//notice we didn't have to use @respository here because spring is still going to create it as a bean as explained above
public interface DroneRepository extends CrudRepository<Drones,Long> {
}
