package practice;

import static java.lang.Thread.sleep;

public class MTConcept2 extends MTConcept implements Runnable {
	//public class MTConcept extends Thread {
	
		public void run() {
			for(int i=0; i<5; i++) {
				System.out.println("Thread in child");
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
