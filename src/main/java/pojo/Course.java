package pojo;

public class Course {

    //存放一些学生信息
    private int id;
    private String name;
    private String classes;

    //存放课程表
    private int chinese;
    private int math;
    private int English;
    //物理
    private int physics;
    //化学
    private int Chemistry;

    //无参构造
    public Course() {
    }

    public Course(int id, String name, String classes, int chinese, int math, int english, int physics, int chemistry) {
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.chinese = chinese;
        this.math = math;
        English = english;
        this.physics = physics;
        Chemistry = chemistry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return English;
    }

    public void setEnglish(int english) {
        English = english;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int getChemistry() {
        return Chemistry;
    }

    public void setChemistry(int chemistry) {
        Chemistry = chemistry;
    }
}
