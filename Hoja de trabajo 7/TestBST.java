public class TestBST {
    public static void main(String[] args) {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>();

        tree.insert(new Association<>("dog", "perro"));
        tree.insert(new Association<>("house", "casa"));

        Association<String, String> result = tree.search(new Association<>("dog", ""));

        if (result != null && result.getValue().equals("perro")) {
            System.out.println("Test OK");
        } else {
            System.out.println("Test FALLÓ");
        }
    }
}