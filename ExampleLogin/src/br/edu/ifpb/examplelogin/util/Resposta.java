package br.edu.ifpb.examplelogin.util;

public class Resposta {

    private int statusCodeHttp;

    private String contentValue;

    public Resposta(int statusCodeHttp, String contentValue) {
        this.statusCodeHttp = statusCodeHttp;
        this.contentValue = contentValue;
    }

    public int getStatusCodeHttp() {
        return statusCodeHttp;
    }

    public void setStatusCodeHttp(int statusCodeHttp) {
        this.statusCodeHttp = statusCodeHttp;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }
}