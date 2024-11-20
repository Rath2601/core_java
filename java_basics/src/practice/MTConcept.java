package practice;

import static java.lang.Thread.*;

public class MTConcept implements Runnable {
//public class MTConcept extends Thread {
	
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println("Thread in parent");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MTConcept t = new MTConcept();
		MTConcept2 t1 = new MTConcept2();
		
        Thread thread = new Thread(t);
        Thread thread1 = new Thread(t1);
        thread1.start();
        thread.start();
        thread.interrupt();
        for(int i=0; i<5; i++) {
			System.out.println("Thread in main");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

       
	}
	
}
