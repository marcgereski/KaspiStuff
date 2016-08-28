package kz.kaspi.stuff.domain;

public class Response {
    public static enum Status {
        ERROR, OK
    }

    private Status status;

    private String message;

    public Response() {
    }

    public Response(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
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
