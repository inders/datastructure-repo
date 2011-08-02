package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
	// This is a MAP based implmentation of TRIE which doesn't support COMPACTION
	// supports operations like add() and find()
	
	private  Node root = new Node(true);

	class StringOps implements java.util.Iterator<String> {

		String inputStr;
		int currentCount = 0;

		public StringOps(String inputStr) {
			this.inputStr = inputStr;
		}

		public boolean hasNext() {
			if (currentCount < inputStr.length())
				return false;
			else 
				return true;
		}

		public String next() {
			if (inputStr.length() - currentCount > 1)
			return inputStr.substring(currentCount, ++currentCount);
			else 
				return null;
		}

		public void remove() {
			// do nothing;

		}

		public String drainString() {
			return inputStr.substring(currentCount, inputStr.length());
		}


	}




	class Node {
		String data;
		Boolean isDummyNode;
		Boolean isLeaf;
		List<String> endofMarkerList = new ArrayList<String>();
		Map <String, Node> next = new HashMap<String, Node>();

		public Boolean isDummyNode() {
			return isDummyNode;
		}

		public Node(Boolean isDummyNode) {
			this.isDummyNode = isDummyNode;
		}

		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public Boolean getIsLeaf() {
			return isLeaf;
		}
		public void setIsLeaf(Boolean isLeaf) {
			this.isLeaf = isLeaf;
		}
		public List<String> getEndofMarkerList() {
			return endofMarkerList;
		}
		public void setEndofMarkerList(List<String> endofMarkerList) {
			this.endofMarkerList = endofMarkerList;
		}
		public  Node getNext(String key) {
			return next.get(key);
		}
		public void setNext(String key, Node nextNode) {
			next.put(key, nextNode);
		}

	}


	private void addDataToNode(Node node, String data) {
		node.setData(data);
	}

	private void addNodeToParent(Node parentNode, String key, Node childNode) {
		parentNode.setNext(key, childNode);
	}

	private Boolean find(String string) {
	StringOps stringOpsOnInput = new StringOps(string);
	String charToBeProcessed = stringOpsOnInput.next();
	Node intermediate_node = root;
	while (charToBeProcessed != null) {
		intermediate_node = intermediate_node.getNext(charToBeProcessed);
		if(intermediate_node.getData().equalsIgnoreCase(charToBeProcessed)) {
		charToBeProcessed = 	stringOpsOnInput.next();
		}
		else 
			return false;
			
	}
		return intermediate_node.getIsLeaf() == null ? false:true;
	}
	
	private void insert(String string) {
		StringOps inputStringOps = new StringOps(string);
		String charToBeProcessed = inputStringOps.next();
		Node intermediate_node;
		Node prevNode = root;
		//find the node to break 
		// 1. it can be a tail node when all characters match or there aren't any in the map
		// 2. it can break in between and then insertion should happen
		// so till you match a node and node is not null keep moving down
		for ( intermediate_node  = root.getNext(charToBeProcessed); 
		intermediate_node != null && intermediate_node.getData().equalsIgnoreCase(charToBeProcessed) ; 
		) {
			charToBeProcessed  = inputStringOps.next();
			prevNode = intermediate_node; 
			if (charToBeProcessed != null)
				intermediate_node = intermediate_node.getNext(charToBeProcessed);
			else
				intermediate_node = null;
		}
		// insert remaining left over characters from input stream and return
		// 
		if (charToBeProcessed != null) {
			while (charToBeProcessed != null) {
				Boolean dummy = false;
				Node node = new Node(dummy);
				addDataToNode(node, charToBeProcessed);
				addNodeToParent(prevNode, charToBeProcessed, node);
				charToBeProcessed = inputStringOps.next();
				prevNode = node;
			}
			prevNode.setIsLeaf(true);
		}
		else {
			prevNode.setIsLeaf(true);
		}
	}



	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("abcd");
		t.insert("abfg");
		t.insert("ab");
		System.out.println("trying to find string abcd in trie =" + t.find("abcd"));
		System.out.println("trying to find string abc in trie =" + t.find("abc"));
		System.out.println("trying to find string abfg in trie =" + t.find("abfg"));
		System.out.println("trying to find string ab in trie =" + t.find("ab"));
		System.out.println("trying to find string a in trie =" + t.find("a"));
		
		
		//construct a trie
	}
}
