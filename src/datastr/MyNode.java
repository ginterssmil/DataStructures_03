package datastr;

public class MyNode<Ttype> {

	private Ttype element;
	private MyNode<Ttype> parentNode = null;
	private MyNode<Ttype> leftChNode = null;
	private MyNode<Ttype> rightChNode = null;
	

	public Ttype getElement() {
		return element;
	}
	public MyNode<Ttype> getParentNode() {
		return parentNode;
	}
	public MyNode<Ttype> getLeftChNode() {
		return leftChNode;
	}
	public MyNode<Ttype> getRightChNode() {
		return rightChNode;
	}
	

	public void setElement(Ttype inputElement) {
		if(inputElement!=null)
		{
			element = inputElement;
		}
		else
		{
			element = (Ttype)new Object();
		}
	}
	public void setParentNode(MyNode<Ttype> parentNode) {
		this.parentNode = parentNode;
	}
	public void setLeftChNode(MyNode<Ttype> leftChNode) {
		this.leftChNode = leftChNode;
	}
	public void setRightChNode(MyNode<Ttype> rightChNode) {
		this.rightChNode = rightChNode;
	}
	
	

	public MyNode(Ttype inputElement){
		setElement(inputElement);
	}
	

	public String toString() {
		return "" + element;
	}
}
