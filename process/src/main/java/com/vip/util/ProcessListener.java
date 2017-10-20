package com.vip.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pro on 2017/10/11.
 */
public class ProcessListener implements Runnable {

    private ProcessData processData;

    private long time = 1000;

    public ProcessListener(ProcessData processData, long time){
        this.processData = processData;
        this.time = time;
    }

    @Override
    public void run(){
        getCpuUsage();
    }

    private void getCpuUsage(){

        if(processData.getPID() == null){
            return;
        }

        BufferedReader in = null;

        try{
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("top -pid " + processData.getPID());
            String str = null;
            processData.setCpuTotal(rt.availableProcessors());
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            boolean flag = false;
            int count = 0;
            while(true){
                str = in.readLine();
                if(str == null){
                    processData.setState(false);
                    return;
                }
                if(!flag && str.indexOf("PID")!=-1){
                    count = str.indexOf("%CPU");
                    flag = true;
                }
                if(str.indexOf(processData.getPID())!=-1){
                    str = str.substring(count,str.length());
                    str = str.substring(0,str.trim().indexOf(" "));
                    processData.setState(true);
                    processData.setCpuUsage(Double.valueOf(str)/processData.getCpuTotal());
                    //System.out.println("cpuUsage: " + processData.getCpuUsage());
                    Thread.sleep(time);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
