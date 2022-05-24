package xyz.lemone.lemon.common.response;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * Response with batch record to return,
 * usually use in page query or conditional query.
 *
 * @param <T> record type
 * @author lemon
 */
public class MultiResponse<T> extends Response {

    /**
     * 成功.
     */
    public static final MultiResponse SUCCESS = MultiResponse.of(null);

    /**
     * total.
     */
    private Integer total;

    /**
     * data.
     */
    private Collection<T> data;

    /**
     * of.
     * @param data  records
     * @param total total number
     * @return
     */
    public static <T> MultiResponse<T> of(Collection<T> data, int total) {
        MultiResponse<T> multiResponse = new MultiResponse<>();
        multiResponse.setSuccess(true);
        multiResponse.setData(data);
        multiResponse.setTotal(total);
        return multiResponse;
    }

    /**
     * of without total.
     * @param data records
     * @return
     */
    public static <T> MultiResponse<T> of(Collection<T> data) {
        return of(data, 0);
    }

    /**
     * buildFailure.
     * @param errCode error code
     * @param errMessage default error message
     * @return
     */
    public static MultiResponse buildFailure(String errCode, String errMessage) {
        MultiResponse response = new MultiResponse();
        response.setSuccess(false);
        response.setErrorCode(errCode);
        response.setErrorMessage(errMessage);
        return response;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MultiResponse.class.getSimpleName() + "[", "]")
                .add("total=" + total)
                .add("data=" + data)
                .add("success=" + success)
                .add("errorCode='" + errorCode + "'")
                .add("errorMessage='" + errorMessage + "'")
                .toString();
    }
}
