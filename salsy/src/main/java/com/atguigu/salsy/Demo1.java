package com.atguigu.salsy;
class AirCondition{
    private int num = 0;
    public synchronized void add() throws InterruptedException {
        while(num!=0){
            this.wait();
        }
        ++num;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        this.notifyAll();
    }
    public synchronized void jian() throws InterruptedException {
        while (num==0) {
            this.wait();
        }
        --num;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        this.notifyAll();
    }
}
public class Demo1 {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    airCondition.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            },"A").start();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    airCondition.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    airCondition.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i <10 ; i++) {
                try {
                    airCondition.jian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
