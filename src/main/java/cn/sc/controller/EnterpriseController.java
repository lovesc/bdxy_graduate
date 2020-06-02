package cn.sc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.sc.pojo.Company;
import cn.sc.pojo.Enterprise;
import cn.sc.pojo.Recruit;
import cn.sc.service.AddressService;
import cn.sc.service.CompanyService;
import cn.sc.service.EnterpriseService;
import cn.sc.service.RecruitService;
import cn.sc.util.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@RequestMapping("/enterprise")
@Controller
public class EnterpriseController {
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private AddressService addressService;
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private CompanyService companyService;

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView model, String province) {
		List<String> provincelist = addressService.findprovince();
		model.addObject("provincelist", provincelist);
		model.setViewName("enterprise/enterprise_list");
		return model;
	}
   
	@RequestMapping("/getprovince")
	@ResponseBody
	public List<String> province( Map<String, String> map) {
		List<String> provincelist = addressService.findprovince();
		return provincelist;
	}
	@RequestMapping(value="/getcity" ,method = RequestMethod.POST)
	@ResponseBody
	public List<String> city( String province) {
		JSONArray json = new JSONArray();
		if (province != "" && province != null) {
			String citys[] = addressService.findcity(province).split(",");
			for (String city : citys) {
				JSONObject jo = new JSONObject();
				jo.put("id", city);
				jo.put("text", city);
				json.add(jo);
			}
		}	
		return json;
	}
	

	/* 获取用户列表 */
	@RequestMapping(value = "/get_List", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, Object> getList(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			 Page page) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("type",  type );
		queryMap.put("name", "%" + name + "%");
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", enterpriseService.findlist(queryMap));
		ret.put("total", enterpriseService.getTotal(queryMap));
		return ret;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, String> edit(Enterprise enterprise) {		
		Map<String, String> ret = new HashMap<String, String>();
		if (enterprise == null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错");
			return ret;
		}		
		if (StringUtils.isEmpty(enterprise.getName())) {
			ret.put("type", "error");
			ret.put("msg", "公司名不能为空");
			return ret;
		}				
		if (enterpriseService.edit(enterprise) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}

	/*
	 * 添加用户操作
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, String> add(Enterprise enterprise) {
		Map<String, String> ret = new HashMap<String, String>();
		if (enterprise == null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错");
			return ret;
		}
		if (StringUtils.isEmpty(enterprise.getName())) {
			ret.put("type", "error");
			ret.put("msg", "公司名不能为空");
			return ret;
		}
		if((enterpriseService.findByUserName(enterprise.getName())!=null)) {
			ret.put("type", "error");
			ret.put("msg", "公司名已存在");
			return ret;
		}
	
		if (enterpriseService.add(enterprise) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}



	/*
	 * 删除用户操作
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, String> delete(@RequestParam(value = "ids[]", required = true) Long[] ids) {
		Map<String, String> ret = new HashMap<String, String>();
		if (ids == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的数据");
			return ret;
		}
		String idsString = "";
		for (Long id : ids) {
			idsString += id + ",";
		}
		idsString = idsString.substring(0, idsString.length() - 1);

		if (enterpriseService.delete(idsString) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
	}
	@RequestMapping("/findname")
    @ResponseBody
    public String findname(String name) {
		request.getSession().setAttribute("name", name);
		if (enterpriseService.findByUserName(name)==null) {
			return "error";
		}
		return "success";
	}        
    @RequestMapping("/findrecruit")
	public ModelAndView findrecruit(ModelAndView model) {
    	String name=request.getSession().getAttribute("name").toString();
		Enterprise enterprise= enterpriseService.findByUserName(name);
		Recruit recruit=recruitService.findByUserName(name);
		model.addObject("enterprise", enterprise);
		model.addObject("recruit", recruit);
		model.setViewName("recruit/torecruit");
		return model;
	}
    @RequestMapping("/findcompany")
   	public ModelAndView findcompany(ModelAndView model) {
       	String name=request.getSession().getAttribute("name").toString();
   		Enterprise enterprise= enterpriseService.findByUserName(name);
   		Company company=companyService.findByUserName(name);
   		model.addObject("enterprise", enterprise);
   		model.addObject("company", company);
   		model.setViewName("company/tocompany");
   		return model;
   	}

}

