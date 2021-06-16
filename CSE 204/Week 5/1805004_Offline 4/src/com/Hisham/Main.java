package com.Hisham;

/*
 * Created By:
 * Syed Jarullah Hisham
 * Roll: 1805004
 * CSE '18 Section A1
 */

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomBST bst = new CustomBST();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printInstruction();
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    insertion();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    InOrderSuccessor();
                    break;
                case 4:
                    InOrderPredecessor();
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    depth();
                    break;
                case 7:
                    max();
                    break;
                case 8:
                    min();
                    break;
                case 9:
                    height();
                    break;
                case 10:
                    inOrder();
                    break;
                case 11:
                    preOrder();
                    break;
                case 12:
                    postOrder();
                    break;
                case 13:
                    System.out.println(ANSI_GREEN + "Size of the Tree: " + bst.getSize());
                    break;
                case 14:
                    exit = true;
                    break;
                default:
                    System.err.println("Invalid Option!! Please try again");
            }
        }
    }

    private static void printInstruction() {
        System.out.println(ANSI_RESET + "\nEnter an option to process:");
        String sb = " 1. Insert Item               2. Search Item        3. Get In Order Successor\n" +
                " 4. Get In Order Predecessor  5. Delete Item        6. Get Item Depth\n" +
                " 7. Get Max Item              8. Get Min Item       9. Get Height\n" +
                "10. Print In Order           11. Print Pre Order   12. Get Post Order\n" +
                "13. Get Size                 14. Exit\n";
        System.out.println(sb);
    }

    private static void insertion() {
        int item = scanner.nextInt();
        if (bst.insertItem(item)) {
            System.out.println(ANSI_GREEN + "Insertion Successful");
        } else {
            System.err.println("Insertion Failed");
        }
    }

    private static void search() {
        int item = scanner.nextInt();
        if (bst.searchItem(item)) {
            System.out.println(ANSI_GREEN + item + " has been found");
        } else {
            System.err.println(item + " has not been found");
        }
    }

    private static void InOrderSuccessor() {
        int item = scanner.nextInt();
        int val = bst.getInOrderSuccessor(item);
        if (val != Integer.MAX_VALUE) {
            System.out.println(ANSI_GREEN + "In-Order Successor of " + item + " is " + val);
        } else {
            System.err.println("Invalid!! In-Order Successor " + val);
        }
    }

    private static void InOrderPredecessor() {
        int item = scanner.nextInt();
        int val = bst.getInOrderPredecessor(item);
        if (val != Integer.MIN_VALUE) {
            System.out.println(ANSI_GREEN + "In-Order Predecessor of " + item + " is " + val);
        } else {
            System.err.println("Invalid!! In-Order Predecessor " + val);
        }
    }

    private static void delete() {
        int item = scanner.nextInt();
        if (bst.deleteItem(item)) {
            System.out.println(ANSI_GREEN + "Deletion Successful");
        } else {
            System.err.println("Deletion Failed");
        }
    }

    private static void depth() {
        int item = scanner.nextInt();
        int depth = bst.getItemDepth(item);
        if (depth != -1) {
            System.out.println(ANSI_GREEN + "Depth of " + item + " is: " + depth);
        } else {
            System.err.println("Invalid!! Depth of " + item + " is: " + depth);
        }
    }

    private static void max() {
        int max = bst.getMaxItem();
        if (max != Integer.MIN_VALUE) {
            System.out.println(ANSI_GREEN + "Maximum Item of the tree is " + max);
        } else {
            System.err.println("Invalid!! Tree is empty");
        }
    }

    private static void min() {
        int min = bst.getMinItem();
        if (min != Integer.MAX_VALUE) {
            System.out.println(ANSI_GREEN + "Minimum Item of the tree is " + min);
        } else {
            System.err.println("Invalid!! Tree is empty");
        }
    }

    private static void height() {
        int height = bst.getHeight();
        if (height != -1) {
            System.out.println(ANSI_GREEN + "Height of the tree is " + height);
        } else {
            System.err.println("Invalid!! Tree is empty");
        }
    }

    private static void inOrder() {
        System.out.println(ANSI_GREEN + "InOrder traversal: ");
        bst.printInOrder();
        System.out.println();
    }

    private static void preOrder() {
        System.out.println(ANSI_GREEN + "PreOrder traversal: ");
        bst.printPreOrder();
        System.out.println();
    }

    private static void postOrder() {
        System.out.println(ANSI_GREEN + "PostOrder traversal: ");
        bst.printPostOrder();
        System.out.println();
    }
}