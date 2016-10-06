package com.app;

import java.util.LinkedList;
import java.util.List;

/**
 * class to show a working example of a Producer Consumer.
 * producer() method adds a value to a linkedlist
 * Consumer() method remove a value from a linklist
 * They both are thread safe using a synchronized keyword.
 */
public class ProducerConsumer {
    List<Integer> list = new LinkedList<Integer>();
    int size = 10;
    public static void main(String[] args) {
        final ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            producerConsumer.producer();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            producerConsumer.consumer();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        t1.start();
        t2.start();
    }

    public synchronized void producer() throws InterruptedException {
        while(true){
            if(list.size() >= size){
                wait();
            }
            int random = (int) (Math.random()*51);
            list.add(random);
            System.out.println("size "+list.size());
            notify();
        }
    }

    public synchronized void consumer() throws InterruptedException {
        while(true){
            if(list.size() <=0){
                wait();
            }
            Thread.sleep(200);
            Integer remove = list.remove(0);
            System.out.println("element removed "+remove);
            notify();
        }
    }
}


