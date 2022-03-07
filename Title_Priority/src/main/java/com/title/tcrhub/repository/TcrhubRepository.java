package com.title.tcrhub.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.title.model.tcrhub.Tcrhub;

@Repository
public interface TcrhubRepository extends JpaRepository<Tcrhub, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "SELECT  pm.rowid_task,\r\n"
			+ "       CASE\r\n"
			+ "         WHEN ( Trim(p.last_rowid_system) = 'SALESFORCE'\r\n"
			+ "                AND Trim(p1.last_rowid_system) = 'SALESFORCE' ) THEN 'High'\r\n"
			+ "         WHEN ( Trim(p.last_rowid_system) = 'PORTAL'\r\n"
			+ "                AND Trim(p1.last_rowid_system) = 'PORTAL' ) THEN 'High'\r\n"
			+ "         WHEN ( Trim(p.last_rowid_system) = 'PORTAL'\r\n"
			+ "                AND Trim(p1.last_rowid_system) = 'SALESFORCE'\r\n"
			+ "                AND ( P.last_rowid_system <> 'VEEVA'\r\n"
			+ "                      AND P1.last_rowid_system <> 'VEEVA' ) ) THEN 'High'\r\n"
			+ "         WHEN ( Trim(p.last_rowid_system) = 'SALESFORCE'\r\n"
			+ "                AND Trim(p1.last_rowid_system) = 'PORTAL' ) THEN 'High'\r\n"
			+ "         ELSE 'Normal'\r\n"
			+ "       END AS taskpriority\r\n"
			+ "FROM   c_bo_prty_mtch PM\r\n"
			+ "       JOIN c_bo_prty P\r\n"
			+ "         ON P.rowid_object = pm.rowid_object\r\n"
			+ "       JOIN c_bo_prty P1\r\n"
			+ "         ON p1.rowid_object = pm.rowid_object_matched\r\n"
			+ "            AND CONVERT(VARCHAR(10), pm.create_date, 111) >\r\n"
			+ "                Dateadd(day, Datediff(day,\r\n"
			+ "                             100,\r\n"
			+ "                             Getdate()), 0)\r\n"
			+ "            AND ( p1.rle_typ IN ( 'HCP','HCO')\r\n"
			+ "                  AND pm.rowid_task IS NOT NULL )\r\n"
			+ "            AND PM.rowid_task IN (SELECT pm.rowid_task\r\n"
			+ "                                  FROM   c_bo_prty_mtch PM\r\n"
			+ "                                         JOIN c_bo_prty P\r\n"
			+ "                                           ON P.rowid_object = pm.rowid_object\r\n"
			+ "                                         JOIN c_bo_prty P1\r\n"
			+ "                                           ON\r\n"
			+ "                                 p1.rowid_object = pm.rowid_object_matched\r\n"
			+ "                                 AND\r\n"
			+ "                                 CONVERT(VARCHAR(10), pm.create_date, 111) >\r\n"
			+ "                                 Dateadd(day, Datediff(day,\r\n"
			+ "                                              100,\r\n"
			+ "                                              Getdate()), 0)\r\n"
			+ "                                              AND ( p1.rle_typ IN ( 'HCP','HCO'\r\n"
			+ "                                                                  )\r\n"
			+ "                                                    AND\r\n"
			+ "                                 pm.rowid_task IS NOT NULL )\r\n"
			+ "                                  GROUP  BY rowid_task\r\n"
			+ "                                  HAVING Count(rowid_task) = 1)\r\n"
			+ "UNION\r\n"
			+ "SELECT rowid_task,\r\n"
			+ "       CASE\r\n"
			+ "         WHEN ( Concat(s1, '', s2) LIKE '%VEEVA%'\r\n"
			+ "                AND Concat(s1, '', s2) LIKE '%SALESFORCE%'\r\n"
			+ "                AND Concat(s1, '', s2) LIKE '%PORTAL%' ) THEN 'Low'\r\n"
			+ "         WHEN ( Concat(s1, '', s2) LIKE '%VEEVA%'\r\n"
			+ "                AND ( Concat(s1, '', s2) LIKE '%PORTAL%'\r\n"
			+ "                       OR Concat(s1, '', s2) LIKE '%SALESFORCE%' OR CONCAT(S1,'',S2) like '%VEEVA%' ) ) THEN\r\n"
			+ "         'Normal'\r\n"
			+ "         ELSE 'High'\r\n"
			+ "       END AS priority\r\n"
			+ "FROM   (SELECT pm.rowid_task,\r\n"
			+ "               Trim(String_agg(Cast(p.last_rowid_system AS NVARCHAR(max)), ','))\r\n"
			+ "               AS S1\r\n"
			+ "                      ,\r\n"
			+ "               Trim(String_agg(Cast(p1.last_rowid_system AS NVARCHAR(\r\n"
			+ "                                    max)), ',')) AS S2\r\n"
			+ "        FROM   c_bo_prty_mtch PM\r\n"
			+ "               JOIN c_bo_prty P\r\n"
			+ "                 ON P.rowid_object = pm.rowid_object\r\n"
			+ "               JOIN c_bo_prty P1\r\n"
			+ "                 ON p1.rowid_object = pm.rowid_object_matched\r\n"
			+ "                    AND CONVERT(VARCHAR(10), pm.create_date, 111) >\r\n"
			+ "                        Dateadd(day, Datediff(day,\r\n"
			+ "                                     100,\r\n"
			+ "                                     Getdate()), 0)\r\n"
			+ "                    AND ( p1.rle_typ IN ( 'HCP','HCO' )\r\n"
			+ "                          AND pm.rowid_task IS NOT NULL )\r\n"
			+ "                    AND PM.rowid_task IN (SELECT pm.rowid_task\r\n"
			+ "                                          FROM   c_bo_prty_mtch PM\r\n"
			+ "                                                 JOIN c_bo_prty P\r\n"
			+ "                                                   ON\r\n"
			+ "                                         P.rowid_object = pm.rowid_object\r\n"
			+ "                                                 JOIN c_bo_prty P1\r\n"
			+ "                                                   ON\r\n"
			+ "p1.rowid_object = pm.rowid_object_matched\r\n"
			+ "AND\r\n"
			+ "CONVERT(VARCHAR(10), pm.create_date, 111) >\r\n"
			+ "Dateadd(day, Datediff(day,\r\n"
			+ "             100,\r\n"
			+ "             Getdate()), 0)\r\n"
			+ "             AND ( p1.rle_typ IN ( 'HCP','HCO')\r\n"
			+ "                   AND\r\n"
			+ "pm.rowid_task IS NOT NULL )\r\n"
			+ " GROUP  BY rowid_task\r\n"
			+ " HAVING Count(rowid_task) > 1)\r\n"
			+ "GROUP  BY pm.rowid_task)a; "
			, nativeQuery = true)
	List<Map<String,String>> getrowidtasks();
}
