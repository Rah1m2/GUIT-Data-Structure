package GradeManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class CodeSave{
    String account;
    String password;
    CodeSave(String a,String p){
        this.account = a;
        this.password = p;
    }
}

public class Commander extends JFrame implements ActionListener {
    private String Code;
    private String Account;

    JButton EditPassword;
    JButton AddUser;
    JButton DropUser;
    JButton AboutInform;
    JLabel Attention;
    JTextField ACfield,PWfield;
    JTextArea AdminConsole;
    JPanel pan1;
    JTabbedPane p;

    List<CodeSave> list = new LinkedList<CodeSave>();
    String str = "";
    String Token[];
//    JCheckBox

    Commander(){
//        List<CodeSave> list = new LinkedList<CodeSave>();
//        list.add(new CodeSave())
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    void init(){
        setLayout(null);
        setBounds(800,450,400,320);
        setTitle("Administrator");

        EditPassword = new JButton("修改密码");
        EditPassword.setBounds(270,20,100,50);
        EditPassword.setVisible(true);

        AddUser =  new JButton("添加账户");
        AddUser.setBounds(270,80,100,50);
        AddUser.setVisible(true);

        DropUser = new JButton("删除账户");
        DropUser.setBounds(270,140,100,50);
        AddUser.setVisible(true);

        AboutInform = new JButton("关于");
        AboutInform.setBounds(270,200,100,50);
        AboutInform.setVisible(true);

        Attention = new JLabel("/**Please input correctly.**/");
        Attention.setBounds(0,-78,500,200);
        Attention.setFont(new Font("宋体",Font.BOLD, 16));
        Attention.setVisible(true);

        ACfield = new JTextField();
        ACfield.setBounds(90,200,120,26);
        PWfield = new JPasswordField();
        PWfield.setBounds(90,230,120,26);

        pan1 = new JPanel();
        p = new JTabbedPane();
        p.add("Admin",pan1);
        p.setBounds(30,30,230,160);
        p.setVisible(true);

        AdminConsole = new JTextArea();
        AdminConsole.setBounds(35,55,220,132);
        AdminConsole.setEnabled(true);

        add(EditPassword);
        add(AddUser);
        add(DropUser);
        add(AboutInform);
        add(Attention);
        add(ACfield);
        add(PWfield);
        add(AdminConsole);
        add(p);

        /*添加监听器*/
        EditPassword.addActionListener(this);
        AddUser.addActionListener(this);
        DropUser.addActionListener(this);
        ACfield.addActionListener(this);
        PWfield.addActionListener(this);

        try {
            RandomAccessFile io = new RandomAccessFile("Code.dat","rw");
            String temp;
            for(int i=0;(temp=io.readLine())!=null;i++){
                str = str+temp;
            }
            Token = str.split(" ");
            for(int i=0;i<Token.length;){
                list.add(new CodeSave(Token[i],Token[i+1]));
                        i = i+2;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Iterator<CodeSave> iter=list.iterator();
//        int j=0;
//        while(iter.hasNext()){
//            System.out.print(list.get(j).account);
//            System.out.println(" "+list.get(j).password);
//            iter.next();
//            j++;
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Scanner scanner;
        try (RandomAccessFile io = new RandomAccessFile("Code.dat","rw")) {
            switch (e.getActionCommand()) {
                case "修改密码":
                    boolean flag = false;
                    for(CodeSave scan:list){
                        if(scan.account.equals(ACfield.getText())){
                            scan.password = PWfield.getText();
                            AdminConsole.append("修改成功。\n");
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) AdminConsole.append("修改失败。\n");
                    for(CodeSave cstr:list){
                        io.writeBytes(cstr.account+" ");
                        io.writeBytes(cstr.password+" ");
                    }
                    io.close();
                    break;
                case "添加账户":
                    String strAc, strPw;
                    strAc = ACfield.getText()+" ";
                    strPw = PWfield.getText()+" ";
                    if(!ACfield.getText().equals("")&& !PWfield.getText().equals("")) {
                        list.add(new CodeSave(strAc, strPw));
                        io.seek(io.length());
                        io.writeBytes(" "+strAc);
                        io.writeBytes(strPw);
                        AdminConsole.append("添加成功。\n");
                        io.close();
                    }
                    else AdminConsole.append("添加失败。\n");
                    break;
                case "删除账户":
                    int i=0;
                    for(CodeSave scan:list){
                        if(scan.account.equals(ACfield.getText())){
                            list.remove(i);
                            AdminConsole.append("删除成功。\n");
                            break;
                        }
                        i++;
                    }
                    //用字节输出流刷新文件
                    File CodeFile = new File("Code.dat");
                    FileOutputStream out = new FileOutputStream(CodeFile,false);
                    //用随机流输出到文件
                    for(CodeSave cstr:list){
                        io.writeBytes(cstr.account+" ");
                        io.writeBytes(cstr.password+" ");
                    }

//                    Iterator<CodeSave> iter=list.iterator();
//                    int j=0;
//                    while(iter.hasNext()){
//                        System.out.print(list.get(j).account);
//                        System.out.println(" "+list.get(j).password);
//                        iter.next();
//                        j++;
//                    }

                    io.close();
                    break;
            }
        }catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
