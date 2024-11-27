import java.util.ArrayList;
import java.util.List;

public class Laba6Number4 {
    /*
    Command превращает запросы в объекты, позволяя передавать
    их как аргументы, ставить в очередь или логировать.
    Паттерн инкапсулирует действия в виде объектов.

    Для реализации отмены/повтора действий (Undo/Redo).
    Когда нужно передавать операции между объектами.
    Для задач очередей или планировщиков.
    */
    public static void main(String[] args) {
        MacroCommand macro = new MacroCommand();

        macro.addCommand(new AddObjectCommand("Дерево"));
        macro.addCommand(new ChangeTerrainCommand("Горы"));
        macro.addCommand(new RemoveObjectCommand("Камень"));

        macro.execute();
    }
}

interface Command {
    void execute();
}

class AddObjectCommand implements Command {
    private final String object;

    public AddObjectCommand(String object) {
        this.object = object;
    }

    @Override
    public void execute() {
        System.out.println("Добавлен объект: " + object);
    }
}

class RemoveObjectCommand implements Command {
    private final String object;

    public RemoveObjectCommand(String object) {
        this.object = object;
    }

    @Override
    public void execute() {
        System.out.println("Удалён объект: " + object);
    }
}

class ChangeTerrainCommand implements Command {
    private final String terrainType;

    public ChangeTerrainCommand(String terrainType) {
        this.terrainType = terrainType;
    }

    @Override
    public void execute() {
        System.out.println("Террейн изменён на: " + terrainType);
    }
}

class MacroCommand implements Command {
    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command cmd) {
        commands.add(cmd);
    }

    @Override
    public void execute() {
        System.out.println("Выполнение макрокоманды:");
        for (Command cmd : commands) {
            cmd.execute();
        }
    }
}

