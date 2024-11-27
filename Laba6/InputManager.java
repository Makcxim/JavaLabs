import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InputManager {
    private static InputManager instance;
    private final Map<String, String> actionKeyMap;
    private final String logFile = "input_log.txt";

    private InputManager() {
        actionKeyMap = new HashMap<>();
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public void registerKeyBinding(String action, String key) {
        actionKeyMap.put(action, key);
    }

    public String getKeyForAction(String action) {
        return actionKeyMap.get(action);
    }

    public void processInput(String keyPressed) {
        String action = null;
        for (Map.Entry<String, String> entry : actionKeyMap.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(keyPressed)) {
                action = entry.getKey();
                break;
            }
        }
        if (action != null) {
            logInput(action, keyPressed);
        } else {
            System.out.println("Нет действия для клавиши: " + keyPressed);
        }
    }

    private void logInput(String action, String key) {
        String logEntry = "Действие: " + action + ", Клавиша: " + key + ", Время: " + LocalDateTime.now();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(logEntry);
    }
}
