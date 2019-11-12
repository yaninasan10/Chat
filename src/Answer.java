public class Answer<T> {
    private T answer;

    Answer(T new_answer) {
        answer = new_answer;
    }

    T GetAnswer() {
        return answer;
    }
}
