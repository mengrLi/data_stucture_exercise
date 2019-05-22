import java.util.Scanner;

public class uniqueWordCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc_in = new Scanner(System.in);
		String input = sc_in.nextLine();
		int count =0;
		String[] array=input.split("\\s+");
		int length = array.length;
		for(int i=0; i<length;i++)
		{
			String str=array[i];
			boolean unique = true;
			for(int j=0; j<i; ++j) {//忽略大小写的比较
				if(str.equalsIgnoreCase(array[j])) {
					unique = false;
				}
			}
			if(unique==true) count++;
		}
		
		System.out.println(count);
			
		}
	}


