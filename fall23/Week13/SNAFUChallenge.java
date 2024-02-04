package fall23.Week13;

/**
 * Full Instructions and Explanation: https://adventofcode.com/2022/day/25
 * 
 * Find the SNAFU sum of the SNAFUInput.txt file.
 */
public class SNAFUChallenge {
	public static void main(String[] args) {
        String expectedSnafu = "2-02===-21---2002==0";

		long sum = 0;
		for (String lineOfSNAFU : SNAFU.iterableInput()) {
			System.out.println(lineOfSNAFU);
			sum += decode(lineOfSNAFU);
		}

		System.out.println("=== RESULT ===");
		System.out.println("result           : " + encode(sum));
        System.out.println("expected result  : " + expectedSnafu);
	}

	private static String encode(long num) {
        return ""; // TODO: Convert a long into SNAFU.
	}

	/**
	 * The easy part. Go right to left on the SNAFU string and add the number to the
	 * sum. Translates from SNAFU base-5 to base-10.
	 * 
	 * @param SNAFU The SNAFU base-5 number code to decode.
	 * @return The base-10 result
	 */
	private static long decode(String SNAFU) {
        return 0l; // TODO: Convert a String of SNAFU into a long.
	}
}
