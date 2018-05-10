package xyz.mint123.lemon.core.support.result;

/**
 * 
 * @author lemon
 * @version 2018/2/28
 */
public class ErrorResult<D> extends JsonResult<D> {

    private static final long serialVersionUID = 305397469774011470L;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
