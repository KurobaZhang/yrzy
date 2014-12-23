package my.yrzy.common.exception;

/**
 * Created by yangzefeng on 14/12/14
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1408609695258051419L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
