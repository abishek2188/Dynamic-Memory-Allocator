// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree root = this.findRoot();
        AVLTree parent = root;
        AVLTree temp = new AVLTree(address,size,key);
        AVLTree current = parent.right;
        while (current != null){
            parent = current;
            if (current.key>key){
                current=current.left;
            }
            else if (current.key<key){
                current=current.right;
            }
            else{
                if (current.address>address){
                    current=current.left;
                }
                else if (current.address<address){
                    current=current.right;
                }
                else{
                    if (current.size>size){
                        current=current.left;
                    }
                    else if (current.size<size){
                        current=current.right;
                    }
                    else{
                        return null;
                    }
                }
            }
        }
        if (parent == root){
            parent.right = temp;
        }
        else if (parent.key>key){
            parent.left = temp;
        }
        else if (parent.key<key){
            parent.right = temp;
        }
        else{
            if (parent.address>address){
                parent.left = temp;
            }
            else if (parent.address<address){
                parent.right = temp;
            }
            else{
                if (parent.size>size){
                    parent.left = temp;
                }
                else if (parent.size<size){
                    parent.right = temp;
                }
                else{
                    return null;
                }    
            }
        }
        temp.parent = parent;
        AVLTree z = parent;
        AVLTree y = z;
        AVLTree x = z;
        while(z.parent != null){
            int balance = height(z.left) - height(z.right);
            if (balance > 1){
                if (y.left == x){
                    rightRotate(z);
                }
                else{
                    leftRotate(y);
                    rightRotate(z);
                }
                return temp;
            }
            else if (balance < -1){
                if (y.right == x){
                    leftRotate(z);
                }
                else{
                    rightRotate(y);
                    leftRotate(z);
                }
                return temp;
            }
            z.height = Math.max(height(z.left),height(z.right)) + 1;
            x=y;
            y=z;
            z = z.parent;
            
        }
        return temp;
    }

    public boolean Delete(Dictionary e)
    {
        return false;
    }
        
    // public AVLTree Find(int k, boolean exact)
    // { 
    //     return null;
    // }

    // public AVLTree getFirst()
    // { 
    //     return null;
    // }

    // public AVLTree getNext()
    // {
    //     return null;
    // }

    public boolean sanity()
    { 
        return false;
    }

    private AVLTree findRoot()
    {
        if (this.parent == null){
            return this;
        }
        else{
            return this.parent.findRoot();
        }
    }

    private int height(AVLTree node){
        if (node == null){
            return -1;
        }
        else{
            return node.height;
        }
    }

    private void rightRotate(AVLTree node){
        AVLTree y = node.left;
        AVLTree T3 = y.right;
        node.left = T3;
        if (T3 != null){
            T3.parent = node;
        }
        y.right = node;
        y.parent = node.parent;
        node.parent = y;
        if (y.parent.parent == null || y.parent.isLessthan(node)){
            y.parent.right = y;
        }
        else{
            y.parent.left = y;
        }
        node.height = Math.max(height(node.left),height(node.right)) + 1;
        y.height = Math.max(height(y.left),height(y.right)) + 1;

    }

    private void leftRotate(AVLTree node){
        AVLTree y = node.right;
        AVLTree T3 = y.left;
        node.right = T3;
        if (T3 != null){
            T3.parent = node;
        }
        y.left = node;
        y.parent = node.parent;
        node.parent = y;
        if (y.parent.parent == null || y.parent.isLessthan(node)){
            y.parent.right = y;
        }
        else{
            y.parent.left = y;
        }
        node.height = Math.max(height(node.left),height(node.right)) + 1;
        y.height = Math.max(height(y.left),height(y.right)) + 1;

    }
    

    private boolean isLessthan(AVLTree child){
        int key = child.key;
        int address = child.address;
        int size = child.size;
        AVLTree current = this;
        if (current.key>key){
            return false;
        }
        else if (current.key<key){
            return true;
        }
        else{
            if (current.address>address){
                return false;
            }
            else if (current.address<address){
                return true;
            }
            else{
                if (current.size>size){
                    return false;
                }
                else if (current.size<size){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
    }

    private boolean isGreaterthan(AVLTree child){
        int key = child.key;
        int address = child.address;
        int size = child.size;
        AVLTree current = this;
        if (current.key>key){
            return true;
        }
        else if (current.key<key){
            return false;
        }
        else{
            if (current.address>address){
                return true;
            }
            else if (current.address<address){
                return false;
            }
            else{
                if (current.size>size){
                    return true;
                }
                else if (current.size<size){
                    return false;
                }
                else{
                    return false;
                }
            }
        }
    }
}


