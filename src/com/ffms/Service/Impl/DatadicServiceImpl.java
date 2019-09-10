package com.ffms.Service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ffms.Dao.DatadicDao;
import com.ffms.Entity.Datadic;
import com.ffms.Service.DatadicService;

@Service
public class DatadicServiceImpl implements DatadicService{

	@Resource
	private DatadicDao datadicDao;
	
	@Override
	public List<Datadic> getDatadicIncome() {
		// TODO Auto-generated method stub
		return datadicDao.getDatadicIncome();
	}

}
