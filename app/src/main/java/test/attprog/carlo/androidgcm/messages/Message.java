package test.attprog.carlo.androidgcm.messages;

/**
 * Created by Carlo on 21/01/2016.
 */
public class Message extends AbstractMessage{

    private CustomData data;

    public Message(String to, String collapse_key, Long time_to_live, boolean delay_when_idle, CustomData data) {
        super(to    , collapse_key, time_to_live, delay_when_idle);
        this.data = data;
    }

    public Message() {
    }

    public CustomData getData() {
        return data;
    }

    public void setData(CustomData data) {
        this.data = data;
    }
}
