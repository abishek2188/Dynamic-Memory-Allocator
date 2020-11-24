// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

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
                BSTree temp = current.right.getFirst_helper();
                current.key = temp.key;
                current.size = temp.size;
                current.address = temp.address;
                if (temp.parent == current){
                    current.right = temp.right;
                }
                else{
                    temp.parent.left = temp.right;
                }
                if (temp.right != null){
                    temp.right.parent = temp.parent;
                }
                //below code for this.delete(this)                
                if (temp.parent == current){
                    direction1 = direction;
                }
                else if(temp.parent.parent == current){
                    direction1 = 0;
                }
                else{
                    direction1 = 1;
                }
                current = temp;

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
        return false;
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

}


 


