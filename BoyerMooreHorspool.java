import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//***************************
//파일명: BoyerMooreHorspool
//작성자: 진형준
//작성일: 2020-06-15
//프로그램 설명:문자열 매칭을 위한 보이어 무어 호스풀 알고리즘 
//점프 정보 저장을 위해 hashmap 자료구조 사용
//***************************
public class BoyerMooreHorspool {
	static int count = 0;

	public static void main(String[] args) {
		System.out.println("hw12_1: 진형준");
		Scanner sc = new Scanner(System.in);
		System.out.println("패턴 입력 : ");
		String pt = sc.nextLine();
		System.out.println("텍스트 입력 : ");
		String Line = sc.nextLine();
		char[] pattern = pt.toCharArray();
		char[] data = Line.toCharArray();
		Map<Character, Integer> jumptable = new HashMap<>();// 점프정보를 저장할 자료구조
		int ptlength = pattern.length - 1;
		for (int i = 0; i < pattern.length - 1; i++) {
			if (i == pattern.length - 1) {
				jumptable.put(pattern[i], pattern.length);
			} else {
				jumptable.put(pattern[i], ptlength);
				ptlength--;
			}

		}
		System.out.print("매칭 위치 = ");
		solution(data, pattern, jumptable);

	}

	public static void solution(char[] data, char[] pattern, Map<Character, Integer> jumptable) {
		int n = data.length;
		int m = pattern.length;
		int i = 0;
		int jumpcount = 0;
		while (i <= n - m) {
			int j = m - 1;
			int k = i + j;
			while (j >= 0 && pattern[j] == data[k]) {
				j--;
				k--;
			}
			if (j == -1) {
				System.out.print(i + " ");
			}
			if (jumptable.containsKey(data[i + m - 1])) {
				i = i + jumptable.get(data[i + m - 1]);
				jumpcount = jumpcount + 1;

			} else {
				i += pattern.length;
				jumpcount = jumpcount + 1;
			}
		}
		System.out.println();
		System.out.println("점프 수 = " + jumpcount);
	}
}
