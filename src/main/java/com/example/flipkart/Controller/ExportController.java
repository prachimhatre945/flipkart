package com.example.flipkart.Controller;
import com.example.flipkart.Entity.Product;
import com.example.flipkart.Repository.ProductRepository;
import com.example.flipkart.Service.ProductService;
import com.example.flipkart.Utilities.CSVHelper;
import com.example.flipkart.Utilities.ExcelHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/download")
public class ExportController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping()
    public void downloadProducts(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=products.csv");

        List<Product> productList = productRepository.findAll();

        PrintWriter writer = response.getWriter();
        CSVHelper.writeProductsToCSV(writer, productList);
    }

    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=products.xlsx");

        List<Product> productList = productRepository.findAll();
        ByteArrayInputStream excelStream = ExcelHelper.productsToExcel(productList);

        org.apache.commons.io.IOUtils.copy(excelStream, response.getOutputStream());
        response.flushBuffer();
    }


}
