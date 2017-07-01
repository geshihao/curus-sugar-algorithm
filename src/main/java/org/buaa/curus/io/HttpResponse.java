package org.buaa.curus.io;

import org.buaa.curus.utils.cons.HttpCons;

/**
 * Created by qixiang on 6/28/17.
 */
public class HttpResponse {

    private int status;
    private String info;
    private Object data;

    public HttpResponse(Object data)
    {
        this.status = HttpCons.S_OK;
        this.info = HttpCons.StatusInfo.get(this.status);
        this.data = data;
    }

    public HttpResponse(int status)
    {
        this.status = status;
        this.info = HttpCons.StatusInfo.get(this.status);
        this.data = null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "status=" + status +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
