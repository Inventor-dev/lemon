package xyz.lemone.lemon.system.support.toolkit;

import com.alibaba.druid.filter.config.ConfigTools;

import java.util.Scanner;

/**
 * 数据库密码生成器.
 *
 * @author lemon
 * @version 2018年1月19日
 */
public class DataBasePasswordGen {

    /**
     * 加密数据库密码.
     *
     * @param args 参数
     * @throws Exception encrypt
     */
    public static void main(String[] args) throws Exception {
        System.out.println("请输入数据库密码:");
        Scanner scan = new Scanner(System.in);
        String plainText = scan.nextLine();
        String encrypt = ConfigTools.encrypt(plainText);
        System.out.println("password:" + encrypt);
        scan.close();
    }

}
