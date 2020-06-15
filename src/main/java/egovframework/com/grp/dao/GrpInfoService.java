package egovframework.com.grp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @title : 사용자 그룹관리 Service 
 * @package : egovframework.com.grp.dao
 * @filename : GrpInfoService.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 사용자 그룹관리에 필요한 Service 모음
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@Service
@Transactional
public class GrpInfoService {
	
	@Autowired
    private GrpInfoDao mapper;
	
	/**
	 * @name : selectGrpList(그룹목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectGrpList() {
        return mapper.selectGrpList();
    }

	/**
	 * @name : selectGrpDetail(그룹상세정보 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectGrpDetail(Map<Object, Object> param) {
        return mapper.selectGrpDetail(param);
    }

	/**
	 * @name : insertGrpInfo(그룹가입신청)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int insertGrpInfo(Map<Object, Object> param) {
        return mapper.insertGrpInfo(param);
    }

	/**
	 * @name : updateGrpInfo(그룹정보 변경)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int updateGrpInfo(Map<Object, Object> param) {
        return mapper.updateGrpInfo(param);
    }

	/**
	 * @name : deleteGrpInfo(그룹정보 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int deleteGrpInfo(Map<Object, Object> param) {
        return mapper.deleteGrpInfo(param);
    }
	
	
	/**
	 * @name : selectGrpUsrList(그룹 사용자목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : List<HashMap<Object,Object>>
	 */
	public List<HashMap<Object, Object>> selectGrpUsrList(Map<Object, Object> param) {
        return mapper.selectGrpUsrList(param);
    }

	/**
	 * @name : insertGrpUsr(그룹 사용자 추가)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int insertGrpUsr(Map<Object, Object> param) {
        return mapper.insertGrpUsr(param);
    }

	/**
	 * @name : selectGrpUsrCk(그룹 기등록여부 확인)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 * @desc : 
	 */
	public int selectGrpUsrCk(Map<Object, Object> param) {
        return mapper.selectGrpUsrCk(param);
    }

	/**
	 * @name : selectUsrCk(그룹에 사용자 기등록여부 확인)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int selectUsrCk(Map<Object, Object> param) {
        return mapper.selectUsrCk(param);
    }
	
	/**
	 * @name : deleteGrpUsr(그룹 사용자 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : int
	 */
	public int deleteGrpUsr(Map<Object, Object> param) {
        return mapper.deleteGrpUsr(param);
    }

}
