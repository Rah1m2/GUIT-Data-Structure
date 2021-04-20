package GradeManagement;

import DataBasePack.EstabDBCConnection;
import DataBasePack.GetDBConnection;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;



/**
 *这个类实现了简单的控制台功能；
 *由于JTextArea被DocumentListener监视时会锁定文本域，所以我创建了多个线程来实现动态修改文本域内容；
 *
 */



public class ConsoleListener implements DocumentListener {
    JTable table;
    JTextArea consoletexta;
    DefaultTableModel model;
    String str[];
    String ct;
    String T[];
    boolean flag;
    boolean flagCls;

    Thread threadR;
    Thread root;
    Thread Auto;
    Thread SearchN;
    Thread SearchDe;
    AutoEnter autoenter;
    Root rt;
    Search sc;
    Stat st;


    EstabDBCConnection estabDBCConnection;
    GetDBConnection getdbconn;
    Connection conn;
    @Override
    public void insertUpdate(DocumentEvent e) {

        threadR = new Thread(this::run);

        autoenter = new AutoEnter();
        Auto = new Thread(autoenter);
        autoenter.setConsole(consoletexta);

        rt = new Root();
        root = new Thread(rt);
        rt.setConsole(consoletexta);
        rt.setCt(ct);
        rt.setFlag(flagCls);
        rt.getConsoleListener(this);

        sc = new Search();
        SearchN = new Thread(sc);
        sc.setConsole(consoletexta);
        sc.setTable(table);

        st = new Stat();
        SearchDe = new Thread(st);
        st.setConsole(consoletexta);

        /*获取用户输入的指令，按照换行符分割*/
         str = consoletexta.getText().split("\n");

         if(Arrays.equals(T, str)){
             flag = false;
         }
         else flag = true;

        if(flag && T!=null) {   //String[]给String
            ct = "";
            for(int i=0;i<T.length;i++){
                if(ct.equals("")){
                    ct = T[i];
                }
                else {
                    ct = ct + "\r\n" + T[i];
                }
            }
        }

         boolean b = true;
         if(flag && T!=null){
             for (int i = 0; i < T.length-1; i++){
                 b = T[i].equals(str[i]);
                if (!b){               //如果有不匹配的
                    root.start();
                    flagCls = true;
                    break;
                }
             }
         }

            int indexT = str.length;
            int c = 0;
            T = new String[indexT];
            while (c < str.length) {
                T[c] = str[c];
                c++;
            }


        String Select = str[str.length-1];
         if(flagCls){
             Select = "No";
             flagCls = false;
         }
         if(Select.matches("SearchNumber:[0123456789]+")){
             Select = "SearchNumber:";
        }

         /*匹配用户输入的指令*/
        switch(Select){
            case "exit":   //直接退出程序
                System.exit(0);
                break;

            case "clean":  //清空表格中的内容
                if(flag) {
                    for (int i = 0; i < table.getRowCount(); i++) {
                        for (int j = 0; j < 3; j++) {
                            model.setValueAt("", i, j);
                        }
                    }
                    Auto.start();  //输入指令后在控制台自动换行的线程
                }
                break;

            case "admin":   //打开管理员界面
                if(flag) {
                    new Commander();
                    Auto.start();
                }
                break;

            case "test":   //用于测试的指令，无实际功能
                if(flag){
                    System.out.println("Test passed!");
                    Auto.start(); //输入指令后在控制台自动换行的线程
                }
                break;

            case "cls":    //将控制台清屏
                if(flag){
                    threadR.start();  //清屏的线程
                    T = null;
                    str = null;
                    ct = "";    //这个是即将打印到控制台的JTextArea的内容，设置为空
                }
                break;

            case "read":
                if(flag){
                    System.out.println("(c) 2020 Mr.Fahrenheit Individual[version 2.9]");
                    Auto.start();
                }
                break;

            case "SearchNumber:":           //这条指令输入学号可以查询对应学号的名称
                if(flag){
                    String Result = str[str.length-1];  //将用户输入的字符保存到Result变量中
                    Scanner scanner = new Scanner(Result);
                    scanner.useDelimiter("[^0-9]+");    //利用正则表达式将纯数字学号提取出来
                    while(scanner.hasNext()){
                        Result = scanner.next();
                    }
                    for(int i=0,flag=0;i<table.getRowCount();i++){
                        for(int j=0;j<table.getColumnCount();j++){
                            if(table.getValueAt(i,j).equals(Result)){
                                sc.setRowColumn(i,j);
                                SearchN.start();
                                flag = 1;
                                break;
                            }
                        }
                        if(flag == 1) break;
                    }
                }
                break;

            case "--ShowDetail":   //
                SearchDe.start();
                break;
        }

    }

