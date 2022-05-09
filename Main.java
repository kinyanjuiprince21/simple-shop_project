package com.example.start;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static User user = new User();

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        System.out.println("HELLO, WELCOME TO OUT SITE.");
        System.out.print("\t1. Register \n\t2. Login\n(Choose 1/2): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> register();
            case 2 -> login();
            default -> {
                System.out.println("Wrong option");
                init();
            }
        }

    }

    public static void register() {
        System.out.println("Registration page");
        System.out.print("Enter your name: ");
        String name = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        System.out.format("Creating new user {name: %s}", name);
        DAO<User, Integer> userDao = new UserDAO();
        userDao.add(user);
        System.out.println("redirecting to login");
        login();
    }

    public static void login() {

        System.out.println(new UserDAO().getAll());

        System.out.println("login page");

        System.out.print("Create a new account? (y/n): ");
        String option = scanner.next();
        if(option.equalsIgnoreCase("y")){
            register();
        }
        System.out.println("");
        System.out.print("Enter your name: ");
        String name = scanner.next();

        User user = new UserDAO().getByName(name);

        if(user.getName() == null) {
            System.out.println("user not found");
            login();
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.next();

        if(!password.equals(user.getPassword())) {
            System.out.println("password does not match");
            login();
            return;
        }
        System.out.println("login successfully, redirecting to shop page");
        Main.user = user;
        shop();
    }

    public static void shop() {
        System.out.format("\n\nHello %s, welcome to the shop.\n", user.getName());
        System.out.print("\t1. Add Item \n\t2. Make a sale \n\t3. Make a purchase \n\t4. View All Items\n\t5. Search Item \n\t6. Log out \n\t7. Exit\n(Choose 1,2,3,4,5,6 or 7): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> addItem();
            case 2 -> saleItem();
            case 3 -> purchase();
            case 4 -> viewItems();
            case 5 -> searchItem();
            case 6 -> login();
            case 7 -> {
                System.out.println("Bye...");
                System.exit(1);
            }
            default -> {
                System.out.println("Wrong option");
                shop();
            }
        }
    }

    private static void searchItem() {
        System.out.print("Enter name of item to search: ");
        String name = scanner.next();
        Item item = new ItemDAO().getByName(name);

        if(item.getName() != null) System.out.println(item);
        else System.out.println("item not found");

        System.out.print("\t1. View All Items\n\t2. Update Item\n\t3. Delete Item\n\t4. Main menu\n(Choose 1,2,3 or 4): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> viewItems();
            case 2 -> deleteItem(item.getId());
            case 3 -> shop();
            default -> {
                System.out.println("Wrong option");
                shop();
            }
        }

    }

    private static void viewItems() {
        List<Item> itemList = new ItemDAO().getAll();
        System.out.println("\tITEMS\n");

        System.out.println("\t-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\t|\tid\t|\tname\t\t\t|\tbuy-price\t|\tquantity\t|\tsale-price\t|\tdate-created\t\t\t|\tdate-updated\t\t\t|");
        System.out.println("\t-------------------------------------------------------------------------------------------------------------------------------------");

        itemList.forEach(item -> {
            System.out.format("\t|\t%d\t|\t%s\t\t\t|\t%,.1f\t\t|\t%d\t\t\t|\t%,.1f\t\t|\t%s\t|\t%s\t|\n", item.getId(), item.getName(), item.getBuyPrice(), item.getQuantity(), item.getSalePrice(), item.getDateCreated(), item.getDateUpdated());
            System.out.println("\t-------------------------------------------------------------------------------------------------------------------------------------");
        });

        System.out.print("\n\t1. Add Item \n\t2. Search Item\n\t3. Update Item\n\t4. Delete Item \n\t5. Main menu\n(Choose 1,2,3,4 or 5): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> addItem();
            case 2 -> searchItem();
            case 3 -> updateItem();
            case 4 -> deleteItem();
            case 5 -> shop();
            default -> {
                System.out.println("Wrong option");
                shop();
            }
        }
    }

    private static void deleteItem(int... id1) {
        int id = 0;
        if(id1.length < 1) {
            System.out.println("You are about to delete an item.");
            System.out.print("Enter item id: ");
            id = scanner.nextInt();
        }else id = id1[0];



        Item item = new ItemDAO().get(id);
        if(item.getName() == null) {
            System.out.println("Item does not exist");
            viewItems();
        }

        int count = 0;

        System.out.print("Are you sure you want to delete item {name: " + item.getName() + "}? (y/n): \t");
        while(true) {
            System.out.print("\t:> ");
            String ans = scanner.next();
            if(ans.equals("y")) {
                new ItemDAO().delete(id);
                System.out.println("you have successfully deleted item {name: " + item.getName() + "}.");
                viewItems();
                break;
            }else if(ans.equals("n")) {
                viewItems();
                break;
            }else{
                if(count == 2){
                    System.out.println("Cannot delete item.");
                    viewItems();
                }
                System.out.print("Wrong value, please choose y/n.\t");
            }
            count++;
        }
    }

    private static void updateItem() {
        System.out.print("Enter item name to update");
        String name = scanner.next();
        Item item = new ItemDAO().getByName(name);
        Item item1 = new Item();

        item1.setId(item.getId());
        item1.setDateCreated(item.getDateCreated());
        item1.setSalePrice(item.getSalePrice());
        item1.setBuyPrice(item.getBuyPrice());
        item1.setQuantity(item.getQuantity());
        item1.setName(item.getName());


        System.out.println(item);
        System.out.format("Do you want to change the item name (curr: %s)? (y/n): ", item.getName());
        String ans = scanner.next();
        if(ans.equalsIgnoreCase("y")) {
            System.out.print("Enter the new name: ");
            String newName = scanner.next();
            item1.setName(newName);
        }

        System.out.format("Do you want to change the item sale price (curr: %f)? (y/n): ", item.getSalePrice());
        ans = scanner.next();
        if(ans.equalsIgnoreCase("y")) {
            System.out.print("Enter the new sale price: ");
            double newPrice = scanner.nextDouble();
            item1.setSalePrice(newPrice);
        }

        System.out.format("Do you want to change the item quantity (curr: %d)? (y/n): ", item.getQuantity());
        ans = scanner.next();
        if(ans.equalsIgnoreCase("y")) {
            System.out.print("Enter the quantity: ");
            int newQty = scanner.nextInt();
            item1.setQuantity(newQty);
        }

        item1.setDateUpdated(new Timestamp(System.currentTimeMillis()));

        if(new ItemDAO().update(item1) < 1) {
            System.out.println("Could not update the item.");
            viewItems();
        }
        System.out.format("You are about to update from %s to \n%s", item, item1);
        viewItems();

    }

    private static void purchase() {
    }

    private static void saleItem() {
    }

    private static void addItem() {

        System.out.println("Add a new Item");
        System.out.print("\titem name:\t");
        String name = scanner.next();
        System.out.print("\tBuy price:\t");
        double buy_price = scanner.nextDouble();
        System.out.print("\tquantity:\t");
        int quantity = scanner.nextInt();
        System.out.print("\tsale price:\t");
        double sale_price = scanner.nextDouble();
        Timestamp date_created = new Timestamp(System.currentTimeMillis());
        Timestamp date_updated = new Timestamp(System.currentTimeMillis());

        Item item = new Item();
        item.setName(name);
        item.setBuyPrice(buy_price);
        item.setQuantity(quantity);
        item.setSalePrice(sale_price);
        item.setDateUpdated(date_updated);
        item.setDateCreated(date_created);

        if(new ItemDAO().add(item) == 0) {
            System.out.println("could not add a new item");
            shop();
        }
        System.out.println("item added successfully");

        System.out.print("\t1. Add Another Item \n\t2. View All Items\n\t3. Main menu\n(Choose 1,2 or 3): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> addItem();
            case 2 -> viewItems();
            case 3 -> shop();
            default -> {
                System.out.println("Wrong option");
                shop();
            }
        }
    }
}
