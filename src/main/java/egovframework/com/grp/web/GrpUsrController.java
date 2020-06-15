package egovframework.com.grp.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.grp.dao.GrpInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @title : 사용자 그룹 매핑 관리
 * @package : egovframework.com.grp.web
 * @filename : GrpUsrController.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 사용자를 그룹과 매핑/삭제등 관리 하는데 필요한 API모음
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@RestController
@Api(value = "GrpUsrController", description = "그룹 사용자 정보 관리 REST API")
@RequestMapping("/grp")
public class GrpUsrController {
	
	@Autowired
	GrpInfoService grpService;
	
	/**
	 * @name : grpUserList(그룹사용자 목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 그룹에 매핑된 사용자 목록을 조회한다.
	 */
	@ApiOperation(value = "그룹사용자 목록 조회", notes = "그룹 사용자 목록을 조회한다.")
    @ApiImplicitParams({
       @ApiImplicitParam(name = "groupId"	, value = "그룹ID"		, required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
	@GetMapping(path = "/usrList")
	public String grpUserList(
			@RequestParam(value = "groupId") 	String grpId) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		String pGrpId 		= URLDecoder.decode(grpId		,"UTF-8");
		
		//입력값 파라미터 정의
		Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
		sqlInpt.put("GRP_ID", pGrpId);					//그룹ID
		
		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
		lst = grpService.selectGrpUsrList(sqlInpt);
		
		rtn = om.writeValueAsString(lst);
		return rtn;
	}
	
	
	/**
	 * @name : groupUserAdd(그룹 사용자 매핑)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 등록된 그룹에 사용자를 매핑한다.
	 */
	@ApiOperation(value = "그룹 사용자 매핑", notes = "그룹에 사용자를 등록한다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userId"	, value = "사용자ID"	, required = true, dataType = "string", paramType = "query", defaultValue = "")
       ,@ApiImplicitParam(name = "groupId"	, value = "그룹ID"		, required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
	@PostMapping(path = "/usrAdd")
	public String groupUserAdd(
			@RequestParam(value = "userId") 	String usrId,
			@RequestParam(value = "groupId") 	String grpId) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		String pUsrId 		= URLDecoder.decode(usrId	,"UTF-8");
		String pGrpId 		= URLDecoder.decode(grpId	,"UTF-8");
		
		//입력값 파라미터 정의
		Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
		sqlInpt.put("USR_ID", pUsrId);
		sqlInpt.put("GRP_ID", pGrpId);

		int chkReUse = grpService.selectGrpUsrCk(sqlInpt);
		int chkUsrAble = grpService.selectUsrCk(sqlInpt);
		if(chkReUse > 0) {
			rtnMap.put("RESULTCD", "9");
			rtnMap.put("RESULTMSG", "이미 등록된 사용자 입니다.");
		}else if(chkUsrAble > 0) {
			rtnMap.put("RESULTCD", "8");
			rtnMap.put("RESULTMSG", "사용자 ID가 올바르지 않습니다.");
		}else {
			int inputCnt = grpService.insertGrpUsr(sqlInpt);
			if(inputCnt > 0) {
				rtnMap.put("RESULTCD", "0");
				rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
			}else {
				rtnMap.put("RESULTCD", "1");
				rtnMap.put("RESULTMSG", "처리에 실패 하였습니다.");
			}
		}
		
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
	/**
	 * @name : groupUserDelete(그룹 사용자 매핑 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 그룹에 등록된 사용자 정보를 삭제한다.
	 */
	@ApiOperation(value = "그룹 사용자 매핑 삭제", notes = "그룹에 등록되어있는 사용자를 삭제한다.")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "userId"		,value = "사용자ID"		,required = true	,dataType = "string"	,paramType = "query"	,defaultValue = "")
       ,@ApiImplicitParam(name = "groupId"	,value = "그룹ID"		,required = true	,dataType = "string"	,paramType = "query"	,defaultValue = "")
    })
	@DeleteMapping(path = "/usrSbt")
	public String groupUserDelete(
			@RequestParam(value = "userId") 	String usrId,
			@RequestParam(value = "groupId") 	String grpId) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		String pUsrId 		= URLDecoder.decode(usrId	,"UTF-8");
		String pGrpId 		= URLDecoder.decode(grpId	,"UTF-8");
		
		//입력값 파라미터 정의
		Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
		sqlInpt.put("USR_ID", pUsrId);
		sqlInpt.put("GRP_ID", pGrpId);
		
		int inputCnt = grpService.deleteGrpUsr(sqlInpt);
		if(inputCnt > 0) {
			rtnMap.put("RESULTCD", "0");
			rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
		}else {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "처리에 실패 하였습니다.");
		}
		
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

}
