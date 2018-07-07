package xyz.mint123.lemon.core.support.result;

public class BaseResult<E> extends JsonResult<E> {

    private static final long serialVersionUID = 6240245965295684913L;

    {
        success = true;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

}
