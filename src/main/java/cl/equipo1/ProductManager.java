package cl.equipo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductManager {
private List<Product> products = new ArrayList<>();

    public Product addProduct(String name, String description, double price) {
        Product product = new Product(name, description, price);
        products.add(product);
        return product;
    }

    public boolean updateProduct(UUID id, String name, String description, double price) {
        Optional<Product> productOpt = findProductById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
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

    public Optional<Product> findProductById(UUID id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
