import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GraphTest{

    Graph<Integer> graphUnderTest = new Graph<>();

    @BeforeEach
    void setUp() {
        graphUnderTest.vertices.add(graphUnderTest.new Node(1));
        graphUnderTest.vertices.add(graphUnderTest.new Node(2));
        graphUnderTest.vertices.add(graphUnderTest.new Node(3));
        graphUnderTest.vertices.add(graphUnderTest.new Node(4));
        graphUnderTest.vertices.add(graphUnderTest.new Node(5));
        graphUnderTest.vertices.add(graphUnderTest.new Node(6));
        graphUnderTest.vertices.add(graphUnderTest.new Node(7));
        graphUnderTest.vertices.add(graphUnderTest.new Node(8));
        graphUnderTest.vertices.get(0).adjacent = graphUnderTest.new Node(4,2);
        graphUnderTest.vertices.get(0).adjacent.adjacent = graphUnderTest.new Node(5,3);
        graphUnderTest.vertices.get(0).adjacent.adjacent.adjacent = graphUnderTest.new Node(2,4);
        graphUnderTest.vertices.get(1).adjacent = graphUnderTest.new Node(1,4);
        graphUnderTest.vertices.get(1).adjacent.adjacent = graphUnderTest.new Node(5,3);
        graphUnderTest.vertices.get(1).adjacent.adjacent.adjacent = graphUnderTest.new Node(8,4);
        graphUnderTest.vertices.get(1).adjacent.adjacent.adjacent.adjacent = graphUnderTest.new Node(6,8);
        graphUnderTest.vertices.get(1).adjacent.adjacent.adjacent.adjacent.adjacent = graphUnderTest.new Node(3,2);
        graphUnderTest.vertices.get(2).adjacent = graphUnderTest.new Node(2,2);
        graphUnderTest.vertices.get(2).adjacent.adjacent = graphUnderTest.new Node(6,9);
        graphUnderTest.vertices.get(3).adjacent = graphUnderTest.new Node(1,2);
        graphUnderTest.vertices.get(3).adjacent.adjacent = graphUnderTest.new Node(7,5);
        graphUnderTest.vertices.get(4).adjacent = graphUnderTest.new Node(1,3);
        graphUnderTest.vertices.get(4).adjacent.adjacent = graphUnderTest.new Node(2,3);
        graphUnderTest.vertices.get(4).adjacent.adjacent.adjacent = graphUnderTest.new Node(7,5);
        graphUnderTest.vertices.get(4).adjacent.adjacent.adjacent.adjacent = graphUnderTest.new Node(8,1);
        graphUnderTest.vertices.get(5).adjacent = graphUnderTest.new Node(2,8);
        graphUnderTest.vertices.get(5).adjacent.adjacent = graphUnderTest.new Node(3,9);
        graphUnderTest.vertices.get(5).adjacent.adjacent.adjacent = graphUnderTest.new Node(8,7);
        graphUnderTest.vertices.get(6).adjacent = graphUnderTest.new Node(4,5);
        graphUnderTest.vertices.get(6).adjacent.adjacent = graphUnderTest.new Node(5,5);
        graphUnderTest.vertices.get(6).adjacent.adjacent.adjacent = graphUnderTest.new Node(8,6);
        graphUnderTest.vertices.get(7).adjacent = graphUnderTest.new Node(7,6);
        graphUnderTest.vertices.get(7).adjacent.adjacent = graphUnderTest.new Node(5,1);
        graphUnderTest.vertices.get(7).adjacent.adjacent.adjacent = graphUnderTest.new Node(2,4);
        graphUnderTest.vertices.get(7).adjacent.adjacent.adjacent.adjacent = graphUnderTest.new Node(6,7);
    }

    @Test
    void connect() {
        graphUnderTest.connect(4,5,6);
        assertEquals(5, graphUnderTest.vertices.get(3).adjacent.adjacent.adjacent.getValue());
        assertEquals(4, graphUnderTest.vertices.get(4).adjacent.adjacent.adjacent.adjacent.adjacent.getValue());

    }

    @Test
    void disconnect() {
        graphUnderTest.disconnect(2,3);
        assertNull(graphUnderTest.vertices.get(1).adjacent.adjacent.adjacent.adjacent.adjacent);
        assertEquals(6, graphUnderTest.vertices.get(2).adjacent.getValue());
    }

    @Test
    void deleteVertex() {
        graphUnderTest.deleteVertex(3);
        assertEquals(4, graphUnderTest.vertices.get(2).getValue());
        assertNull(graphUnderTest.vertices.get(1).adjacent.adjacent.adjacent.adjacent.adjacent);
        assertEquals(8, graphUnderTest.vertices.get(4).adjacent.adjacent.getValue());
    }

    @Test
    void MST() {
        graphUnderTest = graphUnderTest.MST();
        assertEquals(7, graphUnderTest.vertices.get(3).adjacent.adjacent.getValue());
        assertEquals(8, graphUnderTest.vertices.get(5).adjacent.getValue());
        assertEquals(2, graphUnderTest.vertices.get(4).adjacent.adjacent.adjacent.getValue());
        assertEquals(2, graphUnderTest.vertices.get(2).adjacent.getValue());
        assertEquals(4, graphUnderTest.vertices.get(0).adjacent.getValue());

    }

    @Test
    void SP() {
        assertEquals(8, graphUnderTest.SP(4,3));
    }
}