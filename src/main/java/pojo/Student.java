package pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//新建一个类，确定数据后，构造数据模型
public class Student {
        private final SimpleIntegerProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty gender;
        private final SimpleIntegerProperty age;
        private final SimpleStringProperty profession;
        private final SimpleIntegerProperty phone;
        private final SimpleStringProperty classes;
        private final SimpleStringProperty course;

        //学生基本信息构造函数
        public Student(int id, String name, String gender, int age, String profession, int phone) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.gender = new SimpleStringProperty(gender);
            this.age = new SimpleIntegerProperty(age);
            this.profession = new SimpleStringProperty(profession);
            this.phone = new SimpleIntegerProperty(phone);
            this.classes = null;
            this.course = null;
        }

    //使用学生id查询课程构造函数
    public Student(int id,String name, String profession, String classes, String course) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.gender = null;
        this.age = null;
        this.profession = new SimpleStringProperty(profession);
        this.phone = null;
        this.classes = new SimpleStringProperty(classes);
        this.course = new SimpleStringProperty(course);
    }

    //使用课程查询学生数量
        public Student(String course,int id, String name, String classes ) {
            this.course = new SimpleStringProperty(course);
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.classes = new SimpleStringProperty(classes);
            this.gender = null;
            this.age = null;
            this.profession = null;
            this.phone = null;
        }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
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

    public String getProfession() {
        return profession.get();
    }

    public SimpleStringProperty professionProperty() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession.set(profession);
    }

    public int getPhone() {
        return phone.get();
    }

    public SimpleIntegerProperty phoneProperty() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone.set(phone);
    }

    public String getClasses() {
        return classes.get();
    }

    public SimpleStringProperty classesProperty() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes.set(classes);
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
