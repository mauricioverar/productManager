package cl.equipo1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProductManagerTest {
private ProductManager manager;

    @BeforeEach
    void setUp() {
        manager = new ProductManager();
    }

    @AfterEach
    void tearDown() {
        manager = null;
    }

    // Agregar un producto
    @Test
    void agregarProducto_deberiaAgregarProductoALaLista() {
        Product product = manager.addProduct("Laptop", "High performance laptop", 1200.0);

        assertNotNull(product);
        assertEquals("Laptop", product.getName());
        assertEquals(1200.0, product.getPrice(), 0.001);
        assertNotNull(product.getId());

        List<Product> allProducts = manager.getAllProducts();
        assertTrue(allProducts.contains(product));
    }

    // Actualizar un producto
    @Test
    void actualizarProducto_deberiaModificarDatosDeUnProducto() {
        Product product = manager.addProduct("Phone", "Smartphone", 800.0);

        boolean result = manager.updateProduct(
                product.getId(),
                "Updated Phone",
                "New smartphone model",
                900.0);

        assertTrue(result);

        Product updated = manager.findProductById(product.getId()).orElse(null);
        assertNotNull(updated);
        assertEquals("Updated Phone", updated.getName());
        assertEquals("New smartphone model", updated.getDescription());
        assertEquals(900.0, updated.getPrice(), 0.001);
    }

    // Eliminar un producto
    @Test
    void eliminarProducto_deberiaEliminarUnProducto() {
        Product product = manager.addProduct("Mouse", "Wireless mouse", 20.0);

        boolean deleted = manager.deleteProduct(product.getId());

        assertTrue(deleted);
        assertFalse(manager.findProductById(product.getId()).isPresent());
        assertThat(manager.getAllProducts(), not(hasItem(product)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -10.0, 1000000.0})
    void testAddProduct_PriceVariations_ShouldBeStored(double price) {
        assumeTrue(price >= 0, "Solo se consideran precios positivos");

        Product product = manager.addProduct("Teclado", "Mecánico", price);
        assertEquals(price, product.getPrice(), 0.001);
    }

    @Test
    void testHamcrestAssertions() {
        Product product1 = manager.addProduct("Monitor", "24 pulgadas", 300.0);
        Product product2 = manager.addProduct("Teclado", "RGB", 80.0);

        List<Product> allProducts = manager.getAllProducts();

        // Usando Hamcrest
        assertThat(allProducts, hasSize(2));
        assertThat(allProducts, hasItems(product1, product2));
        assertThat(allProducts, everyItem(hasProperty("price", greaterThanOrEqualTo(0.0))));
    }
}
