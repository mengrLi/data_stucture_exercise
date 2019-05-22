import java.util.Scanner;

public class transcriptNamePrinting {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Scanner sc_in = new Scanner(System.in);
	    String input= sc_in.nextLine();
	    String[] nameArray = input.split("\\s+");
	    int count= nameArray.length;
	    if(count==1) {//only first name
	    	System.out.println(input);
	    }
	    else if(count==2)//first name &lastname   firstnsame middname
	    {
	    	String name= nameArray[1];
	    	if(isLastName(name))
	    	{
	    		System.out.println(nameArray[1]+" "+nameArray[0]);
	    		
	    		
	    	}else {
	    	
	    		System.out.println(input);
	    		}
	    }
	   
	    	else 
	    	{
	    		System.out.println(nameArray[2]+" "+nameArray[0]+" "+nameArray[1]);
	    		
	    }

	}

	private static boolean isLastName(String name) {
	
		// TODO Auto-generated method stub
		String contains ="aeiouAEIOU";
		int clength = contains.length();
		int nlength=name.length();
		
		char lastchar=name.charAt(nlength-1);// 拿到字符串最后一个
		
		for(int i=0;i<clength-1;i++)
		{
			if(lastchar==contains.charAt(i))
				return true;
		}

		return false;
	}

}
