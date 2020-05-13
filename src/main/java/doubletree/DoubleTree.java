package doubletree;

/**
 * @Author candy-wind
 * @Data: 2020-04-24 17:14
 * @Version 1.0
 *
 * 二叉树
 */

public class DoubleTree {
    Node root;//根结点
    public void add(int value){
        Node newNode = new Node(value);
        //顺序放数据 顺序二叉树 左小右大
        if(root == null){
            root = newNode;
        }else {
            Node temp = root;
            while (true){
                if(value <temp.getValue()){
                    //小于当前节点的值左边
                    if(temp.getLeft() == null){
                        temp.setLeft(newNode);
                        break;
                    }else {
                        temp = temp.getLeft();
                    }
                }else {

                    if(temp.getRight() == null){
                        temp.setRight(newNode);
                        break;
                    }else {
                        temp = temp.getRight();
                    }
                }
            }
        }
    }

    public void show(){
        showNode(root);

    }

    public void showNode(Node node){
        System.out.print(node.getValue());//前序便利
        if(node.getLeft()!=null){
            showNode(node.getLeft());
        }
//        System.out.println(node.getValue());//前序便利

        if(node.getRight()!=null){
            showNode(node.getRight());
        }
//        System.out.println(node.getValue());//后续便利便利

    }

    public static void main(String[] args) {
        DoubleTree doubleTree = new DoubleTree();
        doubleTree.add(5);
        doubleTree.add(1);
        doubleTree.add(4);
        doubleTree.add(8);
        doubleTree.add(2);
        doubleTree.add(7);
        doubleTree.add(9);
        doubleTree.add(3);
        doubleTree.show();

       int nums[] = {4,5,6,7,0,1,2,3};

       int left = 0;
       int right = nums.length - 1;
        System.out.println(right);

       int mid = (left + right)/2;
        System.out.println(mid);

    }
}
