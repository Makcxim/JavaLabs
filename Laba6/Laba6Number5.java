import java.util.HashMap;
import java.util.Map;

public class Laba6Number5 {
    /*
    Flyweight минимизирует использование памяти путем
    совместного использования множества мелких объектов.
    Паттерн извлекает общие свойства в отдельные объекты,
    чтобы избежать дублирования.

    Когда в приложении есть множество объектов, имеющих общие свойства.
    Для оптимизации производительности и памяти в системах с ограниченными ресурсами.
    */
    public static void main(String[] args) {
        ResourceCache cache = new ResourceCache();

        Resource texture1 = cache.getResource("texture", "grass.png");
        Resource texture2 = cache.getResource("texture", "grass.png"); // Использует кэш
        Resource sound1 = cache.getResource("sound", "explosion.wav");
        Resource sound2 = cache.getResource("sound", "explosion.wav"); // Использует кэш

        Resource texture3 = cache.getResource("texture", "water.png");
    }
}

interface Resource {
    void load();
}

class Texture implements Resource {
    private final String filename;

    public Texture(String filename) {
        this.filename = filename;
        load();
    }

    @Override
    public void load() {
        System.out.println("Загрузка текстуры: " + filename);
    }
}

class Sound implements Resource {
    private final String filename;

    public Sound(String filename) {
        this.filename = filename;
        load();
    }

    @Override
    public void load() {
        System.out.println("Загрузка звука: " + filename);
    }
}

class ResourceCache {
    private final Map<String, Resource> resources = new HashMap<>();

    public Resource getResource(String type, String filename) {
        String key = type + ":" + filename;
        if (resources.containsKey(key)) {
            System.out.println("Использование кэшированного ресурса: " + key);
            return resources.get(key);
        } else {
            Resource resource;
            if (type.equalsIgnoreCase("texture")) {
                resource = new Texture(filename);
            } else if (type.equalsIgnoreCase("sound")) {
                resource = new Sound(filename);
            } else {
                throw new IllegalArgumentException("Неизвестный тип ресурса: " + type);
            }
            resources.put(key, resource);
            return resource;
        }
    }
}

