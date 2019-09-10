package com.ffms.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ffms.Entity.Datadic;
import com.ffms.Entity.Income;
import com.ffms.Entity.PageBean;
import com.ffms.Service.DatadicService;
import com.ffms.Service.IncomeService;
import com.ffms.Service.UserSerivce;
import com.ffms.Utils.StringUtil;

@Controller
public class IncomeController {

	@Resource
	private IncomeService incomeService;
	
	@Resource
	private DatadicService datadicService;
	
	@Resource
	private UserSerivce userService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}
	
	@RequestMapping(value="/incomeManage.do",produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<Datadic> incomeManage(HttpServletRequest request){
		List<Datadic> list =  datadicService.getDatadicIncome();
		System.out.println(list);
		//HttpSession session = request.getSession();
		//User user = (User) session.getAttribute("currentUser");
		//	获取所有的角色
		//Map<String, Object> userMap = new HashMap<String, Object>();
		//userMap.put("userid", user.getId());
		//userMap.put("roleid", user.getRoleid());
		//List<User> userlist = userService.getAllUser(userMap);
		return list;
	}
	
	
	@RequestMapping(value="/getIncomeList.do",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> getIncomeList(@RequestParam(value="page",required = false) String page,
			@RequestParam(value="rows",required = false) String rows,Income s_income) {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		//封装到map集合中
		System.out.println(page);
		System.out.println(rows);
		System.out.println(s_income.getUserid());
		System.out.println(s_income.getRoleid());
		System.out.println(s_income.getDataid());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("incomer", StringUtil.formatLike(s_income.getIncomer()));
		map.put("source", StringUtil.formatLike(s_income.getSource()));
		map.put("dataid", s_income.getDataid());
		map.put("starttime", s_income.getStarttime());
		map.put("endtime", s_income.getEndtime());
		map.put("roleid", s_income.getRoleid());
		map.put("userid", s_income.getUserid());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		System.out.println(s_income.getEndtime()+"==="+s_income.getStarttime());
		//	按分页查询数据
		List<Income> incomes = incomeService.findIncome(map);
		//	查询所有的数据跳数
		Long total = incomeService.getToltalIncome(map);
		System.out.println(incomes);
		System.out.println(total);
		Map<String,Object> cmap = new HashMap<String, Object>();
		cmap.put("incomes",incomes);
		cmap.put("total",total);
		return cmap;
	}
	
	@RequestMapping(value = "/incomeSave.do",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> incomeSave(Income income) {
		System.out.println(income);
		
		int resultRows = 0;
		//	判断是添加数据还是修改数据
		if(income.getId()==null) {
			resultRows = incomeService.addIncome(income);
		}else {
			resultRows = incomeService.updateIncome(income);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (resultRows > 0) { // 执行成功
			map.put("errres", true);
			map.put("errmsg", "数据保存成功！");
		} else {
			map.put("errres", false);
			map.put("errmsg", "数据保存失败");
		}
		
		return map;
	}
	
	@RequestMapping(value="/deleteIncome.do",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,String> deleteIncome(int id){
		System.out.println(id);
		int toltal = 0;
		toltal = incomeService.deleteIncome(id);
		Map<String,String> map = new HashMap<String, String>();
		
		if(toltal>0) {
			map.put("error","200");
			map.put("errmsg", "删除成功！");
		}else {
			map.put("error","201");
			map.put("errmsg","删除失败！");
		}
		return map;
	}
	
	
}
