package cn.sc.dao;

import java.util.List;

import cn.sc.pojo.Address;

public interface AddressDao {
	public List<String> findprovince();
	public String findcity(String province);
	public Address findall(String province);

}
