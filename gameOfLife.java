import java.util.*;
public class gameOfLife {

	public gameOfLife() {
		// TODO Auto-generated constructor stub
	}
	
	public static void print(char[][] gameBoard, int rows, int cols) {
		for(int i=0; i<rows; ++i) {
			for(int j=0; j<cols; ++j) {
				System.out.print(gameBoard[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static int getNumOfNeighbours(char[][] gameBoard, int rows, int cols, int i, int j) {
		int count = 0;
		for( int m=i-1; m<i+2; ++m) {
			if(m>=0 && m<rows) {
				for(int n=j-1; n<j+2;++n) {
					if(n>=0 && n<cols) {
						if(m!=i || n!=j) {
							char cell = gameBoard[m][n];
							if(cell == '@')
								count++;
						}
					}
				}
			}
		}
		return count;
	}
	
	public static void replaceBoard(char[][] gameBoard, char[][] newGameBoard, int rows, int cols) {
		for(int i=0; i<rows; ++i) {
			for(int j=0; j<cols; ++j) {
				gameBoard[i][j] = newGameBoard[i][j];
			}
		}
	}
	
	public static void playGame(char[][] gameBoard, int rows, int cols) {
		char [][] newGameBoard = new char[rows][cols];
		int [][] neighbourNum = new int[rows][cols];
		
		//check each cell of the old game board
		for(int i=0; i<rows; ++i) {
			for(int j=0; j<cols; ++j) {
				char cell = gameBoard[i][j];
				int numNeighbours = getNumOfNeighbours(gameBoard, rows, cols, i, j);
				neighbourNum[i][j] = numNeighbours;
				
				if(cell == '@') {//organism cell
					if(numNeighbours == 0 || numNeighbours == 1 || numNeighbours >= 4)
						newGameBoard[i][j] = '#';
					else
						newGameBoard[i][j] = '@';
				}else if(cell == '#') {//empty cell
					if(numNeighbours == 3)
						newGameBoard[i][j] = '@';
					else
						newGameBoard[i][j] = '#';
				}
			}
		}
		for(int i=0; i<rows; ++i) {
			for(int j=0; j<cols; ++j) {
				System.out.print(neighbourNum[i][j]+" ");
			}
			System.out.println();
		}
		
		//replace the old board with the new board
		replaceBoard(gameBoard, newGameBoard, rows, cols);
	}
	
	public static int getNumOfOrganism(char [][] gameBoard, int rows, int cols) {
		int count = 0;
		for(int i=0; i<rows; ++i) {
			for(int j=0; j<cols; ++j) {
				if(gameBoard[i][j] == '@')
					count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String Line = sc.nextLine();
		String [] strArray = Line.split("\\s+");
		int rows = Integer.parseInt(strArray[0]);
		int cols = Integer.parseInt(strArray[1]);
		
		char [][] gameBoard = new char[rows][cols];
		int i = 0;
		//initialize the board
		while(i<rows) {
			Line = sc.nextLine();
			int length = Line.length();
			for(int j=0; j<length; ++j) {
				gameBoard[i][j] = Line.charAt(j);
			}
			++i;
		}
		
		int rounds = Integer.parseInt(sc.nextLine());
		int r=0;
		while(r<rounds) {
			playGame(gameBoard, rows, cols);
			print(gameBoard, rows, cols);
			++r;
		}
		int surviveNum = getNumOfOrganism(gameBoard, rows, cols);
		System.out.println(surviveNum);
	}
}
