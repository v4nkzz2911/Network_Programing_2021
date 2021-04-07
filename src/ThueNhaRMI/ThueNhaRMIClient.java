/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThueNhaRMI;

import BankManagerRMI.BankRMIClient;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class ThueNhaRMIClient {
    
    public static void menu(){
        System.out.println("1.Tìm kiếm nhà ");
        System.out.println("2.Hiển thị tất cả nhà");
        System.out.println("3.Thuê nhà");
        System.out.println("4.Thoát");
        System.out.println("Lựa chọn: ");
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry client = LocateRegistry.getRegistry("localhost",100);
        ThueNhaRMIInterface regis = (ThueNhaRMIInterface) client.lookup("Server");
        
        Scanner sc = new Scanner(System.in);
        int rep = 0;
        while (rep!=4) {            
            menu();
            rep = sc.nextInt();
            sc.nextLine();
            switch (rep){
                case 1:
                    System.out.println("Nhập mã số nhà cần tìm: ");
                    String target =sc.nextLine();
                    System.out.println("Mã số\tLoại\t\tĐịa chỉ\t\t\tTình trạng");
                    System.out.println(regis.search(target));
                    break;
                case 2:
                    System.out.println("Thông tin toàn bộ các nhà");
                    System.out.println("Mã số\tLoại\t\tĐịa chỉ\t\t\tTình trạng");
                    System.out.println(regis.showAll());
                    break;
                case 3:
                    System.out.println("Nhập mã số nhà cần thuê");
                    System.out.println(regis.rentHouse(sc.nextLine()));
            }
        }
        
    }
    
}
