package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class Main {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(4);
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 1; i <= 100; i++) {
            new Passengers("Passenger " + i, cdl, s).start();
        }
        try {
            cdl.await();
        } catch (Exception ignore) {}
        System.out.println("The bus is full!!!\nBON VOYAGE!!!");
    }
}
