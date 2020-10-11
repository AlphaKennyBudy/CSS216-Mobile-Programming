package kz.sdu.assignments;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryAndProduct {
    public static String[] categories = {"Cosplay", "Figures", "Others"};
    private ArrayList<Category> db;

    public CategoryAndProduct() {
        db = new ArrayList<>();

        Category cosplay = new Category("Cosplay");
        db.add(cosplay);
        cosplay.addProduct("Akatsuki coat", 3234, R.drawable.akatsuki);
    }

    public List<String> getProductsName(int id) {
        List<String> res = new ArrayList<>();
        for (Product p : db.get(id).getProducts()) {
            res.add(p.getName());
        }
        return res;
    }

    public String getProductName(int categoryId, int id) {
        return db.get(categoryId).getProducts().get(id).getName();
    }

    public int getProductPrice(int categoryId, int id) {
        return db.get(categoryId).getProducts().get(id).getPrice();
    }

    public int getProductImg(int categoryId, int id) {
        return db.get(categoryId).getProducts().get(id).getImage();
    }

    private class Category {
        private ArrayList<Product> products;
        private String name;

        Category(String name) {
            this.name = name;
            products = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public ArrayList<Product> getProducts() {
            return products;
        }

        public void addProduct(String name, int price, int image) {
            products.add(new Product(name, price, image));
        }
    }

    private class Product {
        private String name;
        private int price;
        private int image;

        Product(String name, int price, int image) {
            this.name = name;
            this.price = price;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getImage() {
            return image;
        }
    }
}
