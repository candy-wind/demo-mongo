package doubletree;

/**
 * @Author candy-wind
 * @Data: 2020-04-24 17:14
 * @Version 1.0
 */


public class Node {

    int value;
    Node left;
    Node right;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }



    public Node(int value){
        this.value = value;
    }
}
