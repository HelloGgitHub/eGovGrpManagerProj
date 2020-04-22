package egovframework.com.grp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GrpInfoService {
	
	@Autowired
    private GrpInfoDao mapper;
	
	//그룹목록 조회
	public List<HashMap<Object, Object>> selectGrpList() {
        return mapper.selectGrpList();
    }
	//그룹상세정보 조회
	public List<HashMap<Object, Object>> selectGrpDetail(Map<Object, Object> param) {
        return mapper.selectGrpDetail(param);
    }
	//그룹가입신청
	public int insertGrpInfo(Map<Object, Object> param) {
        return mapper.insertGrpInfo(param);
    }
	//그룹정보 변경
	public int updateGrpInfo(Map<Object, Object> param) {
        return mapper.updateGrpInfo(param);
    }
	//그룹정보 삭제
	public int deleteGrpInfo(Map<Object, Object> param) {
        return mapper.deleteGrpInfo(param);
    }
	
	
	
	//그룹 사용자목록 조회
	public List<HashMap<Object, Object>> selectGrpUsrList(Map<Object, Object> param) {
        return mapper.selectGrpUsrList(param);
    }
	//그룹 사용자 추가
	public int insertGrpUsr(Map<Object, Object> param) {
        return mapper.insertGrpUsr(param);
    }
	//그룹 기등록여부 확인
	public int selectGrpUsrCk(Map<Object, Object> param) {
        return mapper.selectGrpUsrCk(param);
    }
	//등록된 사용자 여부 확인
	public int selectUsrCk(Map<Object, Object> param) {
        return mapper.selectUsrCk(param);
    }
	//그룹 사용자 삭제
	public int deleteGrpUsr(Map<Object, Object> param) {
        return mapper.deleteGrpUsr(param);
    }

}
