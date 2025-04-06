package com.project.landValuePredictor.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.landValuePredictor.entity.LandValueDataEntity;

public interface LandDataRepository extends CrudRepository<LandValueDataEntity, Integer>{
	
	public List<LandValueDataEntity> findByLatBetweenAndLngBetween(Double latFrom,Double latTo,Double lngFrom,Double lngTo);
	
}
