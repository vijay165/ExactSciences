package com.hcp.deligate.repository;

import org.springframework.data.repository.CrudRepository;

import com.hcp.deligate.model.UserModel;



public interface FetchDataService extends CrudRepository<UserModel, Integer>{		

}

