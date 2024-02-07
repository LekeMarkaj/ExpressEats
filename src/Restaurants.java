public class Restaurants {
    private String name;
    private Locations location;
    private String workHours;
    private int ratings;
    private Category category;
    private int discount;
    public Restaurants(String name, Locations location, String workHours,int ratings,Category category,int discount) {
        this.name = name;
        this.location = location;
        this.workHours = workHours;
        this.ratings=ratings;
        this.category=category;
        this.discount=discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    @Override
    public String toString(){
        return name+" "+location+" "+workHours+" "+ratings+(ratings==1?" Star":"Stars")+" "+category +" "+(discount>0?discount+"% zbritje":"");
    }
}
