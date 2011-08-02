package medianInStream;
/*
 * @author Inder Singh
 * 
 */
public class Node<T>{

	T data;
	Node<T> left = null;
	Node<T> right = null;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	
	
}
