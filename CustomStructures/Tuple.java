package CustomStructures;

public class Tuple implements Comparable <Tuple>{
    
    private int first;
    private int second; 
    private int third;

    public Tuple(int first, int second, int third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getFirst(){
        return first;
    }

    public int getSecond(){
        return second;
    }

    public int getThird(){
        return third;
    }

    @Override
    public int compareTo(Tuple other) {
        
        int compareFirst = Integer.compare(this.first, other.getFirst());

        if(compareFirst != 0) 
            return compareFirst;
    
        int compareSecond = Integer.compare(this.second, other.getSecond());

        if(compareSecond != 0)
            return -compareSecond;
        
        return Integer.compare(this.third, other.getThird());

    }

}
