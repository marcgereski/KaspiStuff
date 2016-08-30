package kz.kaspi.stuff.domain;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class Response extends org.apache.catalina.connector.Response {

    public static enum Status {
        ERROR, OK
    }

    public static enum Code {
        PageNotFound(404),
        NotAcceptable(406),
        PreconditionFailed(412);

        private final int val;

        Code(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    public static final String ERROR_STATUS_CODE_KEY = "javax.servlet.error.status_code";
    public static final String ERROR_EXCEPTION_KEY = "javax.servlet.error.exception";

    private Status status;

    private String message;

    private String code = "";

    private Throwable exception = new Exception();

    public Response() {
        super();
    }

    public Response(Code code, Throwable exception) {
        this.code = String.valueOf(code.getVal());
        this.exception = exception;
    }

    public Response(Status status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
