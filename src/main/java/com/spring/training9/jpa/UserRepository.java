package com.spring.training9.jpa;

import java.util.List;

import com.spring.training9.entity.TblUser;


public interface UserRepository {
	List<TblUser> findAll();

	TblUser findById(int id);

	TblUser insert(TblUser user);

	TblUser update(TblUser user);

	void delete(int id);

}
