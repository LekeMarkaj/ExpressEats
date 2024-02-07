public class User{

    private String cardNumber;
    private String emri;
    private String mbiemri;
    private long ID;
    private String password;
    private String email;
    private Bank b = null;
    private String phoneNr;

    public User(long ID,String emri,String mbiemri,String cardNumber,String password,String email ,String phoneNr) {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.ID = ID;
        this.cardNumber=cardNumber;
        this.password=password;
        this.email = email;
        this.phoneNr=phoneNr;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public long getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}

