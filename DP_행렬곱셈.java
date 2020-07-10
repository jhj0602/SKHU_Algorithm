
//***************************
// 파일명:Dynamic_Programming2
// 작성자: 진형준
// 작성일: 5/17
// 내용: 행렬곱셈 알고리즘
//2차원 행렬 A과 B를 입력받아, A에 B를 곱한 결과를 반환하는 알고리즘 문제
//***************************
import java.util.Scanner;

public class Dynamic_Programming2 {
	public static void main(String[] args) {
		System.out.println("hw8_2: 진형준.");
		int A[][] = { { 1, 2, 3 }, { 1, 2, 3 } };
		int B[][] = { { 2, 2, 2 }, { 4, 4, 4 }, { 6, 6, 6 } };
		int[][] result = new int[A.length][B[0].length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {

				for (int k = 0; k < A[0].length; k++) {
					result[i][j] += A[i][k] * B[k][j];

				}

			}

		}
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " "); // 열 출력
			}
			System.out.println(); // 행 출력
		}

	}
}