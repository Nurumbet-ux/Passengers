package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

class Passengers extends Thread{
    Semaphore s;
    CountDownLatch cdl;
    public Passengers(String name,CountDownLatch cdl,Semaphore s) {
        super(name);
        this.cdl = cdl;
        this.s = s;
    }
    @Override
    public void run() {
        try {
            s.acquire();
            sleep(500);
            System.out.println(this.getName()+" took ticket!");
            s.release();
            cdl.countDown();
            cdl.await();
        }catch (Exception ignore){

        }
    }
}


public class Main {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(4);
        CountDownLatch cdl = new CountDownLatch(101);
        for (int i = 1; i <=100; i++) {
            new Passengers("Passenger "+i,cdl,s).start();
        }
       while(cdl.getCount()>1){
           try {
               Thread.currentThread().sleep(100);
           } catch (Exception ignore) {
           }
       }
        System.out.println("The bus is full!!!\nBON VOYAGE!!!");
        cdl.countDown();
    }
}
