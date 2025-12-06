import java.text.Format;
public class Dessert {
    int flavor;
    int price;
    public Dessert(int flavor,int price)
    {
        this.flavor=flavor;
        this.price=price;
        numDesserts++;

    }
    static int numDesserts=0;
    public  void printDessert()
    {
        String s=String.format("%d %d %d",flavor,price,numDesserts);
        System.out.println(s);
    }
    public static void main(String[] args) {
        System.out.println("I love dessert!");


    }
}
