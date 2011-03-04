import java.util.Iterator;




public class DoublyLInkedList<T> {

	
	Node head = null;
	Node tail = null;
	

	
	
	public  Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}
	public Node getTail() {
		return tail;
	}
	public void setTail(Node tail) {
		this.tail = tail;
	}
	
	private Node createNode(T data) {
		Node node = new Node();
		node.setData(data);
		node.setNext(null);
		node.setPrevious(null);
		return node;
		
	}
	
	public void insertAtHead(Node node) {
		if (getHead() == null) {
			System.out.println("Creating first Head " + node.getData());
			setHead(node);
			setTail(node);
		}
		else {
		   node.setPrevious(null);
		   node.setNext(getHead());
		   getHead().setPrevious(node);
		   setHead(node);
		   System.out.println("Inserting at head " + node.getData());
		}
		
	}
	
	public Node insertAtHead(T data) {
		if (data == null)
			return null;
		//if head is null first node
		Node node = createNode(data);
		insertAtHead(node);
		return node;
	}
	
	private Node findNode(T data) {
		Node tmp = head;
		while(tmp != null) {
			//System.out.println("findNode :: Comparing " + tmp.getData() + " with " + data);
			if (tmp.getData().equals(data))
				return tmp;
			tmp = tmp.getNext();
		}
		return null;
	}
	
	public void remove(T data) {
		Node node = findNode(data);
		remove(node);
	}
	
	public void remove(Node node) {
	if (node == null)
		return;
	//1 node in list
	if (node.getPrevious() == null &&
			node.getNext() == null) {
		setHead(null);
		setTail(null);
		return;
	}
	//head node
	if (node.getPrevious() == null &&
			node.getNext() != null) {
		//head node
		setHead(node.getNext());
		node.setNext(null);
		return;
	}
	// tail node
	if (node.getNext() == null &&
			node.getPrevious() != null) {
		//tail node
		setTail(node.getPrevious());
		node.getPrevious().setNext(null);
		return;
	}
	// center node
	System.out.println("Removing center node from list with value [ " + node.getData() + "]  whose previous = [" + node.getPrevious().getData() + "] and next = [" + node.getNext().getData() + "]");
	node.getPrevious().setNext(node.getNext());
	node.getNext().setPrevious(node.getPrevious());
	node.setNext(null); node.setPrevious(null);
	}
	
	
	private class ListIterator implements java.util.Iterator<T>  {
		Node currentNode;
		
		public ListIterator() {
			currentNode = getHead();
				
		}
		
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (currentNode != null)
			{
			//	System.out.println("Current node is not null and is pointing to " + currentNode.getData());
				return true;
			}
				else 
				return false;
		}

		public T next() {
			// TODO Auto-generated method stub
			//System.out.println("Current node  = " + currentNode.getData() );
			T data = (T) currentNode.getData();
			currentNode = currentNode.getNext();
			return data;
		}

		public void remove() {
			// TODO Auto-generated method stub
			DoublyLInkedList.this.remove(currentNode);

		}
	}
	
	public Iterator<T> iterator() {
		return new ListIterator();
		
	}
	
	public static void main(String[] args) {
		System.out.println("Test");
		DoublyLInkedList<String> dList = new DoublyLInkedList<String>();
		String str1 = new String("1");
		String str2 = new String("2");
		String str3 = new String("3");
		dList.insertAtHead(str1);
		dList.insertAtHead(str2);
		dList.insertAtHead(str3);
		Iterator<String> it = dList.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			
		}
		System.out.println("Tail Node = " + dList.getTail().getData());
		dList.remove(str1);
		Iterator<String> it1 = dList.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next());
			
		}
		System.out.println("Tail Node = " + dList.getTail().getData());

	}
	
}
