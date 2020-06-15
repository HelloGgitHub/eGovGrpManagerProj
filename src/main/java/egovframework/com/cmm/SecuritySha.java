package egovframework.com.cmm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @title : [공통] 암호화
 * @package : egovframework.com.cmm
 * @filename : SecuritySha.java
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
public class SecuritySha {
	
	public static String SHA256(String str){
		String SHA = "";
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			
			SHA = sb.toString();
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
			SHA = null;
		}
		return SHA;
	}
	
}
