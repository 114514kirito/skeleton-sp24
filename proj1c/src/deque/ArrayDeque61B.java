package deque;
import deque.Deque61B;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    T[] items;
    int size;
    int nextfirst;
    int nextlast;
    public ArrayDeque61B(){
        items=(T[]) new Object[8];
        size=0;
        nextfirst=3;
        nextlast=4;
    }
    private void resize(int capacity){
        T[] NewItems=(T[]) new Object[capacity];
        for(int i=0;i<size;i++){
            NewItems[i]=get(i);
        }
        items=NewItems;
        nextlast=size;
        nextfirst=capacity-1;

    }
    @Override
    public void addFirst(T x) {
        if(size==items.length) {
            resize(items.length * 2);
        }
        items[nextfirst]=x;
        nextfirst=Math.floorMod(nextfirst-1,items.length);
        size+=1;

    }

    @Override
    public void addLast(T x) {
        if(size==items.length){
            resize(items.length*2);
        }
        items[nextlast]=x;
        nextlast=Math.floorMod(nextlast+1,items.length);
        size+=1;
    }

    @Override
    public List<T> toList() {
        List<T> returnlist=new ArrayList<>();
        for(int i=0;i<size;i++){
            returnlist.add(get(i));
        }
        return returnlist;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(isEmpty())return null;
        nextfirst=Math.floorMod(nextfirst+1,items.length);
        T first=items[nextfirst];
        items[nextfirst]=null;
        size-=1;
        if(items.length>15 && size <items.length/4){
            resize(items.length/2);
        }
        return first;
    }

    @Override
    public T removeLast() {
        if(isEmpty())return null;
        nextlast=Math.floorMod(nextlast-1,items.length);
        T last=items[nextlast];
        items[nextlast]=null;
        size-=1;
        if(items.length>15 && size <items.length/4){
            resize(items.length/2);
        }
        return last;

    }

    @Override
    public T get(int index) {
        if(index<0||index>=size)return null;
        int i=Math.floorMod(nextfirst+(index+1),items.length);
        return items[i];
    }
    private T getRecursive(int index,int first){
        if(index<0 ||index>=size)return null;
        if(index==0)return items[first];
        return getRecursive(index-1,Math.floorMod(first+1,items.length));
    }
    @Override
    public T getRecursive(int index) {
        int first=Math.floorMod(nextfirst+1,items.length);
        return getRecursive(index,first);
    }
    private class Arrayiterator implements Iterator<T>{
        int wipos;
        public Arrayiterator(){
            wipos=0;
        }
        @Override
        public boolean hasNext() {
            return wipos<size;
        }

        @Override
        public T next() {
            if(! hasNext())return null;
            int n=Math.floorMod(nextfirst+wipos+1,items.length);
            T item=items[n];
            wipos+=1;
            return item;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new Arrayiterator();
    }
    @Override
    public boolean equals(Object other){
        if(other instanceof Deque61B o){
            if(o.size()!=this.size)return false;
            for(int i=0;i<size;i++){
                if(!o.get(i).equals(get(i)))return false;
            }
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        StringBuilder s=new StringBuilder("[");
        for(int i=0;i<size;i++){
            s.append(get(i).toString());
            s.append(",");
        }
        s.append("]");
        return s.toString();
    }
}
