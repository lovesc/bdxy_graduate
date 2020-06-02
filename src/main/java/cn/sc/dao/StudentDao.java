package cn.sc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.sc.pojo.Student;

public interface StudentDao {
	public Student findByUserName(String studentid);
	public int add( Student student);
	public List<Student>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Student student);
	public int delete(String ids);
}
