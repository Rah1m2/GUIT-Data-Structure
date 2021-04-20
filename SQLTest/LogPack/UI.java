package LogPack;

public class UI {
    public static void main(String args[]){
        UICreator ui = new UICreator();                      //初始化控制页面布局，创建对象同时调用构造方法初始化窗体
        ButtonListener blistener = new ButtonListener();     //初始化监听器
        ui.ConnectListener(blistener);                       //将监听器与UI组件联系起来，传入的参数是刚创建的监听器blistener
        ui.setBounds(800,450,400,320);
        ui.setTitle("Security System");
        blistener.setUI(ui);
    }
}