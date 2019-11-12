
public class BadQuestionException extends Exception {

    private String msg;

    public BadQuestionException(String msg) {

        this.msg = msg;
    }

    public String GetMsg() {

        return this.msg;
    }


}
