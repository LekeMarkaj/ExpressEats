public class Bank {

    private long id;
    private String cardNumber;
    private String balance;

    public Bank(long id, String cardNumber, String balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}

