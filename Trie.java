import java.util.*;
import java.util.Arrays;

public class Trie {
    
    private Node root; //create dummy node

    //Initialize data structure
    public Trie() {
        root = new Node('\0');
    }
    
    //Inserts numberber into phonebook
    public void add(String number) {
        Node curr = root;
        for (int i = 0; i < number.length();i++) {
            int c = Integer.parseInt(number.substring(i, i+1));
            if (curr.children[c] == null) {
                curr.children[c] = new Node(c);
            }
            curr = curr.children[c];
        }
        //mark current Node 
        curr.isNum = true;
    }

    public boolean find(String number) {
        Node node = getNode(number);
        return node != null && node.isNum;
    }
    
    //Returns true if there is any word in the trie that starts with the given prefix 
    public boolean startsWith(String prefix) {
        return getNode(prefix) != null;
    }

    public String preOrder(){
        return preOrder(root);

    }

    private String preOrder(Node curr){
        String hold = "";
        if (curr == null){
            return "";
        }else{
            String lmr = curr.c + " -> ";
            for (int i = 0; i < curr.children.length;i++) {
                lmr += preOrder(curr.children[i]);
            }
            return lmr;
        }
    }

    //returns last node that were looking for
    private Node getNode(String number) {
        Node curr = root;
        for (int i = 0; i < number.length(); i++) {
            int c = Integer.parseInt(number.substring(i, i+1));
            if (curr.children[c] == null) return null;
            curr = curr.children[c];
        }
        return curr;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(Node current, String word, int index) {
        if (index == word.length()) {
            if (!current.isNum) {
                return false;
            }
            current.isNum = false;
            return current.children.length == 0;
        }
        char ch = word.charAt(index);
        int chz = Character.getNumericValue(ch);
        Node node = current.children[chz];
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        return false;
    }


    class Node {
        public int c;
        public boolean isNum;
        public Node[] children;

        public Node(int c) {
            this.c = c;
            isNum = false;
            children = new Node[26];
        }

    }
}

