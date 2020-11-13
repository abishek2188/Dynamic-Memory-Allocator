// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        if (this.next == null) {
            throw new RuntimeException("Calling insert on TailSentinel");
        }
        else{
            A1List temp = this.next;
            A1List temp1= new A1List(address, size, key);
            this.next=temp1;
            temp1.prev=this;
            temp1.next=temp;
            temp.prev=temp1;
            return temp1;
        }
    }

    public boolean Delete(Dictionary d) 
    {
        int k=d.key;
        A1List current = this;
        while (current.next !=null){
            if (current.key==k){
                if (current.address==d.address && current.size==d.size){
                    A1List temp=current.prev;
                    A1List temp1=current.next;
                    temp.next=temp1;
                    temp1.prev=temp;
                    return true;
                }
            }
            current=current.next;
        }
        A1List current1 = this;
        while (current1.prev !=null){
            if (current1.key==k){
                if (current1.address==d.address && current1.size==d.size){
                    A1List temp=current1.prev;
                    A1List temp1=current1.next;
                    temp.next=temp1;
                    temp1.prev=temp;
                    return true;
                }
            }
            current1=current1.prev;
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        if (exact==true){
            A1List current =this;
            while (current.next != null){
                if (current.key==k){
                    return current;
                }
                current=current.next;
            }
            A1List current1 = this;
            while (current1.prev != null){
                if (current1.key==k){
                    return current1;
                }
                current1=current1.prev;
            }
        }
        else{
            A1List current = this;
            while (current.next != null){
                if (current.key <= k){
                    return current;
                }
                current=current.next;
            }
            A1List current1 = this;
            while (current1.prev != null){
                if (current1.key <= k){
                    return current1;
                }
                current1=current1.prev;
            }
        }
        return null;
    }

    public A1List getFirst()
    {
        return null;
    }
    
    public A1List getNext() 
    {
        return null;
    }

    public boolean sanity()
    {
        return true;
    }

}


