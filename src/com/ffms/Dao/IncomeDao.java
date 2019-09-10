package com.ffms.Dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ffms.Entity.Income;

@Repository
public interface IncomeDao {

	List<Income> findIncome(Map<String, Object> map);

	Long getToltalIncome(Map<String, Object> map);

	/**
	 * 添加收入
	 * @param income
	 * @return
	 */
	int addIncome(Income income);

	/**
	 * 更新数据
	 * @param income
	 * @return
	 */
	int updateIncome(Income income);

	/**
	 * 删除收入信息
	 * @param id
	 * @return
	 */
	int deleteIncome(int id);

	/**
	 * 饼状图
	 * @param income
	 * @return
	 */
	List<Income> showIncomePie(Income income);
	
}
