class BinaryTree<E extends Comparable<E>> {

    class Node {
        E data;
        Node left, right;

        Node(E data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node root;

    public void insert(E data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, E data) {
        if (root == null) return new Node(data);

        if (data.compareTo(root.data) < 0)
            root.left = insertRec(root.left, data);
        else
            root.right = insertRec(root.right, data);

        return root;
    }

    public E search(E target) {
        return searchRec(root, target);
    }

    private E searchRec(Node root, E target) {
        if (root == null) return null;

        int cmp = target.compareTo(root.data);

        if (cmp == 0) return root.data;
        else if (cmp < 0) return searchRec(root.left, target);
        else return searchRec(root.right, target);
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.data);
            inOrderRec(root.right);
        }
    }
}