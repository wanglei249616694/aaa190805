package com.atguigu.salsy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

 class MyCache{
    private volatile Map<String,String> map = new HashMap();
    ReentrantReadWriteLock rwl = new  ReentrantReadWriteLock();

    public void put(String key,String value){
        rwl.writeLock().lock();
                  try {
                      System.out.println(Thread.currentThread().getName()+"\t 开始写");
                      map.put(key, value);
                      System.out.println(Thread.currentThread().getName()+"\t 写结束");

                  } catch (Exception e) {
                      e.printStackTrace();
                  }finally {
                      rwl.writeLock().unlock();
                  }

    }

    public void get(String key){
        rwl.ReadLock().lock();
                  try {
                      System.out.println(Thread.currentThread().getName()+"\t 开始读");
                      String result = map.get(key);
                      System.out.println(Thread.currentThread().getName()+"\t 读结束"+result);

                  } catch (Exception e) {
                      e.printStackTrace();
                  }finally {
                      rwl.ReadLock().lock();
                  }

    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <=10; i++) {
            final int tempI = i;
            new Thread(()->{
                myCache.put(tempI+"",tempI+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10; i++) {
            final int tempI = i;
            new Thread(()->{
                myCache.get(tempI+"11111111");
            },String.valueOf(i)).start();
        }
    }
}
