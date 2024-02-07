public class Products {
    private String emri;
    private double qmimi;
    private boolean bestSeller;
    private String emriRestaurants;
    public Products(String emri, double qmimi,boolean bestSeller,String emriRestaurants) {
        this.emri = emri;
        this.qmimi = qmimi;
        this.bestSeller=bestSeller;
        this.emriRestaurants=emriRestaurants;
    }

    public String getEmriRestaurant() {
        return emriRestaurants;
    }

    public void setEmriRestaurant(String emriRestaurants) {
        this.emriRestaurants = emriRestaurants;
    }

    public boolean getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public String getName() {
        return emri;
    }

    public double getPrice() {
        return qmimi;
    }

    char euros = '€';

    public String toStringShort(){
        return emri+" "+qmimi+euros;
    }

    @Override
    public String toString(){
        return emri+" "+qmimi+euros+" "+(bestSeller?"BEST SELLER":"");
    }

}

