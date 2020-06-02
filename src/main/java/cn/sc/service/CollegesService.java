package cn.sc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.sc.pojo.Colleges;
@Service
public interface CollegesService {
	public List<Colleges> findlist();
}
