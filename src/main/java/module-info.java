module com.example.small_systems {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Students to javafx.fxml;
    exports Students;
    opens Teachers;
    exports dao;
    opens dao to javafx.fxml;
    opens pojo to javafx.base;
    exports Teachers;

    exports Administrators;
    opens Administrators to javafx.fxml;
    exports Tips;
    opens Tips;
//    exports dao.students_sql;
//    opens JDBC.students_sql to javafx.fxml;
//    exports JDBC;
//    opens JDBC to javafx.fxml;

}