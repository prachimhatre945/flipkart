package com.example.flipkart.Utilities;
import com.example.flipkart.Entity.Product;
import org.apache.poi.ss.usermodel.*;
        import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {

    public static ByteArrayInputStream productsToExcel(List<Product> products) throws IOException {
        String[] HEADERs = {"ID", "Name", "Brand", "Price", "Stock", "Category"};
        String SHEET = "Products";

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            // Data
            int rowIdx = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getBrand());
                row.createCell(3).setCellValue(product.getPrice());
                row.createCell(4).setCellValue(product.getStock());
//                row.createCell(5).setCellValue(product.getCategory());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
