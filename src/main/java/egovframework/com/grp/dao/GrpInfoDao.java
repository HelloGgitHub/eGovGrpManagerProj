package egovframework.com.grp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @title : 사용자 그룹관리 Dao 
 * @package : egovframework.com.grp.dao
 * @filename : GrpInfoDao.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 사용자 그룹관리에 필요한 CRUD 모음
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@Repository
@Mapper
public interface GrpInfoDao {
	
	List<HashMap<Object, Object>> selectGrpList();
	List<HashMap<Object, Object>> selectGrpDetail(Map<Object, Object> param);
	int insertGrpInfo(Map<Object, Object> param);
	int updateGrpInfo(Map<Object, Object> param);
	int deleteGrpInfo(Map<Object, Object> param);

	List<HashMap<Object, Object>> selectGrpUsrList(Map<Object, Object> param);
	int insertGrpUsr(Map<Object, Object> param);
	int selectGrpUsrCk(Map<Object, Object> param);
	int selectUsrCk(Map<Object, Object> param);
	int deleteGrpUsr(Map<Object, Object> param);

}
