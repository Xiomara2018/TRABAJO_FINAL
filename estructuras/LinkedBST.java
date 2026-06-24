package estructuras;


public class LinkedBST<E extends Comparable<E>> {
    private NodeBST<E> root;

    public LinkedBST() {
        this.root = null; // arbol inicial esta vacio
    }
    
    
    //INSERCION 
    public void insertDiv(E data) throws ItemDuplicated {
    	this.root = insertRecursive(this.root , data);
    }
    
    public NodeBST<E> insertRecursive(NodeBST<E> current, E data) throws ItemDuplicated {
        if (current == null) {
            return new NodeBST<>(data); // encontramos el lugar vacío
        }

        int compare = data.compareTo(current.data);

        if (compare == 0) {
            throw new ItemDuplicated("El dato " + data + " ya existe.");
        } else if (compare < 0) {
            current.left = insertRecursive(current.left, data); // Ir a la izquierda
        } else {
            current.right = insertRecursive(current.right, data); // Ir a la derecha
        }

        return current;
    }
    
    //BUQUEDA
    public E search(E data) throws ItemNotfound{
    	return searchrecurive(this.root, data);
    }
    
    public E searchrecurive(NodeBST<E> current,E data) throws ItemNotfound{
    	if(current == null ) {
    		throw new ItemNotfound("no se encontro el dato ");
    	}
    	int compare = data.compareTo(current.data);
    	if (compare == 0) {
    		return current.data;
    	}
        	
    	if(compare< 0) {
    		return searchrecurive(current.left , data);
    	}
    		else{
    		return searchrecurive(current.right , data);
    	}
    }
    
    
    //VERIFICAR SI ESTA VACIO
    public boolean isEmpty() {
		return this.root == null ;
	}
    
    public String toString() {
    	if (isEmpty()) return "arbol vacio";
    	
    	return "raiz del arbol es" + root.data;
    }
    
    //ELIMINACION
    public void remove(E data) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("esta vacio");
        }
        this.root = removeRe(this.root, data);
    }
    
    
    public NodeBST<E> removeRe(NodeBST<E> current, E data){
    	if (current == null) {
    		return null;
    	}
    	
    	int compare = data.compareTo(current.data);
    	if (compare < 0) {
    		current.left = removeRe(current.left, data);
    	}else if(compare>0){
			current.right = removeRe(current.right, data);
		}else {//si el nodo tiene un hijo o ninguno
			if(current.left == null) return current.right;
			if(current.right == null) return current.left;

			//si tiene do hijos
			current.data = findMin(current.right);
			current.right = removeRe(current.right, current.data);
		}
    	
    	return current;
    	
    }
    private E findMin(NodeBST<E> node) {
        if  (node.left == null) {
        	return node.data ;
        }else{
        	return findMin(node.left);
        }
    }
    
    //inorder
    public void inOrder() {
    	inOrderRec(this.root);
    	System.out.println();
    }
    
    private void inOrderRec(NodeBST<E> current) {
    	
    	if (current != null) {
    		inOrderRec(current.left);   // recorre el ubarbol izquierdo
            System.out.print(current.data + " "); // visitar la raiz
            inOrderRec(current.right);  //recorrer subarbol derecho
        }
	}
    
    //preorder
    public void preOrder() {
        preOrderRec(this.root);
        System.out.println();
    }

    private void preOrderRec(NodeBST<E> current) {
        if (current != null) {
            System.out.print(current.data + " ");
            preOrderRec(current.left);
            preOrderRec(current.right);//final derecha
        }
    }
    
    //posorder
    
    public void postOrder() {
        postOrderRec(this.root);
        System.out.println();
    }

    private void postOrderRec(NodeBST<E> current) {
        if (current != null) {
            postOrderRec(current.left);
            postOrderRec(current.right); 
            System.out.print(current.data + " ");  //raiz al final
        }
    }
    
    
    //esto para el caso aplicado resumido recorrido inverso inorden
    
    public void printDescending() {
        printDescendingRec(this.root);
        System.out.println();
    }

    private void printDescendingRec(NodeBST<E> current) {
        if (current != null) {
            printDescendingRec(current.right); // primero lo mayores
            System.out.print(current.data + " "); // raiz
            printDescendingRec(current.left);  // los menores 
        }
    }
    
    
    //ejer4 
    public void parenthesize() {
        parenthesizeRec(this.root, 0);
        System.out.println();
    }

    private void parenthesizeRec(NodeBST<E> current, int nivel) {
        if (current != null) {
        	//inicio de sangria
            for (int i = 0; i < nivel; i++) System.out.print("  ");
            System.out.println(current.data + " (");
            
            parenthesizeRec(current.left, nivel + 1);
            parenthesizeRec(current.right, nivel + 1);
            
        	//fin de sangria
            for (int i = 0; i < nivel; i++) System.out.print("  ");
            System.out.println(")"); // Cierra]
        }
    }
    
    //verificador del arbol
    public boolean isValidBST() {
        return isValidBST(this.root, null, null);
    }
    
    private boolean isValidBST(NodeBST<E> node, E min, E max) {
        if (node == null) {
            return true;
        }

        // todos los nodos del subarbol izquierdo deben ser menores que la raiz
        if (min != null && node.data.compareTo(min) <= 0) {
            return false;
        }
        //  lo contrario 
        if (max != null && node.data.compareTo(max) >= 0) {
            return false;
        }

        //el maximo permitido es el nodo actual, si va por la izq
        // el minimo permitido es el nodo actual ,si va por la der
        return isValidBST(node.left, min, node.data) && isValidBST(node.right, node.data, max);
    }
}