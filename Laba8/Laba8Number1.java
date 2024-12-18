import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Laba8Number1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Отдых");
        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Dimension fieldSize = new Dimension(200, 30);

        // формула 1 типо быстрая
        JLabel formula1LabelX = new JLabel("Введите X: ");
        formula1LabelX.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField formula1InputX = new JTextField(10);
        formula1InputX.setPreferredSize(fieldSize);
        formula1InputX.setMinimumSize(fieldSize);
        formula1InputX.setMaximumSize(fieldSize);
        formula1InputX.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel formula1LabelN = new JLabel("Введите N: ");
        formula1LabelN.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField formula1InputN = new JTextField(10);
        formula1InputN.setPreferredSize(fieldSize);
        formula1InputN.setMinimumSize(fieldSize);
        formula1InputN.setMaximumSize(fieldSize);
        formula1InputN.setAlignmentX(Component.CENTER_ALIGNMENT);

        // формула как 1 но 2
        JLabel formula2LabelN = new JLabel("Введите N: ");
        formula2LabelN.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField formula2InputN = new JTextField(10);
        formula2InputN.setPreferredSize(fieldSize);
        formula2InputN.setMinimumSize(fieldSize);
        formula2InputN.setMaximumSize(fieldSize);
        formula2InputN.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel formula2LabelR = new JLabel("Введите R: ");
        formula2LabelR.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField formula2InputR = new JTextField(10);
        formula2InputR.setPreferredSize(fieldSize);
        formula2InputR.setMinimumSize(fieldSize);
        formula2InputR.setMaximumSize(fieldSize);
        formula2InputR.setAlignmentX(Component.CENTER_ALIGNMENT);

        // формула 1 X and N
        JPanel formula1Panel = new JPanel();
        formula1Panel.setLayout(new BoxLayout(formula1Panel, BoxLayout.Y_AXIS));
        formula1Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formula1Panel.add(formula1LabelX);
        formula1Panel.add(formula1InputX);
        formula1Panel.add(Box.createVerticalStrut(5));
        formula1Panel.add(formula1LabelN);
        formula1Panel.add(formula1InputN);

        // формула 2 N and R
        JPanel formula2Panel = new JPanel();
        formula2Panel.setLayout(new BoxLayout(formula2Panel, BoxLayout.Y_AXIS));
        formula2Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formula2Panel.add(formula2LabelN);
        formula2Panel.add(formula2InputN);
        formula2Panel.add(Box.createVerticalStrut(5));
        formula2Panel.add(formula2LabelR);
        formula2Panel.add(formula2InputR);

        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(formula1Panel, "formula1");
        cardPanel.add(formula2Panel, "formula2");

        CardLayout cl = (CardLayout)(cardPanel.getLayout());
        cl.show(cardPanel, "formula1");

        // переключение формул
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton formula1Button = new JRadioButton("Формула 1");
        formula1Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton formula2Button = new JRadioButton("Формула 2");
        formula2Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup formulaGroup = new ButtonGroup();
        formulaGroup.add(formula1Button);
        formulaGroup.add(formula2Button);
        formula1Button.setSelected(true);

        radioPanel.add(formula1Button);
        radioPanel.add(formula2Button);

        // считать считай посчитал
        JButton calculateButton = new JButton("Считать");
        calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // результат
        JLabel resultLabel = new JLabel("Результат: ");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel resultOutput = new JLabel("");
        resultOutput.setAlignmentX(Component.CENTER_ALIGNMENT);

        // панелька результата
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.X_AXIS));
        resultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(resultLabel);
        resultPanel.add(Box.createHorizontalStrut(5));
        resultPanel.add(resultOutput);

        // база основа панель
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(cardPanel);           // переменные
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(calculateButton);     // кнопка
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(radioPanel);          // варианты
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(resultPanel);         // результаты
        mainPanel.add(Box.createVerticalStrut(20));

        frame.add(mainPanel, BorderLayout.PAGE_START); // чтобы короче там это ну да считать кнопка не клеилась вниз

        // переключаем формулы
        formula1Button.addActionListener(e -> cl.show(cardPanel, "formula1"));
        formula2Button.addActionListener(e -> cl.show(cardPanel, "formula2"));

        // обработчик кнопки считать
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double result = 0;
                    if (formula1Button.isSelected()) {
                        // формула 1
                        double x = Double.parseDouble(formula1InputX.getText());
                        int n = Integer.parseInt(formula1InputN.getText());
                        result = -0.5;
                        double term = 1.0;
                        for (int i = 1; i <= n; i++) {
                            term *= x / i;
                            result += (i % 2 == 0) ? term : -term;
                        }
                    } else if (formula2Button.isSelected()) {
                        int n = Integer.parseInt(formula2InputN.getText());
                        int r = Integer.parseInt(formula2InputR.getText());
                        // формула 2
                        double a = 1.0;
                        for (int i = 1; i <= n; i++) {
                            for (int j = 1; j <= r; j++) {
                                result += (a * i) / (Math.pow(i, 3) + Math.pow(j, 3));
                            }
                        }
                    }
                    resultOutput.setText(String.format("%.6f", result));
                    // выводит ответ там в титле тайтл
                    frame.setTitle("АААтвет: " + String.format("%.6f", result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Введите корректные числа", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
