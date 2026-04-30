package datastr;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * MAX kaudze
 */

public class MyLinkedHeap<Ttype> {
	// 1.mainigie
	private MyNode<Ttype> rootNode = null;
	private MyNode<Ttype> lastNode = null;
	private int howManyElements = 0;
	private int level = 0;

	// 2.getter, priekš blokiem nav nepieciešams
	public int getHowManyElements() {
		return howManyElements;
	}
	// 3.setter - nav nepieciesams nevienam mainigajam

	// 4.konstruktors/i - bezargumenta konstruktors būs no Object klases

	public boolean isEmpty() {
		return (howManyElements == 0);
	}

	public boolean isFull() {
		try {
			new MyNode<Character>('A');
			return false;
		} catch (OutOfMemoryError e) {
			return true;
		}
	}

	public void enqueue(Ttype element) throws Exception {
		if (isFull()) {
			throw new Exception("Kaudze ir pilna un nav iespējams pievienot elementu");
		}

		if (element == null) {
			throw new Exception("Elements nevar būt null");
		}

		if (isEmpty()) {// ja tiek pievienots pirmais elements
			MyNode<Ttype> newNode = new MyNode<Ttype>(element);
			rootNode = newNode;
			lastNode = newNode;
			howManyElements++;
		} else// ja tiek pievienots kārtējais ( ne pirmais) elements
		{
			MyNode<Ttype> newNode = new MyNode<Ttype>(element);
			// ja būs saknes elementam kreisais bērns
			if (howManyElements == 1) {
				rootNode.setLeftChNode(newNode);
				newNode.setParentNode(rootNode);
				lastNode = newNode;
				howManyElements++;
				level++;
				reheapUp(newNode);
				return;
			}

			// kad pedjeam blokam nav blakus labais bloks
			if (lastNode.getParentNode() != null && lastNode.getParentNode().getRightChNode() == null) {

				MyNode<Ttype> parentNodeTemp = lastNode.getParentNode();
				parentNodeTemp.setRightChNode(newNode);
				newNode.setParentNode(parentNodeTemp);

				lastNode = newNode;
				howManyElements++;
				reheapUp(newNode);
				return;

			}
			// 2^0 = 1 elements 0.līmenī
			// 2^1 = 2 elementi 1.līmenī
			// 2^2 = 4 elementi 2.līmenī
			int sum = 0;
			// es noskaidroju, cik ir jābūt blokiem līdz šim līmenim ieskaitot
			for (int i = 0; i <= level; i++) {
				sum = (int) (sum + Math.pow(2, i));
			}

			// lastNode ir kā pēdejais bloks sava līmenī
			if (sum == howManyElements) {
				MyNode<Ttype> currentNode = rootNode;

				// ja blokam ir kreisais berns, tad jelec uz to
				while (currentNode.getLeftChNode() != null) {
					currentNode = currentNode.getLeftChNode();
				}

				lastNode = currentNode;

				lastNode.setLeftChNode(newNode);
				newNode.setParentNode(lastNode);

				lastNode = newNode;
				howManyElements++;
				level++;
				reheapUp(newNode);
				return;

			} else {
				// pēdējam blokam ir abi bērni
				if (lastNode.getParentNode().getLeftChNode() != null
						&& lastNode.getParentNode().getRightChNode() != null) {

					MyNode currentParent = findInsertionNode();
					currentParent.setLeftChNode(newNode);
					newNode.setParentNode(currentParent);
					lastNode = newNode;
					reheapUp(newNode);
					howManyElements++;
					return;
				}

				// pēdējam blokam nav neviens no bērniem
				if (lastNode.getLeftChNode() == null && lastNode.getRightChNode() == null) {
					lastNode.setLeftChNode(newNode);
					newNode.setParentNode(lastNode);
					lastNode = newNode;
					howManyElements++;
					reheapUp(newNode);
					return;
				}

			}

			// TODO izveidot pedējo scenāriju, kurs no labā bērna spej pārlekt
			// uz blakus apkaškoka kreiso bērnu - paņemt piemēru no apraksta

		}

	}

	private MyNode findInsertionNode() {
		Queue<MyNode> queue = new LinkedList<>();
		queue.add(rootNode);
		while (!queue.isEmpty()) {
			MyNode currentNode = queue.poll();
			if (currentNode.getRightChNode() == null) {
				return currentNode;
			} else {
				queue.add(currentNode.getRightChNode());
			}
			if (currentNode.getLeftChNode() == null) {
				return currentNode;
			} else {
				queue.add(currentNode.getLeftChNode());
			}

		}
		return null;
	}

