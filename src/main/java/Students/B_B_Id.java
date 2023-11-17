package Students;

import dao.JDBC;
import dao.Students_operate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pojo.Student;

import java.sql.ResultSet;

public class B_B_Id {


    @FXML
    private TextField input;

    //这个表格用来定义初始化
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn id;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn profession;
    @FXML
    private TableColumn classes;
    @FXML
    private TableColumn course;



    @FXML
    public void initialize() throws Exception{
        //初始化表格
        set_information();
    }

    //定义一个集合用来存放表格中所有数据
    ObservableList<Student> studentList = FXCollections.observableArrayList();

    //初始化表格信息
    public void set_information () throws Exception{
        //使用左外查询
        //首先给表起一个别名
        //用别名.字段名 来表示一列
//        查询学生所选的课程
        String sql = "SELECT s.id, s.name,s.profession,s.classes,c.course FROM student s JOIN student_course sc ON s.id = sc.student_id JOIN course c ON sc.course_id = c.id";
        ResultSet rs = JDBC.executeQuery(sql);
        while (rs.next()) {
            int student_id =rs.getInt("id");
            String name = rs.getString("name");
            String profession = rs.getString("profession");
            String classes = rs.getString("classes");
            String course = rs.getString("course");

            Student student = new Student(student_id, name, profession, classes, course);
            studentList.add(student);
//            System.out.println("添加成功");
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
        classes.setCellValueFactory(new PropertyValueFactory<>("classes"));
        course.setCellValueFactory(new PropertyValueFactory<>("course"));

        tableView.setItems(studentList);
    }


    //搜索按钮
    //点此按钮进行数据查询
    @FXML
    private Button search;

    public void re_search (ActionEvent actionEvent) throws Exception{
        //查询学生id,同时实现模糊查询和精确查询
        //获取文本框中id
        try {
            Integer get_search = Integer.parseInt(input.getText());
            ObservableList<Student> searchResult = Students_operate.get_id(get_search);

            //清空表中所有数据
            tableView.getItems().clear();

            //将这一列新数据插入表格
            tableView.setItems(searchResult);

            //显示表格
            tableView.setVisible(true);
        }catch (Exception e) {
            Parent root = FXMLLoader.load(getClass().getResource("/Students/B_id.fxml"));
            rtn.getScene().setRoot(root);
        }


    }


    //返回按钮
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
//        JDBC
        Parent root = FXMLLoader.load(getClass().getResource("/Students/Bbb.fxml"));
        rtn.getScene().setRoot(root);
    }

}
