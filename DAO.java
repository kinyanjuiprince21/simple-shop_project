package com.example.start;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAO<T, I> {
    int add(T t);

    int update(T t);

    int delete(I id);

    T get(I id);

    List<T> getAll();
}

class ItemDAO implements DAO<Item, Integer> {

    @Override
    public int add(Item item) {
        String sql = "INSERT INTO Items (name, buy_price, quantity, sale_price, date_created, date_updated) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return CrudUtil.executeUpdate(sql, item.getName(),
                item.getBuyPrice(), item.getQuantity(), item.getSalePrice(),
                item.getDateCreated(), item.getDateUpdated());
    }

    @Override
    public int update(Item item) {
        String sql = "UPDATE Items SET name=?, buy_price=?, quantity=?, sale_price=?, date_updated=? " +
                " WHERE item_id=?";

        return CrudUtil.executeUpdate(sql, item.getName(),
                item.getBuyPrice(), item.getQuantity(), item.getSalePrice(),
                item.getDateUpdated(), item.getId());
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Items WHERE item_id=?";
        return CrudUtil.executeUpdate(sql, id);
    }

    @Override
    public Item get(Integer id) {
        String sql = "SELECT * FROM Items WHERE item_id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        Item item = new Item();
        try {
            if (rst.next()) {
                item.setId(rst.getInt("item_id"));
                item.setName(rst.getString("name"));
                item.setBuyPrice(rst.getDouble("buy_price"));
                item.setQuantity(rst.getInt("quantity"));
                item.setSalePrice(rst.getDouble("sale_price"));
                item.setDateCreated(rst.getTimestamp("date_created"));
                item.setDateUpdated(rst.getTimestamp("date_updated"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public Item getByName(String name) {
        String sql = "SELECT * FROM Items WHERE name=?";
        ResultSet rst = CrudUtil.executeQuery(sql, name);
        Item item = new Item();
        try {
            if (rst.next()) {
                item.setId(rst.getInt("item_id"));
                item.setName(rst.getString("name"));
                item.setBuyPrice(rst.getDouble("buy_price"));
                item.setQuantity(rst.getInt("quantity"));
                item.setSalePrice(rst.getDouble("sale_price"));
                item.setDateCreated(rst.getTimestamp("date_created"));
                item.setDateUpdated(rst.getTimestamp("date_updated"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        String sql = "SELECT * FROM Items";
        List<Item> items = new ArrayList<>();

        ResultSet rst = CrudUtil.executeQuery(sql);

        while (true) {
            try {
                if (!rst.next()) break;
                Item item = new Item();
                item.setId(rst.getInt("item_id"));
                item.setName(rst.getString("name"));
                item.setBuyPrice(rst.getDouble("buy_price"));
                item.setQuantity(rst.getInt("quantity"));
                item.setSalePrice(rst.getDouble("sale_price"));
                item.setDateCreated(rst.getTimestamp("date_created"));
                item.setDateUpdated(rst.getTimestamp("date_updated"));
                items.add(item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return items;
    }
}

class PurchasesDAO implements DAO<Purchases, Integer> {

    @Override
    public int add(Purchases purchases) {
        String sql = "INSERT INTO Purchases (description, item_id, quantity, purchase_price, date_purchased)" +
                " VALUES (?, ?, ?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                purchases.getDescription(),
                purchases.getItem().getId(),
                purchases.getQuantity(),
                purchases.getPurchasePrice(),
                purchases.getDatePurchased()
        );
    }

    @Override
    public int update(Purchases purchases) {
        String sql = "UPDATE Purchases SET description=?, quantity=?, purchase_price=? WHERE purchase_id=?";
        return CrudUtil.executeUpdate(sql,
                purchases.getDescription(),
                purchases.getQuantity(),
                purchases.getPurchasePrice(),
                purchases.getId()
        );
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Purchases WHERE purchase_id=?";
        return CrudUtil.executeUpdate(sql, id);
    }

    @Override
    public Purchases get(Integer id) {
        String sql = "SELECT * FROM Purchases WHERE purchase_id=?";
        ResultSet rst = CrudUtil.executeQuery(sql);
        Purchases purchase = new Purchases();

        try {
            if (rst.next()) {
                purchase.setId(rst.getInt("purchase_id"));
                purchase.setDescription(rst.getString("description"));
                purchase.setItem(new ItemDAO().get(rst.getInt("item_id")));
                purchase.setQuantity(rst.getInt("quantity"));
                purchase.setPurchasePrice(rst.getDouble("purchase_price"));
                purchase.setDatePurchased(rst.getTimestamp("date_purchased"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchase;
    }

    @Override
    public List<Purchases> getAll() {
        String sql = "SELECT * FROM Purchases";
        List<Purchases> purchases = new ArrayList<>();

        ResultSet rst = CrudUtil.executeQuery(sql);

        while (true) {
            try {
                if (!rst.next()) break;
                Purchases purchase = new Purchases();
                purchase.setId(rst.getInt("purchase_id"));
                purchase.setDescription(rst.getString("description"));
                purchase.setItem(new ItemDAO().get(rst.getInt("item_id")));
                purchase.setQuantity(rst.getInt("quantity"));
                purchase.setPurchasePrice(rst.getDouble("purchase_price"));
                purchase.setDatePurchased(rst.getTimestamp("date_purchased"));
                purchases.add(purchase);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return purchases;
    }
}

class SalesDAO implements DAO<Sales, Integer> {
    @Override
    public int add(Sales sales) {
        String sql = "INSERT INTO Sales (item_id, date_sold, quantity) VALUES (?, ?, ?)";
        return CrudUtil.executeUpdate(sql,
                sales.getItem().getId(),
                sales.getDateSold(),
                sales.getQuantity()
        );
    }

    @Override
    public int update(Sales sales) {
        String sql = "UPDATE Sales SET item_id, quantity=? WHERE sales_id=?";
        return CrudUtil.executeUpdate(sql,
                sales.getItem().getId(),
                sales.getQuantity(),
                sales.getId()
        );
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Sales WHERE sales_id=?";
        return CrudUtil.executeUpdate(sql, id);
    }

    @Override
    public Sales get(Integer id) {
        String sql = "SELECT * FROM Sales WHERE sales_id=?";
        Sales sales = new Sales();
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        try {
            if (!rst.next()) return null ;
            sales.setId(rst.getInt("sales_id"));
            sales.setItem(new ItemDAO().get(rst.getInt("item_id")));
            sales.setDateSold(rst.getTimestamp("date_sold"));
            sales.setQuantity(rst.getInt("quantity"));
            return sales;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public List<Sales> getAll() {
        String sql = "SELECT * FROM Sales";
        List<Sales> sales = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql);

        while(true) {
            try {
                if (!rst.next()) break;
                Sales sale = new Sales();
                sale.setId(rst.getInt("sales_id"));
                sale.setItem(new ItemDAO().get(rst.getInt("item_id")));
                sale.setDateSold(rst.getTimestamp("date_sold"));
                sale.setQuantity(rst.getInt("quantity"));
                sales.add(sale);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sales;
    }
}

class UserDAO implements DAO<User, Integer> {

    @Override
    public int add(User user) {
        String sql = "INSERT INTO Users (name, password) VALUES (?, ?)";

        return CrudUtil.executeUpdate(sql, user.getName(), user.getPassword());
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE Users SET name=?, password=? WHERE user_id=?";
        return CrudUtil.executeUpdate(sql, user.getName(), user.getPassword(), user.getId());
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM Users WHERE user_id=?";
        return CrudUtil.executeUpdate(sql, id);
    }

    @Override
    public User get(Integer id) {
        String sql = "SELECT * FROM Users WHERE user_id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        User user = new User();
        try {
            if (rst.next()) {
                user.setId(rst.getInt("user_id"));
                user.setName(rst.getString("name"));
                user.setPassword(rst.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getByName(String name) {
        String sql = "SELECT * FROM Users WHERE name=?";
        ResultSet rst = CrudUtil.executeQuery(sql, name);
        User user = new User();
        try {
            if (rst.next()) {
                user.setId(rst.getInt("user_id"));
                user.setName(rst.getString("name"));
                user.setPassword(rst.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try{
            ResultSet rst1 = CrudUtil.executeQuery(sql);

            while (rst1.next()) {
                User user = new User(
                        rst1.getInt("user_id"),
                        rst1.getString("name"),
                        rst1.getString("password")
                );
                users.add(user);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }
}
