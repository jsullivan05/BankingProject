package com.sullivan.support;

import com.sullivan.implement.AccountDaoImpl;
import com.sullivan.implement.AdminDaoImpl;
import com.sullivan.implement.UserDaoImpl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class JDBCBank {
    static Scanner scan;
    static MenuLogic m;

    static {
        scan = new Scanner(System.in);
        m = new MenuLogic();
    }

    public JDBCBank() {
    }

    public static void main(String[] args) {
        UserDaoImpl udi = new UserDaoImpl();
        AccountDaoImpl adi = new AccountDaoImpl();
        AdminDaoImpl addi = new AdminDaoImpl();
        User retUser = null;
        Admin admin = null;
        String traverse = "";
        String check = "";
        System.out.println("Welcome to the Sullivan Bank! Are you a new user?");
        traverse = scan.nextLine();
        if (traverse.equals("yes")) {
            User newUser = m.register();

            try {
                udi.createUser(newUser.getFname(), newUser.getLname(), newUser.getUname(), newUser.getPw());
                System.exit(1);
            } catch (SQLException var30) {
                var30.printStackTrace();
            }
        } else {
            System.out.println("Welcome back! Are you a user or an admin?");
            traverse = scan.nextLine();
            String p;
            String uname;
            if (traverse.equals("admin")) {
                System.out.println("Please Login with your credentials.");
                System.out.println("Please enter your username:");
                uname = scan.nextLine();
                System.out.println("Please enter your password:");
                p = scan.nextLine();

                try {
                    admin = addi.adminLogin(uname, p);
                } catch (SQLException var29) {
                    var29.printStackTrace();
                }

                admin.toString();
            } else if (traverse.equals("user")) {
                System.out.println("Please Login with your credentials.");
                System.out.println("Please enter your username:");
                uname = scan.nextLine();
                System.out.println("Please enter your password:");
                p = scan.nextLine();

                try {
                    retUser = udi.userLogin(uname, p);
                } catch (SQLException var28) {
                    var28.printStackTrace();
                }

                retUser.toString();
            }
        }

        System.out.println("check if user or admin");
        check = scan.nextLine();
        int menuSelect;
        if (check.equals("user") && retUser != null) {
            System.out.println("Welcome to the main menu\nHere are your options:\n1: view your account(s)\n2: create new account\n3: delete accounts with an empty balance\n4: withdraw from account\n5: deposit into account");
            System.out.println("Please enter a number");
            menuSelect = scan.nextInt();
            switch(menuSelect) {
                case 1:
                    System.out.println("Returning Accounts...");
                    List retAct = null;

                    try {
                        retAct = adi.getAllAccountsSingleUser(retUser);
                    } catch (SQLException var27) {
                        var27.printStackTrace();
                    }

                    Iterator var11 = retAct.iterator();

                    while(var11.hasNext()) {
                        Account i = (Account)var11.next();
                        System.out.println(i);
                    }

                    return;
                case 2:
                    System.out.println("Create a new account...");
                    System.out.println("Please enter a Starting Balance");
                    double balance = 0.0D;
                    balance = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("Please enter a name for your new account");
                    String aname = scan.nextLine();

                    try {
                        adi.createNewAccount(retUser, balance, aname);
                        System.out.println(adi.getAllAccountsSingleUser(retUser));
                    } catch (SQLException var26) {
                        var26.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        adi.deleteEmptyAccounts(retUser);
                    } catch (SQLException var25) {
                        var25.printStackTrace();
                    }

                    System.out.println("Accounts deleted");
                    break;
                case 4:
                    System.out.println("Please enter the amount that you want to withdraw:");
                    double w = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("Please enter the name of the account you want to withdraw from:");
                    String n1 = scan.nextLine();

                    try {
                        adi.withdrawFromAccount(retUser, w, n1);
                    } catch (SQLException var24) {
                        var24.printStackTrace();
                    }

                    System.out.println("Your have withdrawn from your account.");
                    break;
                case 5:
                    System.out.println("Please enter the amount that you want to deposit:");
                    double d = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("Please enter the name of the account you want to deposit into:");
                    String n2 = scan.nextLine();

                    try {
                        adi.depositIntoAccount(retUser, d, n2);
                    } catch (SQLException var23) {
                        var23.printStackTrace();
                    }

                    System.out.println("Your have deposited into your account.");
            }
        } else if (check.equals("admin") && admin != null) {
            System.out.println("Welcome to the Admin menu\nHere are your options:\n1: view All users\n2: create new user\n3: delete all users\n");
            System.out.println("Please enter a number");
            menuSelect = scan.nextInt();
            switch(menuSelect) {
                case 1:
                    System.out.println("View all users");

                    try {
                        udi.getAccountHolders();
                    } catch (SQLException var22) {
                        var22.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Creating new User");
                    User newUser = m.register();

                    try {
                        udi.createUser(newUser.getFname(), newUser.getLname(), newUser.getUname(), newUser.getPw());
                        System.exit(1);
                    } catch (SQLException var21) {
                        var21.printStackTrace();
                    }

                    System.out.println("New user created.");
                    break;
                case 3:
                    System.out.println("Deleting all Users");

                    try {
                        addi.deleteAllUsers();
                    } catch (SQLException var20) {
                        var20.printStackTrace();
                    }

                    System.out.println("You madman! They're all gone!");
            }
        }

    }
}