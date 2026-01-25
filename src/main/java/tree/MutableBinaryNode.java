package tree;

public class MutableBinaryNode<T> {
    private T value;
    private MutableBinaryNode<T> left;
    private MutableBinaryNode<T> right;
    private MutableBinaryNode<T> parent;

    public MutableBinaryNode(T value, MutableBinaryNode<T> left, MutableBinaryNode<T> right, MutableBinaryNode<T> parent) {
        this.value = value;
        setLeft(left);
        setRight(right);
        this.parent = parent;
    }

    public MutableBinaryNode(T value) {
        this(value, null, null, null);
    }

    public MutableBinaryNode(T value, MutableBinaryNode<T> left, MutableBinaryNode<T> right) {
        this(value, left, right, null);
    }

    public T getValue() {
        return value;
    }

    public MutableBinaryNode<T> getLeft() {
        return left;
    }

    public MutableBinaryNode<T> getRight() {
        return right;
    }

    public MutableBinaryNode<T> getParent() {
        return parent;
    }

    public void setLeft(MutableBinaryNode<T> left) {
        this.left = left;
        if (left != null) left.setParent(this);
    }

    public void setRight(MutableBinaryNode<T> right) {
        this.right = right;
        if (right != null) right.setParent(this);
    }

    public void setParent(MutableBinaryNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");

        walk(this, sb);

        sb.append(" ]");

        return sb.toString();
    }

    private void walk(MutableBinaryNode<T> node, StringBuilder sb) {
        if (node == null) return;

        sb.append(" ").append(node.getValue());

        walk(node.getLeft(), sb);
        walk(node.getRight(), sb);
    }

    public void setValue(T value) {
        this.value = value;
    }
}
