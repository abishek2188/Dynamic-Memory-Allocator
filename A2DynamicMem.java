// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
    public void Defragment() {
        BSTree index = new BSTree();
        Dictionary current = freeBlk.getFirst();
        if (current!=null){
            while (current != null){
                index.Insert(current.address,current.size,current.address);
                current = current.getNext();                
            }
            Dictionary x = index.getFirst();
            int start = x.address;
            int change = x.size;
            int endcheck = start +  change;
            Dictionary y = x.getNext();
            while(y != null){
                if (y.address == endcheck){
                    Dictionary temp = new BSTree(x.address,x.size,x.size);
                    freeBlk.Delete(temp);
                    endcheck = endcheck + y.size;
                }
                else{
                    if (endcheck - start != x.size){
                        Dictionary temp = new BSTree(x.address,x.size,x.size);
                        freeBlk.Delete(temp);
                        freeBlk.Insert(start, endcheck - start, endcheck - start); 
                    }
                    start = y.address;
                    endcheck = start + y.size;
                }
                x = y;
                y = y.getNext();
            }
            if(endcheck - start != x.size){
                Dictionary temp = new BSTree(x.address,x.size,x.size);
                freeBlk.Delete(temp);
                freeBlk.Insert(start, endcheck - start, endcheck - start);
            }
        }

        return ;
    }
    
    // public int Allocate(int blockSize) {
    //     if (blockSize <= 0){
    //         return -1;
    //     }
    //     Dictionary x = freeBlk.Find(blockSize, false);
    //     if (x!=null){
    //         allocBlk.Insert(x.address, blockSize, x.address);
    //         int address1 = x.address;
    //         int size1 = x.size;
    //         freeBlk.Delete(x);
    //         int t = size1 - blockSize;
    //         if (t>0){
    //             freeBlk.Insert(address1+blockSize,t,t);
    //         }
    //         return address1;
    //     }
    //     else{
    //         return -1;
    //     }
    // } 
    // // return 0 if successful, -1 otherwise
    // public int Free(int startAddr) {
    //     Dictionary x = allocBlk.Find(startAddr, true);
    //     if (x!=null){
    //         freeBlk.Insert(x.address, x.size, x.size);
    //         allocBlk.Delete(x);
    //         return 0;
    //     }
    //     else{
    //         return -1;
    //     }
    // }
}