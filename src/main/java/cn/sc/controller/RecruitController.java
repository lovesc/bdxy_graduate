package cn.sc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.internal.logging.Logger;

import cn.sc.pojo.Recruit;
import cn.sc.service.EnterpriseService;
import cn.sc.service.RecruitService;
import cn.sc.util.Page;

@RequestMapping("/recruit")
@Controller
public class RecruitController {
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private EnterpriseService enterpriseService;

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView model) {
		recruitService.deletetime();
		List<String> companyname=enterpriseService.findname();
		model.addObject("companyname", companyname);
		model.setViewName("recruit/recruit_list");
		return model;
	}
	@RequestMapping("/torecruit")
	public String torecruit() {
		return "recruit/recruit";
	}

	
	@RequestMapping(value = "/get_List")
	@ResponseBody 
	public Map<String, Object> getList(@RequestParam(value = "time", required = false) String time,
			Page page,String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (time != null && time!="") {
			String[] t=time.split("/");
			String newtime =t[2] + "-" + t[0] + "-" + t[1];
			try {
				Date date=sdf.parse(newtime);
				queryMap.put("time",sdf.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}
		}
		queryMap.put("name", name);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", recruitService.findlist(queryMap));
		ret.put("total", recruitService.getTotal(queryMap));
		return ret;
	}
	/*展示给学生看*/
	@RequestMapping(value = "/get_recruit")
	@ResponseBody // 
	public Map<String, Object> getrecruit(@RequestParam(value = "time", required = false) String time,
			Integer page,Integer rows,String name) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (time != null && time!="") {	
			try {
				Date date=sdf.parse(time);
				queryMap.put("time",sdf.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}
		}
		System.out.println(time);
		queryMap.put("name", name);
		queryMap.put("offset", (page-1)*rows);
		queryMap.put("pageSize", rows);
		ret.put("rows", recruitService.findlist(queryMap));
		ret.put("total", recruitService.getTotal(queryMap));
		return ret;
	}


	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, String> edit(Recruit recruit) {
		Map<String, String> ret = new HashMap<String, String>();
		if (recruit == null) {
			ret.put("type", "error");
			ret.put("msg", "数据捆绑出错");
			return ret;
		}
		if (StringUtils.isEmpty(recruit.getContent())) {
			ret.put("type", "error");
			ret.put("msg", "内容不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(recruit.getName())) {
			ret.put("type", "error");
			ret.put("msg", "公司名称不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(recruit.getAddress())) {
			ret.put("type", "error");
			ret.put("msg", "地点不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(recruit.getTime())) {
			ret.put("type", "error");
			ret.put("msg", "举办时间不能为空");
			return ret;
		}
		if (recruitService.edit(recruit) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}

	/*
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody // 转化为json数据
	public Map<String, String> add(Recruit recruit) {
		Map<String, String> ret = new HashMap<String, String>();
		if (recruit == null) {
			ret.put("type", "error");
			ret.put("msg", "数据捆绑错误");
			return ret;
		}
		if (StringUtils.isEmpty(recruit.getContent())) {
			ret.put("type", "error");
			ret.put("msg", "内容不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(recruit.getName())) {
			ret.put("type", "error");
			ret.put("msg", "公司名称不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(recruit.getAddress())) {
			ret.put("type", "error");
			ret.put("msg", "地点不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(recruit.getTime())) {
			ret.put("type", "error");
			ret.put("msg", "举办时间不能为空");
			return ret;
		}
		if (recruitService.add(recruit) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}

	/*
	 *删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, String> delete(@RequestParam(value = "ids[]", required = true) Long[] ids) {
		Map<String, String> ret = new HashMap<String, String>();
		if (ids == null) {
			ret.put("type", "error");
			ret.put("msg", "选择数据为空");
			return ret;
		}
		String idsString = "";
		for (Long id : ids) {
			idsString += id + ",";
		}
		idsString = idsString.substring(0, idsString.length() - 1);

		if (recruitService.delete(idsString) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
	}

}
