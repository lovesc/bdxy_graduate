package cn.sc.service.impt;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.StudentDao;
import cn.sc.pojo.Student;
import cn.sc.service.StudentService;
@Service
public class StudentServiceImpt implements StudentService{
@Autowired
private StudentDao  studentDao;

@Override
public Student findByUserName(String studentid) {
	// TODO Auto-generated method stub
	return studentDao.findByUserName(studentid);
}

@Override
public int add(Student student) {
	// TODO Auto-generated method stub
	return studentDao.add(student);
}

@Override
public List<Student> findlist(Map<String, Object> queryMap) {
	// TODO Auto-generated method stub
	return studentDao.findlist(queryMap);
}

@Override
public int getTotal(Map<String, Object> queryMap) {
	// TODO Auto-generated method stub
	return studentDao.getTotal(queryMap);
}

@Override
public int edit(Student student) {
	// TODO Auto-generated method stub
	return studentDao.edit(student);
}

@Override
public int delete(String ids) {
	// TODO Auto-generated method stub
	return studentDao.delete(ids);
}
}
