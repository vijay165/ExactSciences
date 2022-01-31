package com.title.avos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.title.model.TitlePriorityModal;
import com.title.model.avos.Avos;

@Repository
public interface AvosRepository extends JpaRepository<Avos, Integer>{
	
	
	@Transactional
	@Modifying
	@Query(value = "Select concat(TargetNamespace,':',a.ProcessId) as taskId ,Summary as title,a.ProcessId, b.paprocessid as paprocessid"
			+ ",opType='Edit',Priority\r\n"
			+ "\r\n"
			+ "from avos.dbo.AeB4PTaskDateView a, avos.dbo.AeB4PTaskPa b where a.ProcessId = b.ProcessId and b.paprocessid in :rowidlist", 
	nativeQuery = true)
	List<Map<String,String>> getrowidtasks(List<Integer> rowidlist);
}
