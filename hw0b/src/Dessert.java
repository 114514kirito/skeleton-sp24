import java.text.Format;
public class Dessert {
    int flavor;
    int price;
    public Dessert(int flavor,int price)
    {
        this.flavor=flavor;
        this.price=price;

    }
    static int numDesserts=0;
    public void printDessert()
    {
        numDesserts++;
        String s=String.format("%d %d %d",this.flavor,this.price,numDesserts);
        System.out.println(s);
    }
    static void main() {
        System.out.println("I love dessert !");
    }
}