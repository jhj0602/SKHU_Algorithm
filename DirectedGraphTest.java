import java.util.Scanner;
//***************************
//파일명: DirectedGraphTest
//작성자: 진형준
//작성일: 6/9
//내용:방향 그래프를 인접 리스트로 구현하는 프로그램을 작성하시오
//***************************
public class DirectedGraphTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("hw10_1 : 진형준");
		System.out.println("정점 수 입력 : ");
		// 정점 수 n을 입력받음
		int n = sc.nextInt();
		DirectedGraph graph = new DirectedGraph(n);
		System.out.println("간선 수 입력");
		int e = sc.nextInt();
		// 간선 수 e를 입력받음

		for (int i = 1; i < e + 1; i++) {
			System.out.println("간선" + i + " 입력 : ");
			// e개의 정점 쌍 v1과 v2를 입력받아 그래프에 <v1, v2>를 삽입
			graph.addEdge(sc.nextInt(), sc.nextInt());

		}
		graph.printAdjList();
		System.out.println();

	}
}

class DirectedGraph {
	private static final Node Object = null;
	private static final Node Node = null;
	private Node[] list;// 인접리스트
	private int numberOfVertices;// 정점수(사이즈)
	private Node temp;

	public class Node {// 리스트의 노드구조를 정의한 클래스

		Node link;
		private int vertex;

		public int getV() {
			return vertex;
		}

		
	}

	public DirectedGraph(int n) {// 정점수가 n인 그래프를 생성
		list = new Node[n];
		numberOfVertices = n;

	}

//간선 v1 v2 삽입
	public void addEdge(int v1, int v2) {
		if (v1 >= numberOfVertices || v2 >= numberOfVertices) {
			System.out.println("간선 삽입 오류 - 잘못된 정점 번호입니다.<" + v1 + "," + v2 + ">");

		} else {
			Node tempnode = new Node();
			tempnode.vertex = v2;
			tempnode.link = list[v1];
			list[v1] = tempnode;

		}

	}

	// 구현을 확인하기 위해 인접리스트를 모두 출력
	public void printAdjList() {
		temp = new Node();// list 노드 배열 저장할 노드 temp 배열 설정
		for (int i = 0; i < numberOfVertices; i++) {

			System.out.printf("정점" + i + "의 인접리스트 = ");
			temp = list[i];
			while (temp != null) {

				System.out.print(temp.getV() + " ");
				temp = temp.link;

			}

			System.out.println();
		}
	}

}