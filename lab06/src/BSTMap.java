import org.apache.commons.lang3.ObjectUtils;
import org.checkerframework.checker.units.qual.K;

import java.util.*;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private class BSTNode{
        K key;
        V value;
        BSTNode left;
        BSTNode right;
        public BSTNode(K i,V value){
            key=i;
            this.value=value;
        }
    }
    private int size;
    private BSTNode root;
    public BSTMap(){
        root=null;
        size=0;
    }
    private BSTNode put(BSTNode h,K key,V value){
        if(h==null)return new BSTNode(key,value);
        int cmp=key.compareTo(h.key);
        if(cmp <0){
            h.left=put(h.left,key,value);
        }
        else if(cmp >0){
            h.right=put(h.right,key,value);
        }
        else {
            h.value=value;
        }
        return h;
    }
    @Override
    public void put(K key, V value) {
        if(! containsKey(key))size++;
        root=put(root,key,value);
    }
    private BSTNode find(BSTNode h,K key){
        if(h==null)return null;
        if(h.key.equals(key))return h;
        int cmp=key.compareTo(h.key);
        if(cmp<0){
            return find(h.left,key);
        }
        else {
            return find(h.right,key);
        }
    }
    @Override
    public V get(K key) {
        BSTNode x = find(root, key);
        return x == null ? null : x.value;

    }

    @Override
    public boolean containsKey(K key) {
        BSTNode x=find(root,key);
        if(x ==null)return false;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root=null;
        size=0;
    }

    @Override
    public Set<K> keySet() {
        Set<K>L=new LinkedHashSet<>();
        Iterator<K>I=iterator();
        while (I.hasNext()){
            L.add(I.next());
        }
        return L;
    }
    private BSTNode remove(BSTNode h,K key){
        if(h==null) return null;
        int cmp=key.compareTo(h.key);
        if(cmp<0){
            h.left=remove(h.left,key);
        }
        else if (cmp>0){
            h.right=remove(h.right,key);
        }
        else {
            if(h.left==null)return h.right;
            if(h.right==null)return h.left;
            else{
                BSTNode t = h;
                BSTNode min = findMin(t.right);
                h.key = min.key;
                h.value = min.value;
                h.right = deleteMin(t.right);
            }
        }
        return h;
    }
    private BSTNode deleteMin(BSTNode h){
        if(h.left==null)return h.right;
        else{
            h.right=deleteMin(h.right);
        }
        return h;
    }
    private BSTNode findMin(BSTNode h){
        if(h.left==null)return h;
        else {
            h=findMin(h.right);
        }
        return h;
    }
    @Override
    public V remove(K key) {
        if(! containsKey(key))return null;
        V value=get(key);
        root=remove(root,key);
        size-=1;
        return value;
    }
    private class BSTIterator implements Iterator<K>{
        private Stack<BSTNode> L=new Stack<>();
        private void pushLeft(BSTNode h){
            while(h!=null){
                L.push(h);
                h=h.left;
            }
        }
        public BSTIterator(){
            pushLeft(root);
        }
        @Override
        public boolean hasNext() {
            return !L.isEmpty();
        }

        @Override
        public K next() {
            BSTNode x=L.pop();
            if(x.right!=null)pushLeft(x.right);
            return x.key;
        }
    }
    @Override
    public Iterator<K> iterator() {
      return new BSTIterator();
    }
}
