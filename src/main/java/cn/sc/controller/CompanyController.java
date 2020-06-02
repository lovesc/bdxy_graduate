package cn.sc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.sun.tools.corba.se.idl.constExpr.And;

import cn.sc.pojo.Address;
import cn.sc.pojo.Company;
import cn.sc.pojo.Recruit;
import cn.sc.service.AddressService;
import cn.sc.service.CompanyService;
import cn.sc.service.EnterpriseService;
import cn.sc.service.RecruitService;
import cn.sc.util.Page;
import cn.sc.util.Selectcity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author 小石头
 *
 */

@RequestMapping("/company")
@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private AddressService addressService;
	@Autowired
	private EnterpriseService enterpriseService;

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView model, String province) {
		companyService.deletetime();
		List<String> provincelist = addressService.findprovince();
		List<String> companyname = enterpriseService.findname();
		model.addObject("provincelist", provincelist);
		model.addObject("companyname", companyname);
		model.setViewName("company/company_list");
		return model;
	}

	/* 学生端查看 */
	@RequestMapping("/tocompany")
	public String torecruit() {
		return "company/company";
	}

	@RequestMapping("/getprovince")
	@ResponseBody
	public List<String> province(Map<String, String> map) {
		List<String> provincelist = addressService.findprovince();
		return provincelist;
	}

	@RequestMapping(value = "/getcity", method = RequestMethod.POST)
	@ResponseBody
	public List<String> city(String province) {
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
		System.out.println(json);
		return json;
	}

	/* 获取用户列表展示给管理员 */
	@RequestMapping(value = "/get_List", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, Object> getList(
			@RequestParam(value = "province", required = false, defaultValue = "") String province,
			@RequestParam(value = "city", required = false, defaultValue = "") String city,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "workname", required = false, defaultValue = "") String workname, Page page) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("province", "%" + province + "%");
		queryMap.put("city", "%" + city + "%");
		queryMap.put("type", "%" + type + "%");
		queryMap.put("name", "%" + name + "%");
		queryMap.put("workname", "%" + workname + "%");
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", companyService.findlist(queryMap));
		ret.put("total", companyService.getTotal(queryMap));
		return ret;
	}

	/* 展示页面给学生 */
	@RequestMapping(value = "/get_company")
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, Object> getcompany(
			@RequestParam(value = "province", required = false, defaultValue ="") String province,
			@RequestParam(value = "city", required = false, defaultValue = "") String city,
			@RequestParam(value = "type", required = false, defaultValue = "") String type,
			@RequestParam(value = "workname", required = false, defaultValue = "") String workname, Integer page,
			Integer rows) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(province.length()>2) {
			queryMap.put("province", "%"+province.substring(0, 2)+"%");	
		}
		else {
			queryMap.put("province", "%"+province+"%");		
		}
		if(city.length()>2) {
			queryMap.put("city", "%"+city.substring(0, 2)+"%");	
		}
		else {
			queryMap.put("city", "%" + city + "%");		
		}		
		queryMap.put("type", "%" + type + "%");
		queryMap.put("workname", "%" + workname + "%");
		queryMap.put("offset", (page - 1) * rows);
		queryMap.put("pageSize", rows);
		ret.put("rows", companyService.findlist(queryMap));
		ret.put("total", companyService.getTotal(queryMap));
		return ret;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, String> edit(Company company) {
		Map<String, String> ret = new HashMap<String, String>();

		if (company == null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错");
			return ret;
		}

		if (StringUtils.isEmpty(company.getName())) {
			ret.put("type", "error");
			ret.put("msg", "公司名不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getProvince())) {
			ret.put("type", "error");
			ret.put("msg", "办公地点不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getStarttime())) {
			ret.put("type", "error");
			ret.put("msg", "招聘开始时间不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getEndtime())) {
			ret.put("type", "error");
			ret.put("msg", "招聘结束时间不能为空");
			return ret;
		}

		if (StringUtils.isEmpty(company.getType())) {
			ret.put("type", "error");
			ret.put("msg", "招聘类型不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getInformation())) {
			ret.put("type", "error");
			ret.put("msg", "招聘内容不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getWorkname())) {
			ret.put("type", "error");
			ret.put("msg", "招聘工作名称不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getEmail())) {
			ret.put("type", "error");
			ret.put("msg", "公司邮箱不能为空");
			return ret;
		}
		if (companyService.edit(company) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败");
			return ret;
		}
		if (company.getEndtime().before(company.getStarttime())) {
			ret.put("type", "error");
			ret.put("msg", "结束时间要大于开始时间");
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
	public Map<String, String> add(Company company) {
		Map<String, String> ret = new HashMap<String, String>();
		if (company == null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错");
			return ret;
		}
		if (StringUtils.isEmpty(company.getName())) {
			ret.put("type", "error");
			ret.put("msg", "公司名不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getProvince())) {
			ret.put("type", "error");
			ret.put("msg", "请选择省份");
			return ret;
		}
		if (StringUtils.isEmpty(company.getCity())) {
			ret.put("type", "error");
			ret.put("msg", "请选择市区");
			return ret;
		}
		if (StringUtils.isEmpty(company.getStarttime())) {
			ret.put("type", "error");
			ret.put("msg", "招聘开始时间不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getEndtime())) {
			ret.put("type", "error");
			ret.put("msg", "招聘结束时间不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getType())) {
			ret.put("type", "error");
			ret.put("msg", "招聘类型不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getInformation())) {
			ret.put("type", "error");
			ret.put("msg", "招聘内容不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getWorkname())) {
			ret.put("type", "error");
			ret.put("msg", "招聘工作名称不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(company.getEmail())) {
			ret.put("type", "error");
			ret.put("msg", "公司邮箱不能为空");
			return ret;
		}
		if (company.getEndtime().before(company.getStarttime())) {
			ret.put("type", "error");
			ret.put("msg", "结束时间要大于开始时间");
			return ret;
		}
		if (companyService.add(company) <= 0) {
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

		if (companyService.delete(idsString) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
	}

}
