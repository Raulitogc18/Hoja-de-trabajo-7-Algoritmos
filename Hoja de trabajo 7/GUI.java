import java.awt.*;
import java.io.*;
import javax.swing.*;

public class GUI extends JFrame {

    private JTextArea inputText;
    private JTextArea outputText;
    private BinaryTree<Association<String, String>> tree;

    public GUI() {
        setTitle("Traductor BST Inglés - Español");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tree = new BinaryTree<>();

        // ✨ Crear áreas de texto
        inputText = new JTextArea();
        outputText = new JTextArea();
        outputText.setEditable(false);

        inputText.setBorder(BorderFactory.createTitledBorder("Texto en inglés"));
        outputText.setBorder(BorderFactory.createTitledBorder("Traducción"));

        JScrollPane leftScroll = new JScrollPane(inputText);
        JScrollPane rightScroll = new JScrollPane(outputText);

        // 🔥 CLAVE: dividir pantalla
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, rightScroll);
        splitPane.setDividerLocation(400);

        add(splitPane, BorderLayout.CENTER);

        // Botones
        JPanel panel = new JPanel();

        JButton loadBtn = new JButton("Cargar Diccionario");
        JButton translateBtn = new JButton("Traducir");

        panel.add(loadBtn);
        panel.add(translateBtn);

        add(panel, BorderLayout.SOUTH);

        // Acciones
        loadBtn.addActionListener(e -> loadDictionary());
        translateBtn.addActionListener(e -> translateText());
    }

    // 📚 Cargar diccionario (seguro)
    private void loadDictionary() {
        tree = new BinaryTree<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("diccionario.txt"));
            String line;

            while ((line = br.readLine()) != null) {

                line = line.trim();

                if (!line.contains(",") || line.length() == 0) continue;

                line = line.replace("(", "").replace(")", "");
                String[] parts = line.split(",");

                if (parts.length < 2) continue;

                String eng = parts[0].trim().toLowerCase();
                String esp = parts[1].trim();

                tree.insert(new Association<>(eng, esp));
            }

            br.close();
            JOptionPane.showMessageDialog(this, "Diccionario cargado!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // 🌍 Traducir
    private void translateText() {
        String text = inputText.getText();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese texto primero.");
            return;
        }

        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");

        for (String word : words) {

            String clean = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            String punctuation = word.replaceAll("[a-zA-Z]", "");

            Association<String, String> found =
                    tree.search(new Association<>(clean, ""));

            if (found != null) {
                result.append(found.getValue()).append(punctuation).append(" ");
            } else {
                result.append("*").append(word).append("* ");
            }
        }

        outputText.setText(result.toString());
    }
}