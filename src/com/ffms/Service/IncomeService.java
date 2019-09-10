package com.ffms.Service;

import java.util.List;
import java.util.Map;


import com.ffms.Entity.Income;

public interface IncomeService {

	List<Income> findIncome(Map<String, Object> map);

	Long getToltalIncome(Map<String, Object> map);

	int addIncome(Income income);
	/**
	 * 	更新
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
	 * 收入 --类型 饼图
	 * @param s_income
	 * @return
	 */
	List<Income> showIncomePie(Income s_income);

}
