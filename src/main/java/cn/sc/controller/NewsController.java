package cn.sc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sc.pojo.News;
import cn.sc.pojo.Recruit;
import cn.sc.service.NewsService;
import cn.sc.util.Page;

@RequestMapping("/news")
@Controller
public class NewsController {
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/list")
   public String list() {
	   return "news/news_list";
   }
	@RequestMapping("/tonews")
	public String tonews() {
		return "news/news";
	}
	@RequestMapping("/get_list")
	@ResponseBody
	public Map<String, Object> getlist(Page page,String newstype,String newstime){
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (newstime != null && newstime!="") {
			String[] t=newstime.split("/");
			String newtime =t[2] + "-" + t[0] + "-" + t[1];
			try {
				Date date=sdf.parse(newtime);
				queryMap.put("newstime",sdf.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}
		}
		queryMap.put("newstype", newstype);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows",newsService.findlist(queryMap));
		ret.put("total", newsService.getTotal(queryMap));
		return ret;
	}
	@RequestMapping("/get_news")
	@ResponseBody
	public Map<String, Object> getnews(Integer page,Integer rows,String newstype){
		Map<String, Object> ret=new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<String,Object>();
		queryMap.put("newstype", newstype);
		queryMap.put("offset", (page-1)*rows);
		queryMap.put("pageSize", rows);
		ret.put("rows",newsService.findlist(queryMap));
		ret.put("total", newsService.getTotal(queryMap));
		return ret;
	}
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, String> add(News news){	
			Map<String, String> ret = new HashMap<String, String>();
			if (news == null) {
				ret.put("type", "error");
				ret.put("msg", "数据捆绑错误");
				return ret;
			}
			if (StringUtils.isEmpty(news.getNewscontent())) {
				ret.put("type", "error");
				ret.put("msg", "新闻内容不能为空");
				return ret;
			}
			if (StringUtils.isEmpty(news.getNewsfbz())) {
				ret.put("type", "error");
				ret.put("msg", "新闻发布人不能为空");
				return ret;
			}
			if (StringUtils.isEmpty(news.getNewstype())) {
				ret.put("type", "error");
				ret.put("msg", "新闻类型不能为空");
				return ret;
			}
			
			if (newsService.add(news) <= 0) {
				ret.put("type", "error");
				ret.put("msg", "添加失败");
				return ret;
			}
			ret.put("type", "success");
			ret.put("msg", "添加成功");
			return ret;
		}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, String> edit(News news){
		
		Map<String, String> ret = new HashMap<String, String>();
		if (news == null) {
			ret.put("type", "error");
			ret.put("msg", "数据捆绑错误");
			return ret;
		}
		if (StringUtils.isEmpty(news.getNewscontent())) {
			ret.put("type", "error");
			ret.put("msg", "新闻内容不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(news.getNewsfbz())) {
			ret.put("type", "error");
			ret.put("msg", "新闻发布人不能为空");
			return ret;
		}
		if (StringUtils.isEmpty(news.getNewstype())) {
			ret.put("type", "error");
			ret.put("msg", "新闻类型不能为空");
			return ret;
		}
		
		if (newsService.edit(news) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "修改失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功");
		return ret;
	}
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

		if (newsService.delete(idsString) <= 0) {
			ret.put("type", "error");
			ret.put("msg", "删除失败");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功");
		return ret;
	}
	@RequestMapping("/findnews")
	public String findnews(Model model,Integer id) {
		model.addAttribute("news",newsService.findById(id));
		return "news/tonews";
	}

}
