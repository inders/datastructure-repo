package trie;

import java.util.HashMap;
import java.util.Map;

public class StringOps {

	/* To find first non repeating character in a string
	 * eg: if string is 'total' then 'o' is the answer
	 * eg: if string is 'tweetwer' then 'r' is the answer
	 * time complexity :
	 * space complexity; 
	 */
	public String findFirstNonRepeatingChar(String s) {
		if (s == null)
			return null;
		// Parse the String and put it in a array of 256 bit characters if we
		// assume it's a ASCII string set, however for unicode the string 
		// could have more characters, hence creating a large array would be waste
		// so we can use a Map which only consume space as the kind of characters 
		// are present
		Map<String, Integer> inputMap = new HashMap<String, Integer>();
		for(int i=0; i < s.length(); i++) {
			String key = s.substring(i, i+1);
			if (inputMap.get(key) == null)
				inputMap.put(key, 1);
			else
				inputMap.put(key, inputMap.get(key) + 1);
		}
		//Now start from the start of the string and find the character which has count 1
		// first in order from left to right
		for(int i=0; i < s.length(); i++) {
			String key = s.substring(i, i+1);
			if(inputMap.get(key) == 1) 
				return key;
			}
		//non the character are non repeating
		return null;
	}
	
	public boolean testFindFirstNonRepeatingChar(String str) {
		String repeatingCharacter = findFirstNonRepeatingChar(str);
		if ( repeatingCharacter == null) {
			System.out.println("No repeating character in [" + str + "]");
			return false;
		}
		else {
			System.out.println("First repeating character in [" + str + "] = [" + repeatingCharacter + "]");
			return true;
		}
	}
			
		public static void main(String[] args) {
			StringOps sOps = new StringOps();
			sOps.testFindFirstNonRepeatingChar("total");
			sOps.testFindFirstNonRepeatingChar("tweetwer");
			sOps.testFindFirstNonRepeatingChar("inder");
		}
	
}
