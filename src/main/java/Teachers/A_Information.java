package Teachers;

import Tips.Comfirmed;
import dao.JDBC;
import dao.Teachers_operate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pojo.Student;

import java.sql.ResultSet;

public class A_Information {



    //    搜索框
    @FXML
    private TableView<Student> tableView;;
    @FXML
    private TableColumn<Student,String> id;
    @FXML
    private TableColumn<Student,String> name;
    @FXML
    private TableColumn <Student,String> gender;
    @FXML
    private TableColumn <Student,Integer>age;
    @FXML
    private TableColumn<Student,String> profession;
    @FXML
    private TableColumn<Student,Integer> phone;


    private ObservableList<Student> studentList = FXCollections.observableArrayList();




    //输入文本框
    @FXML
    private TextField content;



    //返回按钮
    @FXML
    private Button rtn;
    public void set_return(MouseEvent mouseEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Teachers/user.fxml"));
        rtn.getScene().setRoot(root);
    }



    //声明静态成员变量,在下面的方法中,当发生点击事件的时候给此成员变量赋值
    private  static Student selectedData;

//    通过 `MyClass.setSelectedData()` 方法来设置 `selectedData` 的值，
//    通过 `MyClass.getSelectedData()` 方法来获取 `selectedData` 的值。
    //利用静态方法获取成员变量
    public static Student getSelectedData() {
        return selectedData;
    }


    /**初始化表格*/
    //当FXML文件加载并关联控制器类时，JavaFX会自动创建控制器类的实例，
    // 并自动调用initialize()方法。在initialize()方法内部，loadData()方法被调用，
    // 从而实现在加载FXML文件时自动加载数据。
    //将点击事件添加在JavaFX的initialize()方法中。这样，在页面加载时就会注册监听器，并在选中事件发生时执行回调函数。
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

    public  void loadData() throws Exception{
        String sql = "SELECT id, name, gender, age, profession, phone FROM small_systems.student";
        ResultSet rs = JDBC.executeQuery(sql);

        //存储数据库中的每一列
        while (rs.next()) {
            int studentId = rs.getInt("id");
            String studentName = rs.getString("name");
            String studentGender = rs.getString("gender");
            int studentAge = rs.getInt("age");
            String studentProfession = rs.getString("profession");
            int studentPhone = rs.getInt("phone");

            //将所有列都放到集合当中去
            Student student = new Student(studentId, studentName, studentGender, studentAge, studentProfession, studentPhone);
            studentList.add(student);
        }


        //数据库中的列名与数据模型类（pojo）绑定
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableView.setItems(studentList);

    }

    //查询按钮
    @FXML
    private Button search;
    //查询数据
    public void set_search(MouseEvent mouseEvent) throws Exception {
        //获取查询文本框的内容
        String cot = content.getText();
        System.out.println(cot);

//        将文本框的内容传到查询语句中,并获取查询到的这一列
        //学生信息查询,和老师信息查询用一样的方法,所以不需要再单独在老师类中写JDBC的sql语句
        ObservableList<Student> searchResult = Teachers_operate.search(cot);

        // 清空表格中所有数据
        tableView.getItems().clear();

        // 将这一列新数据插入表格
        tableView.setItems(searchResult);

        // 显示表格
        tableView.setVisible(true);

    }

    //增加  按钮
    @FXML
    private Button add;
    //增加行
    public void set_add (MouseEvent mouseEvent) throws Exception {

        //加载新页面
        Parent root=FXMLLoader.load(getClass().getResource("/Teachers/A_increase.fxml"));
        Scene scene =new Scene(root,600,400);
        Stage stage=new Stage();
        stage.setResizable(false);
        stage.setTitle("增加");
        stage.setScene(scene);
        // 获取当前窗口的 Stage 对象
        Stage currentStage = (Stage) add.getScene().getWindow();
        // 将新窗口置于最高层级
        stage.setAlwaysOnTop(true);
        // 设置新窗口为应用程序模态对话框
        stage.initModality(Modality.APPLICATION_MODAL);
        // 设置新窗口的所有者为当前舞台
        stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象
        stage.showAndWait();

    }

    //删除  按钮
    @FXML
    private Button delete;
    //删除行
    @FXML
    private void handleDeleteButtonAction(ActionEvent event) throws Exception{
        // 获取选中的行
        Student selectedData = tableView.getSelectionModel().getSelectedItem();
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
            Stage currentStage = (Stage) change.getScene().getWindow();
            // 将新窗口置于最高层级
            stage.setAlwaysOnTop(true);
            // 设置新窗口为应用程序模态对话框
            stage.initModality(Modality.APPLICATION_MODAL);
            // 设置新窗口的所有者为当前舞台
            stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象
            stage.showAndWait();

            if(Comfirmed.a){
                // 从ObservableList中移除选中的行
                tableView.getItems().remove(selectedData);
                // 获取选中行的主键值
                int studentId = selectedData.getId();
                System.out.println(studentId);
                //删除此行
                Teachers_operate.delete_students(studentId);
            } else {
                //重绘页面
                Stage previousStage =(Stage)delete.getScene().getWindow();
                previousStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Teachers/aaa.fxml")));
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
            Stage currentStage = (Stage) change.getScene().getWindow();

            // 将新窗口置于最高层级
            stage.setAlwaysOnTop(true);

            // 设置新窗口为应用程序模态对话框
            stage.initModality(Modality.APPLICATION_MODAL);

            // 设置新窗口的所有者为当前舞台
            stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象

            stage.showAndWait();
        }

    }

    //修改  按钮
    @FXML
    private Button change;
    //修改行
    public void set_change(MouseEvent mouseEvent) throws Exception{
        // 检查是否有行被选中
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            //加载新页面
            Parent root=FXMLLoader.load(getClass().getResource("/Teachers/A_modify.fxml"));
            Scene scene =new Scene(root,600,400);
            Stage stage=new Stage();
            stage.setResizable(false);
            stage.setTitle("修改学生信息");
            stage.setScene(scene);
            // 获取当前窗口的 Stage 对象
            Stage currentStage = (Stage) change.getScene().getWindow();
            // 将新窗口置于最高层级
            stage.setAlwaysOnTop(true);
            // 设置新窗口为应用程序模态对话框
            stage.initModality(Modality.APPLICATION_MODAL);
            // 设置新窗口的所有者为当前舞台
            stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象
            stage.showAndWait();
        } else {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/Tips/warn.fxml"));
            Scene scene =new Scene(root,280,270);
            Stage stage=new Stage();
            stage.setResizable(false);

            stage.setTitle("搞笑");
            stage.setScene(scene);


            // 获取当前窗口的 Stage 对象
            Stage currentStage = (Stage) change.getScene().getWindow();

            // 将新窗口置于最高层级
            stage.setAlwaysOnTop(true);

            // 设置新窗口为应用程序模态对话框
            stage.initModality(Modality.APPLICATION_MODAL);

            // 设置新窗口的所有者为当前舞台
            stage.initOwner(currentStage);  // currentStage 是当前窗口的 Stage 对象

            stage.showAndWait();
        }

        }




    //刷新按钮
    @FXML
    private Button refresh;
    public void set_refresh(MouseEvent mouseEvent) throws Exception{
        //点击按钮刷新页面
        Stage previousStage =(Stage) add.getScene().getWindow();
        previousStage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Teachers/aaa.fxml")));

    }

}
