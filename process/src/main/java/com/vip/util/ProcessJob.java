package com.vip.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pro on 2017/10/12.
 */
public class ProcessJob {

    public ProcessData processData = new ProcessData();

    public void init(String PID,long time) {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        processData.setPID(PID);
        singleThreadPool.execute(new ProcessListener(processData,time));
        singleThreadPool.shutdown();
    }

    public ProcessData getProcessData(){
        return processData;
    }

    public static void main(String[] args) {
        String PID = "";
        if(args.length >= 1){
            PID = args[0];
        }
        ProcessJob job = new ProcessJob();
        job.init(PID,1000);
        ProcessData c = job.getProcessData();
        c.getCpuUsage();
    }
}
