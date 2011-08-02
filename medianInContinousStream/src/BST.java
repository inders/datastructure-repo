package medianInStream;

import java.io.ObjectInputStream.GetField;
import java.lang.Comparable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
 * @author Inder Singh
 * 
 */

public class BST<E>  {


	
	private TreeNode<E> root;
	private Comparator<? super E> c;

	private TreeNode<E> getNewNode() {
		TreeNode<E> treeNode = new TreeNode<E>();
		return treeNode;

	}

	public BST(Comparator <? super E> c) {
		this.c = c;
	}

	private TreeNode<E> getRoot() {
		return root;
	}

	private void setRoot(TreeNode<E> root) {
		this.root = root;
	}

	public void add(E e) {
		TreeNode<E> node = getNewNode();
		node.setData(e);
		TreeNode<E> currentNode = getRoot();
		TreeNode<E> parent = null;
		if (currentNode == null) {
			setRoot(node);
		}
		while(currentNode != null) {
			parent = currentNode;
			if (c.compare(e, currentNode.getData()) < 0) {
				currentNode.setNumberOfLeftNodes(currentNode.getNumberOfLeftNodes() + 1);
				currentNode = (TreeNode<E>) currentNode.getLeft();
				if (currentNode == null) {
					// insert new node here
					parent.setLeft(node);
				}
			}
			else if (c.compare(e, currentNode.getData()) >= 0) {
				currentNode.setNumberofRightNodes(currentNode.getNumberofRightNodes() + 1);
				currentNode = (TreeNode<E>) currentNode.getRight();
				if (currentNode == null) {
					//insert new node here
					parent.setRight(node);
				}
			}

		}

	}


	/*
	 * @returns Map<String, TreeNode<E>> which has the following values
	 * PARENT, reference to parent node
	 * CHILD, reference to child node
	 * LEFT_CHILD, reference to left child if node pointed by E was left child of parent
	 * RIGHT_CHILD, reference to right child if node pointed by E was right child of parent
	 */
	public Map<String, TreeNode<E>> findNodeAndParent(E data) {
		TreeNode<E> root = getRoot();
		TreeNode<E> currentNode = getRoot();
		TreeNode<E> parentNode = null;
		Map<String, TreeNode<E>> map = new HashMap<String, TreeNode<E>>();
		if (c.compare(root.getData(), data) == 0) {
			//parent and node are same
			map.put("PARENT", root);
			//map.put("CHILD", root);
			return map;
		}
		else {
			//node to be found is not root
			//traverse the tree to find the node

			while (currentNode != null) {
				if (c.compare(data, currentNode.getData()) < 0) {
					System.out.println("findNodeAndParent:: comparing  <" + currentNode.getData() + " and " + data);
					parentNode = currentNode;
					currentNode = (TreeNode<E>) currentNode.getLeft();
				}
				else if (c.compare(data, currentNode.getData()) > 0) {
					System.out.println("findNodeAndParent:: comparing > " + currentNode.getData() + " and " + data);
					parentNode = currentNode;
					currentNode = (TreeNode<E>) currentNode.getRight();
				}
				else if(c.compare(data, currentNode.getData()) == 0) {
					System.out.println("findNodeAndParent:: comparing " + currentNode.getData() + " and " + data + " found match");
					break;
				}
			}
			// if current is pointing to null then node is not found
			if (currentNode != null) {
				map.put("PARENT", parentNode);
				map.put("NODE", currentNode);

			}
		}
		return map;

	}

