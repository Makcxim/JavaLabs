import java.util.ArrayList;
import java.util.List;

public class Laba6Number3 {
    /*
    Observer используется для оповещения нескольких
    объектов о произошедших изменениях в другом объекте.
    Когда объект должен уведомлять других о своем состоянии.
    Для реализации механизмов подписки/уведомления.
    */
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();

        Observer sub1 = new Subscriber("Иван");
        Observer sub2 = new Subscriber("Мария");

        publisher.addObserver(sub1);
        publisher.addObserver(sub2);

        publisher.publish("Паттерн Наблюдатель в действии!");

        publisher.removeObserver(sub1);

        publisher.publish("Новая статья о Java.");
    }
}

interface Observer {
    void update(String article);
}

class Subscriber implements Observer {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String article) {
        System.out.println(name + " получил новую статью: " + article);
    }
}

class NewsPublisher {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void publish(String article) {
        System.out.println("Публикуется статья: " + article);
        notifyObservers(article);
    }

    private void notifyObservers(String article) {
        for (Observer observer : observers) {
            observer.update(article);
        }
    }
}
