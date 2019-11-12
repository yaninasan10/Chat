public class Cat implements Animal {
    private String stringWithLastDate = "";

    public String Answer(String word) throws BadQuestionException {
        Answer<String> text = new Answer<>(word);
        Answer<Emoji> emoji = new Answer<>(new Emoji("ʕ ᵔᴥᵔ ʔ"));
        if ("Stupid animal!".equals(word)) {
            throw new BadQuestionException("Stupid huCat!");
        }
        if (word.contains("food")) {
            return emoji.GetAnswer().ToString();
        }
        return (text.GetAnswer() + "meow");
    }



    @Override
    public String getName() {
        return "Cat";
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

