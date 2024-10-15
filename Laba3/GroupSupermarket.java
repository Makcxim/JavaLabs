import java.util.Arrays;
import java.util.Comparator;

public class GroupSupermarket {
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
