package service;

import datastr.MyLinkedHeap;

public class MainService {

	public static void main(String[] args) {
		MyLinkedHeap<Integer> integerInHeap = new MyLinkedHeap<Integer>();
		try {
		integerInHeap.enqueue(40);
		integerInHeap.enqueue(50);
		integerInHeap.enqueue(35);
		integerInHeap.enqueue(90);
		integerInHeap.enqueue(55);
		integerInHeap.print();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
	}

}
