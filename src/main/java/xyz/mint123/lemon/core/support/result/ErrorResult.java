package xyz.mint123.lemon.core.support.result;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author lemon
 * @version 2018/2/28
 */
public class ErrorResult<E> extends JsonResult<E> {

    private static final long serialVersionUID = 305397469774011470L;

    {
        success  = false;
        status  =HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    /**
     * 返回状态码
     * {@link StateCode}
     */
    protected Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
