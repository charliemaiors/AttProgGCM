package test.attprog.carlo.androidgcm.messages;

/**
 * Created by Carlo on 29/11/2015.
 */
public abstract class AbstractMessage {

    private String to;
    private String collapse_key;
    private Long time_to_live;
    private boolean delay_when_idle;

    public AbstractMessage(String to, String collapse_key, Long time_to_live, boolean delay_when_idle) {
        this.to = to;
        this.collapse_key = collapse_key;
        this.time_to_live = time_to_live;
        this.delay_when_idle = delay_when_idle;
    }

    public AbstractMessage() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCollapse_key() {
        return collapse_key;
    }

    public void setCollapse_key(String collapse_key) {
        this.collapse_key = collapse_key;
    }

    public Long getTime_to_live() {
        return time_to_live;
    }

    public void setTime_to_live(Long time_to_live) {
        this.time_to_live = time_to_live;
    }

    public boolean isDelay_when_idle() {
        return delay_when_idle;
    }

    public void setDelay_when_idle(boolean delay_when_idle) {
        this.delay_when_idle = delay_when_idle;
    }

}
