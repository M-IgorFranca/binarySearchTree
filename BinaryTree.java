public class BinaryTree {
    int repeatedNodes = 0;
    Node root;
    public void addNode(int key){
        Node newNode = new Node(key);

        if(root == null){
            root = newNode;
        }
        else{
            Node currentNode = root;
            Node parent;

            while(true){
                parent = currentNode;

                if(key < currentNode.key) {
                    currentNode = currentNode.leftChild;
                    if (currentNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else if (key > currentNode.key){
                    currentNode = currentNode.rightChild;
                    if(currentNode == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
                else{
                    repeatedNodes++;
                    return;
                }
            }
        }
    }
    public void inOrderTraverseTree(Node currentNode){
        if(currentNode != null){
            inOrderTraverseTree(currentNode.leftChild);
            System.out.println(currentNode);
            inOrderTraverseTree(currentNode.rightChild);
        }
    }

    public void preorderTraverseTree(Node currentNode){
        if(currentNode != null){
            System.out.println(currentNode);
            preorderTraverseTree(currentNode.leftChild);
            preorderTraverseTree(currentNode.rightChild);
        }
    }

    public void postOrderTraverseTree(Node currentNode){
        if(currentNode != null){
            postOrderTraverseTree(currentNode.leftChild);
            postOrderTraverseTree(currentNode.rightChild);
            System.out.println(currentNode);
        }
    }

    public Node findNode(int key){
        Node currentNode = root;

        while(currentNode.key != key){
            if(key < currentNode.key){
                currentNode = currentNode.leftChild;
            }
            else {
                currentNode = currentNode.rightChild;
            }
            if(currentNode == null){
                return null;
            }
        }
        return currentNode;
    }

    public boolean remove(int key){
        Node currentNode = root;
        Node parent = root;

        boolean isItALeftChild = true;

        while(currentNode.key != key){
            parent = currentNode;

            if(key < currentNode.key){
                isItALeftChild = true;
                currentNode = currentNode.leftChild;
            }
            else {
                isItALeftChild = false;
                currentNode = currentNode.rightChild;
            }
            if(currentNode == null){
                return false;
            }
        }
        if(currentNode.leftChild == null && currentNode.rightChild == null){
            if(currentNode == root){
                root = null;
            }
            else if(isItALeftChild){
                parent.leftChild = null;
            }
            else{
                parent.rightChild = null;
            }
        } else if (currentNode.rightChild == null) {
            if(currentNode == root){
                root = currentNode.leftChild;
            }
            else if(isItALeftChild){
                parent.leftChild = currentNode.leftChild;
            }
            else {
                parent.rightChild = currentNode.leftChild;
            }
        }
        else if(currentNode.leftChild == null){
            if(currentNode == root){
                root = currentNode.rightChild;
            }
            else if(isItALeftChild){
                parent.leftChild = currentNode.rightChild;
            }
            else{
                parent.rightChild = currentNode.rightChild;
            }
        }
        else {
            Node replacement = getReplacementNode(currentNode);

            if(currentNode == root){
                root = replacement;
            }
            else if(isItALeftChild){
                parent.leftChild = replacement;
            }
            else {
                parent.rightChild = replacement;
            }
            replacement.leftChild = currentNode.leftChild;
        }
        return true;
    }

    public Node getReplacementNode(Node replacedNode){
        Node replacementParent = replacedNode;
        Node replacement = replacedNode;
        Node currentNode =  replacedNode.rightChild;

        while(currentNode != null){
            replacementParent = replacement;
            replacement = currentNode;
            currentNode = currentNode.leftChild;
        }
        if(replacement != replacedNode.rightChild){
            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;
        }
        return replacement;
    }

    public void findRepeatedNodes(){
        System.out.print("The numbers os the repeated numbers is: ");
        System.out.println(repeatedNodes);
    }

    public static void main(String[] args){
        BinaryTree theTree = new BinaryTree();
        int[] listNumbers = {1,2,3,4,5,6,7,8,9,10,1,2,3};
        for (int i = 0; i < listNumbers.length; i++) {
            theTree.addNode(listNumbers[i]);
        }
        theTree.findRepeatedNodes();
    }
}

class Node{
    int key;
    Node leftChild;
    Node rightChild;

    Node(int key){
        this.key = key;
    }
}