package test.attprog.carlo.androidgcm.messages;

/**
 * Created by Carlo on 07/02/2016.
 */
public class IncomingRegistration {

    private String accountMail;
    private String registrationId;

    public IncomingRegistration(String accountMail, String registrationId) {
        this.accountMail = accountMail;
        this.registrationId = registrationId;
    }

    public String getAccountMail() {
        return accountMail;
    }

    public void setAccountMail(String accountMail) {
        this.accountMail = accountMail;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public String toString() {
        return "IncomingRegistration{" +
                "accountMail='" + accountMail + '\'' +
                ", registrationId='" + registrationId + '\'' +
                '}';
    }
}
