package Teachers;

import dao.Teachers_operate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static dao.Teachers_operate.select_teacher_id;


public class B_B_Increase {
    @FXML
    TextField id;
    @FXML
    TextField course;
    @FXML
    Label name;

    //初始化
    @FXML
    public void initialize() throws Exception{
        //查询id获取到name  并将name的值赋值给Label ,实现,当输入id的时候会查询到人名
        id.textProperty().addListener((observable, oldValue, newValue) -> {
            // 根据输入的 ID 查询数据库获取姓名
            String na=null;
            try {
               na = select_teacher_id(newValue);
            } catch (Exception e) {
                System.out.println("不存在该工号");            }
            // 如果工号存在,将姓名赋值给 Label
                name.setText(na);
        });
    }

    //提交按钮
    @FXML
    private Button commit;
    @FXML
    public void get_commit(MouseEvent mouseEvent) throws Exception {
        //执行增加操作
        upDate();

//        当点击此按钮时,关闭此页面
        Stage previousStage = (Stage) commit.getScene().getWindow();
        previousStage.close();

    }
    //插入数据
    public void upDate()throws Exception {

//        ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
        String teacherId = id.getText();
        String teacherCourse = course.getText();


        //查询数据库中是否存在,如果不存在就新建课程课程
        int num=Teachers_operate.select_course(teacherCourse);
//        System.out.println(num);
        System.out.println("why");

        //查询之前中间表中是否已经有课存在
        boolean exist = Teachers_operate.select_teacher_course(teacherId,num);

        System.out.println(exist);
        //如果不存在，就插入中间表数据
        if (exist) {
            Teachers_operate.add_course(teacherId,num);
        }

    }


    //返回
    @FXML
    private Button rtn;
    public void set_rtn(MouseEvent mouseEvent) throws Exception{
        Stage previousStage = (Stage) rtn.getScene().getWindow();
//                 关闭此页面
        previousStage.close();
    }
}
