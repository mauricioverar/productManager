# 🧪 Pruebas Unitarias para `ProductManager`

Este conjunto de pruebas valida el comportamiento del servicio `ProductManager`, responsable de gestionar productos (agregar, actualizar, eliminar y listar). Las pruebas utilizan **JUnit 5** y **Hamcrest Matchers** para validaciones expresivas y legibles.


## 📁 Estructura del Proyecto

```
src/
└── test/
    └── java/
        └── cl.equipo1/
            └── ProductManagerTest.java
```


## ✅ Funcionalidades Probadas

| Prueba | Descripción | Tipo |
|--------|-------------|------|
| `agregarProducto_deberiaAgregarProductoALaLista` | Verifica que un producto se crea correctamente y se agrega a la lista | Básica |
| `actualizarProducto_deberiaModificarDatosDeUnProducto` | Verifica que los datos de un producto existente se actualizan correctamente | Básica |
| `eliminarProducto_deberiaEliminarUnProducto` | Verifica que un producto se elimina correctamente de la lista | Básica |
| `agregarProducto_deberiaAgregarVariacionesDePrecios` | Prueba parametrizada que valida distintos valores de precio (incluyendo asunciones condicionales) | Parametrizada + Assumptions |
| `afirmacionesConHamcrest` | Muestra el uso de matchers de Hamcrest para validaciones más expresivas | Hamcrest |


## 🔍 Características Destacadas

### 🧩 Pruebas Parametrizadas
Se utiliza `@ParameterizedTest` con `@ValueSource` para probar múltiples valores de precio en una sola prueba:

```java
@ParameterizedTest
@ValueSource(doubles = { 0.0, -10.0, 1000000.0 })
void agregarProducto_deberiaAgregarVariacionesDePrecios(double price) { ... }
```

### 🤔 Assumptions (Asumpciones)
Se usan `assumingThat` para ejecutar bloques condicionales solo si ciertas condiciones se cumplen — útil para evitar fallas en casos que no aplican:

```java
assumingThat(price >= 0, () -> {
    Product product = manager.addProduct("Teclado", "Mecánico", price);
    assertEquals(price, product.getPrice(), 0.001);
});
```

> ⚠️ Nota: En este ejemplo, los precios negativos **no** se consideran válidos, por lo que el bloque de aserción solo se ejecuta si el precio es ≥ 0.

### 🎯 Hamcrest Matchers
Se incluyen aserciones con Hamcrest para mejorar la legibilidad y expresividad:

```java
assertThat(allProducts, hasSize(2));
assertThat(allProducts, hasItems(product1, product2));
assertThat(allProducts, everyItem(hasProperty("price", greaterThanOrEqualTo(0.0))));
```


## 🛠️ Requisitos

- Java 8+
- JUnit 5
- Hamcrest Matchers (usualmente incluido con JUnit o como dependencia separada)

### Dependencias Maven (si aplica)

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest</artifactId>
    <version>2.2</version>
    <scope>test</scope>
</dependency>
```


## ▶️ Ejecutar las Pruebas

### Con Maven

```bash
mvn test
```

### Con Gradle

```bash
gradle test
```

### Desde tu IDE

Ejecuta la clase `ProductManagerTest.java` como prueba JUnit.


## 📌 Notas Importantes

- Asegúrate de que las clases `ProductManager` y `Product` existan en el paquete `cl.equipo1`.
- `Product` debe tener los métodos `getName()`, `getPrice()`, `getDescription()`, `getId()`, y constructores/getters/setters adecuados.
- `ProductManager` debe implementar:
  - `addProduct(String name, String description, double price)`
  - `updateProduct(String id, String name, String description, double price)`
  - `deleteProduct(String id)`
  - `findProductById(String id)`
  - `getAllProducts()`


Y una clase `Product` con campos `id`, `name`, `description`, `price`, y métodos getter/setter.


## 📊 Cobertura de Pruebas

Estas pruebas cubren:

- ✅ Creación de productos
- ✅ Actualización de productos
- ✅ Eliminación de productos
- ✅ Listado y búsqueda
- ✅ Validación de precios (con asunciones)
- ✅ Uso de matchers avanzados (Hamcrest)


## 🤝 Contribuir

¿Quieres mejorar las pruebas?

1. Haz un fork del repositorio.
2. Crea una rama: `git checkout -b feature/mejora-pruebas`
3. Realiza tus cambios.
4. Asegúrate de que todas las pruebas pasen.
5. Haz commit y push.
6. Abre un Pull Request.


## 📄 Licencia

MIT License — ¡Libre para usar, modificar y distribuir!
