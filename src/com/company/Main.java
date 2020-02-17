package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static final int Mb = 25;
    public static final int downloader = 10;

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(Mb);
        Semaphore sem = new Semaphore(3, true);
        CountDownLatch cdl2 = new CountDownLatch(downloader);

        Uploader upl = new Uploader(cdl);
        upl.start();

        try {
            cdl.await();
            System.out.println("Файл загружен на сервер");
            System.out.println("______________________");

            for (int i = 1; i <= downloader; i++) {
                Downloaders dwl = new Downloaders(sem, i, cdl2);
                dwl.start();
            }
            cdl2.await();
            System.out.println("файл удален с сервера");

        }
        catch (Exception e){
        }
    }
}

