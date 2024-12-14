import java.util.ArrayList;
import java.util.List;

public class Laba7Number2 {
    public static void main(String[] args) {
        ConsoleNotesView view = new ConsoleNotesView();
        NotesPresenter presenter = new NotesPresenter(view);
        view.setPresenter(presenter);

        presenter.addNote("Заголовок 1", "Содержимое 1");
        presenter.addNote("Заголовок 2", "Содержимое 2");

        presenter.editNote(0, "Новый заголовок 1", "Обновленное содержимое 1");

        presenter.deleteNote(1);
    }
}

class NoteModel {
    private String title;
    private String content;

    public NoteModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

interface INotesView {
    void displayNotes(List<NoteModel> noteModels);
    void setPresenter(NotesPresenter presenter);
}

class ConsoleNotesView implements INotesView {

    @Override
    public void displayNotes(List<NoteModel> noteModels) {
        System.out.println("Список заметок:");
        for (int i = 0; i < noteModels.size(); i++) {
            System.out.println(i + ". " + noteModels.get(i).getTitle() + " - " + noteModels.get(i).getContent());
        }
        System.out.println();
    }

    @Override
    public void setPresenter(NotesPresenter presenter) {
    }
}

class NotesPresenter {
    private final INotesView view;
    private final List<NoteModel> noteModels;

    public NotesPresenter(INotesView view) {
        this.view = view;
        this.noteModels = new ArrayList<>();
    }

    public void addNote(String title, String content) {
        noteModels.add(new NoteModel(title, content));
        updateView();
    }

    public void editNote(int index, String newTitle, String newContent) {
        if (index >= 0 && index < noteModels.size()) {
            NoteModel n = noteModels.get(index);
            n.setTitle(newTitle);
            n.setContent(newContent);
            updateView();
        }
    }

    public void deleteNote(int index) {
        if (index >= 0 && index < noteModels.size()) {
            noteModels.remove(index);
            updateView();
        }
    }

    private void updateView() {
        view.displayNotes(noteModels);
    }
}
