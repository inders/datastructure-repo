/**
 * 
 */
package medianInStream;

/**
 * @author inder
 *
 */
public class TreeNode<T> extends Node<T>{
long numberOfLeftNodes;
long numberofRightNodes;

public long getNumberOfLeftNodes() {
	return numberOfLeftNodes;
}
public void setNumberOfLeftNodes(long numberOfLeftNodes) {
	this.numberOfLeftNodes = numberOfLeftNodes;
}
public long getNumberofRightNodes() {
	return numberofRightNodes;
}
public void setNumberofRightNodes(long numberofRightNodes) {
	this.numberofRightNodes = numberofRightNodes;
}

}
