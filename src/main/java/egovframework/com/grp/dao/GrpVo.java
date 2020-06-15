package egovframework.com.grp.dao;

/**
 * @title : 사용자 그룹관리 입출력 Vo 
 * @package : egovframework.com.grp.dao
 * @filename : GrpVo.java
 * @author : "egov"
 * @since : 2020. 6. 15.
 * @version : 1.0
 * @desc : 사용자 그룹관리 입출력 Vo
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 15.         "egov"           최초 생성(ver 1.0)
 * 
 */
public class GrpVo {

	private String grpId;
	private String grpNm;
	private String grpDc;
	
	
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public String getGrpNm() {
		return grpNm;
	}
	public void setGrpNm(String grpNm) {
		this.grpNm = grpNm;
	}
	public String getGrpDc() {
		return grpDc;
	}
	public void setGrpDc(String grpDc) {
		this.grpDc = grpDc;
	}
}