    public void run(){
        consoletexta.setText("");
//        consoletexta.append("\n");
    }




    @Override
    public void removeUpdate(DocumentEvent e) {
//        Stat stat = new Stat();
//        Thread stathread = new Thread(stat);
////        stat.setConsole();
//         stat.setConsole(consoletexta);
//        stat.setCt(ct);
//        stathread.start();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("111");
    }

    public void getConsoleText(JTextArea ConsoleTextA){
        consoletexta = ConsoleTextA;
    }

    public void setModel(DefaultTableModel model){
        this.model = model;
    }

    public void setTable(JTable table){
        this.table = table;
    }

}

/*线程区*/
/*实现控制台区仅仅一行可编辑的功能*/
class Root implements Runnable{
    JTextArea j;
    String ct;
    ConsoleListener CL;
    boolean flagCls;
    public void run(){
        j.setText(ct);
        j.setCaretPosition(ct.length());
        CL.flagCls = true;
    }

    void setConsole(JTextArea JT){
        this.j = JT;
    }

    void setCt(String ct){
        this.ct = ct;
    }

    void setFlag(boolean flag){
        this.flagCls = flag;
    }

    void getConsoleListener(ConsoleListener CL){
        this.CL = CL;
    }
}

/*自动换行线程*/
class AutoEnter implements Runnable{
    JTextArea j;

    @Override
    public void run() {
        int i = 0;
        j.append("\n");
        i =j.getCaretPosition();
        j.setCaretPosition(i+1);
    }

    void setConsole(JTextArea JT){
        this.j = JT;
    }
}

/*打印查询结果*/
class Search implements Runnable{
    JTextArea j;
    JTable table;
    int i,k;

    @Override
    public void run() {
        j.append(String.valueOf("\n"+table.getValueAt(i,k)+" "));
        j.append(String.valueOf(table.getValueAt(i,k+1)+" "));
        j.append(String.valueOf(table.getValueAt(i,k+2)+"\n"));
    }

    void setConsole(JTextArea JT){
        this.j = JT;
    }

    public void setTable(JTable table){
        this.table = table;
    }
    void setRowColumn(int i,int j){
        this.i = i;
        this.k = j;
    }
}



class Stat implements Runnable{
    JTextArea j;
    String ct;
    EstabDBCConnection estabDBCConnection;
    GetDBConnection getdbconn;
    Connection conn;
    @Override
    public void run() {
        getdbconn = new GetDBConnection();
        conn = getdbconn.connectDB("fahrenheit","root","724462");
        estabDBCConnection = new EstabDBCConnection();
        if(conn != null) {
            estabDBCConnection.getConn(conn);
            estabDBCConnection.Establish();
        }
        estabDBCConnection.Select();

        for(int i=0;i<(EstabDBCConnection.Data).length;i++){
            j.append("\n"+EstabDBCConnection.Data[i][0]+" ");
            j.append(EstabDBCConnection.Data[i][1]+" ");
            j.append(EstabDBCConnection.Data[i][2]+" ");
//            j.append(EstabDBCConnection.Data[i][3]+" ");
//            j.append(EstabDBCConnection.Data[i][4]+" ");
        }
        j.append("\n");
    }
    void setConsole(JTextArea JT){
        this.j = JT;
    }
    void setCt(String ct){
        this.ct = ct;
    }
}

//class Stat implements Runnable{
//    JTextArea j;
//    String ct;
//    @Override
//    public void run() {
//        j.setText(ct);
//    }
//    void setConsole(JTextArea JT){
//        this.j = JT;
//    }
//    void setCt(String ct){
//        this.ct = ct;
//    }
//}



