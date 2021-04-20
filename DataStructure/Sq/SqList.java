package Sq;

import java.util.Scanner;

public class SqList {
    public static final int MaxSize = 5;
    int data[];            //存放顺序表的元素
    int length;                        //顺序表的实际长度
    Scanner sca = new Scanner(System.in);

    SqList() {
        data = new int[MaxSize];
        length = 0;
    }

    public void InitList()            //初始化顺序表L
    {
        length = 0;
    }

    public void DestroyList()            //销毁顺序表L
    {
    }

    public int GetLength()                //求长度
    {
        return length;
    }

    int GetElem(SqList L, int i, int[] e)    //求第i个元素
    {
        if (i < 1 || i > L.length)            //无效的i值
            return 0;
        else {
            e[0] = L.data[i - 1];
            return 1;
        }
    }

    int Locate(SqList L, int x)        //查找第一个x元素的位置
    {
        int i = 0;
        while (i < L.length && L.data[i] != x)
            i++;                        //查找第1个值为x的元素
        if (i >= L.length)
            return (0);        //未找到返回0
        else
            return (i + 1);                //找到后返回其逻辑序号
    }

    int InsElem(SqList L, int x, int i)    //插入x作为第i个元素//**
    {
        int j;
        if (i < 1 || i > L.length + 1)        //无效的参数i
            return 0;
        for (j = L.length; j >= i; j--)        //将位置为i的元素及之后的元素后移
            L.data[j] = L.data[j - 1];
        L.data[i - 1] = x;                    //在位置i处放入x
        L.length++;                        //顺序表长度增1
        return 1;
    }

    int DelElem(SqList L, int i)        //删除第i个元素//**
    {
        int j;
        if (i < 1 || i > L.length)            //无效的参数i
            return 0;
        for (j = i; j < L.length; j++)        //将位置为i的元素之后的元素前移
            L.data[j - 1] = L.data[j];
        L.length--;                        //顺序表长度减1
        return 1;
    }

    void DelElemMany(SqList L,int i,int num){
        num = num-i+1;
        for(int j=i+num-1;j<L.length;j++)
            L.data[j - num] = L.data[j];
        L.length = L.length-num;
    }

    void DispList(SqList L)                //输出顺序表
    {
        int i;
        if (L.length > 0) {
            System.out.println("当前顺序表中的元素为：");
            for (i = 0; i < L.length; i++)
                System.out.print(L.data[i]+" ");
        } else
            System.out.print("顺序表已空");
        System.out.println();
    }

    void CreateList(SqList L, int a[], int n)    //整体创建顺序表L//**
    {
        int i, k = 0;                        //k累计顺序表L中的元素个数
        for (i = 0; i < n; i++) {
            L.data[k] = a[i];                //向L中添加一个元素
            k++;                        //L中元素个数增1
        }
        L.length = k;                        //设置L的长度
    }

    int menu() {
        int i = 0;
        System.out.print("1.逐个插入元素创建链表\n");
        System.out.print("2.整体创建顺序表\n");
        System.out.print("3.退出\n");
        i = sca.nextInt();
        return i;
    }

    int menu1() {
        int i = 0;
        System.out.print("1.插入元素\n");
        System.out.print("2.输出顺序表\n");
        System.out.print("3.获取第i个元素\n");
        System.out.print("4.查找第一个值为x元素的位置\n");
        System.out.print("5.删除第i元素\n");
        System.out.print("6.退出\n");
        System.out.print("0.test\n");
        i = sca.nextInt();
        return i;
    }

    void menu2(SqList L) {//**

        int i = 0, x = 0, chack = 0, result = 0;
        int[] e=new int[1];

        while (chack != 6) {
            chack = menu1();//选择功能
            result = 0;
            switch (chack) {
                case 1:
                    System.out.print("输入你需要插入的元素");
                    x = sca.nextInt();
                    System.out.print("输入你需要插入的位置");
                    i = sca.nextInt();
                    if (L.length >= MaxSize) {
                        System.out.print("顺序表已满\n");
                        break;
                    }
                    InsElem(L, x, i);            //在数组末尾插入元素i
                    break;
                case 2:
                    DispList(L);//1.输出链表
                    break;
                case 3:
                    System.out.print("输入你需要获取的第i个元素:\n");
                    i = sca.nextInt();
                    result = GetElem(L, i, e);//2.获取第i个元素
                    if (result == 1) {
                        System.out.print("  第" + i + "个元素:" + e[0] + "\n");
                    } else
                        System.out.print("  第" + i + "个元素不存在\n");
                    break;
                case 4:
                    System.out.print("输入你要查找的x元素:\n");
                    e[0] = sca.nextInt();
                    result = Locate(L, e[0]);
                    if (result != 0) {
                        System.out.print("  元素" + e[0] + "是第" + Locate(L, e[0]) + "个元素\n");    //3.查找第一个x元素的位置
                    } else
                        System.out.print("  元素" + e[0] + "不存在\n");    //3.查找第一个x元素的位置
                    break;
                case 5:
                    System.out.print("输入你需要 删除第i个元素:\n");
                    i = sca.nextInt();
                    result = DelElem(L, i);
                    if (result == 1) {
                        System.out.print("  删除第" + i + "个元素\n");//4.删除第i个元素
                    } else
                        System.out.print("  第" + i + "个元素不存在\n");
                    break;
                case 6:
                    System.out.print("销毁L\n");
                    DestroyList();
                    return;
                case 0:
                    System.out.print("input i\n");
                    i = sca.nextInt();
                    System.out.print("input num\n");
                    int k = sca.nextInt();
//                    k = k-i+1;
                    DelElemMany(L,i,k);
                    break;
                default:
                    System.out.print("无该选项\n");
            }
        }
    }

    public static void main(String args[]) {
        int i = 0;
        int chack = 0;
        int x = 0;
        int e;
        Scanner sca = new Scanner(System.in);
        SqList L = new SqList();
        int a[] = {2, 5, 4, 1, 9};
        int n = a.length;

        while (chack != 3) {
            chack = L.menu();//选择创建顺序表的方式
            switch (chack) {
                case 1:
                    L.InitList();            //初始化顺序表L
                    System.out.print("输入需要插入多少个的元素:\n");
                    x = sca.nextInt();
                    if (x > MaxSize) {
                        System.out.print("超出顺序表长度" + MaxSize + "\n");
                        break;
                    } else {
                        System.out.print("输入需要插入的元素:\n");

                        for (i = 1; i <= x; i++) {
                            e = sca.nextInt();
                            L.InsElem(L, e, i);            //插入元素1
                        }
                        L.menu2(L);
                        return;//直接退出
                    }
                case 2:
                    System.out.print("  整体创建L\n");
                    L.CreateList(L, a, n);
                    L.menu2(L);
                    return;//直接退出
                default:
                    System.out.print("无该选\n");
            }
        }
    }
}

