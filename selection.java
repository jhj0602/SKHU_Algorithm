import java.util.Scanner;

public class selection {

	public static void main(String[] args) {
		System.out.println("hw5_1 : 진형준");
		Scanner sc = new Scanner(System.in);
		System.out.println("정수 갯수 입력 :");
		int n = sc.nextInt();
		int mid = 0;//중간값 정하기
		if (n % 2 == 0) {
			mid = n / 2 ;
		} else if (n % 2 != 0) {
			mid = n / 2+1;
		}
		
		int[] arr = new int[n];
		System.out.println(n + "개의 정수 입력 :");
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println("중앙값 = "+select(arr, 0, arr.length - 1, mid));//재귀호출

	}

	public static int select(int[] arr, int p, int r, int mid) {//평균 선형시간 선택 알고리즘
		int q, k;

		if (p == r) {
			return arr[p];
		}
		q = partition(arr, p, r);//파티션 호출
		k = q - p + 1;
		if (mid < k) {//왼족 그룹 확인
			return select(arr, p, q - 1, mid);
		} else if (mid == k) {//기준원소가 찾는원소 
			return arr[q];
		} else {
			return select(arr, q + 1, r, mid - k);//오른쪽 그룹
		}

	}

	public static int partition(int[] arr, int p, int r) {
		int pivot = arr[r];
		int i = p;
		for (int j = p; j < r; j++) {
			if (arr[j] <= pivot) {
				int t = arr[j];
				arr[j] = arr[i];
				arr[i] = t;
				i++;
			}
		}
		int t = arr[i];
		arr[i] = arr[r];
		arr[r] = t;

		return i;
	}

}
