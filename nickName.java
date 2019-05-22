
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class nickName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new nickName().run();	
	}	
	public void run() {
		int totalcount=0;
		
		Scanner scanner=new Scanner(System.in);
		int line = Integer.parseInt(scanner.nextLine());
		String []strArr = new String[line];
		for(int i=0;i<line;i++)
		{
		    strArr[i]=scanner.nextLine();
		}
		
		for(int ii=0;ii<strArr.length;ii++)
		{
			
			
			ArrayList<Integer> arrlist=new ArrayList<>();

		    for(int j=0;j<strArr.length;j++)
		    {
		    	if(ii!=j)
		    	{
		    	String str1=strArr[ii];
		    	String str2=strArr[j];
		    int cou=compareChara(str1,str2);	
		    arrlist.add((cou));
		   // System.out.println(arrlist);
		   
		    }
		    }
		    
		    int size=arrlist.size();
        	    int count= getmax(arrlist);
        	    //System.out.println(count);
		    totalcount=totalcount+count;
		    
		  
		    
		}
		System.out.println(totalcount);
		
	}
	private int getmax(ArrayList<Integer> arrlist) {
		int max = Integer.MIN_VALUE;
		for(int i=0; i<arrlist.size(); i++){
	        if(arrlist.get(i) > max){
	            max = arrlist.get(i);
	        }
	    }
	
		return max;
		
	}
	private int compareChara(String str1, String str2){
		// TODO Auto-generated method stub
		int len=0;
		int count=1;
		int length1=str1.length();
		int length2=str2.length();
		if (length1<=length2)
		{
			len = length1;
		}
		else {
			len=length2;		
		}

		for(int k=0;k<len;k++)
		{
			
			char x=str1.charAt(k);
			char y=str2.charAt(k);
			//System.out.println("x:"+x);
			//System.out.println("y:"+y);
			if(x!=y)
			{
				count=count+k;
				return count;
			}

			
		}
		return count;
		
		
	}
}
	