import java.util.ArrayList;
import java.util.List;

public class Laba7Number3 {
    public static void main(String[] args) {
        Budget model = new Budget();
        BudgetViewModel vm = new BudgetViewModel(model);
        ConsoleBudgetView view = new ConsoleBudgetView(vm);

        vm.setIncome(50000);
        vm.setExpenses(20000);
        vm.setExpenses(25000);
    }
}

class Budget {
    private double income;
    private double expenses;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getBalance() {
        return income - expenses;
    }
}

interface BudgetViewModelListener {
    void onBudgetChanged();
}

class BudgetViewModel {
    private final Budget model;
    private final List<BudgetViewModelListener> listeners = new ArrayList<>();

    public BudgetViewModel(Budget model) {
        this.model = model;
    }

    public double getIncome() {
        return model.getIncome();
    }

    public void setIncome(double value) {
        model.setIncome(value);
        notifyListeners();
    }

    public double getExpenses() {
        return model.getExpenses();
    }

    public void setExpenses(double value) {
        model.setExpenses(value);
        notifyListeners();
    }

    public double getBalance() {
        return model.getBalance();
    }

    // Регистрация/отписка слушателей
    public void addListener(BudgetViewModelListener listener) {
        listeners.add(listener);
    }

    public void removeListener(BudgetViewModelListener listener) {
        listeners.remove(listener);
    }

    // Уведомление всех слушателей об изменениях
    private void notifyListeners() {
        for (BudgetViewModelListener listener : listeners) {
            listener.onBudgetChanged();
        }
    }
}

class ConsoleBudgetView implements BudgetViewModelListener {
    private final BudgetViewModel viewModel;

    public ConsoleBudgetView(BudgetViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addListener(this);
        updateDisplay();
    }

    private void updateDisplay() {
        System.out.println("Управление бюджетом:");
        System.out.println("Доход: " + viewModel.getIncome());
        System.out.println("Расходы: " + viewModel.getExpenses());
        System.out.println("Баланс: " + viewModel.getBalance());
        System.out.println();
    }

    @Override
    public void onBudgetChanged() {
        updateDisplay();
    }
}
