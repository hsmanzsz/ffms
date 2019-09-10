package com.ffms.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ffms.Entity.Datadic;
import com.ffms.Entity.Income;
import com.ffms.Entity.User;
import com.ffms.Service.DatadicService;
import com.ffms.Service.IncomeService;

@Controller
public class ChartController {

	@Resource
	private IncomeService incomeService;
	
	@Resource
	private DatadicService datadicService;
	
	/**
	 * 	支出-类型-饼状图
	 * @param s_income
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showTCpie.do",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> IncomePie(Income s_income,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user =(User) session.getAttribute("currentUser");
		s_income.setRoleid(user.getRoleid());
		s_income.setUserid(user.getId());
		//  获取所有收入信息
		System.out.println(user.getRoleid());
		System.out.println(user.getId());
		List<Income> incomes = incomeService.showIncomePie(s_income);
		// 查询所有的收入类型
		List<Datadic> incomeTypes = datadicService.getDatadicIncome();
		
		Map<String,Object> map = new HashMap<String,Object>();
		// 计算图表所需的 数据
		/**
		 * 		1.计算所有的收入total
		 * 		2.计算各个收入类型所占的比例
		 */
		Integer totalMoney=0,incomeMoney = 0;
		
		Object[] series = new Object[incomeTypes.size()];
		
		Object[] icMoney = new Object[incomeTypes.size()];
		for (Income income : incomes) {
			totalMoney+=income.getMoney();
		}
		System.out.println(totalMoney);
		for (int i = 0; i < incomeTypes.size(); i++) {
			//二维数组
			incomeMoney = 0;
			for(int j = 0 ; j <incomes.size(); j++) {
				if(incomes.get(j).getDataid().equals(incomeTypes.get(i).getId())) {
					incomeMoney+=incomes.get(j).getMoney();
				}
			}
			// 支出集合
			icMoney[i] = incomeMoney;
			// 收入名称
			String incomeName = incomeTypes.get(i).getDatadicvalue();
			System.out.println(incomeName+":"+incomeMoney);
			Object[] temp = new Object[2];
			temp[0] = incomeName+":"+incomeMoney+"元";
			 //比例
			temp[1] =(double)Math.round(100*incomeMoney/totalMoney);
			System.out.println(temp[1]);
			series[i]=temp;
		}
		
		map.put("data",series);
		map.put("money",icMoney);
		return map;
	}
}
