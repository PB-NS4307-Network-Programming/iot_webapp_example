package com.nep.iot.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TemperatureDOA extends CrudRepository<Temperature, Long>{

	public List<Temperature> findFirst10ByOrderByIdDesc();
	
}
