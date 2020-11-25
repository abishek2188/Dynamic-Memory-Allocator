// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java
import java.util.Stack;
public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree root = this.findRoot();
        BSTree parent = root;
        BSTree temp = new BSTree(address,size,key);
        BSTree current = parent.right;
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
        return temp;
    }

    public boolean Delete(Dictionary e)
    { 
        if (e==null){
            return false;
        }
        BSTree root = this.findRoot();
        BSTree parent = root;
        BSTree current = parent.right;
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
                // BSTree temp = current.right.getFirst_helper();
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
                
                BSTree temp = current.right.getFirst_helper();
                current.left.parent = temp;
                temp.left = current.left;
                if (temp.parent != current){
                    temp.parent.left = temp.right;
                    if (temp.right !=null){
                        temp.right.parent = temp.parent;
                    }
                    temp.right = current.right;
                    current.right.parent = temp;
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
            return true;
        }
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        BSTree current = this.findRoot().right;
        if (exact ==true){
            while(current != null){
                if (current.key == key){
                    BSTree x = current.Find_rec(key, true);
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
                    BSTree x = current.Find_rec(key, false);
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

    public BSTree getFirst()
    { 
        BSTree current = this.findRoot().right;
        BSTree parent = current;
        while (current != null){
            parent = current;
            current = current.left;
        }
        return parent;
    }

    public BSTree getNext()
    { 
        if (this.parent == null){
            return null;
        }
        if (this.right != null){
            return this.right.getFirst_helper();
        }
        BSTree current = this;
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
        BSTree slow = this;
        BSTree fast = this;
        while (fast !=null && fast.parent != null){
            fast=fast.parent.parent;
            slow=slow.parent;
            if (slow==fast){
                return false;
            }
        }

        BSTree root = this.findRoot();
        if(root.right==null){
            return true;
        }
        BSTree current = root.right;
        if (current.parent != root){
            return false;
        }
        Stack<BSTree> bsStack = new Stack<BSTree>();
        bsStack.push(current);
        while (bsStack.empty() == false){
            BSTree x = bsStack.pop();
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
        bsStack = new Stack<BSTree>();
        bsStack.push(current);
        while (bsStack.empty() == false){
            BSTree x = bsStack.pop();
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

    private BSTree findRoot()
    {
        if (this.parent == null){
            return this;
        }
        else{
            return this.parent.findRoot();
        }
    }

    private BSTree getFirst_helper()
    { 
        BSTree current = this;
        if (current.parent == null){
            current = current.right;
        }
        BSTree parent = current;
        while (current != null){
            parent = current;
            current = current.left;
        }
        return parent;
    }

    private BSTree getLast_helper()
    {
        BSTree current = this;
        if (current.parent == null){
            current = current.right;
        }
        BSTree parent = current;
        while (current != null){
            parent = current;
            current = current.right;
        }
        return parent;
    }

    private BSTree Find_rec(int key, boolean exact)
    {
        BSTree current = this.left;
        if (current == null){
            return null;
        }
        if (exact ==true){
            while(current != null){
                if (current.key == key){
                    BSTree x = current.Find_rec(key, true);
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
                    BSTree x = current.Find_rec(key, false);
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

    private boolean isLessthan(BSTree child){
        int key = child.key;
        int address = child.address;
        int size = child.size;
        BSTree current = this;
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

    private boolean isGreaterthan(BSTree child){
        int key = child.key;
        int address = child.address;
        int size = child.size;
        BSTree current = this;
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


 


