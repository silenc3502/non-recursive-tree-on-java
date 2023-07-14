package dataStructure.stack;

import java.util.EmptyStackException;

public class ContextStack<T> {
    private StackNode<T> top;
    private int size;

    public void push(T item) {
        StackNode<T> newNode = new StackNode<>(item);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = top.getData();
        top = top.getNext();
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "ContextStack{" +
                "top=" + top +
                ", size=" + size +
                '}';
    }
}
