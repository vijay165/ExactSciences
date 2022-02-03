package com.HCPTOHCO.Repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HCPTOHCO.Model.HcpToHcoModal;

@Repository
public interface HcpToHcoRepo extends JpaRepository<HcpToHcoModal, String> {
	List<HcpToHcoModal> findAll();

}
