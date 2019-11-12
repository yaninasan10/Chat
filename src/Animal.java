public interface Animal extends Comparable<Animal> {
    String Answer(String word) throws BadQuestionException;

    String getName();

    int compareTo(Animal animal);

    void changeStringWithLastDate(String change_string);

    String getStringWithLastDate();
}
