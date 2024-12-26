import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Интерфейс Flyweight (общий для всех фигур)
interface UMLShape {
    void draw(Graphics g, int x, int y, int width, int height, String text);
}

// прямоугольник
class ClassBox implements UMLShape {
    private final Color color;
    private final Font font;

    public ClassBox(Color color, Font font) {
        this.color = color;
        this.font = font;
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height, String text) {
        Graphics2D g2 = (Graphics2D) g;
        Stroke originalStroke = g2.getStroke(); // исходный стиль

        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        // фикс того что линии окантовки широкими были, ставим им ширину в 1
        g2.setStroke(new BasicStroke(1));
        g.drawRect(x, y, width, height);

        g.setFont(font);
        String[] lines = text.split("\\n", 2);
        int lineHeight = g.getFontMetrics().getHeight();

        //  заголовок
        if (lines.length > 0) {
            g.drawString(lines[0], x + 5, y + 20);
        }

        // линия под заголовком
        g.drawLine(x, y + 25, x + width, y + 25);

        // текст в теле прямоугольника
        if (lines.length > 1) {
            String[] bodyLines = lines[1].split("\\n");
            for (int i = 0; i < bodyLines.length; i++) {
                g.drawString(bodyLines[i], x + 5, y + 40 + i * lineHeight);
            }
        }

        // восстанавливаем стиль обратно
        g2.setStroke(originalStroke);
    }
}


// линия
class Line implements UMLShape {
    private final Color color;
    public final boolean isVertical;
    private final boolean hasArrow;
    private final int thickness;

    public Line(Color color, boolean isVertical, boolean hasArrow, int thickness) {
        this.color = color;
        this.isVertical = isVertical;
        this.hasArrow = hasArrow;
        this.thickness = thickness;
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height, String text) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));

        if (isVertical) {
            g2.drawLine(x, y, x, y + height);
            if (hasArrow) {
                int arrowSize = 10;
                g2.drawLine(x, y + height, x - arrowSize, y + height - arrowSize);
                g2.drawLine(x, y + height, x + arrowSize, y + height - arrowSize);
            }
            return;
        }

        g2.drawLine(x, y, x + width, y);
        if (hasArrow) {
            int arrowSize = 10;
            g2.drawLine(x + width, y, x + width - arrowSize, y - arrowSize);
            g2.drawLine(x + width, y, x + width - arrowSize, y + arrowSize);
        }
    }
}


// Фабрика Flyweight
class UMLShapeFactory {
    private static final Map<String, UMLShape> shapes = new HashMap<>();

    public static UMLShape getClassBox(Color color, Font font) {
        String key = "ClassBox:" + color.toString() + ":" + font.toString();
        UMLShape shape = shapes.get(key);
        if (shape == null) {
            shape = new ClassBox(color, font);
            shapes.put(key, shape);
            System.out.println("Создаётся новый ClassBox: " + key);
        }
        return shape;
    }

    public static UMLShape getLine(Color color, boolean isVertical, boolean hasArrow, int thickness) {
        String key = "Line:" + color.toString() + ":" + isVertical + ":" + hasArrow + ":" + thickness;
        UMLShape shape = shapes.get(key);
        if (shape == null) {
            shape = new Line(color, isVertical, hasArrow, thickness);
            shapes.put(key, shape);
            System.out.println("Создаётся новая Line: " + key);
        }
        return shape;
    }
}


// Хранение данных о нарисованном элементе
class DrawInstruction {
    UMLShape shape;
    int x, y, width, height;
    String text;

    public DrawInstruction(UMLShape shape, int x, int y, int width, int height, String text) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }
}


// Панель для рисования (View)
class UMLPanel extends JPanel {
    private final List<DrawInstruction> instructions = new ArrayList<>();
    private DrawInstruction selectedInstruction = null;
    private int offsetX, offsetY;