	/*
	 * find minimum node from a given node
	 * this will always be the left most subtree starting
	 * from node
	 * @return K node
	 * @param K node
	 */
	public Map<String, TreeNode<E>> getMinimumNode(TreeNode<E> node) {
		Map <String, TreeNode<E>> treeMap = new HashMap<String, TreeNode<E>>();
		if (node == null) {
			return null;
		}
		System.out.println("Getting minimum starting from node" + node.getData());
		
		TreeNode<E> parentNode = null;
		while ( node.getLeft() != null) {
			parentNode = node;
			node = (TreeNode<E>) node.getLeft();
		}
		if (parentNode != null) 
			treeMap.put("PARENT", parentNode);
		else 
			treeMap.put("PARENT", node);
		treeMap.put("NODE", node);
		System.out.println("Min Node = " + node.getData());
		System.out.println("Min Node Parent = " + parentNode.getData());
		return  treeMap;
	}

	/*
	 * find max node from a given node
	 * this will always be the right most subtree starting
	 * from node
	 * @return K node
	 * @param K node
	 */
	public Map<String, TreeNode<E>> getMaxNodeInLeftSubtree(TreeNode<E> node) {
		Map <String, TreeNode<E>> treeMap = new HashMap<String, TreeNode<E>>();
		if (node == null) {
			return null;
		}
		System.out.println("Getting max starting from node" + node.getData());
		
		
		TreeNode<E> parentNode = node;
		node = (TreeNode<E>) node.getLeft();
		while ( node.getRight() != null) {
			parentNode = node;
			node = (TreeNode<E>) node.getRight();
		}
		if (parentNode != null)
		treeMap.put("PARENT", parentNode);
		else
			treeMap.put("PARENT", node);
		treeMap.put("NODE", node);
		//System.out.println("Max Node = " + node.getData());
		//System.out.println("Max Node Parent = " + parentNode.getData());
		return  treeMap;
	}
	
	public boolean isRoot(TreeNode<E> node) {
		if (node == null)
			return false;
		if (node == getRoot())
			return true;
		return false;
	}
	
	public boolean isTailNode(TreeNode<E> node) {
		if (node == null) {
			return false;
		}
		if (node.getLeft() == null && node.getRight() == null) {
			return true;
		}
		return false;
	}
	
	public boolean isLeftChild(TreeNode<E> node, TreeNode<E> parent) {
		if (node == null || parent == null || parent.getLeft() == null) 
			return false;
		if (parent.getLeft().getData() == node.getData())
			return true;
		return false;
	}
	
	public boolean isRightChild(TreeNode<E> node, TreeNode<E> parent) {
		if (node == null || parent == null || parent.getRight() == null) 
			return false;
		if (parent.getRight().getData() == node.getData())
			return true;
		return false;
	}

	public boolean hasLeftChildOnly(TreeNode<E> node) {
		if (node == null)
			return false;
		if (node.getLeft() != null && node.getRight() == null)
			return true;
		return false;
	}
	
	public boolean hasRightChildOnly(TreeNode<E> node) {
	if (node == null)
		return false;
	if (node.getLeft() == null && node.getRight() != null)
		return true;
	return false;
	}
	
	public boolean hasBothChildren(TreeNode<E> node) {
		if (node == null)
			return false;
		if (node.getLeft() != null && node.getRight() != null)
			return true;
		return false;
	}

	public void printInroder(TreeNode<E> node) {
		if (node == null)
			return;
		printInroder((TreeNode<E>) node.getLeft());
		System.out.println(" Node = " + node.getData() + " Left subtree count =" + node.getNumberOfLeftNodes() + " Right subtree count = "+ node.getNumberofRightNodes());
		printInroder((TreeNode<E>) node.getRight());
	}
	
	@SuppressWarnings("unused")
	private void updateLeftAndRightCounters( TreeNode<E> node) {
		if (node == null)
			return;
		TreeNode<E> currentNode = getRoot();
		if (node == currentNode) {
			//root node is supposed to be deleted
			//no need to update counters
		}
		
		//assume node is always present in tree
		// else this function will udpate the counters incorrectly
		while (true) {
			if (c.compare(node.getData(), currentNode.getData()) < 0) {
				//node is present in left subtree of CurrentNode
				currentNode.setNumberOfLeftNodes(currentNode.getNumberOfLeftNodes() - 1);
				currentNode = (TreeNode<E>) currentNode.getLeft();
			}
			else if (c.compare(node.getData(), currentNode.getData()) > 0 ) {
				//node is present in right subtree of currentNode
				currentNode.setNumberofRightNodes(currentNode.getNumberofRightNodes() - 1);
				currentNode = (TreeNode<E>) currentNode.getRight();
			}
			else if (c.compare(node.getData(), currentNode.getData()) == 0) {
				break;
			}
		}
			
	}
	
