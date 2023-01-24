package SortingDemoPackage;


/**
 * Shows the growth rate function for a set of integers
 * @author mhrybyk
 *
 */
public class GrowthRateFunctionsDemo {

	public static void main(String[] args) {
		
		int MAXNUMBERS = 31;
		long nOperations = 0;
		
		// show growth function for each number in the series
		
		for(int i = 1; i < MAXNUMBERS; i++) {
			nOperations = 1 << i;   // left shift raises to a power of 2
			System.out.printf("N %24d, ", nOperations); 
			
//			System.out.printf("2^N %24d, ", nOperations << 1);
			
			System.out.printf("N^1.5 %24d, ", (long) Math.pow(nOperations, 1.5));
			
			System.out.printf("N^2 %24d, ", (long) Math.pow(nOperations, 2));

//			System.out.print("\tlogN ");
//			System.out.print(log2(nOperations));
//			
//			System.out.print("\tloglogN ");
//			System.out.print(log2(log2(nOperations)));
//			
//			System.out.print("\t(logN)^2 ");
//			System.out.print((int) Math.pow(log2(nOperations),2));
			
			System.out.print("\tNlogN ");
			System.out.printf("%24d", nOperations * log2(nOperations));	
			
			System.out.println();
		}

	}
	
	/**
	 * Returns base 2 logarithm of an integer
	 * @param nOperations
	 * @return base 2 logarithm
	 */
	private static long log2(long nOperations) {
		return (long) (Math.log10(nOperations)/Math.log10(2));
	}

}
