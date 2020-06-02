package cn.sc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import cn.sc.pojo.Admin;
import cn.sc.pojo.Colleges;
import cn.sc.service.AdminService;
import cn.sc.service.CollegesService;
import cn.sc.util.Page;
import net.sf.json.JSONArray;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private CollegesService collegesService;
	/*打开管理员列表*/
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		List<Colleges> collegeslist=collegesService.findlist();
		model.addObject("colleges", collegeslist);
		model.addObject("collegesJson", JSONArray.fromObject(collegeslist));
		model.setViewName("admin/admin_list");
		return model;
	}
	/*获取列表*/
	@RequestMapping(value = "/get_List",method=RequestMethod.POST)
	@ResponseBody  
	public Map<String, Object> getList(
			@RequestParam(value="number",required=false)String number,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("number", number);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", adminService.findList(queryMap));
		ret.put("total", adminService.getTotal(queryMap));
		return ret;
	}
	@RequestMapping(value = "/edit",method=RequestMethod.POST)
	@ResponseBody  
	public Map<String,String> edit(Admin admin){
		Map<String,String> ret=new HashMap<String,String>();
		if(admin==null) {
			ret.put("type","error");
			ret.put("msg","数据为空");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getUsername())) {
			ret.put("type","error");
			ret.put("msg","请输入账号");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getPassword())) {
			ret.put("type","error");
			ret.put("msg","请输入密码");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getNumber())) {
			ret.put("type","error");
			ret.put("msg","请选择院校");
			return ret;
		}
		Admin existadmin = adminService.findByUserName(admin.getUsername());
		if(existadmin!=null) {
			if(admin.getId()!=existadmin.getId()) {
				ret.put("type","error");
				ret.put("msg","该账号已经存在");
				return ret;
			}
		}
		if(adminService.edit(admin)<=0) {
			ret.put("type","error");
			ret.put("msg","修改失败");
			return ret;
		}
		ret.put("type","success");
		ret.put("msg","修改成功");
		return ret;
	}
	/*
	 * 添加账号
	 * */
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	@ResponseBody  
	public Map<String,String> add(Admin admin){
		Map<String,String> ret=new HashMap<String,String>();
		if(admin==null) {
			ret.put("type","error");
			ret.put("msg","请检查数据");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getUsername())) {
			ret.put("type","error");
			ret.put("msg","请输入账号");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getPassword())) {
			ret.put("type","error");
			ret.put("msg","请输入密码");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getNumber())) {
			ret.put("type","error");
			ret.put("msg","请选择院校");
			return ret;
		}
		Admin existAdmin = adminService.findByUserName(admin.getUsername());
		if(existAdmin!=null) {
			ret.put("type","error");
			ret.put("msg","该账号已经存在");
			return ret;
		}
		if(adminService.add(admin)<=0) {
			ret.put("type","error");
			ret.put("msg","添加失败");
			return ret;
		}
		ret.put("type","success");
		ret.put("msg","添加成功");
		return ret;
	}
	
	
	/*
	 * 删除信息
	 * */
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	@ResponseBody  
	public Map<String,String> delete(
			@RequestParam(value="ids[]",required=true) Long[] ids
			){
		Map<String,String> ret=new HashMap<String,String>();
		if(ids==null) {
			ret.put("type","error");
			ret.put("msg","请选择删除数据");
			return ret;
		}
		String idsString="";
		for(Long id:ids) {
			idsString +=id+",";
		}
		idsString=idsString.substring(0,idsString.length()-1);
		
		if(adminService.delete(idsString)<=0) {
			ret.put("type","error");
			ret.put("msg","删除失败");
			return ret;
		}
		ret.put("type","success");
		ret.put("msg","删除成功");
		return ret;
		}
		
}
