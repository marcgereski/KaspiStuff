package kz.kaspi.stuff.domain;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response {

    public static enum Status {
        ERROR, AUTHORIZATION_ERROR, DATA_ERROR, OK
    }

    private Status code;
    private String message;
    private final HttpServletResponse response;
    private final ModelMap map = new ModelMap();

    public Response(HttpServletResponse response) {
        this.response = response;
    }

    public Response(HttpServletResponse response, Status code, String message) {
        this.response = response;
        this.code = code;
        this.message = message;
        map.addAttribute("code", code);
        map.addAttribute("message", message);
    }

    public void setCode(Status code) {
        this.code = code;
        map.addAttribute("code", code);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        map.addAttribute("message", message);
    }

    public String getCode() {
        return code.toString();
    }

    public void sendRedirect(String target) throws IOException {
        response.sendRedirect(target);
    }
}
