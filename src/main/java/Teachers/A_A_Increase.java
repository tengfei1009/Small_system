package Teachers;


import dao.Students_operate;
import dao.Teachers_operate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.Student;

import java.io.IOException;

//增添学生数据
public class A_A_Increase {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private TextField age;
    @FXML
    private TextField profession;
    @FXML
    private TextField phone;

/**这个增加功能写完,感觉到有些不合理,因为学生可以自定义学号,也可以默认系统创建学号,但是如果这样,插入,数据一旦多起来就不好查询了*/


    boolean sss=true;
    //提交按钮
    @FXML
    private Button commit;
    //提交
    public void get_commit(ActionEvent actionEvent) throws Exception {
        //默认输入数据是不存在的,如果存在 exit的返回值为true,就不插入数据
        boolean exit= false;
        try{
            //查询数据库中是否已经存在数据,如果存在就不插入数据
            //判断输入不能为空
            exit = Students_operate.get_forget(Integer.parseInt(id.getText()));
        } catch (Exception e) {

        }

        if(exit){
            System.out.println("该用户已存在,请重新录入");
        } else {
            //执行增加操作
            upDate();

            if(sss){
                //当点击此按钮时,关闭此页面
                Stage previousStage = (Stage) commit.getScene().getWindow();
                previousStage.close();
            }else {
                Parent root = FXMLLoader.load(getClass().getResource("/Teachers/A_increase.fxml"));
                commit.getScene().setRoot(root);
            }
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


    //插入数据
    public void upDate() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        try{
            int studentId = Integer.parseInt(id.getText());
            String studentName = name.getText();
            String studentGender = gender.getText();
            int studentAge = Integer.parseInt(age.getText());
            String studentProfession = profession.getText();
            int studentPhone = Integer.parseInt(phone.getText());
            Student student = new Student(studentId, studentName, studentGender, studentAge, studentProfession, studentPhone);
            Teachers_operate.add_students(student);
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