import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Chat implements ChatWindow.ChatListener {
    private List<Animal> list = new ArrayList();
    private LocalDateTime localTime = LocalDateTime.now();
    private DateTimeFormatter timeFormat =
            DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
    ChatWindow chatwindow;
    int DOG_OR_NOT;

    public Chat() {
        chatwindow = new ChatWindow();
        chatwindow.setListener(this);
        chatwindow.setVisible(true);
        this.list.add(new Dog());
        this.list.add(new Cat());
        this.list.add(new Parrot());
    }

    public String answer(String word, int ind) {
                 String ans = "";
            try {
                ans = (((Animal) list.get(ind)).Answer(word));
            } catch (BadQuestionException new_word) {
                return (new_word.GetMsg());
            }
            return ans;
    }

    @Override
    public ArrayList<String> sortByLastDate() {
        List<Animal> animalByDate = new ArrayList<>(list);
        animalByDate.sort(Comparator.comparing(Animal::getStringWithLastDate));
        ArrayList<String> namesByDate = new ArrayList<String>();
        for (int i = animalByDate.size() - 1; i >= 0; i--) {
            namesByDate.add(animalByDate.get(i).getName());
        }
        return namesByDate;
    }

    @Override
    public ArrayList<String> sortByAlphabet() {
        List<Animal> animalByAlphabet = new ArrayList<>(list);
        animalByAlphabet.sort(Comparator.comparing(Animal::getName));
        ArrayList<String> namesByAlphabet = new ArrayList<String>();
        for (Animal it : animalByAlphabet) {
            namesByAlphabet.add(it.getName());
        }
        return namesByAlphabet;
    }


    @Override
    public void sendAnswer(String str, int ind) {
        localTime = LocalDateTime.now();
        chatwindow.getVectorMessage().add("[" +
                localTime.format(timeFormat) + "] me: " + chatwindow.getField().getText());
        chatwindow.getVectorMessage().add("[" +
                localTime.format(timeFormat) + "] " + list.get(ind).getName() + ": " + answer(str, ind));
        if (ind == 0) {
            chatwindow.getVectorDog().add(chatwindow.getVectorMessage().get(chatwindow.getVectorMessage().size() - 2));
            chatwindow.getVectorDog().add(chatwindow.getVectorMessage().get(chatwindow.getVectorMessage().size() - 1));
            list.get(ind).changeStringWithLastDate(chatwindow.getVectorDog().get(chatwindow.getVectorDog().size() - 1));
        }
        if (ind == 1) {
            chatwindow.getVectorCat().add(chatwindow.getVectorMessage().get(chatwindow.getVectorMessage().size() - 2));
            chatwindow.getVectorCat().add(chatwindow.getVectorMessage().get(chatwindow.getVectorMessage().size() - 1));
            list.get(ind).changeStringWithLastDate(chatwindow.getVectorCat().get(chatwindow.getVectorCat().size() - 1));
        }
        if (ind == 2) {
            chatwindow.getVectorParrot().add(chatwindow.getVectorMessage().get(chatwindow.getVectorMessage().size() - 2));
            chatwindow.getVectorParrot().add(chatwindow.getVectorMessage().get(chatwindow.getVectorMessage().size() - 1));
            list.get(ind).changeStringWithLastDate(chatwindow.getVectorParrot().get(chatwindow.getVectorParrot().size() - 1));
        }
    }
}




