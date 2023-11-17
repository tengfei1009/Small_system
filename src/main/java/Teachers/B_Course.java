package Teachers;

import Tips.Comfirmed;
import dao.JDBC;
import dao.Teachers_operate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.Teacher;

import java.sql.ResultSet;


public class B_Course {
    //课程管理


    //获取文本框中的内容
    @FXML
    private TextField input;


   @FXML
   private TableView<Teacher> tableView;
    @FXML
   private TableColumn<Teacher,String> id;
    @FXML
    private TableColumn<Teacher,String> name;
    @FXML
    private  TableColumn<Teacher,String> gender;
    @FXML
    private TableColumn<Teacher,Integer> age;
    @FXML
    private TableColumn<Teacher,String> course;

    private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();



    //声明静态成员变量,在下面的方法中,当发生点击事件的时候给此成员变量赋值
    private  static Teacher selectedData;

    //利用静态方法获取成员变量
    public static Teacher getSelectedData() {
        return selectedData;
    }

    //初始化表格
    @FXML
    public void initialize() throws Exception{

        /**初始化表格*/
        loadData();

        //使用 selectedItemProperty() 方法来监听选中行的变化
        //并在选中行发生变化时触发相应的代码。当选中行发生变化时，我们将新选中的行数据存储在 selectedData 变量中。

        /**observable、oldValue 和 newValue 都是 ChangeListener 接口的方法参数。

         observable：表示正在监听的对象，它是一个可观察的对象，当其状态发生变化时，会触发监听器。
         oldValue：表示变化前的值，即先前的选中行数据。
         newValue：表示变化后的值，即当前选中的新行数据。
         */
        //添加键盘监听事件
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                //监听,获取当前选中行的数据
                // 将选中行的数据存储在变量中   这样在另一个页面初始化的时候直接调用此变量,获取并给另一个页面设置初始化值
                selectedData = newValue;
                System.out.println("获取到值");
            }
        });
    }

    //初始化表格
    public  void loadData() throws Exception{
        String sql = "SELECT t.id, t.name, t.gender, t.age, c.course FROM teacher t JOIN teacher_course tc ON t.id=tc.teacher_id JOIN course c ON tc.course_id=c.id  ORDER BY teacher_id ASC";
        ResultSet rs = JDBC.executeQuery(sql);

        while (rs.next()) {
            String teacherId = rs.getString("id");
            String teacherName = rs.getString("name");
            String teacherGender = rs.getString("gender");
            int teacherAge = rs.getInt("age");
            String  teacherCourse = rs.getString("course");

            Teacher teacher = new Teacher(teacherId, teacherName, teacherGender, teacherAge, teacherCourse);
            teacherList.add(teacher);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        course.setCellValueFactory(new PropertyValueFactory<>("course"));

        tableView.setItems(teacherList);

    }

    //查询按钮
    @FXML
    private Button search;
    //查询数据
    public void set_search(MouseEvent mouseEvent) throws Exception {
        //获取查询文本框的内容
        String cot = input.getText();
        System.out.println(cot);

//        将文本框的内容传到查询语句中,并获取查询到的这一列
        //教师信息查询
        ObservableList<Teacher> searchResult = Teachers_operate.course_search(cot);

        // 清空表格中所有数据
        tableView.getItems().clear();

        // 将这一列新数据插入表格
        tableView.setItems(searchResult);

        // 显示表格
        tableView.setVisible(true);

    }

//    给老师增加课程,老师可以上很多课
    @FXML
    private Button increase;
    public void set_increase(MouseEvent mouseEvent) throws Exception{
        Parent root=FXMLLoader.load(getClass().getResource("/Teachers/B_increase.fxml"));
        Scene scene =new Scene(root,600,400);
        Stage stage=new Stage();
        stage.setResizable(false);
        stage.setTitle("增加老师课程");
        stage.setScene(scene);
        stage.show();
    }
//    修改课程
    @FXML Button set;
    public void re_set(MouseEvent mouseEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Teachers/B_b_modify.fxml"));
        rtn.getScene().setRoot(root);
    }


//    删除课程
    @FXML
    private Button delete;
    public void handleDeleteButtonAction () throws Exception {
        // 获取选中的行
//        selectedData = tableView.getSelectionModel().getSelectedItem();

        //根据课程获取选中行的id
        // 获取选中行的课程，删除老师的课程
        //获取中间表的teacher_id
        String teacherId = selectedData.getId();
//        获取中间表的course_Id
        String course = selectedData.getCourse();
        int courseId= Teachers_operate.select_course(course);
        System.out.println(teacherId);
        System.out.println(courseId);


        if (selectedData != null) {

            //先跳转页面,当确认后修改个人信息,如果点的取消,关闭提示页面,重绘页面
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/Tips/comfirmed.fxml"));
            Scene scene =new Scene(root,286,161);
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("删除");
            stage.setScene(scene);
            // 获取当前窗口的 Stage 对象
            Stage currentStage = (Stage) delete.getScene().getWindow();
            // 将新窗口置于最高层级
            stage.setAlwaysOnTop(true);
            // 设置新窗口为应用程序模态对话框
            stage.initModality(Modality.APPLICATION_MODAL);
            // 设置新窗口的所有者为当前舞台
            stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象
            stage.showAndWait();


            //如果同意删除，就直接删除
            if(Comfirmed.a){
                // 从ObservableList中移除选中的行
                tableView.getItems().remove(selectedData);


                //删除此行
                Teachers_operate.delete_course(teacherId,courseId);
            } else {
                //否则重绘页面
                Stage previousStage =(Stage)delete.getScene().getWindow();
                previousStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Teachers/bbb.fxml")));
            }
        } else {
            //如果什么都没填，就报错
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/Tips/warn.fxml"));
            Scene scene =new Scene(root,280,270);
            Stage stage=new Stage();
            stage.setResizable(false);

            stage.setTitle("搞笑");
            stage.setScene(scene);


            // 获取当前窗口的 Stage 对象
            Stage currentStage = (Stage) delete.getScene().getWindow();

            // 将新窗口置于最高层级
            stage.setAlwaysOnTop(true);

            // 设置新窗口为应用程序模态对话框
            stage.initModality(Modality.APPLICATION_MODAL);

            // 设置新窗口的所有者为当前舞台
            stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象

            stage.showAndWait();
        }

    }


    //返回按钮
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Teachers/user.fxml"));
        rtn.getScene().setRoot(root);
    }



}
