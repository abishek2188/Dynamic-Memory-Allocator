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
        A1List current =this;
        if (current.prev==null){
            current=current.next;
        }
        A1List current1 = this;
        if (current1.next==null){
            current1=current1.prev;
        }
        if (exact==true){
            while (current.next != null){
                if (current.key==k){
                    return current;
                }
                current=current.next;
            }
            while (current1.prev != null){
                if (current1.key==k){
                    return current1;
                }
                current1=current1.prev;
            }
        }
        else{
            while (current.next != null){
                if (current.key <= k){
                    return current;
                }
                current=current.next;
            }
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
        if (this.next.next!=null){
            return this.next;
        }
        return null;
    }

    public boolean sanity()
    {
        return true;
    }

    public static void main(String[] args){
        A1List x = new A1List();
        x.Insert(2,3,1);
        x.Insert(3,5,6);
        System.out.println(x.next.address);
        A1List y=x.Find(2,true);
        if (y==null){
            System.out.println("yes");
        }
        A1List t= x.Find(6, false);
        System.out.println(t.address);
        x.next.next.next.Delete(t);
        System.out.println(x.next.address);

        
    }

}


