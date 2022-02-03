package com.search.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.search.api.model.BoParty;

@Repository
public interface BoPartyRepo extends JpaRepository<BoParty, String>{
	
	 @Transactional
	    @Modifying
	    @Query(value="select max(MDM_ID) from C_BO_PRTY_XREF where RLE_TYP='HCO' and mdm_id<>'N'", nativeQuery = true)
//	@Query(value="select max(mdm_id) from c_bo_prty_xref where mdm_id<>'N' and RLE_TYP='HCO'", nativeQuery = true)
	 List<String> getMDM_ID();
	 
	 @Transactional
	    @Modifying
	    @Query(value="update  C_BO_PRTY_xref set PKEY_SRC_OBJECT=:source_id where ROWID_OBJECT=:rowid", nativeQuery = true)
	 	void updatePkeySrc(String source_id,String rowid);
	 
	 @Transactional
	    @Modifying
	    @Query(value="select PKEY_SRC_OBJECT from C_BO_PRTY_XREF where PKEY_SRC_OBJECT=:source_id", nativeQuery = true)
	 List<String> verigySourceId(String source_id);
	 
	 @Transactional
	    @Modifying
	    @Query(value="select PKEY_SRC_OBJECT from C_BO_PSTL_ADDR_XREF where PKEY_SRC_OBJECT=:portal_Id", nativeQuery = true)
	 List<String> verigyportalId(String portal_Id);
	 
	 	@Transactional
	    @Modifying
	    @Query(value="update  C_BO_PSTL_ADDR_XREF set PKEY_SRC_OBJECT=:port_Id where ROWID_OBJECT=:rowid", nativeQuery = true)
	 	void updateAddressPkeySrc(String port_Id,String rowid);
	 

}
