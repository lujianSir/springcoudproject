package com.itheima.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) {
        Object o=new Object();
        synchronized (o){
            System.out.println(o);
        }

        Lock lock=new ReentrantLock();
        try{
            if(lock.tryLock()){
                System.out.println(lock);
            }
        }catch (Exception e){
            e.getMessage();
        }finally {
            lock.unlock();
        }

    }
}
