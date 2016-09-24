package com.example;


import java.util.concurrent.Semaphore;

public class DiningPhilosopher {

	Semaphore[] forks = new Semaphore[5];                       //��ũ ���ڸ� ������ �� �ִ�.
	Philosopher[] philosophers = new Philosopher[5];            //ö������ ���� ���� �� �� �ִ�.
	
	public DiningPhilosopher() {
		
		for(int i = 0 ; i < forks.length ; i++) {
			Semaphore s = new Semaphore(1, false);
			forks[i] = s;
		}
	}
	
	public void DoDining()  throws InterruptedException {
		for (int i=0 ; i< philosophers.length ; i++) {
			Philosopher p = new Philosopher(i, forks[i], forks[(i+1) % 5]);
			philosophers[i] = p;
			System.out.println("ö���� " + i + " �Ļ� ����.");
			
			philosophers[i].start();
		}
		
		
		Thread.sleep(10000);
		
		for (int i=0 ; i< philosophers.length ; i++) {
			philosophers[i].StopEating();
			philosophers[i].join();
		}
		
		for (int i=0 ; i< philosophers.length ; i++) {
			System.out.println("ö����" +philosophers[i].id + " ��(��) �Ļ縦 �߽��ϱ�? : " + philosophers[i].isEat);
		}
		
		System.out.println("��� ö���ڰ� �Ļ縦 ���ƽ��ϴ�.");
	}
	
	public static void main(String[] args) {
		
		try {
			DiningPhilosopher d = new DiningPhilosopher();
			d.DoDining();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
