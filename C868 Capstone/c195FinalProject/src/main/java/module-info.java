module main.scheduler.c195finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens main.scheduler.c195finalproject to javafx.fxml;
    opens main.scheduler.c195finalproject.builder;
    opens main.scheduler.c195finalproject.controller to javafx.fxml;
    opens main.scheduler.c195finalproject.data;
    opens main.scheduler.c195finalproject.list;
    opens main.scheduler.c195finalproject.model;
    opens main.scheduler.c195finalproject.utility;
    //opens main.scheduler.c195finalproject.validator;

    exports main.scheduler.c195finalproject;
    exports main.scheduler.c195finalproject.controller;
    exports main.scheduler.c195finalproject.builder;
    exports main.scheduler.c195finalproject.data;
    exports main.scheduler.c195finalproject.list;
    exports main.scheduler.c195finalproject.model;
    exports main.scheduler.c195finalproject.utility;
    //exports main.scheduler.c195finalproject.validator;
}






/*
module main.scheduler.c195finalproject {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;


        opens main.scheduler.c195finalproject to javafx.fxml;

        opens main.scheduler.c195finalproject.controller to javafx.fxml;

        exports main.scheduler.c195finalproject;
        exports main.scheduler.c195finalproject.controller;


        }

 */