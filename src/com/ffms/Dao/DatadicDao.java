package com.ffms.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ffms.Entity.Datadic;

@Repository
public interface DatadicDao {

	List<Datadic> getDatadicIncome();
	
}
