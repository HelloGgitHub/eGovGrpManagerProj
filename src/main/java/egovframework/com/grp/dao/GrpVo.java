package egovframework.com.grp.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
