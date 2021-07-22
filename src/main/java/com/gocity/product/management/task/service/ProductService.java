package com.gocity.product.management.task.service;

import com.gocity.product.management.task.model.Category;
import com.gocity.product.management.task.model.Product;
import com.gocity.product.management.task.repository.CategoryRepository;
import com.gocity.product.management.task.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@Service
@Log4j2
public class ProductService {

    private final ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) throws IOException {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        readCategoryFile();
        readProductFile();
    }

    private void readCategoryFile() {
        URL resource = this.getClass().getClassLoader().getResource("categories.csv");
        try {
            assert resource != null;
            try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
                String line;
                int counter = 0;
                while ((line = br.readLine()) != null) {
                    if (counter > 0) {
                        String[] values = line.split(",");
                        categoryRepository.add(Category.builder()
                                .id(Integer.valueOf(values[0]))
                                .name(values[1])
                                .build());
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readProductFile() throws IOException {
        // get file and turn into workbook
        URL resource = this.getClass().getClassLoader().getResource("products.xlsx");
        File file = null;
        try {
            assert resource != null;
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert file != null;
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0); // save as sheet
        //evaluating cell type
        for (Row row : sheet) {
            if (row.getRowNum() > 0) {
                Product.ProductBuilder builder = Product.builder();
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    if (columnIndex > 0) {
                        try {
                            switch (columnIndex) {
                                case 1:
                                    if (cell.getStringCellValue() != null)
                                        builder.name(cell.getStringCellValue());
                                    break;
                                case 2:
                                    if (cell.getStringCellValue() != null)
                                        builder.description(cell.getStringCellValue());
                                    break;
                                case 3:
                                    String category = getCategoryName((int)cell.getNumericCellValue());
                                    builder.category(category);
                                    break;
                                case 4:
                                    if (cell.getStringCellValue() != null)
                                        builder.creationDate(cell.getStringCellValue());
                                    break;
                                case 5:
                                    if (cell.getStringCellValue() != null)
                                        builder.updateDate(cell.getStringCellValue());
                                    break;
                                case 6:
                                    if (cell.getStringCellValue() != null)
                                        builder.lastPurchasedDate(cell.getStringCellValue());
                                    break;
                            }
                        } catch (IllegalStateException e) {
                            log.info("Cell value was of unexpected type");
                        }
                    }
                }
                Product product = builder.build();
                productRepository.add(product);
                log.info("Added: {}", product);
            }
        }
    }

    public String getCategoryName(int categoryId) {
        Category category = categoryRepository.get(categoryId);
        return category.getName();
    }

    public List<Product> getProducts() {
        return productRepository.getAll();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new NullPointerException();
        }
        productRepository.add(product);
    }
}
