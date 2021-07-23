/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HayChonGiaDungRMI_23_7;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class HayChonGiaDung_Client {
    public static void menu(){
        System.out.println("1.Lấy ngẫu nhiên sản phẩm");
        System.out.println("2.Đoán giá cho sản phẩm");
        System.out.println("3.Thoát");
        System.out.println("Lựa chọn: ");
    }
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry regis = LocateRegistry.getRegistry("localhost", 10);
        HayChonGiaDung_Interface client = (HayChonGiaDung_Interface) regis.lookup("Server");
        
        Scanner sc = new Scanner(System.in);
        int current=-1;
        int remain=-1;
        while (true) {            
            if(current==-1){
                System.out.println("Chưa chọn sản phẩm nào");
            } else{
                System.out.println("Đã chọn sản phẩm: "+client.currentProduct(current));
                System.out.println("Số lần đoán còn lại: "+remain);
            }
            
            menu();
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon){
                case 1:
                    current = client.randomChoice();
                    remain = 7;
                    
                    break;
                case 2:
                    while (remain>0) {                        
                        System.out.println("Nhập giá đoán:");
                        int predict = sc.nextInt();
                        sc.nextLine();
                        System.out.println(client.checkPredict(predict, current));
                        if (client.checkPredict(predict, current).equals("Dự đoán chính xác")){
                            current=-1;
                            break;
                        } 
                        remain--;
                    }
                    if (remain==0){
                        System.out.println("Bạn đã hết lượt đoán");
                    }
                    current = -1;
                    break;
                case 3:
                    System.exit(0);
            }
            
        }
    }
}
