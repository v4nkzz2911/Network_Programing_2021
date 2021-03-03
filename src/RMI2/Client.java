package RMI2;

import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void menu(){
        System.out.println("1.Tính tổng");
        System.out.println("2.Tính hiệu");
        System.out.println("Lựa chọn: ");
                
    }
    public Client() throws Exception {
        Scanner sc = new Scanner(System.in);
        int rep=0;
        System.out.print("Nhap vao a: ");
        int a = sc.nextInt();
        System.out.println("Nhap vao b: ");
        int b = sc.nextInt();
        
        
        Registry client = LocateRegistry.getRegistry("localhost",3232 );
        interface_rmi regis = (interface_rmi) client.lookup("Server");
        
        while (rep!=3) {            
            menu();
            rep =sc.nextInt();
            switch (rep){
                case 1: 
                    System.out.println("Tổng: "+regis.sum(a, b));
                    break;
                case 2:
                    System.out.println("Hiệu: "+regis.substract(a, b));
                    break;
                    
            }
        }
    }

    public static void main(String[] args) throws Exception , NotBoundException{
        Client c = new Client();
    }
}
