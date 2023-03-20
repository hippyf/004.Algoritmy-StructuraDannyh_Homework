public class RedBlackTree {

    private Node root;
    private class Node {
        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;
    }

    private enum Color {
        RED, BLACK
    }
/**
 * Добавление Ноду (если дерево не содержит элементов, то добавить root)
 * @param value значение
 * @return
 */
    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root,value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

/**
 * Добавить Ноду
 * @param node наименовение ноды
 * @param value значение
 * @return
 */
    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.leftChild != null) {
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = rebalance(node.leftChild);
                    return result;
                } else {
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            } else {
                if (node.rightChild != null) {
                    boolean result = addNode(node.rightChild,value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }
/**
 * Смена цвета (только когда у детей 2 красных цвета)
 * @param node
 */
    private void colorSwap(Node node) {
        node.leftChild.color = Color.BLACK;
        node.rightChild.color = Color.BLACK;
        node.color = Color.RED;
    }
/**
 * Левосторонний поворот
 * @param node
 * @return
 */
    private Node leftSwap(Node node) {
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

/**
 * Правостороний поворот
 * @param node
 * @return
 */
    private Node rightSwap(Node node) {
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }
/**
 * Ребалансировка
 * @param node
 * @return
 */
    private Node rebalance(Node node) {
        Node result = node;
        boolean needBalancing;
        do {
            needBalancing = false;
            if (result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftChild == null || result.leftChild.color == Color.BLACK)) {
                needBalancing = true;
                result = rightSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild != null && result.leftChild.leftChild.color == Color.RED) {
                needBalancing = true;
                result = leftSwap(result);
            }
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.rightChild != null && result.rightChild.color == Color.RED) {
                needBalancing = true;
                colorSwap(result);
            }
        } while(needBalancing);
        return result;
    }
} 