	@SuppressWarnings("unused")
	private long getRank(TreeNode<E> node) {
		if (node == null)
			return 0;
		return node.getNumberOfLeftNodes() + 1;
	}
	
	/*
	 * Index of a node is it's position as in a inorder traversal of a binary search tree
	 * At each node you can calculate it's rank as sizeof(left subtree of current node) + 1
	 * Challenge:: This calculation becomes a problem once you move deep in a right subtree
	 * as it's rank should include rank of previous ancestors and it's left subtrees

	 * Easier approach is the following ->
	 *
	 * Rank of Node = (Number of nodes in left subtree) + 1
	 * Check k against rank of root
	 * if k == rank(root)
	 *   return
	 * if ( k < rank(root))
	 *   go in left subtree of root
	 * Till you are going in left subtrees you can calculate rank using above formula   
	 * if the rank you are looking for is > rank of current node then you move to right subtree
	 * and look for new rank = (Rank you are looking - rank of currentNode( as these many nodes you have already considered and are less than the rank you are looking)
	 * The above helps in not carrying  forward any information when you move to right subtree and you look for a node with a smaller index
	 * in the right subtree
	 *  
	 */
	public E getNodeAt(long kindex) {
		TreeNode<E> currentNode = getRoot();
		
		while (currentNode != null) {
			long currentRank = getRank(currentNode);
			if (currentRank == kindex)
				return currentNode.getData();

			if (kindex < currentRank) {
				currentNode = (TreeNode<E>) currentNode.getLeft();
			}
			else if ( kindex > currentRank) {
				currentNode = (TreeNode<E>) currentNode.getRight();
				kindex = kindex - currentRank;
			}
		}
		return null;
	}
	
