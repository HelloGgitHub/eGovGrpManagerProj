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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "GrpController", description = "그룹 정보 관리 REST APIddddddddddddd")
@RequestMapping("/grp")
public class GrpController {
	
	@Autowired
	GrpInfoService grpService;
	
	@ApiOperation(value = "그룹목록 조회")
	@GetMapping(path = "/list")
	public String UserList() {
		
		String rtn = "";
		ObjectMapper om = new ObjectMapper();

		List<HashMap<Object, Object>> lst = new ArrayList<HashMap<Object, Object>>();
		lst = grpService.selectGrpList();
		System.out.println(lst);
		
		try {
			rtn = om.writeValueAsString(lst);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}
	
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
		System.out.println(lst);
		
		try {
			rtn = om.writeValueAsString(lst);
		} catch (JsonProcessingException e) {
			rtn = "json Mapper Error.";
			e.printStackTrace();
		}
		
		return rtn;
	}
	
	@ApiOperation(value = "그룹 등록", notes = "그룹을 등록합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "Internal Server Error !!"),
            @ApiResponse(code = 404, message = "Not Found !!")
    })
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
		System.out.println(rtnMap);
		return rtn;
	}

	
	@ApiOperation(value = "그룹 정보수정")
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
		System.out.println(rtnMap);
		return rtn;
	}
	
	
	@ApiOperation(value = "그룹 삭제", notes = "그룹을 삭제한다")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "groupId"	, value = "그룹ID"	, required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK !!"),
        @ApiResponse(code = 500, message = "Internal Server Error !!"),
        @ApiResponse(code = 404, message = "Not Found !!")
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
		System.out.println(rtnMap);
		return rtn;
	}
	
}
