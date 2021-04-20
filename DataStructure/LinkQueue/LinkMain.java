package LinkQueue;

class LinkNode {
    public int data;
    public LinkNode next;
}
class LinkQueue{
    LinkNode front,rear;
}

public class LinkMain {
    public static void main(String args[]){
        LinkQueue Q = new LinkQueue();
        LinkOp lop = new LinkOp();
        lop.InitQueue(Q,1);
        lop.EnQueue(Q,2);
        lop.EnQueue(Q,3);
        lop.EnQueue(Q,4);
        lop.DeQueue(Q);
        lop.Display(Q);
    }
}

class LinkOp{
    public void InitQueue(LinkQueue Q,int e){
        Q.front = new LinkNode();
        Q.rear = Q.front;
        Q.front.data = e;
        Q.front.next = null;
    }
    public void EnQueue(LinkQueue Q,int e){
        Q.rear.next = new LinkNode();
        Q.rear = Q.rear.next;
        Q.rear.next = null;
        Q.rear.data = e;
    }
    public void DeQueue(LinkQueue Q){
        Q.front = Q.front.next;
    }
    public void Display(LinkQueue Q){
        while(Q.front != null){
            System.out.print(Q.front.data+" ");
            Q.front = Q.front.next;
        }
    }
}