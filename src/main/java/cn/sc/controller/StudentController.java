package cn.sc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.sc.pojo.Admin;
import cn.sc.pojo.Colleges;
import cn.sc.pojo.Company;
import cn.sc.pojo.Enterprise;
import cn.sc.pojo.Recruit;
import cn.sc.pojo.Student;
import cn.sc.service.AddressService;
import cn.sc.service.CollegesService;
import cn.sc.service.EnterpriseService;
import cn.sc.service.StudentService;
import cn.sc.util.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RequestMapping("/student")
@Controller
public class StudentController {
	@Autowired
	private CollegesService collegesService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private AddressService addressService;
	@Autowired
	private EnterpriseService enterpriseService;

	/* 跳转学生页面 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model, String state, HttpServletRequest request) {
		List<Colleges> collegeslist = collegesService.findlist();
		List<String> provincelist = addressService.findprovince();
		model.addObject("colleges", collegeslist);
		model.addObject("collegesJson", JSONArray.fromObject(collegeslist));
		if (state.equals("就业")) {
			
			model.setViewName("student/student_list");
		} else {

			model.setViewName("student/student_list2");
		}
		model.addObject("provincelist", provincelist);
		request.getSession().setAttribute("state", state);
		return model;
	}
	/**
	 * @author 
	 * 管理员获取毕业生
	 *
	 */
	@RequestMapping("/list1")
	public String getlist1(Integer number) {
		request.getSession().setAttribute("number", number);
		request.getSession().setAttribute("collegesJson",JSONArray.fromObject(collegesService.findlist()));
		return "student/student_list3";
	}

