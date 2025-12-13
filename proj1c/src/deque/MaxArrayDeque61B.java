package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    Comparator<T> c;
    public MaxArrayDeque61B(Comparator<T>c){
        super();
        this.c=c;
    }
    public T max(){
        if(size()==0)return null;
        T max=get(0);
        for(int i=1;i<size;i++){
           if( c.compare(get(i),max)>0) {
                max = get(i);
            }

        }
        return max;
    }
    public T max(Comparator<T> c){
        if(size()==0)return null;
        T max=get(0);
        for(int i=1;i<size;i++){
           if( c.compare(get(i),max)>0) {
                max = get(i);
            }

        }
        return max;
    }

}
