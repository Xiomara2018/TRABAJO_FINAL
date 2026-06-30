package estructuras;

public class Cola<T> {
    private NodeQueue<T> first; //primer elemento de la cola
    private NodeQueue<T> last; //ultimo elemento de la cola
    private int size; //tamaño de la cola

    public Cola(){ //constructor
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public boolean IsEmpty(){ //la cola esta vacia?
        return first == null; //devolvera true si esta vacia
    }

    public void enqueue(T x){ //Insertar elemento
        NodeQueue<T> newnode = new NodeQueue<>(x); //creacion de nuevo nodo
        if (IsEmpty()){  //si esta vacio los nodos first y last se les asignara el valor de newnode
            first = newnode;
            last = newnode;
        }
        else{ //caso contrario se enlazara el proximo nodo del last y a continuacion se asignara el ultimo nuevo dato como last
            last.next = newnode;
            last  = newnode;


        }
        size++; //a cada insercion el tamaño de la cola incrementara +1
    }

    public T dequeue() throws ExceptionIsEmpty{ //sacar de la cola 
        if(IsEmpty()){ //si la cola esta vacia se mandara un mensaje
            throw new ExceptionIsEmpty("La cola de solicitudes esta vacia");
        }
        T aux = this.first.getdata(); //se obtendra el primer dato con la variable auxiliar aux
        this.first = this.first.next; //y ahora el primero vendra a ser el siguiente del primero, desencolando el primer nodo de la cola
        if (this.first == null){ //si ya no hay mas elementos, el nodo last tambien sera null
            this.last = null;
        }
        size--; //por cada desencolamiento se reducira el tamaño de la cola

        return aux; //retornara el valor auxiliar
    }

    public T peek() throws ExceptionIsEmpty{ //deja ver el primero en la cola sin desencolarlo
        if(IsEmpty()){
            throw new ExceptionIsEmpty("La cola de solicitudes esta vacia");
        }
        return this.first.getdata();
    }

    public int size(){
        return size;
    }

    public String mostrar(){
        if(IsEmpty()){
            return "La cola de solicitudes está vacía.";
        }
        
        StringBuilder textoCola = new StringBuilder();
        textoCola.append("Cola graficada: \n");
        
        NodeQueue<T> rec = first;
        
        while (rec != null){ // Corregido: antes decía rec.next != null y se comía el último
            textoCola.append(rec.getdata().toString());
            if(rec.getnext() != null){
                textoCola.append(" -> ");
            }
            rec = rec.getnext();
        }
        
        return textoCola.toString();

    
    
}
