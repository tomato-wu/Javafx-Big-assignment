import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class start extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //主面板
        VBox mainPane = new VBox(20);
        mainPane.setAlignment(Pos.CENTER);

        //最上面的标题
        Label title = new Label("学生成绩管理系统");
        title.setTextFill(Color.RED);
        title.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 24));

/*********************************************************************************************************************/

        //输入
        Button writeScoreButton = new Button("录入成绩");
        //输入按钮引入样式表
        writeScoreButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        writeScoreButton.getStyleClass().add("num-button");
        //输入按钮阴影效果
        DropShadow shadow0 = new DropShadow();
        writeScoreButton.setEffect(shadow0);
        //输入功能实现
        writeScoreButton.setOnAction(e->{
            try{
                primaryStage.close();
                new writeScore().start(new Stage());
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });  //跳转到录入成绩界面

/*********************************************************************************************************************/

        //查找
        Button searchButton = new Button("查询成绩");
        //查找按钮引入样式表
        searchButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        searchButton.getStyleClass().add("num-button");
        //查找按钮阴影效果
        DropShadow shadow1 = new DropShadow();
        searchButton.setEffect(shadow1);
        //查找功能实现
        searchButton.setOnAction(e->{       //跳转到查询成绩界面
            try{
                primaryStage.close();
                new searchScore().start(new Stage());
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

/*********************************************************************************************************************/

        //打印
        Button printScoreButton = new Button("打印成绩");
        //打印按钮引入样式表
        printScoreButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        printScoreButton.getStyleClass().add("num-button");
        //打印按钮阴影效果
        DropShadow shadow2 = new DropShadow();
        printScoreButton.setEffect(shadow2);
        printScoreButton.setOnAction(e->{       //跳转到打印成绩界面
            try{
                primaryStage.close();
                new printAllScore().start(new Stage());
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

/*********************************************************************************************************************/

        //清空数据库数据
        Button ClearButton = new Button("清空数据");
        //清空按钮引入样式表
        ClearButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        ClearButton.getStyleClass().add("num-button");
        //清空按钮阴影效果
        DropShadow shadow4 = new DropShadow();
        ClearButton.setEffect(shadow4);
        //清空数据库功能实现
        ClearButton.setOnAction(e->{
            File file =new File("data.txt");
            try {
                if(!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter =new FileWriter(file);
                fileWriter.write("");
                fileWriter.flush();
                fileWriter.close();

                //弹出提示框 显示清除成功，提供ok按钮，按下返回
                {
                    Stage stage = new Stage();
                    VBox okPane = new VBox(20);
                    okPane.setAlignment(Pos.CENTER);
                    //最上面的标题
                    Label okTitle = new Label("清除成功！");
                    okTitle.setTextFill(Color.RED);
                    okTitle.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 20));
                    // ok按钮
                    Button writeAgainButton = new Button("OK");
                    writeAgainButton.setOnAction(e2->{
                        try{
                            stage.close();
                        } catch (Exception e3){
                            e3.printStackTrace();
                        }
                    });
                    //舞台布置
                    okPane.getChildren().addAll(okTitle,writeAgainButton);
                    Scene s = new Scene(okPane,200,100);
                    stage.setScene(s);
                    stage.show();
                }

            } catch (IOException e6) {
                e6.printStackTrace();
            }
        });

/*********************************************************************************************************************/

        //退出
        Button exitButton = new Button("退出程序");
        //退出按钮引入样式表
        exitButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        exitButton.getStyleClass().add("num-button");
        //退出按钮阴影效果
        DropShadow shadow3 = new DropShadow();
        exitButton.setEffect(shadow3);
        //退出功能实现
        exitButton.setOnAction(e->{       //跳转到打印成绩界面
                primaryStage.close();
        });

/*********************************************************************************************************************/

        //所有部件加入到主面板
        mainPane.getChildren().addAll(title,writeScoreButton,searchButton,printScoreButton,ClearButton,exitButton);
        mainPane.setId("mainPane");
        //搭建舞台
        Scene scene = new Scene(mainPane, 400, 500);
        scene.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        primaryStage.setTitle("功能选择窗口");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
