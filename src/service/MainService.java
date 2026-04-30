package service;

import datastr.MyLinkedHeap;
import model.Patient;

public class MainService {

	public static void main(String[] args) {
		MyLinkedHeap<Integer> integersInHeap = new MyLinkedHeap<Integer>();
		MyLinkedHeap<Patient> patients = new MyLinkedHeap<Patient>();
		try
		{
			integersInHeap.enqueue(40);//P: 40
			integersInHeap.enqueue(50);//P: 50(LC: 40)
			integersInHeap.enqueue(35);//P: 50(LC: 40, RC:35)
			integersInHeap.enqueue(99);//P:99 (LC:50, RC:35), P:50 (LC:40)
			integersInHeap.enqueue(55);//P:99 (LC:55, RC:35), P:55 (LC:40, RC:50)
			integersInHeap.enqueue(2);//P:99 (LC:55, RC:35), P:55 (LC:40, RC:50); ):35 (LC:2)
			integersInHeap.print();
			
			System.out.println("Max vertiba: " + integersInHeap.dequeue());
			
			System.out.println("=====Tests ar pacientiem=====");
			patients.enqueue(new Patient("Janis", "Berzins", 1));
			patients.enqueue(new Patient("Liga", "Jauka", 3));
			patients.enqueue(new Patient("Baiba", "Kalnina", 2));
			patients.enqueue(new Patient("Juris", "Nagis", 5));
			patients.print();
			System.out.println("Max pacients: " + patients.dequeue());
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}