package Teachers;

import dao.Teachers_operate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


//修改
public class B_B_Modify {


    //获取老ID,根据老ID修改学生信息
    static String oldId;

    //定义一个初始化方法,当每次获取到行时,更新行数据
    public void initialize() {
        //每次重新设置老id
        oldId= B_Course.getSelectedData().getId();
        System.out.println(oldId);
    }

    //提交按钮
    @FXML
    Button commit;
    public void set_commit(MouseEvent mouseEvent) throws Exception{
        //调用修改的方法
        upDate();
        //当点击此按钮时,关闭此页面
//        Stage previousStage = (Stage) commit.getScene().getWindow();
//        previousStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/Teachers/bbb.fxml"));
        commit.getScene().setRoot(root);
    }



    @FXML
    private TextField input;
    private void upDate() throws Exception{
        // 从文本框中获取内容
        String course= input.getText();

        // 更新 stu 对象的属性
        B_Course.getSelectedData().setCourse(course);

        //利用course来查询对应在课程表中的主键id
        int course_id= Teachers_operate.select_course(course);
        // 执行数据库操作
        //里面传递两个参数,首先需要获取到,该列的id,也就是教师工号;
//        教师工号的获取,需要从上一个页面的列中获取.所以需要调用selectData

        //还是需要查询重复,如果有重复的则无法修改课程
        boolean exist = Teachers_operate.select_teacher_course(oldId,course_id);

        //如果有存在相同数据的就不改
        if(exist){
            Teachers_operate.edit_course(oldId,course_id);

        }else {
            System.out.println("该课程已存在,请重新插入");
        }

        // 在数据库中对上面的值进行匹配，如果匹配成功就直接修改

    }


    //返回按钮
    @FXML
    private Button rtn;

    public void set_return(MouseEvent mouseEvent) throws Exception {
//        JDBC
        Parent root = FXMLLoader.load(getClass().getResource("/Teachers/bbb.fxml"));
        rtn.getScene().setRoot(root);
    }
}