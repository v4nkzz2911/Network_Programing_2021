/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankManagerRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class BankRMIClient {
    public static void menu(){
        System.out.println("1.Chuyển tiền");
        System.out.println("2.Rút tền");
        System.out.println("3.Kiểm tra số dư");
        System.out.println("4.Xem lịch sử");
        System.out.println("5.Thoát");
        System.out.println("Lựa chọn: ");
    }

    public BankRMIClient() throws RemoteException, NotBoundException {
        Registry client = LocateRegistry.getRegistry("localhost", 3232);
        BankInt regis = (BankInt) client.lookup("Server");
        
        Scanner sc = new Scanner(System.in);
        int rep = 0;
        while(rep!=5){
            menu();
            rep = sc.nextInt();
            sc.nextLine();
            String source,target;
            int amount;
            switch (rep){
                case 1:
                    System.out.println("Nhập tên tk của bạn: ");
                    source = sc.nextLine();
                    System.out.println("Nhập tên tk đích: ");
                    target = sc.nextLine();
                    System.out.println("Nhập số tiền cần chuyển: ");
                    amount = sc.nextInt();
                    sc.nextLine();
                    System.out.println(regis.transfer(amount, source, target));
                    break;
                case 2:
                    System.out.println("Nhập tên tk của bạn: ");
                    target = sc.nextLine();
                    System.out.println("Nhập số tiền cần rút: ");
                    amount = sc.nextInt();
                    System.out.println(regis.withdrawal(amount, target));
                    break;
                case 3:
                    System.out.println("Nhập tên tk của bạn: ");
                    target = sc.nextLine();
                    System.out.println("Số dư: "+regis.checkBalance(target));
                    break;
                case 4:
                    System.out.println("Lịch sử: ");
                    System.out.println(regis.checkHistory());
            }
        }
        
        
        
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        BankRMIClient client = new BankRMIClient();
    }
    
    
}
