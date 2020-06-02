package cn.sc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.sc.pojo.Admin;
import cn.sc.pojo.Colleges;
import cn.sc.pojo.Student;
import cn.sc.pojo.User;
import cn.sc.service.AdminService;
import cn.sc.service.CollegesService;
import cn.sc.service.StudentService;
import cn.sc.service.UserService;
import net.sf.json.JSONArray;

@RequestMapping("/system")
@Controller
public class SystemControlle {
	@Autowired
	private CollegesService collegesService;
	@Autowired
	private StudentService studentService;

	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("system/index");
		return model;
	}
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView student(ModelAndView model) {
		model.setViewName("index/student");
		return model;
	}
	/*
	 * 学生注册
	 * */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(ModelAndView model) {
		model.setViewName("system/register");
		return model;
	}
	@RequestMapping(value = "/index1", method = RequestMethod.GET)
	public ModelAndView index1(ModelAndView model) {
		List<Colleges> collegeslist=collegesService.findlist();
		model.addObject("colleges", collegeslist);
		model.addObject("collegesJson", JSONArray.fromObject(collegeslist));
		model.setViewName("system/index1");
		return model;
	}

	/**
	 * ��½ҳ��
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login_out", method = RequestMethod.GET)
	public String loginOut(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return "redirect:login";
	}

	/**
	 * 登录表单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "role", required = true) int role, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isEmpty("username")) {
			map.put("type", "error");
			map.put("msg", "请输入用户名");
			return map;
		}
		if (StringUtils.isEmpty("password")) {
			map.put("type", "error");
			map.put("msg", "请输入密码");
			return map;
		}
		if (role == 2) {
			User user = userService.findByUserName(username);
			if (user == null) {
				map.put("type", "error");
				map.put("msg", "该用户不存在");
				return map;
			}
			if (!password.equals(user.getPassword())) {
				map.put("type", "error");
				map.put("msg", "密码错误");
				return map;
			}
			request.getSession().setAttribute("user", user);
		}
		if (role == 3) {
			Admin admin = adminService.findByUserName(username);
			if (admin == null) {
				map.put("type", "error");
				map.put("msg", "该用户不存在");
				return map;
			}
			if (!password.equals(admin.getPassword())) {
				map.put("type", "error");
				map.put("msg", "密码错误!");
				return map;
			}
			request.getSession().setAttribute("user", admin);
		}
		if (role == 1) {
			Student student=studentService.findByUserName(username);
			if (student == null) {
				map.put("type", "error");
				map.put("msg", "该用户不存在");
				return map;
			}
			if(student.getRole().equals("未激活")) {
				map.put("type", "error");
				map.put("msg", "该账号未注册!");
				return map;
			}
			if (!password.equals(student.getPassword())) {
				map.put("type", "error");
				map.put("msg", "密码错误!");
				return map;
			}
			request.getSession().setAttribute("user", student);
		}
		request.getSession().setAttribute("collegeslist", collegesService.findlist());
		request.getSession().setAttribute("userType", role);
		map.put("role", role + "");
		map.put("type", "success");
		map.put("msg", "登录成功!");
		return map;

	}
}
