package Grafos;

import java.util.*;

public class Graph <T>{
    //grafo não direcionado
    private ArrayList<Vertex<T>> vertices;
    private ArrayList<Edge<T>> edges;
    private int[][] matrix;

    public Graph(){
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void  addVertex(T data){
        Vertex<T> vertex = new Vertex<>(data);
        vertices.add(vertex);
    }

    public Vertex<T> findVertex(T data){
        for (int i = 0; i < vertices.size(); i++){
            if (vertices.get(i).getData().equals(data)){
                return vertices.get(i);
            }
        }
        return null;
    }

    public void addEdge(T init, T end, int weight){
        Vertex<T> start = findVertex(init);
        Vertex<T> finish = findVertex(end);
        if (start != null && finish != null){
            Edge<T> edge = new Edge<>(start,finish,weight);
            edges.add(edge);
        }

    }


    public ArrayList<Vertex<T>> getVertices() {
        return vertices;
    }

    public ArrayList<Edge<T>> getEdges() {
        return edges;
    }

    public boolean findEdgeUndirected(Vertex<T> x, Vertex<T> y){
        for (int i = 0; i < edges.size(); i++){
            if ((edges.get(i).getFirst() == x && edges.get(i).getLast() == y) || (edges.get(i).getLast() == x && edges.get(i).getFirst() == y))
                return true;
        }
        return false;
    }

    public ArrayList<Vertex<T>> findNeighbors(Vertex<T> vertex) {
        ArrayList<Vertex<T>> neighbors = new ArrayList<>();

        for (Edge<T> edge : edges) {
            Vertex<T> vertex1  = edge.getFirst();
            Vertex<T> vertex2 = edge.getLast();

            if (vertex1 == vertex) {
                neighbors.add(vertex2);
            } else if (vertex2 == vertex) {
                neighbors.add(vertex1);
            }
        }

        return neighbors;
    }

    public void adjMatrix(){
        matrix = new int[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++){
            for (int j = 0; j < vertices.size(); j++) {
                if (findEdgeUndirected(vertices.get(i), vertices.get(j))){
                    matrix[i][j] = 1;
                    matrix[j][i] = 1;
                }
                else
                    matrix[i][j] = 0;
            }
        }
        printAdjMatrix();
    }

    public void printAdjMatrix(){
        System.out.print(" ");
        for (int i = 0; i < vertices.size(); i++){
            System.out.print(" " + vertices.get(i).getData());
        }
        System.out.println( );
        for (int i = 0; i < vertices.size(); i++){
            System.out.print(vertices.get(i).getData());
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void adjList(){
        for (int i = 0; i < vertices.size(); i++){
            System.out.print(vertices.get(i).getData() + " -> ");
            for (int j = 0; j < vertices.size(); j++) {
                if (findEdgeUndirected(vertices.get(i), vertices.get(j))){
                    System.out.print( " " + vertices.get(j).getData());
                }
            }
            System.out.println();
        }

    }

    public int vertexDegree(T value){
        int count = 0;
        Vertex<T> vertex= findVertex(value);
        if (vertex != null){
            for (int i = 0; i < edges.size(); i++){
                if (edges.get(i).getFirst() == vertex || edges.get(i).getLast() == vertex)
                    count++;
            }
            return count;
        } else {
            //não tem o vértice
            System.out.println("vertex not found");
            return -1;
        }
    }
    public boolean verifyKonigsbergProblem(){
        //não é um caminho de euler, então não possui solução.
        int odd = 0;
        for (int i = 0; i < vertices.size(); i++){
            if (vertexDegree(vertices.get(i).getData()) % 2 != 0){
                odd++;
            }
        }
        if (odd == 0 || odd == 2)
            return true;
        else
            return false;
    }

    public Map<Vertex<T> , Integer> dijkstra(T start){
        Vertex<T> startVertex = findVertex(start);
        if ( startVertex != null){
            Map<Vertex<T>, Integer> distances = new HashMap<>();

            for (Vertex<T> vertex : getVertices() )
                distances.put(vertex, Integer.MAX_VALUE);

            distances.put(startVertex,0);

            PriorityQueue<Vertex<T>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
            priorityQueue.addAll(getVertices());

            while (!priorityQueue.isEmpty()) {

                Vertex<T> current = priorityQueue.poll();
                int currentD = distances.get(current);

                for (Edge<T> edge : getEdges()) {
                    if (edge.getFirst() == current || edge.getLast() == current) {

                        Vertex<T> neighbor = (edge.getFirst() == current) ? edge.getLast() : edge.getFirst();
                        int overCurrentD = currentD + edge.getWeight();

                        if (overCurrentD < distances.get(neighbor)) {
                            distances.put(neighbor, overCurrentD);
                            priorityQueue.remove(neighbor);
                            priorityQueue.add(neighbor);
                        }
                    }
                }
            }

            return distances;
        }else {
            return null;
        }
    }

    public void printDijkstraMap(Map<Vertex<T>, Integer> map, T start){
        for (Map.Entry<Vertex<T>, Integer> entry : map.entrySet()) {
            Vertex<T> vertex = entry.getKey();
            Integer distance = entry.getValue();
            System.out.println("Distância mínima de " + start + " para " + vertex.getData() + ": " + distance);
        }
    }


    public int minimumVertexColoring() {

        int verticesSize = getVertices().size();

        ArrayList<Integer> colors = new ArrayList<>(verticesSize);

        for (int i = 0; i < verticesSize; i++) {
            colors.add(-1);
        }
        colors.set(0, 0);

        for (int i = 1; i < verticesSize; i++) {
            Set<Integer> usedColors = new HashSet<>();
            ArrayList<Vertex<T>> neighbors = findNeighbors(getVertices().get(i));
            for (Vertex<T> neighbor : neighbors) {
                if (colors.get(getVertices().indexOf(neighbor)) != -1) {
                    usedColors.add(colors.get(getVertices().indexOf(neighbor)));
                }
            }

            int availableColor = 0;
            while (usedColors.contains(availableColor)) {
                availableColor++;
            }

            colors.set(i, availableColor);
        }

        int max = colors.stream().mapToInt(Integer::intValue).max().getAsInt();
        return max + 1;
    }

    public boolean verifyColors(int n){
        //da para colorir entre o minimo de cores até o máximo de vértices
        return n >= minimumVertexColoring() && n <= getVertices().size();
    }

}
