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
            return null;
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
        if (d==null){
            return false;
        }
        int k=d.key;
        A1List current = this;
        if (current.prev==null){
            current=current.next;
        }
        while (current.next !=null){
            if (current.key==k){
                if (current.address==d.address && current.size==d.size){
                    if (current==this){
                        if (current.prev.prev==null){
                            current.address=-1;
                            current.size=-1;
                            current.key=-1;
                            current.prev=null;
                        }
                        else{
                            current.address=current.prev.address;
                            A1List temp=current.prev;
                            current.size=temp.size;
                            current.key=temp.key;
                            current.prev=temp.prev;
                            temp.prev.next=current;
                            temp.next=null;
                            temp.prev=null;
                        }
                    }
                    else{
                        A1List temp=current.prev;
                        A1List temp1=current.next;
                        temp.next=temp1;
                        temp1.prev=temp;
                        current.next=null;
                        current.prev=null;
                    }
                    return true;
                }
            }
            current=current.next;
        }
        A1List current1 = this;
        if (current1.next==null){
            current1=current1.prev;
        }
        while (current1.prev !=null){
            if (current1.key==k){
                if (current1.address==d.address && current1.size==d.size){
                    if (current1==this){
                        if (current1.prev.prev==null){
                            current1.address=-1;
                            current1.size=-1;
                            current1.key=-1;
                            current1.prev=null;
                        }
                        else{
                            current1.address=current1.prev.address;
                            A1List temp=current1.prev;
                            current1.size=temp.size;
                            current1.key=temp.key;
                            current1.prev=temp.prev;
                            temp.prev.next=current1;
                            temp.next=null;
                            temp.prev=null;
                        }
                    }
                    else{
                        A1List temp=current1.prev;
                        A1List temp1=current1.next;
                        temp.next=temp1;
                        temp1.prev=temp;
                        current1.next=null;
                        current1.prev=null;
                    }
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
        if (current.address != -1 || current.size != -1 || current.key != -1 || current1.address != -1 || current1.size != -1 || current1.key != -1 ){
            return false;
        }
        return true;
    }

}


