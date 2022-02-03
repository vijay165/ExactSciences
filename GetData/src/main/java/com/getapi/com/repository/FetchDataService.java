package com.getapi.com.repository;

import org.springframework.data.repository.CrudRepository;

import com.getapi.com.model.UserModel;

public interface FetchDataService extends CrudRepository<UserModel, Integer>{		

}
