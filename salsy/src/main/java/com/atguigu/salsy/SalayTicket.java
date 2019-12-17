package com.atguigu.salsy;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int number=30;
    private Lock lock = new ReentrantLock();
      public  void salay(){
          lock.lock();
          try {
              if(number>0){
                  System.out.println(Thread.currentThread().getName()+"\t 卖出第:"+(number--)+"\t 还剩下:"+number);
                     }
          } catch (Exception e) {
              e.printStackTrace();
          }finally {
              lock.unlock();
          }
      }










    //  public synchronized void salay(){
    //    if(number>0){
    //        System.out.println(Thread.currentThread().getName()+"\t 卖出第:"+(number--)+"\t 还剩下:"+number);
    //    }
    // }
}





public class SalayTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35; i++) {
                    ticket.salay();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35; i++) {
                    ticket.salay();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35; i++) {
                    ticket.salay();
                }
            }
        },"C").start();
    }
}
