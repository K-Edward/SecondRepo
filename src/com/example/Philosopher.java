package com.example;

import java.util.Random;
import java.util.concurrent.Semaphore;



public class Philosopher extends Thread {
	
	public int id;
	private Semaphore leftFork;
	private Semaphore rightFork;
	private boolean stopEating;
	
	public boolean isEat;
	
	Random rand;

	public Philosopher(int id, Semaphore left, Semaphore right) {
		this.id = id;
		this.leftFork = left;
		this.rightFork = right;
		
		stopEating = false;
		rand = new Random();
		isEat = false;
	}
	
	private void Think() throws InterruptedException  {
		System.out.println("철학자" + id + " 은(는) 생각 중 입니다.");
		sleep(rand.nextInt(1000));
	}
	
	private void Eat() throws InterruptedException {
		leftFork.acquire();
		rightFork.acquire();
		
		System.out.println("철학자" + id + " 은(는) 식사 중 입니다.");
		sleep(rand.nextInt(1000));
		isEat = true;
		
		leftFork.release();
		rightFork.release();
	}
	
	public void StopEating() {
		stopEating = true;
	}
	
	
	public void run() {
		
		while(!stopEating) {
			try {
				this.Think();
				this.Eat();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
