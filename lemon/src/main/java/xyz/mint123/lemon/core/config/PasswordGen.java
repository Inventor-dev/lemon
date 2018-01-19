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
public class PasswordGen {

	/**
	 * 钥匙长度
	 */
	private static final int keySize = 512;
	
	/**
	 * 生成钥匙
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public static String[] genKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException{
		return ConfigTools.genKeyPair(keySize);
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
		System.out.println("请输入数据库密码:");
		Scanner scan = new Scanner(System.in);
		String plainText = scan.nextLine();
        String[] arr = genKeyPair();
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        String encrypt = encrypt(arr[0],plainText);
		System.out.println("password:" + encrypt);
        scan.close();
	}
	
	
	
	
	
	
}
