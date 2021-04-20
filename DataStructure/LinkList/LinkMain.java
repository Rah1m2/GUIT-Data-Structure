package LinkList;
import java.util.Scanner;
import java.util.LinkedList;
class LinkList {
    public int data;
    public LinkList next;
    public void setData(int data){
        this.data = data;
    }
    public void setNext(LinkList next){
        this.next = next;
    }
}

public class LinkMain{
    public static void main(String args[]){
        int n = 1;
        int data = 0;
        LinkOp lop = new LinkOp();
        LinkList L = new LinkList();
        lop.InitLinkList(L);
        Scanner scanner = new Scanner(System.in);

//        LinkList p = lop.InitLinkList(L);
        System.out.println("Please input your choice: 1InsertElem");
        while(true) {
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Please input the elem data:");
                    data = scanner.nextInt();
                    lop.addNodeHead(L, data);
                    break;
                case 2:
                    System.exit(0);
                    break;
                case 3:
                    lop.DisplayLinkList(L);
                    break;
                case 4:
                    data = scanner.nextInt();
                    lop.DelNodeAny(L,data);
                    break;
                default:
                    System.out.println("Illegal input");
                    break;
            }
        }
    }
}

class LinkOp{
    public void InitLinkList(LinkList L){
        L.data = 7;
        L.next = null;
    }

    public void addNodeHead(LinkList L,int data){
        LinkList L2 = new LinkList();
        L2.data = data;
        L2.next = null;
        while(L.next != null){
            L = L.next;
        }
        L.next = L2;
    }

    public void DisplayLinkList(LinkList L){
        L = L.next;
        System.out.println("Listed as followed:");
        while(L != null){
            System.out.print(L.data+" ");
            L = L.next;
        }
    }

    public void DelNodeTail(LinkList L){
        LinkList F = L;
        while(L.next != null){
            F = L;
            L = L.next;
        }
        F.next = null;
    }

    public void DelNodeAny(LinkList L,int data){
        LinkList F = L;
        boolean flag = false;
        int i = 0;
        while(L.next != null){
            if(data == i){
                F.next = L.next;
                L.next = null;
                flag = true;
                break;
            }
            F = L;
            L = L.next;
            i++;
        }
        if(!flag)
            System.out.println("Not found!");
    }
}
