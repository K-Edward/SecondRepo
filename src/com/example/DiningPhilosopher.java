package com.example;


import java.util.concurrent.Semaphore;

public class DiningPhilosopher {

	Semaphore[] forks = new Semaphore[5];                       //포크 숫자를 설정할 수 있다.
	Philosopher[] philosophers = new Philosopher[5];            //철학자의 수를 설정 할 수 있다.
	
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
			System.out.println("철학자 " + i + " 식사 시작.");
			
			philosophers[i].start();
		}
		
		
		Thread.sleep(10000);
		
		for (int i=0 ; i< philosophers.length ; i++) {
			philosophers[i].StopEating();
			philosophers[i].join();
		}
		
		for (int i=0 ; i< philosophers.length ; i++) {
			System.out.println("철학자" +philosophers[i].id + " 은(는) 식사를 했습니까? : " + philosophers[i].isEat);
		}
		
		System.out.println("모든 철학자가 식사를 마쳤습니다.");
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
