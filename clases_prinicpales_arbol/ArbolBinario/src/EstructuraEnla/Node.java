package EstructuraEnla;

public class Node<T> {
	public T data;
	public Node<T> right ;
	public Node<T> left ;	
	
	public Node(T data) {
		this.data = data;
		this.right = null;
		this.left = null;
	}
	
	
	//construtor para nodo hijos
	public Node(T data, Node<T> left, Node<T> right) {
		this.data = data;
        this.left = left;
        this.right = right;
    }


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}
		
	
	
}

