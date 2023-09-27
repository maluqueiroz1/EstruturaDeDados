package teoriaDosGrafos;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Graph<Character> graph = new Graph<>();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');

        graph.addEdge('A', 'B', 10);
        graph.addEdge('A', 'C', 25);
        graph.addEdge('A', 'D', 3);
        graph.addEdge('A', 'E', 7);
        graph.addEdge('B', 'C', 2);
        graph.addEdge('B', 'D', 13);
        graph.addEdge('B', 'E', 4);
        graph.addEdge('C', 'D', 18);
        graph.addEdge('C', 'E', 9);
        graph.addEdge('E', 'F', 11);
        graph.addEdge('D', 'F', 8);

        System.out.println("Grafo 1");
        System.out.println("Matriz de adjacência:");
        graph.adjMatrix();
        System.out.println();
        System.out.println("Lista de adjacência:");
        graph.adjList();
        System.out.println();
        System.out.println("Verificação se problema das pontes de konigsberg tem solução:");
        boolean answer = graph.verifyKonigsbergProblem();
        if (answer)
            System.out.println("possui solução");
        else
            System.out.println("não possui solução");
        System.out.println();
        System.out.println("Algoritmo de djikstra:");
        Map<Vertex<Character>, Integer> distances = graph.dijkstra('A');
        graph.printDijkstraMap(distances, 'A');
        System.out.println();
        System.out.println("Verificação se o grafo representa um mapa de n cores:");
        boolean answer1 = graph.verifyColors(5);
        if (answer1)
            System.out.println("representa");
        else
            System.out.println("não representa");
        System.out.println();

        Graph<Integer> graph2 = new Graph<>();
        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addVertex(3);
        graph2.addVertex(4);
        graph2.addVertex(5);
        graph2.addVertex(6);
        graph2.addVertex(7);
        graph2.addVertex(8);

        graph2.addEdge(1, 2, 3);
        graph2.addEdge(1, 3, 22);
        graph2.addEdge(1, 4, 5);
        graph2.addEdge(2, 5, 12);
        graph2.addEdge(2, 6, 1);
        graph2.addEdge(3, 6, 9);
        graph2.addEdge(4, 7, 2);
        graph2.addEdge(5, 7, 16);
        graph2.addEdge(5, 8, 4);
        graph2.addEdge(6, 8, 7);
        graph2.addEdge(7, 8, 6);

        System.out.println("Grafo 2");
        System.out.println("Matriz de adjacência:");
        graph2.adjMatrix();
        System.out.println();
        System.out.println("Lista de adjacência:");
        graph2.adjList();
        System.out.println();
        System.out.println("Verificação se problema das pontes de konigsberg tem solução:");
        boolean answer2 = graph2.verifyKonigsbergProblem();
        if (answer2)
            System.out.println("possui solução");
        else
            System.out.println("não possui solução");
        System.out.println();
        System.out.println("Algoritmo de djikstra:");
        Map<Vertex<Integer>, Integer> distances1 = graph2.dijkstra(2);
        graph2.printDijkstraMap(distances1, 2);
        System.out.println();
        System.out.println("Verificação se o grafo representa um mapa de n cores:");
        boolean answer3 = graph2.verifyColors(7);
        if (answer3)
            System.out.println("representa");
        else
            System.out.println("não representa");
    }


}
