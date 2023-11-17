package Administrators;

import dao.Administrators_operate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.Teacher;

import java.io.IOException;

public class A_A_increase {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private TextField age;
    @FXML
    private TextField teach;
    @FXML
    private TextField password;



    boolean sss=true;

    //提交按钮
    @FXML
    private Button commit;
    //提交
    public void set_commit(MouseEvent mouseEvent) throws Exception{
//        执行增加操作
        upDate();
//
        if(sss){
//        当点击此按钮时,关闭此页面
            Stage previousStage = (Stage) commit.getScene().getWindow();
            previousStage.close();
        }else {
            Parent root = FXMLLoader.load(getClass().getResource("/Administrators/A_increase.fxml"));
            commit.getScene().setRoot(root);
        }

    }

    //返回按钮
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
        Stage previousStage = (Stage) rtn.getScene().getWindow();
        // 关闭此页面
        previousStage.close();
    }


    //设置一个中间变量

    //插入数据
    public void upDate() throws Exception {
        try {
//        ObservableList<Teacher> teachers = FXCollections.observableArrayList();
            String teacherId = id.getText();
            String teacherName = name.getText();
            String teacherGender = gender.getText();
            int teacherAge = Integer.parseInt(age.getText());
            String teacherTeach = teach.getText();
            int teacherPassword = Integer.parseInt(password.getText());

            Teacher teacher = new Teacher(teacherId, teacherName, teacherGender, teacherAge, teacherTeach, teacherPassword);
            Administrators_operate.add_teachers(teacher);
        } catch (Exception e) {
            Parent root = null;
            try {
                sss=false;
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
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            commit.getScene();
        }
        }
    }

