package com.company;

import java.util.concurrent.CountDownLatch;

public class Uploader extends  Thread{
    private CountDownLatch cdl;
    int uploadspeed = 500/20;

    public Uploader(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    public synchronized void run(){
        for (int i = 1; i <=25; i++) {
            System.out.println("файл загружается " + i*20 + "Mb");
            try {
                Thread.sleep(uploadspeed*10);
                cdl.countDown();

            } catch (Exception e) {
            }
        }

    }
}

