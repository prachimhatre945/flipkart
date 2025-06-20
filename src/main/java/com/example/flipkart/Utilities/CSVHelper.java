package com.example.flipkart.Utilities;

import com.example.flipkart.Entity.Product;

import java.io.PrintWriter;
import java.util.List;

public class CSVHelper {
    public static void writeProductsToCSV(PrintWriter writer, List<Product> products) {
        writer.println("ID,Name,Brand,Price,Stock,Category");

        for (Product p : products) {
            writer.printf("%d,%s,%s,%.2f,%d,%s%n",
                    p.getId(),
                    p.getName(),
                    p.getBrand(),
                    p.getPrice(),
                    p.getStock(),
                    p.getCategory()
            );
        }
    }
}
