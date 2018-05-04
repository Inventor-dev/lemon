package xyz.mint123.lemon.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.esotericsoftware.reflectasm.MethodAccess;

/**
 * @author lemon
 * @version 2018-1-22
 */
public class ReflectUtil {

	 public static Map<String, MethodAccess> methodMap = new HashMap<String, MethodAccess>();

	/**
     * 执行某对象方法
     * 
     * @param owner 对象
     * @param methodName 方法名
     * @param args  参数
     * @return 方法返回值
     */
    public static final Object invokeMethod(Object owner, String methodName, Object... args) {
        Class<?> ownerClass = owner.getClass();
        String key = null;
        if (args != null) {
            Class<?>[] argsClass = new Class[args.length];
            for (int i = 0, j = args.length; i < j; i++) {
                if (args[i] != null) {
                    argsClass[i] = args[i].getClass();
                }
            }
            // 用于区分重载的方法
            key = ownerClass + "_" + methodName + "_" + StringUtils.join(argsClass, ",");
        } else {
            // 用于区分重载的方法
            key = ownerClass + "_" + methodName;
        }
        MethodAccess methodAccess = methodMap.get(key);
        if (methodAccess == null) {
            methodAccess = MethodAccess.get(ownerClass);
            // 缓存Method对象
            methodMap.put(key, methodAccess);
        }
        return methodAccess.invoke(owner, methodName, args);
    }
}
