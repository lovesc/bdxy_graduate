package cn.sc.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class News {
	private Integer id;
	private String newsname;// 新闻标题
	private String newscontent;// 新闻内容
	private String newsfbz;// 发布者
	@JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
	private Date newstime;// 发布时间
	private String newstype;// 新闻类型

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewsname() {
		return newsname;
	}

	public void setNewsname(String newsname) {
		this.newsname = newsname;
	}

	public String getNewscontent() {
		return newscontent;
	}

	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}

	public String getNewsfbz() {
		return newsfbz;
	}

	public void setNewsfbz(String newsfbz) {
		this.newsfbz = newsfbz;
	}

	public Date getNewstime() {
		return newstime;
	}

	public void setNewstime(Date newstime) {
		this.newstime = newstime;
	}

	public String getNewstype() {
		return newstype;
	}

	public void setNewstype(String newstype) {
		this.newstype = newstype;
	}

}
