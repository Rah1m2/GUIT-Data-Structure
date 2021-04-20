package javaLinkHW;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SLinkNode {

    public static void main(String args[]) {
        List<Integer> Collect = new LinkedList<>();
        Collect.add(1);
        Collect.add(2); //我设置为从此处开始遍历
        Collect.add(3);
        Collect.add(4);
        Collect.add(5); //链表创建为12345
        ListIterator<Integer> ite = Collect.listIterator(1);//从索引为1处开始遍历
        //遍历开始
        ite.next();
        //向前遍历一个
        System.out.println(ite.next());
        System.out.println(ite.previous());
        System.out.println(ite.previous());
//        for(Integer e:Collect){
//            System.out.println(e);
//        }
    }
}
