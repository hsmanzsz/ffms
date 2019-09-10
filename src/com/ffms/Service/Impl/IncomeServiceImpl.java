package com.ffms.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ffms.Dao.IncomeDao;
import com.ffms.Entity.Income;
import com.ffms.Service.IncomeService;
import com.ffms.Utils.DateUtils;

@Service
public class IncomeServiceImpl implements IncomeService{

	@Resource
	private IncomeDao incomedao;
	
	@Override
	public List<Income> findIncome(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return incomedao.findIncome(map);
	}

	@Override
	public Long getToltalIncome(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return incomedao.getToltalIncome(map);
	}

	@Override
	public int addIncome(Income income) {
		// TODO Auto-generated method stub
		income.setCreatetime(DateUtils.getCurrentTime());
		return incomedao.addIncome(income);
	}

	@Override
	public int updateIncome(Income income) {
		// TODO Auto-generated method stub
		income.setUpdatetime(DateUtils.getCurrentTime());
		return incomedao.updateIncome(income);
	}

	@Override
	public int deleteIncome(int id) {
		// TODO Auto-generated method stub
		return incomedao.deleteIncome(id);
	}

	@Override
	public List<Income> showIncomePie(Income income) {
		// TODO Auto-generated method stub
		
		return incomedao.showIncomePie(income);
	}

}
