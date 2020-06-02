package cn.sc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.sc.pojo.Student;
@Service
public interface StudentService {
	public Student findByUserName(String studentid);
	public int add(Student student);
	public List<Student>  findlist(Map<String, Object> queryMap);
	public int  getTotal(Map<String,Object> queryMap);
	public int edit(Student student);
	public int delete(String ids);
}
