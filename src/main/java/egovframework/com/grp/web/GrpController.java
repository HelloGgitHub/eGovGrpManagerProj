package egovframework.com.grp.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.grp.dao.GrpInfoService;
import egovframework.com.grp.dao.GrpVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @title : 사용자 그룹관리 
 * @package : egovframework.com.grp.web
 * @filename : GrpController.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 사용자 그룹관리에 필요한 API 모음
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
@RestController
@Api(value = "GrpController", description = "그룹 정보 관리 REST API")
@RequestMapping("/grp")
public class GrpController {
	
	@Autowired
	GrpInfoService grpService;
	
	/**
	 * @name : UserList(그룹목록 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 사용자 그룹 목록을 조회한다.
	 */
	@ApiOperation(value = "그룹목록 조회")
	@GetMapping(path = "/list")
	public String UserList() {
		
		String rtn = "";
		ObjectMapper om = new ObjectMapper();

		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
		lst = grpService.selectGrpList();
		
		try {
			rtn = om.writeValueAsString(lst);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}

	
	/**
	 * @name : GrpDetailInfo(그룹정보 조회)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 그룹에 대한 정보를 조회한다.
	 */
	@ApiOperation(value = "그룹정보 조회")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "groupId", value = "그룹ID", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
	@GetMapping(path = "/detailInfo/{groupId}")
	public String GrpDetailInfo(@PathVariable("groupId") String grpId) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
		
		Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
		sqlInpt.put("GRPID", URLDecoder.decode(grpId		,"UTF-8"));
		
		lst = grpService.selectGrpDetail(sqlInpt);
		
		try {
			rtn = om.writeValueAsString(lst);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}

	
	/**
	 * @name : InsertGroupInfo(그룹정보 등록)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 신규 그룹을 생성한다.
	 */
	@ApiOperation(value = "그룹 등록", notes = "그룹을 등록합니다.")
	@PostMapping(path = "/addnew")
	public String InsertGroupInfo(@RequestBody GrpVo param) throws Exception {

		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		
		//입력값 파라미터 정의
		Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
		sqlInpt.put("GROUP_ID"	, param.getGrpId());
		sqlInpt.put("GROUP_NM"	, param.getGrpNm());
		sqlInpt.put("GROUP_DC"	, param.getGrpDc());
		
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		int inputCnt = grpService.insertGrpInfo(sqlInpt);
		if(inputCnt > 0) {
			rtnMap.put("RESULTCD", "0");
			rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
		}else {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "등록에 실패 하였습니다.");
		}
		
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}

	
	/**
	 * @name : UserChangeInfo(그룹정보 수정)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 등록된 그룹정보를 수정한다.
	 */
	@ApiOperation(value = "그룹정보 수정")
	@PutMapping(path = "/modifyInfo")
	public String UserChangeInfo(@RequestBody GrpVo param) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		//입력값 파라미터 정의
		Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
		sqlInpt.put("GROUP_ID"	, param.getGrpId());
		sqlInpt.put("GROUP_NM"	, param.getGrpNm());
		sqlInpt.put("GROUP_DC"	, param.getGrpDc());
		
		int inputCnt = grpService.updateGrpInfo(sqlInpt);
		if(inputCnt > 0) {
			rtnMap.put("RESULTCD", "0");
			rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
		}else {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "사용자 정보 변경에 실패 하였습니다.");
		}
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}
	
	
	/**
	 * @name : GrpDeleteInfo(그룹 삭제)
	 * @date : 2020. 6. 15.
	 * @author : "egov"
	 * @return_type : String
	 * @desc : 등록된 사용자 그룹정보를 삭제 한다.
	 */
	@ApiOperation(value = "그룹 삭제", notes = "그룹을 삭제한다")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "groupId"	, value = "그룹ID"	, required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
	@DeleteMapping(path = "/deleteGrp")
	public String GrpDeleteInfo(@RequestParam(value = "groupId") String grpId) throws Exception {
		String rtn = "";
		ObjectMapper om = new ObjectMapper();
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		
		String pGrpId = URLDecoder.decode(grpId,"UTF-8");
		
		//입력값 파라미터 정의
		Map<Object, Object> sqlInpt = new HashMap<Object, Object>();
		sqlInpt.put("GROUP_ID"	, pGrpId);
		
		int inputCnt = grpService.deleteGrpInfo(sqlInpt);
		if(inputCnt > 0) {
			rtnMap.put("RESULTCD", "0");
			rtnMap.put("RESULTMSG", "정상 처리 되었습니다.");
		}else {
			rtnMap.put("RESULTCD", "1");
			rtnMap.put("RESULTMSG", "삭제에 실패 하였습니다.");
		}
		
		rtn = om.writeValueAsString(rtnMap);
		return rtn;
	}
	
}
