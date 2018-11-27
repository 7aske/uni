package Example0;

import java.util.Arrays;

public class Example0 {
	public static void main(String[] args) {
		int[][] arr = {{2, 4}, {1, 7}, {4, 2}, {1, 7}, {1, 4}};

		for (int i = 0; i < arr.length ; i++) {
			System.out.printf("%s", Arrays.toString(arr[i]));

		}
	}

	public static int[][] sort(int[][] m) {

		for (int i = 0; i < m.length; i++) {
			int[] temp = m[i];
			for(int j= 1; j < m.length; j++){
				if(m[j-1][0] > temp[0]){
					temp = m[j-1];
					m[j-1] = m[j];
					m[j] = temp;
				} else if(m[j-1][0] > temp[j] && m[j-1][1] > temp[1]){
					temp = m[j-1];
					m[j-1] = m[j];
					m[j] = temp;
				}
			}
		}
		return m;
	}
}
