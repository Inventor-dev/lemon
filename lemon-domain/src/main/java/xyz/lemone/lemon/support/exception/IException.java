package xyz.lemone.lemon.support.exception;

import xyz.lemone.lemon.support.error.IError;

/**
 * 异常约定.
 *
 * @author lemon
 * @version 2021/10/03
 */
public interface IException {
    
    /**
     * 获取通用异常信息.
     *
     * @return
     */
    IError getError();
    
}
