package estructuras;

public class NodeAVL<E> {
    E data;                 // dato almacenado
    NodeAVL<E> left;       // hijo izquierdo
    NodeAVL<E> right;      // hijo derecho
    int height;            // altura del nodo
    public NodeAVL(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;   // un nodo nuevo siempre tiene altura 1
    }
}
