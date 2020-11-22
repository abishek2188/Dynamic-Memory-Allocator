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
        BSTree current = this.findRoot();
        BSTree temp = new BSTree(address,size,key);
        BSTree parent = current;
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
        if (parent.key>key){
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
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        return null;
    }

    public BSTree getFirst()
    { 
        BSTree current = this;
        BSTree parent = current;
        while (current != null){
            parent = current;
            current = current.left;
        }
        return parent;
    }

    public BSTree getNext()
    { 
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

}


 


