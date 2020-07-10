import java.util.Scanner;

//***************************
//파일명: Dynamic_Programming
//작성자: 진형준
//작성일: 5/16
//내용: 행렬 최대점수 경로 구하기, 최대점수 구하기
//***************************
public class Dynamic_Programming {

	static int n; // N
	static int[][] List;// 행렬 원소를 저장할 배열
	static int[][] Dynamic_Arr;// 최대점수를 구할 동적프로그래밍 배열
	static int[][] Temp;// 최대 점수 경로를 구할 temp 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("hw8_1: 진형준 선택과제 수행 했습니다.");
		System.out.println("행의 크기 입력 :");
		n = sc.nextInt();
		List = new int[n + 1][n + 1];
		Dynamic_Arr = new int[n + 1][n + 1];
		Temp = new int[n + 1][n + 1];
		System.out.println(n + " * " + n + " 크기의 행렬 원소 입력 :");
		for (int i = 1; i < n + 1; i++) {// (0,0)은 제외
			for (int j = 1; j < n + 1; j++) {
				List[i][j] = sc.nextInt();
				Dynamic_Arr[i][j] = List[i][j];

			}
		}

		int DP_ouput_result = Dynamic();// 함수 호출
		System.out.println("최대 점수 = " + DP_ouput_result);
		int i = n;// 초기값
		int j = n;
		int dp_expression = n * 2 - 1;// 최대경로 갯수를 구하는 동적프로그래밍 식
		String[] line = new String[dp_expression];// 경로를 저장할 배열

		line[0] = "(" + i + " ," + j + ")";
		int k = 1;
		while (Temp[i][j] != 0) {
			line[k] = "(" + Temp[i][j] / (n + 1) + " ," + Temp[i][j] % (n + 1) + ") ";
			k++;
			int temp = Temp[i][j];
			i = Temp[i][j] / (n + 1);// 행의 관한 인덱스 처리 증감 식
			j = temp % (n + 1);// 열의 관한 인덱스 처리 증감식
		}
		System.out.print("최대 점수 경로= ");
		for (int p = line.length - 1; p >= 0; p--) {
			System.out.print(line[p]);
		}

	}

	static int Dynamic() {

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (i == 1 && j == 1) {
					continue;
				} else if (i == 1) {
					Dynamic_Arr[i][j] = Dynamic_Arr[i][j - 1] + List[i][j];
					Temp[i][j] = i * (n + 1) + j - 1;// 경로 를 구할 인덱스를 저장

				} else if (j == 1) {
					Dynamic_Arr[i][j] = Dynamic_Arr[i - 1][j] + List[i][j];
					Temp[i][j] = (i - 1) * (n + 1) + j;

				} else {
					// 행렬 원소를 기준으로 오른쪽값과 아래쪽 값 중 큰 값을 비교해서 최대점수 에 저장
					if (Dynamic_Arr[i - 1][j] > Dynamic_Arr[i][j - 1]) {
						Dynamic_Arr[i][j] = Dynamic_Arr[i - 1][j] + List[i][j];
						Temp[i][j] = (i - 1) * (n + 1) + j;
					} else {
						Dynamic_Arr[i][j] = Dynamic_Arr[i][j - 1] + List[i][j];
						Temp[i][j] = i * (n + 1) + j - 1;
					}

				}

			}

		}
		return Dynamic_Arr[n][n];
	}

}