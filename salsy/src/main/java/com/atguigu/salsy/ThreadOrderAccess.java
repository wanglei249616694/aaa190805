package com.atguigu.salsy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private int flag = 1;
    private Lock lock =new ReentrantLock();
    private Condition c1= lock.newCondition();
    private Condition c2= lock.newCondition();
    private Condition c3= lock.newCondition();
    public void print5(){
        lock.lock();
                  try {
                        while(flag!=1){
                            c1.await();
                        }
                      for (int i = 1; i <=5; i++) {
                          System.out.println(Thread.currentThread().getName()+"\t"+i);
                      }
                      flag = 2;
                        c2.signal();
                  } catch (Exception e) {
                      e.printStackTrace();
                  }finally {
                      lock.unlock();
                  }
    }

    public void print10(){
        lock.lock();
        try {
            while(flag!=2){
                c2.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while(flag!=3){
                c3.await();
            }
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag = 3;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                shareResource.print5();
            }
            },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}
