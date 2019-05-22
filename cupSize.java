import java.util.Scanner;

public class cupSize {
    public static void main(String[] args) {
         new cupSize().run();
    }
    private void run() {
        Scanner scanner =new Scanner(System.in);
        String cupSizeStandard = scanner.nextLine(); // 25 50 75
        String []cupSizearray = cupSizeStandard.split("\\s");
        int customer_num = Integer.parseInt(scanner.nextLine());
        customer []customer_info = new customer[customer_num];// 创建一个数组存放客户信息，数组长度是客户数量，每一个客户信息用一个客户类存储
        for(int i=0; i<customer_num; i++)
        {
            String info =scanner.nextLine();
            String infoarray[] =info.split("\\s");
            customer cc = new customer();// 新建客户对象。讲客户信息从scanner读入，每读入一个， 讲客户信息匹配
            info_manage(cc,infoarray);
            customer_info[i]=cc;

        }// 创建客户对象，客户信息初始化
        for(customer c:customer_info){
            c.customer_drinktype=drinktype(c.age);
            c.customer_cupsize = cupsize(cupSizearray,c.height1,c.height2);
            System.out.println(c.name+" "+c.customer_drinktype+" "+c.customer_cupsize+" "+c.age+" "+c.height1+" "+c.height2+" ");
        }
    }
    private String cupsize(String[] cupSizearray, int height1, int height2) {
        int size[] = new int[cupSizearray.length];// 创建一个数组存放size信息。数组长度是cupsizearray长度
        for(int i=0; i<cupSizearray.length;i++)
        {
            size[i]=Integer.parseInt(cupSizearray[i]);
        }
        double total = 0;
        if(height1<=5)
        {
            total = height1*10;
            
        }
        else {
            total=50+(height1-5+(height2*0.1))*8;
        }
        if(total<=size[0])
        {
            return "Extra small";
            
        }else if(total>size[0]&&total<=size[1])
        {
            return "Small";

        }
        else if(total >size[1]&&total<=size[2])
        {
            return "Medium";

        }
        else
            return "Large";
    }
    private String drinktype(int age) {
        if (age < 18 ){
            return "water";
        }
        else
            return "beer";
    }
    private void info_manage(customer cc,String infoarray[]) {// 一定要带类型
        cc.name=infoarray[0];
        cc.age=Integer.parseInt(infoarray[1]);
        cc.height1 =Integer.parseInt(infoarray[2]);
        cc.height2 =Integer.parseInt(infoarray[3]);
    }

    class customer{
        String name;
        int age;
        int height1;
        int height2;
        String customer_cupsize;
        String customer_drinktype;

    }

}
