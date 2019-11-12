public class Parrot implements Animal {
    private String stringWithLastDate = "";

    public String Answer(String word) throws BadQuestionException {
        Answer<String> text = new Answer<>(word);
        Answer<Emoji> emoji = new Answer<>(new Emoji("o(>ω<)o"));
        if ("Popka".equals(word)) {
            throw new BadQuestionException("is not a clever guy!");
        }
        if (word.contains("beautiful")) {
            return emoji.GetAnswer().ToString();
        }
        return text.GetAnswer();
    }

    @Override
    public String getName() {
        return "Parrot";
    }

    @Override
    public int compareTo(Animal animal) {
        return 0;
    }

    @Override
    public void changeStringWithLastDate(String change_string) {
        stringWithLastDate = change_string;
    }

    @Override
    public String getStringWithLastDate() {
        return stringWithLastDate;
    }

}
