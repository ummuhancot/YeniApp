package com.tpe.service;

import com.tpe.domain.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService {

    static Scanner scanner = new Scanner(System.in);

    List<Admin> adminList = new ArrayList<>();

    public void Kayıt(){

        Admin admin = new Admin();
        System.out.println(" Ad :");
        admin.setName(scanner.nextLine());

        System.out.println(" Email : " );
        admin.setEmail(scanner.nextLine());

        System.out.println(" Password : ");
        admin.setPassword(scanner.nextLine());

        adminList.add(admin);
        System.out.println(admin);


    }




}
