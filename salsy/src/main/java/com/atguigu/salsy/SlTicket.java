package com.atguigu.salsy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tcket{
    private int number = 30;
    public Lock lock = new ReentrantLock();
    public void sl(){
        lock.lock();
                  try {
                      if (number>0){
                          System.out.println(Thread.currentThread().getName()+"\t 卖出第:"+(number--)+"\t 还剩下:"+number);
                      } 
                  } catch (Exception e) {
                      e.printStackTrace();
                  }finally {
                      lock.unlock();
                  }
        
    }

}
public class SlTicket {
    public static void main(String[] args) {
        Tcket tcket = new Tcket();
        
    }
}