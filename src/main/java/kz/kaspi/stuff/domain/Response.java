package kz.kaspi.stuff.domain;

import org.springframework.ui.ModelMap;

public class Response extends org.apache.catalina.connector.Response{

    public static enum Status {
        ERROR, AUTHORIZATION_ERROR, DATA_ERROR, OK
    }

    public static enum Code {
        OK(200),
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

    private Status status;
    private String message;
    private final ModelMap map = new ModelMap();

    public Response() {
    }

    public Response(Status status, String message) {
        this.status = status;
        this.message = message;
        map.addAttribute("code", status);
        map.addAttribute("message", message);
    }

    public void setStatus(Status status) {
        this.status = status;
        map.addAttribute("code", status);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        map.addAttribute("message", message);
    }


}
