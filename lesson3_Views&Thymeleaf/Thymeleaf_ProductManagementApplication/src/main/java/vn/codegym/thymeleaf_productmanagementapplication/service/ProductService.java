package vn.codegym.thymeleaf_productmanagementapplication.service;

import vn.codegym.thymeleaf_productmanagementapplication.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    private static final Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Bia Hà Nội", 16000, "Lon 330ml", "Habeco"));
        products.put(2, new Product(2, "Trứng gà", 40000, "Hộp 12 quả", "Vinaco"));
        products.put(3, new Product(3, "Bánh Oreo", 22000, "Bánh quy kẹp kem socola", "Mondelez International"));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public void save(Product product) {
        products.put(product.getId(), product);
    }

    public Product findById(int id) {
        return products.get(id);
    }

    public void update(int id, Product product) {
        products.put(id, product);
    }

    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}
