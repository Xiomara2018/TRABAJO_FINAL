package estructuras;

public class AVLTree<E extends Comparable<E>> {
    private NodeAVL<E> root;
    public AVLTree() {
        this.root = null;
    }
    // ALTURA DEL NODO
    private int height(NodeAVL<E> node) {
        return (node == null) ? 0 : node.height;
    }
    // FACTOR DE BALANCE
    // izquierda - derecha
    // sirve para detectar desbalance en el árbol AVL
    private int getBalance(NodeAVL<E> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }
    // ROTACIÓN DERECHA
    private NodeAVL<E> rightRotate(NodeAVL<E> y) {
        NodeAVL<E> x = y.left;      // hijo izquierdo
        NodeAVL<E> T2 = x.right;    // subárbol intermedio
        // rotación: x sube, y baja a la derecha
        x.right = y;
        y.left = T2;
        // actualiza altura de y después del cambio
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // actualizar altura de x (nueva raíz del subárbol)
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x; // nueva raíz del subárbol
    }

    // ROTACIÓN IZQUIERDA
    private NodeAVL<E> leftRotate(NodeAVL<E> x) {

        NodeAVL<E> y = x.right;     // hijo derecho
        NodeAVL<E> T2 = y.left;     // subárbol intermedio

        // rotación: y sube, x baja a la izquierda
        y.left = x;
        x.right = T2;

        // actualizar alturas después del cambio
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y; // nueva raíz del subárbol
    }

    // INSERTAR
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    // INSERCIÓN RECURSIVA
    private NodeAVL<E> insertRec(NodeAVL<E> node, E data) throws ItemDuplicated {
        // si el nodo está vacío se inserta el nuevo libro
        if (node == null) {
            return new NodeAVL<>(data);
        }
        // comparar el dato con el nodo actual
        int cmp = data.compareTo(node.data);

        // si son iguales no se permite duplicados
        if (cmp == 0) {
            throw new ItemDuplicated("Elemento duplicado: " + data);
        }

        // si es menor se va a ir a la izquierda
        if (cmp < 0) {
            node.left = insertRec(node.left, data);
        }

        // si es mayor se va a ir a la derecha
        else {
            node.right = insertRec(node.right, data);
        }

        // actualizar altura del nodo actual después de insertar
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // calcular balance del nodo
        int balance = getBalance(node);

        // CASO LL (izquierda-izquierda)
        if (balance > 1 && data.compareTo(node.left.data) < 0)
            return rightRotate(node);

        // CASO RR (derecha-derecha)
        if (balance < -1 && data.compareTo(node.right.data) > 0)
            return leftRotate(node);

        // CASO LR (izquierda-derecha)
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // CASO RL (derecha-izquierda)
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        // si está balanceado, se retorna el nodo sin cambios
        return node;
    }
    // BUSCAR UN ELEMENTO
    public E search(E data) throws ItemNotfound {
        return searchRec(root, data);
    }
    // Método recursivo para buscar un dato
    private E searchRec(NodeAVL<E> node, E data) throws ItemNotfound {
        // Si llegó a null significa que no existe
        if (node == null) {
            throw new ItemNotfound("Elemento no encontrado.");
        }
        // Comparar el dato buscado con el dato del nodo actual
        int cmp = data.compareTo(node.data);
        // Si son iguales se encontró el dato
        if (cmp == 0) {
            return node.data;
        }
        // Si es menor buscar por la izquierda
        if (cmp < 0) {
            return searchRec(node.left, data);
        } 
        // Si es mayor buscar por la derecha
        return searchRec(node.right, data);
    }
    // VERIFICAR SI EL ÁRBOL ESTÁ VACÍO
    public boolean isEmpty() {
        return root == null;
    }