	/* 获取各省的下级市区 */
	@RequestMapping(value = "/getcity", method = RequestMethod.POST)
	@ResponseBody
	public List<String> city(String province, Map<String, String> ret) {
		List<String> citylist = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
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
	

	/* 修改密码 */
	@RequestMapping(value = "/toAlterPwd", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView toAlterPwd(ModelAndView model) {
		model.setViewName("index/alterPwd");
		return model;
	}

	/* 修改学生 */
	@RequestMapping(value = "/alterstudent", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView alterstudent(ModelAndView model) {
		Student student = (Student) request.getSession().getAttribute("user");
		Enterprise enterprise = enterpriseService.findByUserName(student.getName());
		model.addObject("students", student);
		model.addObject("enterprise", enterprise);
		model.setViewName("index/alterstudent");
		return model;
	}
	/* 展示管理员列表 */
	@RequestMapping(value = "/get_student", method = RequestMethod.POST)
	@ResponseBody
	public List<Double> getstudent(
			String state,Page page){
		Map<String, Object> ret = new HashMap<String, Object>();
		Integer number=Integer.parseInt(request.getSession().getAttribute("number").toString());
		String[] types= {"计算机", "金融", "医疗", "销售", "教育", "法律","其他"};
		ret.put("number", number);
		double count=studentService.getTotal(ret);
		List<Double> list1=new ArrayList<>();
		for(String type:types) {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("type", type);
			queryMap.put("number", number);
			double row=studentService.getTotal(queryMap);
			double y=Double.parseDouble(String.format("%.2f", row/count));
			list1.add(y);
		}
		return list1;
	}

	/* 获取列表给辅导员 */
	@RequestMapping(value = "/get_List", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(value = "studentname", required = false,defaultValue="") String studentname,
			@RequestParam(value = "province", required = false,defaultValue="") String province,
			@RequestParam(value = "city", required = false,defaultValue="") String city,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "studentid", required = false) String studentid,
			@RequestParam(value = "role", required = false) String role, Page page) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Admin admin = (Admin) request.getSession().getAttribute("user");
		Integer number = admin.getNumber();
		String state = request.getSession().getAttribute("state").toString();
		queryMap.put("studentname", "%"+studentname+"%");
		queryMap.put("state", state);
		queryMap.put("role", role);
		queryMap.put("number", number);
		queryMap.put("province", "%"+province+"%");
		queryMap.put("city", "%"+city+"%");
		queryMap.put("type", type);
		queryMap.put("studentid", studentid);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", studentService.findlist(queryMap));
		ret.put("total", studentService.getTotal(queryMap));
		return ret;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody // 转为json数据
	public Map<String, String> add(Student student) {
		Map<String, String> ret = new HashMap<String, String>();
		if (student == null) {
			ret.put("type", "error");
			ret.put("msg", "请检查数据");
			return ret;
		}
		if (StringUtils.isEmpty(student.getStudentid())) {
			ret.put("type", "error");
			ret.put("msg", "请输入学生学号");
			return ret;
		}

		if (!student.getStudentid().matches(("1[6-9][0-9]{8}"))) {
			ret.put("type", "error");
			ret.put("msg", "请输入正确的学号");
			return ret;
		}
		if (studentService.findByUserName(student.getStudentid()) != null) {
			ret.put("type", "error");
			ret.put("msg", "该学号已经存在");
			return ret;
		}
		if (StringUtils.isEmpty(student.getStudentname())) {
			ret.put("type", "error");
			ret.put("msg", "请输入学生姓名");
			return ret;
		}
		if (StringUtils.isEmpty(student.getSex())) {
			ret.put("type", "error");
			ret.put("msg", "请选择学生性别");
			return ret;
		}
		student.setState("待业");
		student.setPassword("");
		student.setRole("未激活");
		Admin admin = (Admin) request.getSession().getAttribute("user");
		Integer number = admin.getNumber();
		student.setNumber(number);
		if (studentService.add(student) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功");
		return ret;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, String> delete(@RequestParam(value = "studentids[]", required = true) Long[] studentids) {
		Map<String, String> ret = new HashMap<String, String>();
		if (studentids == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的数据");
			return ret;
		}
		String idsString = "";
		for (Long id : studentids) {
			idsString += id + ",";
		}
		idsString = idsString.substring(0, idsString.length() - 1);
		if (studentService.delete(idsString) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
	}

	/*
	 * 学生注册
	 */
	@RequestMapping(value = "/register")
	@ResponseBody
	public Map<String, String> register(Student student) {
		Map<String, String> ret = new HashMap<String, String>();
		if (student == null) {
			ret.put("type", "error");
			ret.put("msg", "数据绑定出错");
			return ret;
		}
		if (studentService.findByUserName(student.getStudentid()) == null) {
			ret.put("type", "error");
			ret.put("msg", "请输入正确的学号");
			return ret;
		}
		if (studentService.findByUserName(student.getStudentid()).getRole().equals("已激活")) {
			ret.put("type", "error");
			ret.put("msg", "该账号已经注册请直接登录");
			return ret;
		} else {
			student.setRole("已激活");
			student.setState("待业");
		}
		if (studentService.findByUserName(student.getStudentid()).getNumber() != student.getNumber()) {
			ret.put("type", "error");
			ret.put("msg", "学号与所选院校不符");
			return ret;
		}
	
		student.setId(studentService.findByUserName(student.getStudentid()).getId());
		if (studentService.edit(student) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "注册失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "注册成功");
		return ret;
	}

	/*
	 * 学生修改密码
	 */
	@RequestMapping(value = "/alertPwd", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, String> alertPawd(@Param(value = "password") String password,
			@Param(value = "oldPassword") String oldPassword) {
		Map<String, String> ret = new HashMap<String, String>();
		Student student = (Student) request.getSession().getAttribute("user");
		System.out.println(oldPassword);
		if (!student.getPassword().equals(oldPassword)) {
			ret.put("type", "error");
			ret.put("msg", "原密码错误");
			return ret;
		}
		student.setPassword(password);
		if (studentService.edit(student) < 0) {
			ret.put("type", "error");
			ret.put("msg", "密码修改失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "密码修改成功");
		return ret;
	}

	/*
	 * 辅导员修改学生信息
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody // 将数据格式转换成json数据格式
	public Map<String, String> edit(Student student) {
		Map<String, String> ret = new HashMap<String, String>();
		if (student.getStudentid() == null) {
			Student student2 = (Student) request.getSession().getAttribute("user");
			student.setStudentid(student2.getStudentid());
			student.setId(studentService.findByUserName(student2.getStudentid()).getId());
			student.setNumber(student2.getNumber());
			student.setPassword(student2.getPassword());
			student.setRole("已激活");
			student.setState("就业");
			student.setSex(student2.getSex());
			student.setStudentname(student2.getStudentname());
			request.getSession().setAttribute("students", student);
		} else {
			//student.setId(studentService.findByUserName(student.getStudentid()).getId());
				student.setRole("已激活");
				if (StringUtils.isEmpty(student.getState())) {
					ret.put("type", "error");
					ret.put("msg", "就业情况不能为空");
					return ret;
				}
				if (StringUtils.isEmpty(student.getProvince())) {
					ret.put("type", "error");
					ret.put("msg", "工作地点不能为空");
					return ret;
				}
				if (StringUtils.isEmpty(student.getInformation())) {
					ret.put("type", "error");
					ret.put("msg", "工作介绍不能为空");
					return ret;
				}
				if (StringUtils.isEmpty(student.getType())) {
					ret.put("type", "error");
					ret.put("msg", "工作类型不能为空");
					return ret;
				}
			} 
		
		if (studentService.edit(student) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}

	@RequestMapping(value = "import_student", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> importFile(MultipartFile excelFile) {
		Map<String, String> ret = new HashMap<String, String>();
		if (excelFile == null) {
			ret.put("type", "error");
			ret.put("msg", "请选择文件!");
			return ret;
		}
		if (excelFile.getSize() > 10000000) {
			ret.put("type", "error");
			ret.put("msg", "文件超过10m");
			return ret;
		}
		String suffix = excelFile.getOriginalFilename().substring(excelFile.getOriginalFilename().lastIndexOf(".") + 1,
				excelFile.getOriginalFilename().length());
		if (!"xlsx,xls".contains(suffix)) {
			ret.put("type", "error");
			ret.put("msg", "请导入xlsx,xls格式表格!");
			return ret;
		}
		String msg = "";
		try {
			msg = addProductByFile(excelFile.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("".equals(msg)) {
			msg = "导入成功";
		}
		ret.put("type", "success");
		ret.put("msg", msg);
		return ret;
	}

	private String addProductByFile(InputStream inputStream) {
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("user");
		Integer number = admin.getNumber();
		try {
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);
			// 去掉表头，送第二行开始读
			for (int rowIndex = 1; rowIndex <= sheetAt.getLastRowNum(); rowIndex++) {
				XSSFRow row = sheetAt.getRow(rowIndex);
				Student student = new Student();
				if (getCellContent(row.getCell(0)) == null) {
					msg += "到" + (rowIndex + 1) + "导入失败";
					break;
				} else {
					student.setStudentid(getCellContent(row.getCell(0)));
					student.setStudentname(getCellContent(row.getCell(1)));
					student.setSex(getCellContent(row.getCell(2)));
					student.setNumber(number);
					student.setStudentphone(getCellContent(row.getCell(3)));
					student.setRole("未激活");
					student.setPassword("");
					student.setState("待业");
					if (studentService.add(student) <= 0) {
						msg += "到" + (rowIndex + 1) + "导入失败";
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	public static String getCellContent(XSSFCell xssfCell) {
		String cellValue = "null";
		if (null != xssfCell) {
			switch (xssfCell.getCellType()) {
			case 0:
				cellValue = String.valueOf((int) xssfCell.getNumericCellValue());
				break;
			case 1:
				cellValue = xssfCell.getStringCellValue();
				break;
			case 2:
				cellValue = xssfCell.getNumericCellValue() + "";

				break;
			case 3:
				cellValue = "";
				break;
			case 4:
				cellValue = String.valueOf(xssfCell.getBooleanCellValue());
				break;
			case 5:
				cellValue = String.valueOf(xssfCell.getErrorCellValue());
				break;
			}
		} else {
			cellValue = "";
		}

		return cellValue;
	}

}
