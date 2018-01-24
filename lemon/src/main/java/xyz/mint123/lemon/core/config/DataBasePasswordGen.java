package xyz.mint123.lemon.core.config;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
/**
 * 在此处 生成 公钥 秘钥  和密码
 */
import java.util.Scanner;

import com.alibaba.druid.filter.config.ConfigTools;
/**
 * 数据库密码生成器
 * @author YP
 * @version 2018年1月19日
 */
public class DataBasePasswordGen {

	/**
	 * 钥匙长度
	 */
	private static final int  KEY_SIAE= 512;

	/**
	 * 生成钥匙
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public static String[] genKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException{
		return ConfigTools.genKeyPair(KEY_SIAE);
	}
	
	
	/**
	 * 通过私钥 和 明文 密码  生成加密密码  
	 * @param key
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key, String plainText) throws Exception{
		return ConfigTools.encrypt(key, plainText);
	}
	
	/**
	 * 通过公钥 和 密码  解密
	 * @param publicKeyText
	 * @param cipherText
	 * @return
	 * @throws Exception 
	 */
	public String decrypt(String publicKeyText, String cipherText) throws Exception{
		return ConfigTools.decrypt(publicKeyText,cipherText);
	}





	/**
	 * 获取 公钥私钥和密码 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
        String key ="MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAg9NAzM6jDDRhEYqkajvJ8hxjk2EPNDptVbZliyUHZ6Tb4/4Diw1GQl4x6kafm+hWrfCLal1NG1pCF9YEcyjhyQIDAQABAkBslKvyRl3cZqRxi24Dpr2fM6z9LtwUG/AEwpj/NH/V6K7bSR8av1o2dre0+Id5iHCSLSJSuVsZ+JJSv6Z4Nn4BAiEAz9D/7j0EC7J8Zu+kSfSXSQ6yblA+Nyt5WERQ5ok/esECIQCiY8ZacySrJJl8DEBE2J+0oLgLHCmFEuceq1266DbRCQIhAKakKRWYx825ahgAwfSrtURYDKx4m+sRaVRmM5kpJUYBAiAv/uFfVmzeEIE0wsI0FvqM/num/PnglQeubuPTHFMnUQIhAJdGZyYPVDkaW0Wk6ZwyNWV6O3+X8yhhecZWuVENxN8v";
        System.out.println("请输入数据库密码:");
        Scanner scan = new Scanner(System.in);
        String plainText = scan.nextLine();
        String encrypt = encrypt(key,plainText);
		System.out.println("password:" + encrypt);
        scan.close();
	}
	
	
	
	
	
	
}
