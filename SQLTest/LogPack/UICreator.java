package LogPack;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UICreator extends JFrame{
    Box boxS,boxF;
    Box boxH;
    JButton ButtonOK;
    ButtonListener Pwlistener;
//  ButtonListener Aclistener;
    JLabel label;
    JLabel label2;
    JLabel labelTitle;
    JLabel Warn;
    JTextField passwordField,accontField;

    public UICreator(){
        init();
    }

    void init(){
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        boxH = Box.createHorizontalBox();
        boxS = Box.createVerticalBox();
        boxF = Box.createVerticalBox();
        label = new JLabel("账号：");
        label2 = new JLabel("密码：");
        labelTitle = new JLabel("学生成绩管理系统",labelTitle.CENTER);
        Warn = new JLabel("账号或者密码有误");

        passwordField = new JPasswordField();
        accontField = new JTextField();
        ButtonOK = new JButton("OK");
        boxS.add(label);
        boxS.add(label2);
        boxF.add(accontField);
        boxF.add(passwordField);
        boxH.add(boxS);
        boxH.add(Box.createHorizontalStrut(10));
        boxH.add(boxF);
        boxH.add(Box.createHorizontalStrut(10));
        boxH.add(ButtonOK);
        add(boxH);
        add(labelTitle);

        Font font = new Font("隶书",Font.PLAIN,25);
        labelTitle.setFont(font);
        labelTitle.setBounds(85,-78,200,200);
        boxH.setBounds(85,55,200,45);

        initializeCodes();
    }

    void setWarningMessage(){
              add(Warn);
              setVisible(true);
    }
    void ConnectListener(ButtonListener blistner){
        Pwlistener = blistner;            //获取主方法中创建的监听器对象
        passwordField.addActionListener(Pwlistener);        //将主方法中创建的监听器对象 联系到密码框上
        ButtonOK.addActionListener(Pwlistener);
        Pwlistener.getAccount(accontField);        //用主方法中创建的监听器对象 调用监听器中的getAccount()方法
        Pwlistener.getPassword(passwordField);
//        accontField.addActionListener(Pwlistener);
    }

    void initializeCodes() {
        String dstr = "user 000";
        try {
            RandomAccessFile io = new RandomAccessFile("Code.dat","rw");
            if(io.length() == 0){
                io.writeBytes(dstr);
            }
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}