package CommonLinkList;
import java.util.LinkedList;
public class LinkMain {
    public static void main(String args[]){
            LinkList<String> L= new LinkList<String>(); //可以放任意类型的，所以叫泛型
            L.InsertTail("A");
            L.InsertTail("B");
            L.InsertTail("C");
            L.ShowList();
            String str = L.getElemData(2);
            System.out.println("Elem:"+str);
            L.InsertAny(1,"X");
            L.ShowList();
            L.Reverse();
            L.ShowList();
    }
}

class Node<T>{
    public T data;
    public Node<T> next;
    public Node(T data,Node<T> next){
        this.data = data;
        this.next = next;
    }
    public Node(){
        this(null,null);
    }
}

class LinkList<T>{
    public Node<T> head;
    public Node<T> temp;
    public LinkList(){
        this.head = new Node<T>();
    }
    /*尾插法*/
    public void InsertTail(T data){
        temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new Node<T>(data,null);
    }
    /*打印整个链表*/
    public void ShowList(){
        temp = head.next;
        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    /*获取指定位置元素的数据域*/
    public String getElemData(int site){
        temp = head;
        int i = 0;
        while(temp!=null){
            if(site == i)
                return (String)temp.data;
            i++;
            temp =  temp.next;
        }
        return "Not Found";
    }
    /*获取指定位置元素的指针域*/
    public Node<T> getElem(int site){
        temp = head;
        int i = 0;
        while(temp!=null){
            if(site == i)
                return temp;
            i++;
            temp =  temp.next;
        }
        return null;
    }
    /*在指定位置插入元素*/
    public void InsertAny(int site,T data){
        Node<T> temp = this.getElem(site);
        temp.next = new Node<>(data,temp.next);
    }
    /*单链表倒置*/
    public void Reverse(){
        Node<T> front = null;
        Node<T> mid = head.next;
        Node<T> behind = null;

        while(mid!=null){
            behind = mid.next;
            mid.next = front;
            front = mid;
            mid = behind;
        }
        head.next = front;
    }
}
