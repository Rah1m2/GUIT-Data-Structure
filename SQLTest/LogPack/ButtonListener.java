package LogPack;
import GradeManagement.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ButtonListener implements ActionListener{
    JTextField ac,pw;
    UICreator ui0;
    JLabel Warn;

    public void actionPerformed(ActionEvent e){
        String Account[] = new String[100];
        String Password[] = new String[100];
        String Token[];
        String str = "";
        /*读取保存的密码*/
        try {
            RandomAccessFile io = new RandomAccessFile("Code.dat","rw");
            String temp;
            for(int i=0;(temp=io.readLine())!=null;i++){
                str = str+temp;
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }

        Token = str.split(" ");
        for(int i=0;i<Token.length;i++){
            if(i%2 == 0){
                Account[i] = Token[i];
            }
            else{
                Password[i-1] = Token[i];
            }
        }

        if(e.getActionCommand().equals("OK")){}
        else {
            pw = (JTextField) e.getSource();
        }
//        String Account[] = new String[100];
//        String Password[] = new String[100];
//        Account = new String[]{"Fahrenheit","sa","testAccount"};
//        Password = new String[]{"724462","010501","000"};
        boolean flag = false;

        for(int i=0;i<Account.length;i++){
            if(ac.getText().equals(Account[i]) && pw.getText().equals(Password[i]) ||
             ac.getText().equals("Administrator") && pw.getText().equals("God") ||
                    ac.getText().equals("administrator") && pw.getText().equals("Lucifer")){
                ui0.dispose();
                Panel panel = new Panel();
                panel.setBounds(500,200, 1020, 820);
                panel.setTitle("System");
                if(ac.getText().equals("Administrator")||ac.getText().equals("administrator")){
                    panel.setAuth(true);
                }
                else{
                    panel.setAuth(false);
                }
                flag = true;
                break;
            }
        }

        if(!flag){
            ui0.setWarningMessage();
            ui0.setVisible(true);
            ui0.add(Warn);
            ui0.Warn.setBounds(130,-35,220,300);
        }
    }


    /*附带方法区*/
    public void setUI(UICreator u){
        ui0 = u;
        ui0.setVisible(true);
        Warn = new JLabel("账号或者密码有误");
    }
    public void getAccount(JTextField AC){
        ac = AC;
    }
    public void getPassword(JTextField PW){
        pw = PW;
    }

}