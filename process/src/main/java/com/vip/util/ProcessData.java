package com.vip.util;

/**
 * Created by pro on 2017/10/12.
 */
public class ProcessData {

    //进程id
    public String PID;
    //cpu占用率
    public Double cpuUsage = 0.0;
    //运行状态
    public Boolean state = false;

    public Integer cpuTotal = 0;

    public Integer getCpuTotal() {
        return cpuTotal;
    }

    public void setCpuTotal(Integer cpuTotal) {
        this.cpuTotal = cpuTotal;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
