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
        A1List head = this;
        while (head.prev != null){
            head = head.prev;
        }
        A1List temp = head.next;
        A1List temp1= new A1List(address, size, key);
        head.next=temp1;
        temp1.prev=head;
        temp1.next=temp;
        temp.prev=temp1;
        return temp1;
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
        A1List current =this.getFirst();
        if (current==null){
            return null;
        }
        if (exact==true){
            while (current.next != null){
                if (current.key==k){
                    return current;
                }
                current=current.next;
            }
        }
        else{
            while (current.next != null){
                if (current.key >= k){
                    return current;
                }
                current=current.next;
            }
        }
        return null;
    }

    public A1List getFirst()
    {
        A1List current = this;
        while (current.prev != null){
            current=current.prev;
        }
        if (current.next.next!=null){
            return current.next;
        }
        return null;
    }
    
    public A1List getNext() 
    {
        if (this.next == null || this.next.next == null) {
            return null;
        }
        return this.next;
    }

    public boolean sanity()
    {
        A1List slow = this;
        A1List fast = this;
        while (fast !=null && fast.next != null){
            fast=fast.next.next;
            slow=slow.next;
            if (slow==fast){
                return false;
            }
        }
        A1List slow1=this;
        A1List fast1=this;
        while (fast1 != null && fast1.prev != null){
            fast1=fast1.prev.prev;
            slow1=slow1.prev;
            if (slow1==fast1){
                return false;
            }
        }
        A1List current = this;
        while (current.next != null){
            if (current.next.prev != current){
                return false;
            }
            current=current.next;
        }
        A1List current1 = this;
        while (current1.prev!=null){
            if (current1.prev.next != current1){
                return false;
            }
            current1=current1.prev;
        }
        return true;
    }

}


