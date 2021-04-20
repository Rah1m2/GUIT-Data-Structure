package DataBasePack;

import java.sql.*;
import java.sql.Connection;

public class EstabDBCConnection {
    Connection conn;
    Statement sql;
    ResultSet rs;
    Delete Del;
    Alter Alt;
    public static String Data[][];
    int row;
    public void Establish() {
        try {
            //初始化数据库相关
            sql=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);//让游标可以上下浮动
    }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    //初始化删除与查找功能
    public void Select(){
        try{
            rs = sql.executeQuery("SELECT number,sex,Grade FROM test");

            rs.last();
            row = rs.getRow();
            Data = new String[row][3];
            rs=sql.executeQuery("SELECT * FROM test");
            int i = 0;
            while(rs.next()) {
                String number = rs.getString(1);
                String sex = rs.getString(2);
                float Grade = rs.getFloat(3);
                // String name=rs.getString(2);
                // Date date=rs.getDate(3);
                Data[i][0] = number;
                Data[i][1] = sex;
                Data[i][2] = String.valueOf(Grade);
                i++;
            }
        }
        catch(SQLException e){}
    }

    public void SelectAll(){
        try{
            rs = sql.executeQuery("SELECT * FROM test");
            rs.last();
            row = rs.getRow();
            Data = new String[row][3];
            rs=sql.executeQuery("SELECT * FROM test");
            int i = 0;
            while(rs.next()) {
                String number = rs.getString(1);
                String sex = rs.getString(2);
                float Grade = rs.getFloat(3);
                float Math = rs.getFloat(4);
                float English = rs.getFloat(5);
                float CS = rs.getFloat(6);
                Data[i][0] = number;
                Data[i][1] = sex;
                Data[i][2] = String.valueOf(Grade);
//                Data[i][3] = String.valueOf(Math);
//                Data[i][4] = String.valueOf(English);
//                Data[i][5] = String.valueOf(CS);
                i++;
            }
        }
        catch(SQLException e){}
    }

   public void Save(String InputData[][],int Row){
        try {
            sql.executeUpdate("DELETE FROM test");  //"INSERT INTO test VALUES"+"','"+InputData[0][0]+"','"+InputData[0][1]+"','"+","+InputData[0][2]
            //写入数据至数据库
            for(int i=0;i<Row;i++){
                sql.executeUpdate("INSERT INTO test VALUES('"+InputData[i][0]+"','"+InputData[i][1]+"',"+InputData[i][2]+")");
            }
//            sql.executeUpdate("INSERT INTO test VALUES('"+InputData[0][0]+"','"+InputData[0][1]+"',"+InputData[0][2]+")");
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getConn(Connection Conn){
        conn = Conn;
    }

    public void setStringData(  String data[][]){
        Data = data;
    }
    public int getRow(){
        return row;
    }
}


/*未使用方法*/
class Select{
    Statement sql;
    void getSql(Statement s) {
        sql = s;
    }
    void Alter1(int num) {
        try {
            int alt = sql.executeUpdate("INSERT INTO test(number,sex,grade) VALUES('R12','将三',80),('R10','李武',1.76)");
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class Delete{
    Statement sql;
    void getSql(Statement s) {
        sql = s;
    }
    void Delete1(int num) {
        try {
            int del = sql.executeUpdate("Delete from test where number="+num);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class Alter{
    Statement sql;
    void getSql(Statement s) {
        sql = s;
    }
    void Alter1(int num) {
        try {
            int alt = sql.executeUpdate("update test set sex='男' where number = "+num);
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
