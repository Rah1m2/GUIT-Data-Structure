package GradeManagement;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TableListener implements KeyListener {
    JTable table, ListenedTable;
    DefaultTableModel model;
    int row;
    int FinalRow;

    void setTable(JTable table) {
        this.table = table;
    }
    void setModel(DefaultTableModel model){
        this.model = model;
    }
    void setRow(int row) {
        this.row = row;
    }


    //键盘监听器
    @Override
    public void keyTyped(KeyEvent e) {
        /*回车增加行*/
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            if (/*table.getRowCount() == table.getSelectedRow()+1 || */table.getSelectedRow() == 0 ||table.getRowCount() == 0) {
                Object[] newRow = new Object[3];
                ((DefaultTableModel) table.getModel()).addRow(newRow);
                int row = table.getRowCount() - 1;
                table.setRowSelectionInterval(row, row);
            }
        }

        /*退格键删除行*/
        if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE && table.getRowCount()>5){
            //删除光标行
            if(table.getRowCount() - 1 != -1) {
                model.removeRow(table.getSelectedRow());//删除操作
            }
            //将光标定位到删除后的最后一行
            if(table.getRowCount() - 1 != -1) {
                int row = table.getRowCount() - 1;
                table.setRowSelectionInterval(row, row);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}



//                if (table.getSelectedRow() == 0 ||table.getRowCount() == FinalRow) {
//                Object[] newRow = new Object[3];
//                ((DefaultTableModel) table.getModel()).addRow(newRow);
//                int row = table.getRowCount() - 1;
//                table.setRowSelectionInterval(row, row);
//            }


//    public void valueChanged(ListSelectionEvent e) {
//        if (table.getRowCount() == table.getSelectedRow() + 1) {
//            Object[] newRow = new Object[3];
//            ((DefaultTableModel) table.getModel()).addRow(newRow);
//        }
//    }