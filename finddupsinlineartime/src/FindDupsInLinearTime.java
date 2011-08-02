
//You are given an array of elements. Some/all of them are duplicates. 
//Find them in 0(n) time and 0(1) space. Property of inputs - 
//Number are in the range of 1..n where n is the limit of the array. 
public class FindDupsInLinearTime {

	/* Algorithm in English
	 * 1. Read input from startPos
	 * 2. If input is within the maxRange or it's not in correct place then it's a candidate to be replaced else incrementStartPos
	 * 3. finalPosition for input is input[i]
	 * 4. if finalPosition < maxRange then swap input with finalPosition and increment finalPosition with MaxRange
	 * 5. else no need to swap as previous instances of this element are recorded at finalPos so just increment finalPosition with MaxRange to record another instance
	 * 6. if input at startPos is in correct place or reached zero then increment startPos++ till you go through end of Array
	 * 7. Iterate through the array and divide each element with maxRange. The divisor indicates how many times a element was repeated and 0 indicates a missing element
	 */
	public static void main(String[] args) {
		int[] inputArr = {0, 4, 3, 4, 1};
		findDuplicates(inputArr, 4);
		printDuplicatesAndMissingElements(inputArr);
		int[] inputArr1 = {0, 2, 3, 4, 3, 2};
		findDuplicates(inputArr1, 5);
		printDuplicatesAndMissingElements(inputArr1);
	}

	private static void findDuplicates(int[] inputArr, int maxRange) {
		// for simplicity reasons we will assume nothing is there in 0 index of array 
		// and start from 1
		int i = 1;
		int maxArrLength = inputArr.length;
		
		while(true) {
			if ( i > maxArrLength - 1) 
				break;
			
			int finalPos = inputArr[i];
			System.out.println(" Read :: " + inputArr[i]);
			
			if (finalPos > maxRange) {
				//this element is already in final position skip and move further looking for elements within maxRange
				i++;
				continue;
			}
			int finalPosElement = inputArr[inputArr[i]];
			int startPos = i;
			int startPosElement = inputArr[i];
			
			// if startPos element isn't in place and it's within the maxRange..
			// then candidate for swap or reaching final pos...
			if (inputArr[startPos] != i || 
					inputArr[startPos] <= maxRange) {

				if (finalPosElement > maxRange) {
					// if finalposElement > maxRange then no swapping required
					// just add maxRange to denote multiple entry
					inputArr[finalPos] += maxRange;	
					//make startPosElement as 0
					inputArr[startPos] = 0;
					System.out.println("No swapping required incemented " + inputArr[finalPos] );
				}
				else {
					//swap
					int temp = startPosElement;
					inputArr[startPos] = finalPosElement;
					inputArr[finalPos] = temp + maxRange;
					System.out.println("Swapped : " + startPosElement + " with " +finalPosElement);
				}
			}
			printArray(inputArr);
			// if the element in current evaluated position isn't still correct repeat the above again
			if (inputArr[startPos] == i ||
					 
					inputArr[startPos] > maxRange) {
				System.out.println(" Read :: "+ inputArr[startPos] + " it's already in place");
				inputArr[startPos] += maxRange;
				i++;
			}
			else if (inputArr[startPos] == 0){
				i++;
				continue;
			}
			else {
				continue;
			}
				
		}
		
	}
	
	private static void printArray(int[] inputArr) {
		for (int i=1; i < inputArr.length; i++) {
			System.out.print(inputArr[i] +",");
		}
		System.out.println("");
	}
	private static void printDuplicatesAndMissingElements(int[] inputArr) {
		int maxRange = inputArr.length;
		for (int i=1; i < inputArr.length; i++) {
			if (inputArr[i] == 0) {
				System.out.println(" Element [" + i + "] is missing");
			}
			int input = inputArr[i];
			int mod = input / maxRange;
			if ( mod > 1) {
				System.out.println (" Element [" + i + "] is repeated + [" + mod + "] times");
				
			}
		}
	}

	
}
