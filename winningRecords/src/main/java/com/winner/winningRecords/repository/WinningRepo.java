package com.winner.winningRecords.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.winner.winningRecords.model.WinningModel;

public interface WinningRepo extends CrudRepository<WinningModel, Integer>{
	 @Transactional
	    @Modifying
	    @Query(value = "update C_BO_PRTY set PROCESS_IND='Y' where SOURCE_ID= :userId",
	            nativeQuery = true)
	void updateUser(@Param("userId") String userId);
}