    public UMLPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (DrawInstruction instr : instructions) {
                    if (instr.width == 0) {
                        // Линии
                        if (instr.shape instanceof Line line) {
                            if (line.isVertical) {
                                if (e.getX() >= instr.x - 5 && e.getX() <= instr.x + 5 &&
                                        e.getY() >= instr.y && e.getY() <= instr.y + instr.height) {
                                    selectedInstruction = instr;
                                    offsetX = e.getX() - instr.x;
                                    offsetY = e.getY() - instr.y;
                                    break;
                                }
                            }
                            if (e.getY() >= instr.y - 5 && e.getY() <= instr.y + 5 &&
                                    e.getX() >= instr.x && e.getX() <= instr.x + instr.width) {
                                selectedInstruction = instr;
                                offsetX = e.getX() - instr.x;
                                offsetY = e.getY() - instr.y;
                                break;
                            }
                        }
                    }
                    // для прямоугольников
                    if (e.getX() >= instr.x && e.getX() <= instr.x + instr.width &&
                            e.getY() >= instr.y && e.getY() <= instr.y + instr.height) {
                        selectedInstruction = instr;
                        offsetX = e.getX() - instr.x;
                        offsetY = e.getY() - instr.y;
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedInstruction = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedInstruction != null) {
                    selectedInstruction.x = e.getX() - offsetX;
                    selectedInstruction.y = e.getY() - offsetY;
                    repaint();
                }
            }
        });
    }

    public void addElement(UMLShape shape, int x, int y, int width, int height, String text) {
        instructions.add(new DrawInstruction(shape, x, y, width, height, text));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DrawInstruction instr : instructions) {
            instr.shape.draw(g, instr.x, instr.y, instr.width, instr.height, instr.text);
        }
    }
}


// Главное окно (Controller)
class Laba10Number1 extends JFrame {
    public Laba10Number1() {
        super("UML dada");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        UMLPanel panel = new UMLPanel();
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addClassButton = new JButton("Добавить класс");
        addClassButton.addActionListener(e -> {
            JTextField titleField = new JTextField();
            JTextArea bodyArea = new JTextArea(5, 20);
            JComboBox<String> colorPicker = new JComboBox<>(new String[]{"Red", "Blue", "Green"});

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            inputPanel.add(new JLabel("Заголовок:"));
            inputPanel.add(titleField);
            inputPanel.add(new JLabel("Тело:"));
            inputPanel.add(new JScrollPane(bodyArea));
            inputPanel.add(new JLabel("Цвет:"));
            inputPanel.add(colorPicker);

            int result = JOptionPane.showConfirmDialog(this, inputPanel, "Новый класс", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String title = titleField.getText();
                String body = bodyArea.getText();
                String colorChoice = (String) colorPicker.getSelectedItem();
                Color color = switch (colorChoice) {
                    case "Red" -> Color.RED;
                    case "Blue" -> Color.BLUE;
                    case "Green" -> Color.GREEN;
                    default -> Color.WHITE;
                };

                String text = title + "\n" + body;
                Font font = new Font("Arial", Font.PLAIN, 14);
                int textWidth = Math.max(getFontMetrics(font).stringWidth(title), getFontMetrics(font).stringWidth(body)) + 20;
                int textHeight = (text.split("\\n").length * getFontMetrics(font).getHeight()) + 20;

                UMLShape classBox = UMLShapeFactory.getClassBox(color, font);
                panel.addElement(classBox, 350, 250, Math.max(120, textWidth), Math.max(60, textHeight), text);
            }
        });
        buttonPanel.add(addClassButton);

        JButton addLineButton = new JButton("Добавить линию");
        addLineButton.addActionListener(e -> {
            JComboBox<String> lineTypePicker = new JComboBox<>(new String[]
                    {"Вертикальная", "Горизонтальная", "Вертикальная со стрелочкой", "Горизонтальная со стрелочкой"}
            );
            JSpinner thicknessSpinner = new JSpinner(new SpinnerNumberModel(7, 1, 20, 1));
            JComboBox<String> colorPicker = new JComboBox<>(new String[]{"Red", "Blue", "Green"});

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            inputPanel.add(new JLabel("Тип линии:"));
            inputPanel.add(lineTypePicker);
            inputPanel.add(new JLabel("Ширина линии:"));
            inputPanel.add(thicknessSpinner);
            inputPanel.add(new JLabel("Цвет:"));
            inputPanel.add(colorPicker);

            int result = JOptionPane.showConfirmDialog(this, inputPanel, "Новая линия", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String lineType = (String) lineTypePicker.getSelectedItem();
                int thickness = (int) thicknessSpinner.getValue();
                String colorChoice = (String) colorPicker.getSelectedItem();
                Color color = switch (colorChoice) {
                    case "Red" -> Color.RED;
                    case "Blue" -> Color.BLUE;
                    case "Green" -> Color.GREEN;
                    default -> Color.BLACK;
                };

                boolean isVertical = lineType.contains("Вертикальная");
                boolean hasArrow = lineType.contains("со стрелочкой");

                UMLShape line = UMLShapeFactory.getLine(color, isVertical, hasArrow, thickness);
                if (isVertical) {
                    panel.addElement(line, 400, 100, 0, 200, "");
                } else {
                    panel.addElement(line, 200, 300, 200, 0, "");
                }
            }
        });
        buttonPanel.add(addLineButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Laba10Number1::new);
    }
}
