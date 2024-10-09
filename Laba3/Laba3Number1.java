import java.util.Arrays;
import java.util.Comparator;

public class Laba3Number1 {
    public static void main(String[] args) {
        Product product1 = new Product("Grocery", "001", "Apple", "USA", 1.50);
        Product product2 = new Product("Grocery", "002", "Banana", "Ecuador", 1.20);
        Product product3 = new Product("Electronics", "100", "Smartphone", "China", 250.00);

        GroupSupermarket group = new GroupSupermarket(new Product[]{product1, product2, product3});

        Product product4 = new Product("Electronics", "101", "Laptop", "Japan", 800.00);
        group.addProduct(product4);

        group.removeProductByCode("002");

        group.sortProductsByName();

        System.out.println(group);
    }
}

class GroupSupermarket {
    private static int counter = 0;
    private final int uniqueNumber;
    private Product[] products;

    public GroupSupermarket() {
        this.uniqueNumber = counter++;
        this.products = new Product[0];
    }

    public GroupSupermarket(Product[] products) {
        this.uniqueNumber = counter++;
        this.products = products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Product[] getProducts() {
        return this.products;
    }

    public void setProduct(Product product, int index) {
        if (index >= 0 && index < products.length) {
            this.products[index] = product;
            return;
        }
        System.out.printf("Индекс должен быть в диапазоне от 0 до %d%n", products.length - 1);
    }

    public Product getProduct(int index) {
        if (index >= 0 && index < products.length) {
            return this.products[index];
        }
        System.out.printf("Индекс должен быть в диапазоне от 0 до %d%n", products.length - 1);
        return null;
    }

    public void addProduct(Product product){
        products = Arrays.copyOf(products, products.length + 1);
        products[products.length - 1] = product;
    }

    public void removeProductByCode(String code){
        products = Arrays.stream(products)
                .filter(product -> !product.getProductCode().equals(code))
                .toArray(Product[]::new);
    }

    public void sortProductsByName(){
        Arrays.sort(products, Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Уникальный номер группы: " + uniqueNumber + "\nТовары:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        return sb.toString();
    }
}

class Product {
    private String nameotdela; // Название отдела
    private String productCode; // Код товара
    private String name; // Наименование товара
    private String country; // Страна-производитель
    private double retailPrice; // Розничная цена

    private String namesource; // Поставщик

    public Product(String nameotdela, String productCode, String name, String country, double retailPrice) {
        this.nameotdela = nameotdela;
        this.productCode = productCode;
        this.name = name;
        this.country = country;
        this.retailPrice = retailPrice;
    }

    // Геттеры и сеттеры
    public String getNameotdela() {
        return nameotdela;
    }

    public void setNameotdela(String nameotdela) {
        this.nameotdela = nameotdela;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getNamesource() {
        return namesource;
    }

    public void setNamesource(String namesource) {
        this.namesource = namesource;
    }

    @Override
    public String toString() {
        return String.format("Отдел: %s, Код товара: %s, Наименование: %s, Страна: %s, Цена: %.2f, Поставщик: %s",
                nameotdela, productCode, name, country, retailPrice, namesource != null ? namesource : "Не указан");
    }
}
