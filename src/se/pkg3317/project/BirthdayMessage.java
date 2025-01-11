package se.pkg3317.project;


public class BirthdayMessage extends Decorator {

   Message message ;

    public BirthdayMessage(Message message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.getMessage() + "Happpy Birthday User!!!";
    }
}
