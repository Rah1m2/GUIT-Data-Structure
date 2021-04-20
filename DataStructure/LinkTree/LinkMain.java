package LinkTree;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.Scanner;

class LinkTree {
    public char data;
    public LinkTree LChild,RChild;
}

public class LinkMain{
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        char[] arr = str.toCharArray();
        LinkOp lop = new LinkOp();
        LinkTree T = lop.FormCreateLinkTree(null,arr,Clock.Clo);
        lop.FormTraverseTree(T);
    }
}

class LinkOp{
    Scanner scan;
    LinkOp(){
        scan = new Scanner(System.in);
    }
    public LinkTree FormCreateLinkTree(LinkTree T,char[] ch,int i){
        Clock.Clo++;
        LinkTree tl;
        LinkTree t2;
//        char ch = scan.next().charAt(0);
//        char[] arr = str.toCharArray();
        if(ch[Clock.Clo] == '#'){
            T = null;
        }
        else{
            T = new LinkTree();
            T.data = ch[Clock.Clo];
            tl = FormCreateLinkTree(T.LChild,ch,Clock.Clo);
            if(tl != null){
                T.LChild = tl;
            }
            t2 = FormCreateLinkTree(T.RChild,ch,Clock.Clo);
            if(t2 != null){
                T.RChild = t2;
            }
        }
        return T;
    }

    public void FormTraverseTree(LinkTree T){
        if(T != null){
            System.out.print(T.data);
            FormTraverseTree(T.LChild);
            FormTraverseTree(T.RChild);
        }
        else{
            System.out.print("#");
        }
    }
}

class Clock{
    public static int Clo = -1;
}

