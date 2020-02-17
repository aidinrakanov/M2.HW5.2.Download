package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders extends Thread {
    private  int file = 500;
    private int speed = 100;
    CountDownLatch cdl2;
    Semaphore sem;
    private int id;

    public Downloaders(Semaphore sem, int id, CountDownLatch cdl2) {
        this.cdl2 = cdl2;
        this.sem = sem;
        this.id = id;
    }

    public synchronized void run() {
        try {
            sem.acquire();
            System.out.println("человек " + id + " начал загрузку");
            sleep((file/speed) * 1000);
            System.out.println("человек " + id + " завершил загрузку");
            sem.release();

            cdl2.countDown();
        }
        catch (Exception e) {
        }
    }
}
