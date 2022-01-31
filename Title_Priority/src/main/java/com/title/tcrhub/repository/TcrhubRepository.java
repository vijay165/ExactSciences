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
	@Query(value = "select\r\n"
			+ "   pm.ROWID_TASK,\r\n"
			+ "   case\r\n"
			+ "      when\r\n"
			+ "         (\r\n"
			+ "            Trim(p.LAST_ROWID_SYSTEM) = 'SALESFORCE' \r\n"
			+ "            AND Trim(p1.LAST_ROWID_SYSTEM) = 'SALESFORCE' \r\n"
			+ "         )\r\n"
			+ "      then\r\n"
			+ "         'High' \r\n"
			+ "      when\r\n"
			+ "         (\r\n"
			+ "            Trim(p.LAST_ROWID_SYSTEM) = 'PORTAL' \r\n"
			+ "            AND Trim(p1.LAST_ROWID_SYSTEM) = 'PORTAL' \r\n"
			+ "         )\r\n"
			+ "      then\r\n"
			+ "         'High' \r\n"
			+ "      when\r\n"
			+ "         (\r\n"
			+ "            Trim(p.LAST_ROWID_SYSTEM) = 'PORTAL' \r\n"
			+ "            AND Trim(p1.LAST_ROWID_SYSTEM) = 'SALESFORCE' \r\n"
			+ "            AND \r\n"
			+ "            (\r\n"
			+ "               P.LAST_ROWID_SYSTEM <> 'VEEVA' \r\n"
			+ "               AND P1.LAST_ROWID_SYSTEM <> 'VEEVA'\r\n"
			+ "            )\r\n"
			+ "         )\r\n"
			+ "      then\r\n"
			+ "         'High' \r\n"
			+ "      when\r\n"
			+ "         (\r\n"
			+ "            Trim(p.LAST_ROWID_SYSTEM) = 'SALESFORCE' \r\n"
			+ "            AND Trim(p1.LAST_ROWID_SYSTEM) = 'PORTAL' 				--AND pm.rowid_task<>pm.rowid_task \r\n"
			+ "         )\r\n"
			+ "      then\r\n"
			+ "         'High' \r\n"
			+ "      Else\r\n"
			+ "         'Normal' \r\n"
			+ "   end\r\n"
			+ "   as taskpriority \r\n"
			+ "from\r\n"
			+ "   C_BO_PRTY_MTCH PM \r\n"
			+ "   join\r\n"
			+ "      C_BO_PRTY P \r\n"
			+ "      ON P.ROWID_OBJECT = pm.ROWID_OBJECT \r\n"
			+ "   JOIN\r\n"
			+ "      C_BO_PRTY P1 \r\n"
			+ "      ON p1.ROWID_OBJECT = pm.ROWID_OBJECT_MATCHED \r\n"
			+ "      and CONVERT(VARCHAR(10), p.create_Date, 111) > dateadd(day, datediff(day, 10, GETDATE()), 0) \r\n"
			+ "      and \r\n"
			+ "      (\r\n"
			+ "         p.RLE_TYP = 'HCO' \r\n"
			+ "         and pm.ROWID_TASK is not NULL\r\n"
			+ "      )\r\n"
			+ "      and PM.ROWID_TASK in \r\n"
			+ "      (\r\n"
			+ "         select\r\n"
			+ "            pm.ROWID_TASK \r\n"
			+ "         from\r\n"
			+ "            C_BO_PRTY_MTCH PM \r\n"
			+ "            join\r\n"
			+ "               C_BO_PRTY P \r\n"
			+ "               ON P.ROWID_OBJECT = pm.ROWID_OBJECT \r\n"
			+ "            JOIN\r\n"
			+ "               C_BO_PRTY P1 \r\n"
			+ "               ON p1.ROWID_OBJECT = pm.ROWID_OBJECT_MATCHED \r\n"
			+ "               and CONVERT(VARCHAR(10), p.create_Date, 111) > dateadd(day, datediff(day, 10, GETDATE()), 0) \r\n"
			+ "               and \r\n"
			+ "               (\r\n"
			+ "                  p.RLE_TYP = 'HCO' \r\n"
			+ "                  and pm.ROWID_TASK is not NULL\r\n"
			+ "               )\r\n"
			+ "         group by\r\n"
			+ "            ROWID_TASK \r\n"
			+ "         having\r\n"
			+ "            COUNT(rowid_task) = 1 \r\n"
			+ "      )\r\n"
			+ "   UNION\r\n"
			+ "   select\r\n"
			+ "      ROWID_TASK,\r\n"
			+ "      case\r\n"
			+ "         when\r\n"
			+ "            (\r\n"
			+ "               CONCAT( S1, '', S2) like '%VEEVA%' \r\n"
			+ "               AND CONCAT( S1, '', S2) like '%SALESFORCE%' \r\n"
			+ "               AND CONCAT( S1, '', S2) like '%PORTAL%'\r\n"
			+ "            )\r\n"
			+ "         then\r\n"
			+ "            'Low' \r\n"
			+ "         when\r\n"
			+ "            (\r\n"
			+ "               CONCAT( S1, '', S2) like '%VEEVA%' \r\n"
			+ "               AND \r\n"
			+ "               (\r\n"
			+ "                  CONCAT( S1, '', S2) like '%PORTAL%' \r\n"
			+ "                  OR CONCAT( S1, '', S2) like '%SALESFORCE%'\r\n"
			+ "               )\r\n"
			+ "            )\r\n"
			+ "         then\r\n"
			+ "            'Normal' \r\n"
			+ "         else\r\n"
			+ "            'High' \r\n"
			+ "      end\r\n"
			+ "      as priority \r\n"
			+ "   from\r\n"
			+ "      (\r\n"
			+ "         select\r\n"
			+ "            pm.rowid_task,\r\n"
			+ "            TRIM(STRING_AGG(CAST(p.LAST_ROWID_SYSTEM as Nvarchar(MAX)), ',')) AS S1,\r\n"
			+ "            TRIM(STRING_AGG(CAST(p1.LAST_ROWID_SYSTEM as Nvarchar(MAX)), ',')) AS S2 \r\n"
			+ "         from\r\n"
			+ "            C_BO_PRTY_MTCH PM \r\n"
			+ "            join\r\n"
			+ "               C_BO_PRTY P \r\n"
			+ "               ON P.ROWID_OBJECT = pm.ROWID_OBJECT \r\n"
			+ "            JOIN\r\n"
			+ "               C_BO_PRTY P1 \r\n"
			+ "               ON p1.ROWID_OBJECT = pm.ROWID_OBJECT_MATCHED \r\n"
			+ "               and CONVERT(VARCHAR(10), p.create_Date, 111) > dateadd(day, datediff(day, 10, GETDATE()), 0) \r\n"
			+ "               and \r\n"
			+ "               (\r\n"
			+ "                  p.RLE_TYP = 'HCO' \r\n"
			+ "                  and pm.ROWID_TASK is not NULL\r\n"
			+ "               )\r\n"
			+ "               and PM.ROWID_TASK in \r\n"
			+ "               (\r\n"
			+ "                  select\r\n"
			+ "                     pm.ROWID_TASK \r\n"
			+ "                  from\r\n"
			+ "                     C_BO_PRTY_MTCH PM \r\n"
			+ "                     join\r\n"
			+ "                        C_BO_PRTY P \r\n"
			+ "                        ON P.ROWID_OBJECT = pm.ROWID_OBJECT \r\n"
			+ "                     JOIN\r\n"
			+ "                        C_BO_PRTY P1 \r\n"
			+ "                        ON p1.ROWID_OBJECT = pm.ROWID_OBJECT_MATCHED \r\n"
			+ "                        and CONVERT(VARCHAR(10), p.create_Date, 111) > dateadd(day, datediff(day, 10, GETDATE()), 0) \r\n"
			+ "                        and \r\n"
			+ "                        (\r\n"
			+ "                           p.RLE_TYP = 'HCO' \r\n"
			+ "                           and pm.ROWID_TASK is not NULL\r\n"
			+ "                        )\r\n"
			+ "                  group by\r\n"
			+ "                     ROWID_TASK \r\n"
			+ "                  having\r\n"
			+ "                     COUNT(rowid_task) > 1 \r\n"
			+ "               )\r\n"
			+ "         group by\r\n"
			+ "            pm.ROWID_TASK\r\n"
			+ "      )\r\n"
			+ "      a;"
			, nativeQuery = true)
	List<Map<String,String>> getrowidtasks();
}