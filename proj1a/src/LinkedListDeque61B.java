import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private class Node{
        Node prev;
        T items;
        Node next;
        public Node(Node prev,T items,Node next){
            this.prev=prev;
            this.items=items;
            this.next=next;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque61B(T i){
        sentinel=new Node(null,null,null);
        Node first=new Node(sentinel,i,sentinel);
        sentinel.prev=first;
        sentinel.next=first;
        size=1;
    }
    public LinkedListDeque61B(){
        sentinel=new Node(null,null,null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size=0;
    }

    @Override
    public void addFirst(T x) {
        Node first=new Node(sentinel,x,sentinel.next);
        sentinel.next.prev=first;
        sentinel.next=first;
        size+=1;

    }

    @Override
    public void addLast(T x) {
        Node last=new Node(sentinel.prev,x,sentinel);
        sentinel.prev.next=last;
        sentinel.prev=last;
        size+=1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node P=sentinel.next;
        while (P !=sentinel){
            returnList.add(P.items);
            P= P.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next==sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size==0)return null;
        T items=sentinel.next.items;
        sentinel.next.next.prev=sentinel;
        sentinel.next=sentinel.next.next;
        size-=1;
        return items;

    }

    @Override
    public T removeLast() {
        if(size==0)return null;
        size-=1;
        Node last=sentinel.prev;
        T items=last.items;
        Node newLast=sentinel.prev.prev;
        sentinel.prev=newLast;
        newLast.next=sentinel;
        return items;
    }

    @Override
    public T get(int index) {
        if(this.size==0 || index <0 ||index>size){
            return null;
        }
        Node P=sentinel.next;
        int count=0;
        while (P!=sentinel &&count!=index){
            P=P.next;
            count+=1;
        }
        return P.items;
    }
    private T getRecursive(int index,Node L){
        if (index==0)return L.items;
        return getRecursive(index-1,L.next);

    }
    @Override
    public T getRecursive(int index) {
        if(index <0 || size==0)
        {
            return null;
        }
        return getRecursive(index,sentinel.next);
    }
}