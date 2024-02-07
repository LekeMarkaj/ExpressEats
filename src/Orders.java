import java.util.ArrayList;

public class Orders {
    char euros = '€';
    private int id;
    private ArrayList<Products> p;
    private int r;
    private double total;
    private String emri;
    private String location;
    private String nrTel;
    public String comment;
    public Orders(int id, ArrayList<Products> p,int r,double total,String emri,String location,String nrTel,String comment) {
        this.id = id;
        this.p = p;
        this.r=r;
        this.total=total;
        this.emri=emri;
        this.location=location;
        this.nrTel=nrTel;
        this.comment=comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Products> getP() {
        return p;
    }

    public void setP(ArrayList<Products> p) {
        this.p = p;
    }
    @Override
    public String toString(){

        StringBuilder result = new StringBuilder("\nFull Name: "+emri);
        result.append("\nAddress: ").append(location);
        result.append("\nNr.Tel: ").append(nrTel);
        result.append("\n\nProducts:\n");
        for (Products product : p) {
            result.append(product.toStringShort()).append("\n");
        }

        result.append("\n").append(r).append(" Minutes away\n");

        if (!comment.trim().isEmpty()) {
            result.append("\nComment: ").append(comment).append("\n");
        }

        result.append("\nTotal: ").append(total+2).append(euros);

        return result.toString();
    }
}