	public void delete(E data) {
		/* Deletion has the following three cases
		 * 1. get node from data
		 * 2. if node has only a left child - left child replaces current node
		 * 3. if node has only right child - right child replaces current node
		 * 4. if node has both left and right childs
		 * 
		 */
		if (data == null)
			return ;
		//find the node to be deleted
		Map<String, TreeNode<E>> nodeMap = findNodeAndParent(data);
		TreeNode<E> parentOfNodeToBeDeleted = nodeMap.get("PARENT");
		TreeNode<E> nodeToBeDeleted =  nodeMap.get("NODE");
		if (hasLeftChildOnly(nodeToBeDeleted)) {
			//update the counters
			updateLeftAndRightCounters(nodeToBeDeleted);
			//only left child is present which can replace this node
			if (isLeftChild(nodeToBeDeleted, parentOfNodeToBeDeleted)) {
				parentOfNodeToBeDeleted.setLeft(nodeToBeDeleted.getLeft());
			}
			else if (isRightChild(nodeToBeDeleted, parentOfNodeToBeDeleted)) {
				parentOfNodeToBeDeleted.setRight(nodeToBeDeleted.getLeft());
			}
			nodeToBeDeleted.setData(null);
		}
		else if (hasRightChildOnly(nodeToBeDeleted)) {
			//update the counters
			updateLeftAndRightCounters(nodeToBeDeleted);
			//only  right child is present which can replace this node
			if (isLeftChild(nodeToBeDeleted, parentOfNodeToBeDeleted)) {
				parentOfNodeToBeDeleted.setLeft(nodeToBeDeleted.getRight());
			}
			else if (isRightChild(nodeToBeDeleted, parentOfNodeToBeDeleted)) {
				parentOfNodeToBeDeleted.setRight(nodeToBeDeleted.getRight());
			}
			nodeToBeDeleted.setData(null);
		}
		else {
			// both left and right childs are present
			//find the maximum from nodeToBeDeleted left subtree which can replace this node 
			// after deletion and maintain the BST property
			System.out.println("Node to Be deleted = " + nodeToBeDeleted.getData());
			Map<String, TreeNode<E>> delNodeMap = getMaxNodeInLeftSubtree((TreeNode<E>) nodeToBeDeleted); 
			TreeNode<E> replacementNode = delNodeMap.get("NODE");
			TreeNode<E> replacementNodeParent = delNodeMap.get("PARENT");
			System.out.println(" ReplacementNode = " + replacementNode.getData() + " and it's parent = " + replacementNodeParent.getData());
			if (isTailNode(replacementNode)) {
				//update counters
				updateLeftAndRightCounters(replacementNode);
				//replacement node is a tail node without any childs
				if (isLeftChild(replacementNode, replacementNodeParent)) {
					System.out.println(" replacing  " + nodeToBeDeleted.getData() + " with " + replacementNode.getData());
					replacementNodeParent.setLeft(null);
				}
				if (isRightChild(replacementNode, replacementNodeParent)) {
					System.out.println(" replacing  " + nodeToBeDeleted.getData() + " with " + replacementNode.getData());

					replacementNodeParent.setRight(null);
				}
				nodeToBeDeleted.setData(replacementNode.getData());
				replacementNode.setData(null);
			}
			else  {
				//update counters for left and right
				updateLeftAndRightCounters(replacementNode);
				//replacement Node is not a tail node as it has a left subtree
				nodeToBeDeleted.setData(replacementNode.getData());
				if(replacementNode.getLeft() != null)
					replacementNodeParent.setRight(replacementNode.getLeft());
				replacementNode.setData(null);
			}
		}

	}
	
	public void testCase1createBSTTree(final BST<Long> bst) {
		bst.add(new Long(100));
		bst.add(new Long(75));
		bst.add(new Long(120));
		bst.add(new Long(73));
		bst.add(new Long(85));
		bst.add(new Long(110));
		bst.add(new Long(130));
		bst.add(new Long(76));
		bst.add(new Long(87));
		bst.add(new Long(115));
		bst.add(new Long(125));
		bst.add(new Long(78));
		bst.add(new Long(86));
		bst.add(new Long(90));
		bst.add(new Long(116));
	}
	
	public static void main(String[] args) {
		
		
		class LongComparator implements Comparator<Long>  {

			public int compare(Long o1, Long o2) {
				int result = o1.compareTo(o2);
				if (result == 0)
					return 0;
				else if (result < 0)
					return -1;
				else
					return 1;

			}

		};
		
		LongComparator c = new LongComparator();
		final BST<Long> bst = new BST<Long>(c);
		bst.testCase1createBSTTree(bst);
		bst.printInroder(bst.getRoot());
		// delete non tail node with one left child only
		System.out.println("At present node at 5 Index = " + bst.getNodeAt(5));
		System.out.println("At present node at 9 Index = " + bst.getNodeAt(9));
		
		bst.delete(new Long(75));
		System.out.println("Tree after deletion of node 75 with one left child only");
		bst.printInroder(bst.getRoot());
		//delete node with both childs
		bst.delete(new Long(85));
		System.out.println("Tree after deletion of node 85 with both childs and tail node replacing the deleted node");
		bst.printInroder(bst.getRoot());
		System.out.println("At present node at 5 Index = " + bst.getNodeAt(5));
		
		bst.delete(new Long(120));
		System.out.println("Tree after deletion of node 120 with both childs and node with left subtree replacing it");
		bst.printInroder(bst.getRoot());
		
		
	}
}



