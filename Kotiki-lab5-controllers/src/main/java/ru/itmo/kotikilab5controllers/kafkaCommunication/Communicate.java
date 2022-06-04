package ru.itmo.kotikilab5controllers.kafkaCommunication;

public class Communicate {
    private Object[] parametrs;
    private String method;

    public Communicate(Object[] parametrs, String method) {
        this.parametrs = parametrs;
        this.method = method;
    }

    public Object[] getParametrs() {
        return parametrs;
    }

    public void setParametrs(Object[] parametrs) {
        this.parametrs = parametrs;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
