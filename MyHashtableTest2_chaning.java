import java.util.Scanner;
//***************************
//파일명:MyHashtableTest2 클래스 
//작성자: 진형준
//작성일: 2020-05-04
//(가) 충돌해결 방법 변경
//open addressing ----> chaining
//(나) 저장하는 내용 변경
//<학번> ----> <학번, 성적> 저장    
//학번(양의 정수)은 Key로서 중복되지 않는다.
//성적(정수)은 Value로서 학번으로부터 구하고자 하는 값이다.
//***************************

public class MyHashtableTest2 {

	public static void main(String[] args) {
		System.out.println("hw7_2:진형준");
		System.out.println("선택과제 (가),(나) 수행");
		Scanner sc = new Scanner(System.in);
		// 해시테이블 크기 입력받기
		System.out.print("해시테이블 크기 입력:");
		int size = sc.nextInt();
		// 크기가 size인 해시테이블 생성
		MyHashtable2 studentTable = new MyHashtable2(size);//객체 생성
		int menu = 0;
		int id;
		int score;
		do {
			System.out.print("\n1:삽입  2:검색  3:삭제  4:테이블 출력  5:종료-----> ");
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				System.out.println("삽입할 학번(양의 정수값)을 입력하세요: ");
				id = sc.nextInt();
				System.out.println("삽입할 성적(정수값)을 입력하세요: ");
				score = sc.nextInt();
				System.out.println(studentTable.put(id, score));
				break;

			case 2:
				System.out.print("검색할 학번(정수값)을 입력하세요: ");
				id = sc.nextInt();
				System.out.println(id + "학번");
				if (studentTable.get(id) == null) {
					System.out.println(id + " 학번 성적이 없습니다");
				} else
					System.out.print(studentTable.get(id));
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

class MyHashtable2 {//선택과제를 위해서 MyHashtable2로 클래스명 지정
	private int m;// 해시테이블 크기
	private static final Object EMPTY = null;
	private static final int DELETED = -2;
	private Node[] HashTable; // 해시 테이블

	public static class Node { // Node 클래스
		private int key;
		private int value;
		private Node next;

		public Node(int newkey, int newvalue, Node newnext) { // newnext 다음노드를 참조하는 참조변수
			key = newkey;
			value = newvalue;
			next = newnext;
		}

		public int getKey() {
			return key;
		}

		public int getValue() {
			return value;
		}
	}

	public MyHashtable2(int size) {
		m = size;
		HashTable = new Node[m];
		for (int i = 0; i < size; i++) {
			HashTable[i] = (Node) EMPTY;
		}
	}

	private int hash(int key) { // 해시코드
		return key % m;
	} // 나눗셈 연산

	public String get(int key) { // 검색 연산
		int i = hash(key);
		for (Node x = HashTable[i]; x != EMPTY; x = x.next) {// 연결리스트 탐색
			if (x.key == key)
				return "[학번 :" + x.getKey() + ", 성적 :" + x.getValue() + "]";
		} // 탐색 성공
		return "성적이 존재 하지 않습니다."; // 탐색 실패
	}

	// 해시테이블에서 key value 값을 삽입하고 삽입 성공 여부를 문자열 리턴
	public String put(int key, int value) { // 삽입 연산
		int i = hash(key);
		for (Node x = HashTable[i]; x != EMPTY; x = x.next)
			if (x.key == key) { // 이미 key 존재
				x.value = value; // 데이터만 갱신
				return key + " 학번이 존재하여 성적이 " + value + " 으로  갱신 되었습니다";

			}
		HashTable[i] = new Node(key, value, HashTable[i]); // 첫번째 값으로 추가
		return "학번과 데이터를 추가 합니다.";
	}

	public boolean remove(int key) {
		int i = hash(key);
		for (Node x = HashTable[i]; x != EMPTY; x = x.next)
			if (x.key == key) { // key 존재
				x.key = DELETED;// key 삭제
				x.value = DELETED; // 데이터 삭제
				return true;

			}
		return false;
	}

	public void print() {
		System.out.println("해시테이블 내용");
		for (int i = 0; i < m; ++i) {
			System.out.print("[" + i + "]");
			Node temporary = HashTable[i];//해시테이블을 노드타입으로 저장
			while (temporary != null) {
				if (temporary.key != DELETED && temporary.key != DELETED)
					//테이블내용이 DELETED값이면 출력 x 위의 DELETED조건문 없애면 DELETED 출력 가능 
					System.out.print("-->[학번: " + temporary.getKey() + ", 성적: " + temporary.getValue() + "]");
				temporary = temporary.next;//반복진행
			}
			System.out.println();
		}
	}

}