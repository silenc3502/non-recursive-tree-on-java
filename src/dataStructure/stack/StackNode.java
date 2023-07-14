package dataStructure.stack;

public class StackNode<T> {
    private T data;
    private StackNode<T> next;

    public StackNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public StackNode<T> getNext() {
        return next;
    }

    public void setNext(StackNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
