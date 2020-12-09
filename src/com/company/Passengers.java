package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

class Passengers extends Thread {
    Semaphore s;
    CountDownLatch cdl;

    public Passengers(String name, CountDownLatch cdl, Semaphore s) {
        super(name);
        this.cdl = cdl;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            s.acquire();
            System.out.println(this.getName() + " took ticket!");
            s.release();
            cdl.countDown();
        } catch (Exception ignore) {

        }
    }
}
