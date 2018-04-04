package zhdd;

public class CalcMethod {
	static int[] num = { 1, 23, 4, 22, 55, 6, 8, 5, 37 };

	public static void main(String[] args) throws InterruptedException {
		
		int query[] = new int[]{0,1, 2,	4,	5,	6,	7};
		System.out.println(new CalcMethod().binary_search(query, 0));
	}

	public boolean binary_search(int[] seq, int query) {
		int start = 0;
		int end = seq.length;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int val = seq[mid];
			if (val == query)
				return true;
			else if (val < query) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return false;
	}
}
