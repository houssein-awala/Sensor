package com.distributed.system.project;

public class Data {
    protected String data;
    private final Object dataLock=new Object();
    public Data() {
    }

    public void readData(String data)
    {

    }

    public String getData() {
        synchronized (dataLock) {
            return data;
        }
    }

    public void setData(String data) {
        synchronized (dataLock) {
            this.data = data;
        }
    }
}
