package cl.equipo1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductManager {
private List<Product> products = new ArrayList<>();

    public Product addProduct(String name, String description, double price) {
        Product product = new Product(name, description, price);
        products.add(product);
        return product;
    }

    public boolean updateProduct(UUID id, String name, String description, double price) {
        Product product = findProductById(id);
        if (product != null) {
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(UUID id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    public Product findProductById(UUID id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null; // Si no se encuentra el producto
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
