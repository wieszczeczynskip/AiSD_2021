import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Graph <T>{
    ArrayList<Node> vertices = new ArrayList<>();

    class Node {
        T value;
        Node adjacent;
        int weight;

        public Node(T value) {
            this.value = value;
            this.adjacent = null;
            this.weight = 0;
        }

        public Node(T value, int weight) {
            this.value = value;
            this.adjacent = null;
            this.weight = weight;
        }

        public T getValue() {
            return value;
        }
    }

    public void addVertex(T value) {
        vertices.add(new Node(value));
    }

    public void connect(T v1, T v2, int weight) {
        Node pointer1 = null;
        Node pointer2 = null;
        for (int i = 0; i<vertices.size(); i++) {
            if (vertices.get(i).getValue() == v1) {
                pointer1 = vertices.get(i);
            }
            if (vertices.get(i).getValue() == v2) {
                pointer2 = vertices.get(i);
            }
        }
        if (pointer1 == null || pointer2 == null) {
            throw new NoSuchElementException();
        }
        while (pointer1.adjacent != null) {
            if (pointer1.adjacent.getValue() == v2) {
                throw new AlreadyConnectedException();
            }
            pointer1 = pointer1.adjacent;
        }
        while (pointer2.adjacent != null) {
            if (pointer2.adjacent.getValue() == v1) {
                throw new AlreadyConnectedException();
            }
            pointer2 = pointer2.adjacent;
        }
        pointer1.adjacent = new Node(v2, weight);
        pointer2.adjacent = new Node(v1, weight);
    }

    public void disconnect(T v1, T v2) {
        Node pointer1 = null;
        Node pointer2 = null;
        for (int i = 0; i<vertices.size(); i++) {
            if (vertices.get(i).getValue() == v1) {
                pointer1 = vertices.get(i);
            }
            if (vertices.get(i).getValue() == v2) {
                pointer2 = vertices.get(i);
            }
        }
        if (pointer1 == null || pointer2 == null) {
            throw new NoSuchElementException();
        }
        while (pointer1.adjacent != null && pointer1.adjacent.getValue() != v2) {
            pointer1 = pointer1.adjacent;
        }
        if (pointer1.adjacent == null && pointer1.getValue() != v1) {
            throw new NotYetConnectedException();
        }
        while (pointer2.adjacent != null && pointer2.adjacent.getValue() != v1) {
            pointer2 = pointer2.adjacent;
        }
        if (pointer2.adjacent == null && pointer2.getValue() != v2) {
            throw new NotYetConnectedException();
        }
        pointer1.adjacent = pointer1.adjacent.adjacent;
        pointer2.adjacent = pointer2.adjacent.adjacent;
    }

    public void deleteVertex(T value) {
        Node pointer = null;
        for (int i = 0; i<vertices.size(); i++) {
            if (vertices.get(i).getValue() == value) {
                pointer = vertices.get(i);
                break;
            }
        }
        if (pointer == null) {
            throw new NoSuchElementException();
        }
        while (pointer.adjacent != null) {
            disconnect(pointer.value, pointer.adjacent.value);
        }
        vertices.remove(pointer);
    }

    public void changeWeight(T v1, T v2, int newWeight) {
        disconnect(v1, v2);
        connect(v1, v2, newWeight);
    }

    @Override
    public String toString() {
        Node pointer;
        String string = "";
        for (int i = 0; i< vertices.size(); i++) {
            pointer = vertices.get(i);
            string = string + "Vertex: " + pointer.value + "\n";
            pointer = pointer.adjacent;
            if (pointer == null) {
                string = string + "Connects to nothing" + "\n";
                continue;
            }
            string = string + "Connects to:" + "\n";
            while (pointer != null) {
                string = string + "- " + pointer.value + " with the weight " + pointer.weight + "\n";
                pointer = pointer.adjacent;
            }
        }
        return string;
    }

    public void printGraph() {
        Node pointer;
        for (int i = 0; i< vertices.size(); i++) {
            pointer = vertices.get(i);
            System.out.println("Vertex: " + pointer.value);
            pointer = pointer.adjacent;
            if (pointer == null) {
                System.out.println("Connects to nothing");
                continue;
            }
            System.out.println("Connects to:");
            while (pointer != null) {
                System.out.println("- " + pointer.value + " with the weight " + pointer.weight);
                pointer = pointer.adjacent;
            }
        }
    }

    public Graph<T> MST() {
        Graph<T> MSTGraph = new Graph<>();
        for (int i = 0; i<vertices.size(); i++) {
            MSTGraph.addVertex(vertices.get(i).getValue());
        }
        ArrayList<ArrayList<Object>> edges = new ArrayList<>();
        for (int i = 0; i<vertices.size(); i++) {
            Node pointer = vertices.get(i);
            T v1 = pointer.value;
            EdgesAddingLoop:
            while (pointer.adjacent != null) {
                for (int j = 0; j<edges.size(); j++) {
                    if (edges.get(j).get(0) == pointer.adjacent.value && edges.get(j).get(1) == v1) {
                        pointer = pointer.adjacent;
                        continue EdgesAddingLoop;
                    }
                }
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(v1);
                temp.add(pointer.adjacent.value);
                temp.add(pointer.adjacent.weight);
                edges.add(temp);
                pointer = pointer.adjacent;
            }
        }
        for (int i = 0; i<edges.size(); i++) {
            for (int j = 0; j<edges.size()-1-i; j++) {
                if ((Integer) edges.get(j).get(2) > (Integer) edges.get(j+1).get(2)) {
                    ArrayList<Object> temp = edges.get(j);
                    edges.set(j, edges.get(j+1));
                    edges.set(j+1, temp);
                }
            }
        }
        ArrayList<ArrayList<T>> forest = new ArrayList<>();
        for (int i = 0; i<vertices.size(); i++) {
            ArrayList<T> temp = new ArrayList<>();
            temp.add(vertices.get(i).getValue());
            forest.add(temp);
        }
        Loop:
        while (edges.size() > 0) {
            ArrayList<Object> edge = edges.get(0);
            for (int i = 0; i<forest.size(); i++) {
                for (int j = 0; j<forest.get(i).size(); j++) {
                    if (forest.get(i).get(j) == edge.get(0)) {
                        for (int k = 0; k<forest.get(i).size(); k++) {
                            if (forest.get(i).get(k) == edge.get(1)) {
                                edges.remove(0);
                                continue Loop;
                            }
                        }
                    }
                }
            }
            MSTGraph.connect((T)edge.get(0), (T)edge.get(1), (Integer)edge.get(2));
            ArrayList<T> pointer1 = null;
            ArrayList<T> pointer2 = null;
            for (int i = 0; i<forest.size(); i++) {
                for (int j = 0; j<forest.get(i).size(); j++) {
                    if (forest.get(i).get(j) == edge.get(0)) {
                        pointer1 = forest.get(i);
                    }
                    if (forest.get(i).get(j) == edge.get(1)) {
                        pointer2 = forest.get(i);
                    }
                }
            }
            pointer1.addAll(pointer2);
            forest.remove(pointer2);
            edges.remove(0);
        }
        return MSTGraph;
    }

    public int SP(T source, T destination) {
        boolean check1 = false;
        boolean check2 = false;
        for (int i = 0; i<vertices.size(); i++) {
            if (vertices.get(i).getValue() == source) {
                check1 = true;
            }
            if (vertices.get(i).getValue() == destination) {
                check2 = true;
            }
        }
        if (!check1 || !check2) {
            throw new NoSuchElementException();
        }
        ArrayList<ArrayList<Object>> distances = new ArrayList<>();
        ArrayList<Node> unvisited = new ArrayList<>();
        for (int i = 0; i<vertices.size(); i++) {
            unvisited.add(vertices.get(i));
            ArrayList<Object> temp = new ArrayList<>();
            temp.add(vertices.get(i).getValue());
            temp.add(Integer.MAX_VALUE);
            temp.add(false);
            distances.add(temp);
        }
        for (int i = 0; i<distances.size(); i++) {
            if (distances.get(i).get(0) == source) {
                distances.get(i).set(1, 0);
            }
        }
        Node currentVer = null;
        ArrayList<Object> currentDist = null;
        for (int i = 0; i<unvisited.size(); i++) {
            if (unvisited.get(i).getValue() == source) {
                currentVer = unvisited.get(i);
            }
        }
        for (int i = 0; i<distances.size(); i++) {
            if (distances.get(i).get(0) == source) {
                currentDist = distances.get(i);
            }
        }
        while (unvisited.size() != 0) {
            Node pointer = currentVer.adjacent;
            while(pointer != null) {
                for (int i = 0; i<distances.size(); i++) {
                    if (distances.get(i).get(0) == pointer.getValue()) {
                        if ((Boolean)distances.get(i).get(2)) {
                            continue;
                        }
                        int dist = Integer.min((Integer)currentDist.get(1) + pointer.weight, (Integer)distances.get(i).get(1));
                        distances.get(i).set(1,dist);
                    }
                }
                pointer = pointer.adjacent;
            }
            currentDist.set(2,true);
            if (currentDist.get(0) == destination) {
                return (Integer)currentDist.get(1);
            }
            unvisited.remove(currentVer);
            int smallest = Integer.MAX_VALUE;
            for (int i = 0; i<distances.size(); i++) {
                if ((Integer)distances.get(i).get(1) < smallest && !(Boolean)distances.get(i).get(2)) {
                    smallest = (Integer)distances.get(i).get(1);
                }
            }
            for (int i = 0; i<distances.size(); i++) {
                if ((Integer)distances.get(i).get(1) == smallest && !(Boolean)distances.get(i).get(2)) {
                    currentDist = distances.get(i);
                }
            }
            for (int i = 0; i<unvisited.size(); i++) {
                if (unvisited.get(i).getValue() == currentDist.get(0)) {
                    currentVer = unvisited.get(i);
                }
            }
        }
        throw new NoSuchElementException();
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);
        graph.connect(1,4,2);
        graph.connect(1,5,3);
        graph.connect(4,7,5);
        graph.connect(7,5,5);
        graph.connect(2,5,3);
        graph.connect(2,3,2);
        graph.connect(2,6,8);
        graph.connect(2,8,4);
        graph.connect(6,8,7);
        graph.connect(5,8,1);
        graph.connect(7,8,6);
        graph.connect(3,6,9);
        graph.connect(1,2,4);
        System.out.println(graph.MST());
        System.out.println(graph.SP(4,8));
    }
}
