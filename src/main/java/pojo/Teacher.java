package pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Teacher {

    /**这些成员变量被声明为final，表示它们在初始化后不能再被修改。
     然而，你的类中又提供了一个无参的构造函数Teacher()，这意味着你可以在对象创建后通过该构造函数修改这些final成员变量的值，
     从而与 final 的限制相冲突。这些成员变量被声明为final，表示它们在初始化后不能再被修改。然而，你的类中又提供了一个无参的构造函数Teacher()，
     这意味着你可以在对象创建后通过该构造函数修改这些final成员变量的值，从而与 final 的限制相冲突。*/

    //所以这个地方不能有空参构造
//    public Teacher() {
//    }

//    存放教师表
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty gender;
    private final SimpleIntegerProperty age;
    private final SimpleStringProperty teach;
    private final SimpleIntegerProperty password;

    private final SimpleStringProperty course;

    public Teacher(String id, String name, String gender, int age, String course) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
        this.teach = null;
        this.password = null;

        this.course = new SimpleStringProperty(course);
    }

    //给老师添加许多课程
    public Teacher(String id, String course) {
        this.id = new SimpleStringProperty(id);
        this.name = null;
        this.gender = null;
        this.age = null;
        this.course = new SimpleStringProperty(course);
        this.teach = null;
        this.password = null;
    }


    public Teacher(String course) {
        this.id = null;
        this.name = null;
        this.gender = null;
        this.age = null;
        this.course = new SimpleStringProperty(course);
        this.teach = null;
        this.password = null;
    }



    ///管理员管理教师基本信息
    public Teacher(String id, String name, String gender, int age, String teach, int password) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
        this.teach = new SimpleStringProperty(teach);
        this.password = new SimpleIntegerProperty(password);

        this.course = null;

    }


    //查询到教师id

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }
    public String getTeach() {
        return teach.get();
    }

    public SimpleStringProperty teachProperty() {
        return teach;
    }

    public void setTeach(String teach) {
        this.teach.set(teach);
    }

    public int getPassword() {
        return password.get();
    }

    public SimpleIntegerProperty passwordProperty() {
        return password;
    }

    public void setPassword(int password) {
        this.password.set(password);
    }


    public String getCourse() {
        return course.get();
    }

    public SimpleStringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }


}
