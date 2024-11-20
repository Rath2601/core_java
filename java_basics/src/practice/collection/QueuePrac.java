package practice.collection;

import java.util.PriorityQueue;
import java.util.Queue;

public class QueuePrac {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		pq.add(3);
		pq.add(7);
		pq.add(1);
		pq.offer(6);
		pq.offer(5);
		pq.offer(1);
		pq.poll();
		pq.poll();
		
		PriorityQueue<Integer> pq1 = new PriorityQueue<>();

		pq1.offer(3);
		pq1.offer(7);
		pq1.offer(1);
		pq1.add(6);
		pq1.add(5);
		
		pq1.poll();
		pq1.poll();

		System.out.println(pq);
		System.out.println(pq1);

		System.out.println(pq.peek());
		
		Queue<Integer> q = new PriorityQueue<Integer>();
	}
}
