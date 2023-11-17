package Students;

import dao.Administrators_operate;
import dao.Students_operate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class D_Forget {

    @FXML
    private TextField username;
    @FXML
    private TextField phone;
    @FXML
    private TextField number;
    @FXML
    private TextField pwd;
    @FXML
    private  TextField pass;

    //返回按钮
    @FXML
    private Button rtn;
    public void set_return(ActionEvent actionEvent) throws Exception{
//        JDBC
        Parent root = FXMLLoader.load(getClass().getResource("/Students/Main.fxml"));
        rtn.getScene().setRoot(root);
    }


    //提交按钮
    @FXML
    private Button commit;
    public void set_commit(ActionEvent actionEvent) throws IOException {
       try {
           // 从文本框中获取内容
           int updatedId = Integer.parseInt(username.getText());
           int new_password= Integer.parseInt(pwd.getText());

           String get_pass =pass.getText();
           int get_username = Integer.parseInt(username.getText());
           //查询管理员
           boolean manage= Administrators_operate.Secret_key(get_pass);

           //查询用户名
           boolean user = Students_operate.get_forget(get_username);
           //使用查询语句，传递两个参数，如果说满足管理员代码, 并且能够查询到用户名存在就可以修改密码
           if(manage && user) {
               Students_operate.update_student(new_password,updatedId);
               System.out.println("成功修改");

               System.out.println("提交成功");
               //返回登录界面
               Parent root = FXMLLoader.load(getClass().getResource("/Students/Main.fxml"));
               commit.getScene().setRoot(root);
       } else {
               //加载弹框
               Parent root = null;
               root = FXMLLoader.load(getClass().getResource("/Tips/warn.fxml"));
               Scene scene =new Scene(root,280,270);
               Stage stage=new Stage();
               stage.setResizable(false);

               stage.setTitle("搞笑");
               stage.setScene(scene);

               // 获取当前窗口的 Stage 对象
               Stage currentStage = (Stage) commit.getScene().getWindow();

               // 将新窗口置于最高层级
               stage.setAlwaysOnTop(true);

               // 设置新窗口为应用程序模态对话框
               stage.initModality(Modality.APPLICATION_MODAL);

               // 设置新窗口的所有者为当前舞台
               stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象

               stage.showAndWait();
               //重绘页面
               Stage previousStage =(Stage)commit.getScene().getWindow();
               previousStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Students/Forget.fxml")));
           }

        }catch(Exception e) {
           //加载弹框
           Parent root = null;
           root = FXMLLoader.load(getClass().getResource("/Tips/warn.fxml"));
           Scene scene =new Scene(root,280,270);
           Stage stage=new Stage();
           stage.setResizable(false);

           stage.setTitle("搞笑");
           stage.setScene(scene);


           // 获取当前窗口的 Stage 对象
           Stage currentStage = (Stage) commit.getScene().getWindow();

           // 将新窗口置于最高层级
           stage.setAlwaysOnTop(true);

           // 设置新窗口为应用程序模态对话框
           stage.initModality(Modality.APPLICATION_MODAL);

           // 设置新窗口的所有者为当前舞台
           stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象

           stage.showAndWait();

           //重绘页面
           Stage previousStage =(Stage)commit.getScene().getWindow();
           previousStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Students/Forget.fxml")));
       }


    }


}
