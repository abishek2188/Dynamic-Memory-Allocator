// Class: Height balanced AVL Tree
// Binary Search Tree
import java.util.Stack;
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
        AVLTree z = temp;
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
        if (e==null){
            return false;
        }
        AVLTree root = this.findRoot();
        AVLTree parent = root;
        AVLTree current = parent.right;
        AVLTree deleteParent = new AVLTree();
        int address = e.address;
        int size = e.size;
        int key = e.key;
        int direction = 0;  //direction is 0 if current is right child of parent, 1 if current is left child of parent
        int direction1 = 0; //direction1 for this.delete(this)
        int direction2;
        while (current != null){
            direction2 = direction1;
            direction1 = direction;
            parent = current;
            if (current.key>key){
                current=current.left;
                direction = 1;
            }
            else if (current.key<key){
                current=current.right;
                direction = 0;
            }
            else{
                if (current.address>address){
                    current=current.left;
                    direction = 1;
                }
                else if (current.address<address){
                    current=current.right;
                    direction = 0;
                }
                else{
                    if (current.size>size){
                        current=current.left;
                        direction = 1;
                    }
                    else if (current.size<size){
                        current=current.right;
                        direction = 0;
                    }
                    else{
                        direction1 = direction2;
                        parent = current.parent;
                        deleteParent = parent;
                        break;
                    }
                }
            }
        }
        if (current != null){
            if (current.left == null && current.right == null){
                if (direction == 1){
                    parent.left = current.right;
                }
                else{
                    parent.right = current.right;
                }
            }
            else if (current.left == null){
                current.right.parent = parent;
                if (direction == 1){
                    parent.left = current.right;
                }
                else{
                    parent.right = current.right;                    
                }
            }
            else if (current.right == null){
                current.left.parent = parent;
                if (direction == 1){
                    parent.left = current.left;
                }
                else{
                    parent.right = current.left;
                }
            }
            else{
                // AVLTree temp = current.right.getFirst_helper();
                // current.key = temp.key;
                // current.size = temp.size;
                // current.address = temp.address;
                // if (temp.parent == current){
                //     current.right = temp.right;
                // }
                // else{
                //     temp.parent.left = temp.right;
                // }
                // if (temp.right != null){
                //     temp.right.parent = temp.parent;
                // }
                // //below code for this.delete(this)                
                // if (temp.parent == current){
                //     direction1 = direction;
                // }
                // else if(temp.parent.parent == current){
                //     direction1 = 0;
                // }
                // else{
                //     direction1 = 1;
                // }
                // current = temp;
                
                AVLTree temp = current.right.getFirst_helper();
                current.left.parent = temp;
                temp.left = current.left;
                if (temp.parent != current){
                    deleteParent = temp.parent;
                    temp.parent.left = temp.right;
                    if (temp.right !=null){
                        temp.right.parent = temp.parent;
                    }
                    temp.right = current.right;
                    current.right.parent = temp;
                }
                else{
                    deleteParent = temp;
                }
                if (direction == 1){
                    parent.left = temp;
                }
                else{
                    parent.right = temp;
                }
                temp.parent = parent;
                
            }
            if (this == current){   //code for this.delete(this)
                if (deleteParent == current.parent){
                    deleteParent = current;
                }
                parent = current.parent;
                current.parent = parent.parent;
                current.key = parent.key;
                current.left = parent.left;
                current.right = parent.right;
                current.address = parent.address;
                current.size = parent.size;
                if (parent.parent != null){
                    if (direction1 == 1){
                        parent.parent.left = current;
                    }
                    else{
                        parent.parent.right = current;
                    }
                }
                if (parent.left != null){
                    parent.left.parent = current;
                }
                if (parent.right != null){
                    parent.right.parent = current;
                }
            }

            AVLTree z = deleteParent;
            while(z.parent != null){
                int temp10 = z.height;
                int balance = height(z.left) - height(z.right);
                if (balance > 1){
                    AVLTree y = z.left;
                    if (height(y.left)>=height(y.right)){
                        rightRotate(z);
                        z=y;
                    }
                    else{
                        AVLTree temp = y.right;
                        leftRotate(y);
                        rightRotate(z);
                        z=temp;
                    }
                }
                else if (balance < -1){
                    AVLTree y = z.right;
                    if (height(y.right)>=height(y.left)){
                        leftRotate(z);
                        z=y;
                    }
                    else{
                        AVLTree temp = y.left;
                        rightRotate(y);
                        leftRotate(z);
                        z = temp;
                    }
                }
                else{
                    z.height = Math.max(height(z.left),height(z.right)) + 1;
                }
                if (temp10 == z.height){
                    break;
                }
                z = z.parent;
            
            }
            
            return true;
        }
        return false;
    }
        
    public AVLTree Find(int key, boolean exact)
    { 
        AVLTree current = this.findRoot().right;
        if (exact ==true){
            while(current != null){
                if (current.key == key){
                    AVLTree x = current.Find_rec(key, true);
                    if (x==null){
                        return current;
                    }
                    else{
                        return x;
                    }
                }
                else if (current.key > key){
                    current=current.left;
                }
                else{
                    current=current.right;
                }
            }
        }
        else{
            while(current != null){
                if (current.key >= key){
                    AVLTree x = current.Find_rec(key, false);
                    if (x==null){
                        return current;
                    }
                    else{
                        return x;
                    }
                }
                else{
                    current=current.right;
                }
            }
        }
        return null;
    }

    public AVLTree getFirst()
    { 
        AVLTree current = this.findRoot().right;
        AVLTree parent = current;
        while (current != null){
            parent = current;
            current = current.left;
        }
        return parent;
    }

    public AVLTree getNext()
    {
        if (this.parent == null){
            return null;
        }
        if (this.right != null){
            return this.right.getFirst_helper();
        }
        AVLTree current = this;
        while(current.parent != null && current.parent.left != current){
            current = current.parent;
        }
        if (current.parent != null){
            return current.parent;
        }
        return null;
    }

    public boolean sanity()
    { 
        AVLTree slow = this;
        AVLTree fast = this;
        while (fast !=null && fast.parent != null){
            fast=fast.parent.parent;
            slow=slow.parent;
            if (slow==fast){
                return false;
            }
        }

        AVLTree root = this.findRoot();
        if(root.key!=-1 || root.address !=-1 || root.size != -1){
            return false;
        }
        if(root.right==null){
            return true;
        }
        AVLTree current = root.right;
        if (current.parent != root){
            return false;
        }
        Stack<AVLTree> bsStack = new Stack<AVLTree>();
        bsStack.push(current);
        while (bsStack.empty() == false){
            AVLTree x = bsStack.pop();
            if (x.height != Math.max(height(x.left),height(x.right))+1){
                return false;
            }
            int balance = height(x.left) - height(x.right);
            if (balance > 1 || balance < -1){
                return false;
            }
            if (x.right != null){
                if (x.right.parent != x){
                    return false;
                }
                if (!x.isLessthan(x.right)){
                    return false;
                }
                bsStack.push(x.right);
            }
            if (x.left != null){
                if (x.left.parent != x){
                    return false;
                }
                if (!x.isGreaterthan(x.left)){
                    return false;
                }
                bsStack.push(x.left);
            }
        }
        bsStack = new Stack<AVLTree>();
        bsStack.push(current);
        while (bsStack.empty() == false){
            AVLTree x = bsStack.pop();
            if (x.right != null){
                if (!x.isLessthan(x.right.getFirst_helper())){
                    return false;
                }
                bsStack.push(x.right);
            }
            if (x.left != null){
                if (!x.isGreaterthan(x.left.getLast_helper())){
                    return false;
                }
                bsStack.push(x.left);
            }
        }

        return true;
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

    private AVLTree getFirst_helper()
    { 
        AVLTree current = this;
        if (current.parent == null){
            current = current.right;
        }
        AVLTree parent = current;
        while (current != null){
            parent = current;
            current = current.left;
        }
        return parent;
    }

    private AVLTree getLast_helper()
    {
        AVLTree current = this;
        if (current.parent == null){
            current = current.right;
        }
        AVLTree parent = current;
        while (current != null){
            parent = current;
            current = current.right;
        }
        return parent;
    }

    private AVLTree Find_rec(int key, boolean exact)
    {
        AVLTree current = this.left;
        if (current == null){
            return null;
        }
        if (exact ==true){
            while(current != null){
                if (current.key == key){
                    AVLTree x = current.Find_rec(key, true);
                    if (x==null){
                        return current;
                    }
                    else{
                        return x;
                    }
                    
                }
                else if (current.key > key){
                    current=current.left;
                }
                else{
                    current=current.right;
                }
            }
        }
        else{
            while(current != null){
                if (current.key >= key){
                    AVLTree x = current.Find_rec(key, false);
                    if (x==null){
                        return current;
                    }
                    else{
                        return x;
                    }
                }
                else{
                    current=current.right;
                }
            }
        }
        return null;
    }
}


