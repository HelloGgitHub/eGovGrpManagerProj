package egovframework.com.grp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GrpInfoDao {
	
	//그룹목록 조회
	List<HashMap<Object, Object>> selectGrpList();
	//상세정보 조회
	List<HashMap<Object, Object>> selectGrpDetail(Map<Object, Object> param);
	//그룹정보 등록
	int insertGrpInfo(Map<Object, Object> param);
	//그룹정보 변경
	int updateGrpInfo(Map<Object, Object> param);
	//그룹정보 삭제
	int deleteGrpInfo(Map<Object, Object> param);

	
	
	//그룹 사용자목록 조회
	List<HashMap<Object, Object>> selectGrpUsrList(Map<Object, Object> param);
	//그룹 사용자 추가
	int insertGrpUsr(Map<Object, Object> param);
	//그룹 사용자 기등록여부 확인
	int selectGrpUsrCk(Map<Object, Object> param);
	//그룹 사용자 삭제
	int deleteGrpUsr(Map<Object, Object> param);

	
}
