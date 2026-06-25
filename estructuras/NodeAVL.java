package estructuras;

public class NodeAVL<T> {
    T data;                 // dato almacenado
    NodeAVL<T> left;       // hijo izquierdo
    NodeAVL<T> right;      // hijo derecho
    int height;            // altura del nodo
    public NodeAVL(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;   // un nodo nuevo siempre tiene altura 1
    }
}
