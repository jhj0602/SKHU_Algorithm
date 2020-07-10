import java.util.Scanner;
//***************************
//파일명:MyHashtableTest 클래스 
//작성자: 진형준
//작성일: 2020-05-04
//내용: 학번을 저장해 두고 검색하는 해시 테이블 프로그램을 작성하시오.
// 해시 함수는 나누기 방법. 즉, h(x) = x % m
// 충돌해결은 open addressing 중에서 linear probing
//***************************

public class MyHashtableTest {

	public static void main(String[] args) {
		System.out.println("hw7_1:진형준");
		Scanner sc = new Scanner(System.in);
		// 해시테이블 크기 입력받기
		System.out.print("해시테이블 크기 입력:");
		int size = sc.nextInt();
		// 크기가 size인 해시테이블 생성
		MyHashtable studentTable = new MyHashtable(size);
		int menu = 0;
		int id;
		do {
			System.out.print("\n1:삽입  2:검색  3:삭제  4:테이블 출력  5:종료-----> ");
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				System.out.print("삽입할 학번(정수값)을 입력하세요: ");
				id = sc.nextInt();
				if (studentTable.put(id)) {
					System.out.println(id + " 삽입 성공");
				} else {
					System.out.println(id + " 삽입 실패");
				}
				break;

			case 2:
				System.out.print("검색할 학번(정수값)을 입력하세요: ");
				id = sc.nextInt();
				if (studentTable.contains(id)) {
					System.out.println(id + " 학생을 찾았습니다.");
				} else {
					System.out.println(id + " 학생을 찾을 수 없습니다.");
				}
				break;
			case 3:
				System.out.println("삭제할 학번(정수값)을 입력하세요");
				id = sc.nextInt();
				if (studentTable.remove(id)) {
					System.out.println(id + " 삭제 성공");
				} else {
					System.out.println(id + " 삭제 실패");
				}
				break;

			case 4:
				studentTable.print();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다");
				break;
			default:
				System.out.println("메뉴 번호가 잘못 되었습니다.다시 입력하세요");
			}

		} while (menu != 5);
		sc.close();
	}
}

class MyHashtable {
	private int[] table;// 해시 테이블
	private int m;// 해시테이블 크기
	private static final int EMPTY = -1;
	private static final int DELETED = -2;

	public MyHashtable(int size) {
		table = new int[size];
		m = size;
		for (int i = 0; i < size; i++) {
			table[i] = EMPTY;
		}
	}

	// 해시테이블에서 key 값을 삽입하고 삽입 성공 여부를 리턴
	public boolean put(int key) {
		if (contains(key) == true) {
			return false;
		} else {
			int InitialValue = hash(key);
			int i = InitialValue, j = 1;//초기값
			do {
				if (table[i] == EMPTY||table[i]==DELETED) { // 삽입 위치 발견
					table[i] = key; // key를 해시테이블에 저장
					break;

				}
				if (table[i] == key) { // 이미 key 존재

				}
				i=hash(InitialValue,j);
				j++;
			} while (i != InitialValue); // 현재 i가 초기위치와 같게되면 루프 종료
			return true;
		}

	}

	public boolean remove(int key) {
		if (contains(key) == true) {
			int InitialValue = hash(key);
			int i = InitialValue, j = 1;
			do {
				if (table[i] == key) { // 삽입 위치 발견
					table[i] = DELETED; // key를 해시테이블에 저장
					break;

				}

				i=hash(InitialValue,j);//다음위치 즉 인덱스를 가져오기위해 호츌
				j++;
			} while (i != InitialValue); // 현재 i가 초기위치와 같게되면 루프 종료
			return true;
		} else {

			return false;
		}

	}

	// 해시테이블에서 key 값을 검색하고 검색 성공 여부를 리턴
	public boolean contains(int key) {
		int index = search(key);
		return (index >= 0);
	}

	private int search(int key) {
		int InitialValue = hash(key);
		int i = InitialValue, j = 1;
		while (table[i] != EMPTY) { // a[i]가 empty가 아니면
			
			if (table[i] == key)
				return key; // 탐색 성공
			i=hash(InitialValue,j);
			j++;
		}
		return EMPTY; // 탐색 실패

	}

	public void print() {
		System.out.println("해시테이블 내용");
		for (int i = 0; i < m; i++) {
			if (table[i] == EMPTY) {
				System.out.println("[" + i + "]");
			} else if (table[i] == DELETED) {
				System.out.println("[" + i + "] DELETED");
			} else {
				System.out.println("[" + i + "]" + table[i]);
			}

		}
	}

	// division method 해시 함수 h(x)= x mod m
	private int hash(int x) {

		return x % m;
	}
	
	// linear probing i번째 해시 함수 h(x) =(h(x)+i) mod m
	private int hash(int i, int x) {
		return (i + x) % m;
	}
}