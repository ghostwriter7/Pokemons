package tree;

public record BinaryNode<T>(T value, BinaryNode<T> left, BinaryNode<T> right) {
}
