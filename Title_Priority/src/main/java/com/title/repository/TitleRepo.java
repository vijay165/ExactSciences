/*
 * package com.title.repository;
 * 
 * import java.util.List; import java.util.Map;
 * 
 * import javax.transaction.Transactional;
 * 
 * import org.springframework.boot.autoconfigure.data.jpa.
 * JpaRepositoriesAutoConfiguration; import
 * org.springframework.data.jpa.repository.JpaRepository; import
 * org.springframework.data.jpa.repository.Modifying; import
 * org.springframework.data.jpa.repository.Query; import
 * org.springframework.stereotype.Repository;
 * 
 * 
 * import com.title.model.PartyModel;
 * 
 * @Repository public interface TitleRepo extends JpaRepository<PartyModel,
 * String> {
 * 
 * 
 * @Transactional
 * 
 * @Modifying
 * 
 * @Query(value =
 * "select concat(TargetNamespace,':',ProcessId) as taskId ,Summary as \"title\",\r\n"
 * + "Priority= 'Low',\r\n" + "opType='Edit'\r\n" +
 * "from avos.dbo.AeB4PTaskDateView where ProcessId in (select ProcessId from avos.dbo.AeB4PTaskPa where paprocessid\r\n"
 * +
 * "in ( select rowid_task from tcr_hub.dbo.C_BO_PRTY_MTCH where ROWID_OBJECT_MATCHED in ( select rowid_object from tcr_hub.dbo.C_BO_PRTY\r\n"
 * + "where RLE_TYP ='HCO')))\r\n" + "and state =1\r\n" +
 * "and DATEADD(MILLISECOND, CreationTimeMillis % 1000, DATEADD(SECOND, CreationTimeMillis / 1000, '19700101'))\r\n"
 * + "> dateadd(day,datediff(day,2,GETDATE()),0)", nativeQuery = true)
 * List<Map<String,String>> getMDM_ID();
 * 
 * 
 * }
 */