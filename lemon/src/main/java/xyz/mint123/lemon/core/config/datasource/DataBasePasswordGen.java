package xyz.mint123.lemon.core.config.datasource;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
/**
 * 在此处 生成 公钥 秘钥  和密码
 */
import java.util.Scanner;

import com.alibaba.druid.filter.config.ConfigTools;
/**
 * 数据库密码生成器
 * @author lemon
 * @version 2018年1月19日
 */
public class DataBasePasswordGen {


	/**
	 * 加密数据库密码
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("请输入数据库密码:");
        Scanner scan = new Scanner(System.in);
        String plainText = scan.nextLine();
        String encrypt = ConfigTools.encrypt("buzhidao");
		System.out.println("password:" + encrypt);
        scan.close();
	}
	
	
	
	
	
	
}
