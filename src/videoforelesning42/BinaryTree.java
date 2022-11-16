package videoforelesning42;


import java.util.ArrayDeque;

public class BinaryTree {
    static class BinaryTreeNode {
        char value;
        BinaryTreeNode left_child;
        BinaryTreeNode right_child;

        BinaryTreeNode(char value) {
            this.value = value;
            this.left_child = null;
            this.right_child = null;
        }

        BinaryTreeNode addLeftChild(char value) {
            this.left_child = new BinaryTreeNode(value);
            return this.left_child;
        }
        BinaryTreeNode addRightChild(char value) {
            this.right_child = new BinaryTreeNode(value);
            return this.right_child;
        }

    }

    static void printLevelOrder(BinaryTreeNode root) {
        ArrayDeque<BinaryTreeNode> queue = new ArrayDeque<>();

        queue.addFirst(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.removeFirst();

            if (current.left_child!=null) {
                queue.addLast(current.left_child);
            }
            if (current.right_child!=null) {
                queue.addLast(current.right_child);
            }

            System.out.print(current.value + " ");
        }
    }

    static void printPreOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        printPreOrder(node.left_child);
        printPreOrder(node.right_child);
    }

    static void printInOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        printPreOrder(node.left_child);
        System.out.print(node.value + " ");
        printPreOrder(node.right_child);
    }

    static void printPostOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        printPreOrder(node.left_child);
        printPreOrder(node.right_child);
        System.out.print(node.value + " ");
    }

    static void printPreOrderNonRecursive(BinaryTreeNode root) {
        ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();

        stack.addLast(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode current = stack.removeLast();

            if (current.right_child!=null) {
                stack.addLast(current.right_child);
            }
            if (current.left_child!=null) {
                stack.addLast(current.left_child);
            }


            System.out.print(current.value + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode('A');

        BinaryTreeNode b = root.addLeftChild('B');
        BinaryTreeNode c = root.addRightChild('C');

        BinaryTreeNode d = b.addLeftChild('D');
        BinaryTreeNode e = b.addRightChild('E');
        BinaryTreeNode f = c.addLeftChild('F');
        BinaryTreeNode g = c.addRightChild('G');

        //printLevelOrder(root);
        System.out.print("Pre order: ");
        printPreOrder(root);
        System.out.println();
        System.out.print("In order: ");
        printInOrder(root);
        System.out.println();
        System.out.print("Post order: ");
        printPostOrder(root);

        System.out.println("Pre order non-recursive:");
        printPreOrderNonRecursive(root);
    }
}
