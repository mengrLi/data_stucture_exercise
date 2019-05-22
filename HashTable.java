import java.util.Scanner;

public class HashTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HashTable().run();
		

	}

	private void run() {
		// TODO Auto-generated method stub
		Scanner scanner =new Scanner(System.in);
		int n= Integer.parseInt(scanner.nextLine());
		int[] hashtable =new int[n];
		for(int k=0; k<n; ++k) {
			hashtable[k] = 0;
		}
		
		String str=scanner.nextLine();
		String []numsToInsert=str.split("\\s+");
        int numsCount = numsToInsert.length - 1;
		
		
		
		for(int k=0; k<numsCount; ++k) {	//process each number to insert
			int i = Integer.parseInt(numsToInsert[k]);
			int j = i%n;					//compute index j of the location
			String triedPositions = Integer.toString(j);
			int cur = -1;
			int digit = -1;
			
			while(hashtable[j] != 0 && i>0) {
				cur = j;			//keep track of tried unavail loc
				digit = i%10;		//remove the right most digit of i
				i = (i-digit)/10;	//get the new i
				j = i%n;			//recompute the j = i mod N
				
				if(digit%2 == 1) {	//the digit removed was odd
					j = cur+j;		//move j locations forward from the current location
					j = j%n;		//wrap round
				}else {				//the digit removed was even
					j = cur-j;		//move j locations backward from the current location
					j = (j+n)%n;	//wrap round
				}
				String newPosition = Integer.toString(j);	//new location to try
				triedPositions = triedPositions + " " + newPosition;
			}
			
			//new location to try is j
			
			while(hashtable[j] != 0 && i==0) {	//all digits of i have been removed, unable to find free loc
				cur = j;			//keep track of tried unavail loc
				if(digit%2 == 1) {	//move forward until find new j		
					j++;
					j = j%n;		//new location to try
				}else {				//move backward until find new j
					j--;
					j = (j+n)%n ;	//new location to try
				}
				String newPosition = Integer.toString(j);	//new location to try
				triedPositions = triedPositions + " " + newPosition;
			}
			
			hashtable[j] = Integer.parseInt(numsToInsert[k]);	//put the number into the available location
			System.out.println(triedPositions);
		}
	}

		
		

		
		
	

}
