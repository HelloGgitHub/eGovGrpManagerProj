package egovframework.com.cmm;

import java.util.ArrayList;
import java.util.List;

/**
 * @title : [공통] Util
 * @package : egovframework.com.cmm
 * @filename : ComUtil.java
 * @author : "egov"
 * @since : 2020. 6. 12.
 * @version : 1.0
 * 
 *  ======= 변경이력 =======
 * 
 * 날자                       변경자                  설명
 * ----------         -------           ------------------------------------
 * 2020. 6. 12.         "egov"           최초 생성(ver 1.0)
 * 
 */
public class ComUtil {

	/**
	 * @name : listforPaging
	 * @date : 2020. 6. 12.
	 * @author : "egov"
	 * @return_type : List<Object>
	 * @desc : List값을 받아서 페이지 표기 항목 갯수만큼 페이징 처리
	 */
	public List<Object> listforPaging(Object pLst, int page, int pageSize){
		List<Object> reLst = new ArrayList<Object>();
		List<Object> pArrayList = (ArrayList<Object>)pLst;  
		
		if((pArrayList.size()/pageSize)+1 < page) {
			return reLst;
		}
		
		int getPage = 0;
		if(page == 1) {
			getPage = 0;
		}else {
			getPage = (page-1)*pageSize;
		}
		
		for(int i = 0; pageSize > i; i++) {
			if(getPage == pArrayList.size()) {break;}
			reLst.add(i, pArrayList.get(getPage));
			
			getPage++;
		}
		
		return reLst;
	}
	
}
