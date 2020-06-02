package cn.sc.service.impt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sc.dao.CollegesDao;
import cn.sc.pojo.Colleges;
import cn.sc.service.CollegesService;
@Service
public class CollegesServiceImpt implements CollegesService {
 @Autowired
 private CollegesDao collegesDao;
	public List<Colleges> findlist() {
		// TODO Auto-generated method stub
		return collegesDao.findlist();
	}

}
