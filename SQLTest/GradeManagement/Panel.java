package GradeManagement;

import DataBasePack.EstabDBCConnection;
import DataBasePack.GetDBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Panel extends JFrame implements ActionListener {
    JTable table;
    DefaultTableModel model;
    JScrollPane jscrollpane;
    JScrollPane jscrollpaneArea;
    Object TableElem[][];
    Object title[] = {"学号","姓名","总成绩"};
    String ValueSave[][];

    JButton buttonInitDB;
    JButton buttonRead;
    JButton buttonSave;
    JButton buttonAddRow;
    JButton buttonDelete;

    JTabbedPane p;
    JPanel pan1;

    JTextArea ConsoleArea;
    //创建数据库连接器对象
    EstabDBCConnection estabDBCConnection;
    GetDBConnection getdbconn;
    Connection conn;
    //监听器
    ConsoleListener consoleListener;
    TableListener tablelistener;

    boolean Authority;

    public void setAuth(boolean Authority){
        this.Authority = Authority;
        if(!Authority){
            table.setEnabled(false);
        }
    }

    public Panel() {
      init();
      setVisible(true);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    /*初始化函数*/
    void init(){
        setLayout(null);
        TableElem = new Object[15][3];
        for(int i=0;i<15;i++){
            for(int j=0;j<3;j++){
                if(j!=1)
                    TableElem[i][j] = "";
                else
                    TableElem[i][j] = "";
            }
        }

        //创建表格
        model = new DefaultTableModel(TableElem,title);//初始化存放学生成绩等信息的表格
        table = new JTable(model);        //创建一个管理表格的model
        jscrollpane = new JScrollPane();   //为表格添加滚轮
        jscrollpane.setViewportView(table);
        jscrollpane.setBounds(0,0,1000,500);
        table.setRowHeight(35);

        JTableHeader tableHeader = this.table.getTableHeader(); //更改表格列头的字体大小
        Font font = new Font("宋体",Font.PLAIN,20);
        table.setFont(font);
        font = new Font("隶书",Font.PLAIN,25);
        tableHeader.setFont(font);
        jscrollpane.setVisible(true);   //设置为可见

        //创建空面板（控制台的框架）
        pan1 = new JPanel();
        p = new JTabbedPane();
        p.add("Console",pan1); //为Console，这个是管理系统界面下方的控制台的名称
        p.setBounds(0,560,1000,300);
        p.setVisible(true);

        //创建文本区(并且添加滚轮)
        ConsoleArea = new JTextArea();
        jscrollpaneArea = new JScrollPane(ConsoleArea);
        jscrollpaneArea.setBounds(0,585,1000,210);
        jscrollpaneArea.setVisible(true);
        ConsoleArea.setFont(new Font("Dialog", 1, 15));
        ConsoleArea.append("(c) 2020 Mr.Fahrenheit Individual[version 2.9]");
        ConsoleArea.append("\r\n");

        //创建按钮
        buttonSave = new JButton("保存");
        buttonRead = new JButton("读取");
        buttonAddRow = new JButton("加行");
        buttonDelete = new JButton("删行");
        buttonInitDB = new JButton("初始化");
        buttonSave.setBounds(100,500,70,50);
        buttonRead.setBounds(20,500,70,50);
        buttonInitDB.setBounds(180,500,90,50);
        buttonAddRow.setBounds(280,500,70,50);
        buttonDelete.setBounds(360,500,70,50);
        buttonSave.setVisible(true);
        buttonRead.setVisible(true);
        buttonAddRow.setVisible(true);
        buttonSave.setEnabled(false);
        buttonRead.setEnabled(false);
        buttonAddRow.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonInitDB.setEnabled(true);

        //添加组件入窗体
        add(buttonSave);
        add(buttonRead);
        add(buttonInitDB);
        add(buttonAddRow);
        add(buttonDelete);
        add(jscrollpaneArea);
        add(jscrollpane);
        add(p);


        /*监听器区域*/
        buttonRead.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonInitDB.addActionListener(this);
        buttonAddRow.addActionListener(this);
        buttonDelete.addActionListener(this);
        //Console监听器
        consoleListener = new ConsoleListener();
        consoleListener.getConsoleText(ConsoleArea);
        consoleListener.setModel(model);
        consoleListener.setTable(table);
        ConsoleArea.getDocument().addDocumentListener(consoleListener);
        //Table监听器
        tablelistener = new TableListener();
        tablelistener.setTable(table);
        tablelistener.setRow(14);
        tablelistener.setModel(model);
        table.addKeyListener(tablelistener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //初始化数据库连接
        estabDBCConnection = new EstabDBCConnection();
        if(conn != null) {       //判断用户是否点击初始化，也可以用try-catch
            estabDBCConnection.getConn(conn);
            estabDBCConnection.Establish();
        }

        switch(e.getActionCommand()){

            case "初始化":
//                ConsoleArea.append("初始化中...");
//                ConsoleArea.append("\r\n");
                getdbconn = new GetDBConnection();
                conn = getdbconn.connectDB("fahrenheit","root","724462");
                if(conn == null){
                    ConsoleArea.append("初始化失败！");
                    ConsoleArea.append("\r\n");
                }
                else {
                    ConsoleArea.append("初始化成功！");
                    ConsoleArea.append("\r\n");
                    buttonRead.setEnabled(true);
                    if(Authority) {
                        buttonSave.setEnabled(true);
                        buttonAddRow.setEnabled(true);
                        buttonDelete.setEnabled(true);
                    }
                }
                break;

            case "读取":
                estabDBCConnection.Select();
                int Row = estabDBCConnection.getRow();
                //清空表格
                while((EstabDBCConnection.Data).length > table.getRowCount()){
                    Object[] newRow = new Object[3];
                    model.addRow(newRow);
                }

                for(int i=0;i<table.getRowCount();i++) {
                    for(int j=0;j<3;j++) {
                        model.setValueAt("", i, j);
                    }
                }
                //输出到表格

                for(int i=0;i<Row;i++) {
                    for(int j=0;j<3;j++) {
                        model.setValueAt(EstabDBCConnection.Data[i][j], i, j);
                    }
                }
                ConsoleArea.append("读取成功");
                ConsoleArea.append("\r\n");
                break;

            case "保存":
                Row = table.getRowCount();
                ValueSave = new String[Row][3];
                int k = 0,j = 0;
                for(int i = 0;i<Row;i++){
                    for(j = 0;j<3;j++){
                        if((table.getValueAt(i,j)).equals("")){
                            k--;
                            break;
                        }
                        ValueSave[k][j] = (String) table.getValueAt(i,j);
                    }
                    k++;
                }
                estabDBCConnection.Save(ValueSave,k);
                ConsoleArea.append("保存成功");
                ConsoleArea.append("\r\n");
                break;

            case "加行":
                Object[] newRow = new Object[3];
                ((DefaultTableModel) table.getModel()).addRow(newRow);
                int row = table.getRowCount() - 1;
                table.setRowSelectionInterval(row, row);
                break;

            case "删行":
                if(table.getSelectedRow() == -1){
                    model.removeRow(table.getRowCount()-1);
                }
                else {
                    model.removeRow(table.getSelectedRow());
                }
                break;
        }

    }
}








