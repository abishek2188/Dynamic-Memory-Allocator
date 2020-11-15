// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        Dictionary x = freeBlk.Find(blockSize, false);
        if (x!=null){
            freeBlk.Delete(x);
            allocBlk.Insert(x.address, blockSize, x.address);
            int t = x.size - blockSize;
            if (t>0){
                freeBlk.Insert(x.address+blockSize,t,t);
            }
            return x.address;
        }
        else{
            return -1;
        }
    } 
    
    public int Free(int startAddr) {
        Dictionary x = allocBlk.Find(startAddr, true);
        if (x!=null){
            allocBlk.Delete(x);
            freeBlk.Insert(x.address, x.size, x.key);
            return 0;
        }
        else{
            return -1;
        }
    }
}