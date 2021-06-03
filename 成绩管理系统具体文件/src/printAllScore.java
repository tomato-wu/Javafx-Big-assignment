import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class printAllScore extends Application {

    TableView<Student> table = new TableView<>(); //表控件是通过实例化 TableView 类创建的。

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new Group());
        primaryStage.setWidth(930);
        primaryStage.setHeight(500);
        primaryStage.setTitle("成绩表");
        //最上面的标题
        Label title = new Label("学生成绩单");
        title.setTextFill(Color.RED);
        title.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 24));

        // 加载表格
        TableColumn name = new TableColumn("姓名");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn ID = new TableColumn("学号");
        ID.setMinWidth(100);
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn Gmath = new TableColumn("高等数学");
        Gmath.setMinWidth(100);
        Gmath.setCellValueFactory(new PropertyValueFactory<>("gaomath"));

        TableColumn eng = new TableColumn("大学英语");
        eng.setMinWidth(100);
        eng.setCellValueFactory(new PropertyValueFactory<>("Eng"));

        TableColumn JavaScore = new TableColumn("Java程序设计");
        JavaScore.setMinWidth(100);
        JavaScore.setCellValueFactory(new PropertyValueFactory<>("JavaScores"));

        TableColumn os = new TableColumn("操作系统");
        os.setMinWidth(100);
        os.setCellValueFactory(new PropertyValueFactory<>("OS"));

        TableColumn AVERAGE = new TableColumn("平均分");
        AVERAGE.setMinWidth(100);
        AVERAGE.setCellValueFactory(new PropertyValueFactory<>("average"));

        TableColumn RANK = new TableColumn("排名");
        RANK.setMinWidth(100);
        RANK.setCellValueFactory(new PropertyValueFactory<>("rank"));

        TableColumn Sport = new TableColumn("体育");
        Sport.setMinWidth(100);
        Sport.setCellValueFactory(new PropertyValueFactory<>("sport"));

        TableColumn CPP = new TableColumn("C++语言");
        CPP.setMinWidth(100);
        CPP.setCellValueFactory(new PropertyValueFactory<>("cpp"));

        getProductsFromFile();

        table.getColumns().addAll(name,ID,JavaScore,Gmath,eng,os,Sport,CPP,AVERAGE);

       //提示文本
        Label tips = new Label("点击最上面成绩标签可排序");

        //返回按钮
        Button returnButton = new Button("返回主页面");
        //返回主界面按钮引入样式表
        returnButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        returnButton.getStyleClass().add("num-button");
        //返回主界面按钮阴影效果
        DropShadow shadow3 = new DropShadow();
        returnButton.setEffect(shadow3);
        //返回按钮功能实现
        returnButton.setOnAction(e -> {
            try {
                primaryStage.close();
                new start().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(tips,table, returnButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setId("mainPane");

        //加入到主面板
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        scene.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        //搭建舞台
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*****************************************************************************************************************/

    public class Student {
        private final SimpleStringProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty gaomath;
        private final SimpleStringProperty Eng;
        private final SimpleStringProperty cpp;
        private final SimpleStringProperty JavaScores;
        private final SimpleStringProperty OS;
        private final SimpleStringProperty sport;
        private final SimpleStringProperty average;
        private final SimpleStringProperty rank;

        Student(String id, String name,String JavaScores, String gaomath,String Eng, String OS, String sport,
                String cpp, String average,String rank)
        {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.cpp = new SimpleStringProperty(cpp);
            this.gaomath = new SimpleStringProperty(gaomath);
            this.Eng = new SimpleStringProperty(Eng);
            this.JavaScores =new SimpleStringProperty(JavaScores);
            this.OS=new SimpleStringProperty(OS);
            this.sport=new SimpleStringProperty(sport);
            this.average=new SimpleStringProperty(average);
            this.rank=new SimpleStringProperty(rank);
        }

        public String getId() {
            return this.id.get();
        }
        public void setId(String id) {
            this.id.set(id);
        }
        public String getCpp() {
            return this.cpp.get();
        }
        public void setCpp(String cpp) {
            this.id.set(cpp);
        }
        public String getName() {
            return this.name.get();
        }
        public void setName(String name) {
            this.name.set(name);
        }
        public String getGaomath() {
            return this.gaomath.get();
        }
        public void setGaomath(String gaomath) {
            this.gaomath.set(gaomath);
        }
        public String getEng() {
            return this.Eng.get();
        }
        public void setEng(String gaomath) {
            this.Eng.set(gaomath);
        }
        public String getJavaScores() {
            return this.JavaScores.get();
        }
        public void setJavaScores(String JavaScores) {
            this.JavaScores.set(JavaScores);
        }
        public String getOS() {
            return this.OS.get();
        }
        public void setOS(String OS) {
            this.OS.set(OS);
        }
        public String getSport() {
            return this.sport.get();
        }
        public void setSport(String sport) {
            this.sport.set(sport);
        }
        public String getAverage() {
            return this.average.get();
        }
        public void setAverage(String average) {
            this.average.set(average);
        }
        public String getRank() {
            return this.rank.get();
        }
        public void setRank(String rank) {
            this.rank.set(rank);
        }
    }

    /*****************************************************************************************************************/
    //从data.txt中读取数据传给表格
    private void getProductsFromFile() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
            String line;
            String[] fields;
            while ((line = br.readLine()) != null){
                fields = line.split(" ");
                double temp = Double.parseDouble(fields[2])+Double.parseDouble(fields[3])+Double.parseDouble(fields[4])
                        +Double.parseDouble(fields[5])+Double.parseDouble(fields[6])+Double.parseDouble(fields[7]);
                temp = temp/6;
                temp = Math.round(temp*100)/100;
                table.getItems().add(new Student(fields[1], fields[0], fields[2], fields[3], fields[4], fields[5],
                        fields[6], fields[7],String.valueOf(temp),"1"));
            }
            br.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
