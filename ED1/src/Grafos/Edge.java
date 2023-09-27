package Grafos;

public class Edge<T>{

    private Vertex<T> first;
    private Vertex<T> last;
    private int weight;

    public Edge(Vertex<T> first, Vertex<T> last, int weight){
        this.first = first;
        this.last = last;
        this.weight = weight;
    }

    public int getWeight() {return weight;}

    public Vertex<T> getFirst() {
        return first;
    }

    public Vertex<T> getLast() {
        return last;
    }

}
