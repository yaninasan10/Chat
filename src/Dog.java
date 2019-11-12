public class Dog implements Animal {
    private String stringWithLastDate = "";

    public String Answer(String word) throws BadQuestionException {
        Answer<String> text = new Answer<>(word);
        Answer<Emoji> emoji = new Answer<>(new Emoji("(๑˘︶˘๑)"));
        if (word.contains("Bad boy!")) {
            throw new BadQuestionException("Bad human!");
        } else if (word.contains("Good boy!")) {
            return emoji.GetAnswer().ToString();
        } else return (text.GetAnswer() + "woof");
    }

    @Override
    public String getName() {
        return "Dog";
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
