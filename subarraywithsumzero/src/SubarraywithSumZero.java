import java.util.HashMap;
import java.util.Map;


public class SubarraywithSumZero {

	public Index findLongestSubArraywithSumZero(long[] inputArray) {
	    // 1. Generate a cummulative array
		for (int i=1; i < inputArray.length; i++) {
			inputArray[i] += inputArray[ (i-1)];
		}
		//2. Read the array, store it in map
		// For a subraary with 0 there should + and - numbers
		// we need to figure the longest distance indexes which have same numbers
		// since our array is cummulative postives and negatives which sum to 0 will bring the
		// intermittent array back to same number where it started
		// eg intermittent array = -6, -8, -10, -6, -4, 1, 2, 3, 4, 1
		// now from -6 we again reach -6
		// and later from 1 back to 1 which implies these two arrays are summing to 0
		// we just need the longest one
		Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		Index maxIndex = null;
		for (int i=0; i < inputArray.length; i++) {
			Integer index = new Integer(i);
			Integer val = new Integer((int) inputArray[i]);
			if (indexMap.get(val) == null) {
				indexMap.put(val, index);
			}
			else {
				if (maxIndex == null) {
					maxIndex = new Index();
					//collosion found the subarray
					maxIndex.setStart(indexMap.get(val));
					maxIndex.setEnd(new Integer(i));
				}
				else  {
					Index index1 = new Index();
					index1.setStart(indexMap.get(val));
					index1.setEnd(new Integer(i));
					if (maxIndex.compareTo(index1) <= 0) {
						maxIndex = index1;
					}
				}
			}
		}
			return maxIndex;		
	}
	
	public static void main(String[] args) {
		long inputArray[] = {3,2,4,-6,-8,10,11};
		
		SubarraywithSumZero subarraywithSumZero = new SubarraywithSumZero();
		Index maxIndex = subarraywithSumZero.findLongestSubArraywithSumZero(inputArray);
		System.out.println("Longgest Subarray with sum starts at [" + maxIndex.getStart() + " ] and ends at [" + maxIndex.getEnd() + "]");
	}
}