	// MAX kaudzes gadījums
	private void reheapUp(MyNode<Ttype> nodeTemp) {
		// vai blokam ir vecāks
		if (nodeTemp.getParentNode() != null) {
			MyNode<Ttype> parentTempNode = nodeTemp.getParentNode();
			// salidzinam, vai bloka vertiba gadījuma nav lielāka par vecak vērtību
			if (((Comparable) nodeTemp.getElement()).compareTo(parentTempNode.getElement()) > 0) {
				// mainam vietā vērtības
				swap(nodeTemp, parentTempNode);
				reheapUp(parentTempNode);// izsaucam so pašu funkciju rekursīvi, bet jau uz vacaka bloku
			}
		}
	}

	private void swap(MyNode<Ttype> node1, MyNode<Ttype> node2) {
		Ttype temp = node1.getElement();
		node1.setElement(node2.getElement());
		node2.setElement(temp);

	}

	public void print() throws Exception {
		if (isEmpty()) {
			throw new Exception("Kaudze ir tukša un to nevar izprintēt");
		}

		printHelper(rootNode);
	}

	private void printHelper(MyNode<Ttype> nodeTemp) {
		if (nodeTemp != null) {
			System.out.println("P: " + nodeTemp.getElement());
			// noskaidrojam, vai eksiste kreisais bērns
			if (nodeTemp.getLeftChNode() != null) {
				System.out.println(
						"P: " + nodeTemp.getElement() + " Left child: " + nodeTemp.getLeftChNode().getElement());
				// izpildi so pasu funkciju uz kreiso bērnu
				printHelper(nodeTemp.getLeftChNode());
			}
			// noskaidrojam, vai eksistē labais bērns
			if (nodeTemp.getRightChNode() != null) {
				System.out.println(
						"P: " + nodeTemp.getElement() + " Right child: " + nodeTemp.getRightChNode().getElement());
				printHelper(nodeTemp.getRightChNode());
			}
		}
	}


	public Ttype dequeue() throws Exception
	{
		if(isEmpty()) {
			throw new Exception("Kaudze ir tukša un nevar atgriezt max elementu");
		}

		Ttype maxElement =  rootNode.getElement();

		rootNode.setElement(lastNode.getElement());

		//tagdejais pedjeais mezgls ir sava vecaka kreisais bērns
		if(lastNode.getParentNode().getLeftChNode() == lastNode) {
			lastNode.getParentNode().setLeftChNode(null);
		}

		if(lastNode.getParentNode().getRightChNode() == lastNode) {
			lastNode.getParentNode().setRightChNode(null);
		}

		// lastNode samainīt (level samazinat, kur tas ir nepieciešams)
		howManyElements--;
		reheapDown(rootNode);


		return maxElement;
	}


	private void reheapDown(MyNode<Ttype> nodeTemp) {
		if(nodeTemp != null) {
			//ja ir tikai viens bērns un tas ir kreisais
			if(nodeTemp.getLeftChNode() != null 
					&& nodeTemp.getRightChNode() == null) {
				if( ((Comparable)nodeTemp.getElement())
						.compareTo(nodeTemp.getLeftChNode().getElement()) < 0 ) {
					swap(nodeTemp, nodeTemp.getLeftChNode());
				}
			}

			//ja ir abi bērni
			else if (nodeTemp.getLeftChNode() != null 
					&& nodeTemp.getRightChNode() != null) {

				//pārbaudam, vai kreisais berns ir lielāks par labo
				if(((Comparable)nodeTemp.getLeftChNode().getElement())
						.compareTo(nodeTemp.getRightChNode().getElement()) > 0) {
					//vai kreisais bērns ir lielaķs par pasu bloka vērtību
					if( ((Comparable)nodeTemp.getLeftChNode().getElement())
							.compareTo(nodeTemp.getElement()) > 0) {
						swap(nodeTemp, nodeTemp.getLeftChNode());
						reheapDown(nodeTemp.getLeftChNode());
					}

				}
				//ja kreisais bērns ir mazaks vai vienāds ar labo bērnu
				else
				{
					//vai labais bērns ir lielaķs par pasu bloka vērtību
					if( ((Comparable)nodeTemp.getRightChNode().getElement())
							.compareTo(nodeTemp.getElement()) > 0) {
						swap(nodeTemp, nodeTemp.getRightChNode());
						reheapDown(nodeTemp.getRightChNode());
					}
				}



			}

		}
	}
}
