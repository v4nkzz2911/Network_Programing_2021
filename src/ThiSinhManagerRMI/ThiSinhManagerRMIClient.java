/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiSinhManagerRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class ThiSinhManagerRMIClient {
    
    public static void menu(){
        System.out.println("1.Tìm thí sinh");
        System.out.println("2.Hiển thị toàn bộ");
        System.out.println("3.Hiển thị danh sách trúng tuyển");
        System.out.println("4.Thoát");
        System.out.println("Lựa chọn: ");
    }
    
    public ThiSinhManagerRMIClient() throws RemoteException, NotBoundException {
        Registry client = LocateRegistry.getRegistry("localhost",6969);
        ThiSinhManagerRMIInterface regis = (ThiSinhManagerRMIInterface) client.lookup("Server");
        
        Scanner sc = new Scanner(System.in);
        int rep = 0;
        while (rep!=4) {            
            menu();
            rep = sc.nextInt();
            sc.nextLine();
            switch (rep){
                case 1 :
                    String target;
                    System.out.println("Nhập tên cần tìm: ");
                    target = sc.nextLine();
                    System.out.println(regis.search(target));
                    break;
                case 2 :
                    System.out.println("Tất cả thí sinh");
                    System.out.println(regis.showAll());
                    break;
                case 3 :
                    System.out.println("Danh sách trúng tuyển");
                    System.out.println(regis.showAdmitted());
                    break;
            }
        }
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        ThiSinhManagerRMIClient client = new ThiSinhManagerRMIClient();
    }
    
}
