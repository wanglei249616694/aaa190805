package com.atguigu.salsy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditions{
    private int numb = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();
    public  void jiajia() throws InterruptedException {
        lock.lock();
                  try {
                      while (numb!=0){
                          condition.await();
                      }
                      ++numb;
                      System.out.println(Thread.currentThread().getName()+"\t"+numb);
                      condition.signalAll();

                  } catch (Exception e) {
                      e.printStackTrace();
                  }finally {
                      lock.unlock();
                  }

    }
    public  void jianjian() throws InterruptedException {
        lock.lock();
                  try {
                      while (numb==0){
                          condition.await();
                      }
                      --numb;
                      System.out.println(Thread.currentThread().getName()+"\t"+numb);
                      condition.signalAll();
                  } catch (Exception e) {
                      e.printStackTrace();
                  }finally {
                      lock.unlock();
                  }

    }
}
public class Demo2 {
    public static void main(String[] args) {
        AirConditions airConditions = new AirConditions();
        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    airConditions.jiajia();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            },"A").start();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    airConditions.jianjian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    airConditions.jiajia();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    airConditions.jianjian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
