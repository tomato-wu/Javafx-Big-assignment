import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.plaf.FontUIResource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class searchScore extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //主面板
        VBox searchPane = new VBox(20);
        searchPane.setAlignment(Pos.CENTER);

        //最上面的标题
        Label title = new Label("学生成绩查询系统");
        title.setTextFill(Color.RED);
        title.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 24));

        //姓名
        HBox namePane = new HBox(20);
        namePane.setAlignment(Pos.CENTER);
        Label name = new Label("姓名");
        TextField nameInput = new TextField();
        namePane.getChildren().addAll(name,nameInput);
        //学号
        HBox IDPane = new HBox(20);
        IDPane.setAlignment(Pos.CENTER);
        Label ID = new Label("学号");
        TextField IDInput = new TextField();
        IDPane.getChildren().addAll(ID,IDInput);

        /*********************************************************************************************************************/

        //查询按钮
        Button searchButton = new Button("查询");
        //查找按钮引入样式表
        searchButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        searchButton.getStyleClass().add("num-button");
        //查找按钮阴影效果
        DropShadow shadow1 = new DropShadow();
        searchButton.setEffect(shadow1);
        //查询功能实现
        searchButton.setOnAction(e->{
            try{
                // 把姓名和学号传给全球变量,所有人都可以使用
                globalUse.name = nameInput.getText().trim();
                globalUse.ID = IDInput.getText().trim();

                //查找信息
                BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
                String str;
                boolean CanFind=false;                  // 用来标记是否找到该数据项

                // 扫描文档，看是不是能找到该学生信息
                while((str = input.readLine())!= null)  // 读取一行
                {
                    String[] fields = str.split(" "); //用空格分离
                    //这里出bug了 fields[0]
                    if( fields[0].equals(globalUse.name) && fields[1].equals(globalUse.ID) )
                    {
                        CanFind = true;//data.txt中存在该数据
                       // 把其他成绩信息传递给全球变量
                        globalUse.Java = fields[2] ;
                       globalUse.Math = fields[3]  ;
                      globalUse.English = fields[4] ;
                        globalUse.OS = fields[5] ;
                      globalUse.Sport = fields[6];
                       globalUse.C = fields[7];
                    }
                }
                //找得到
                if(CanFind){  //找得到，跳转到修改界面 modifyData
                    primaryStage.close();
                    new modifyData().start(new Stage());
                }
                // 找不到,data.txt中没有该数据项，弹出错误框
                else{
                    Stage stage = new Stage();
                    VBox erroPane = new VBox(20);
                    erroPane.setAlignment(Pos.CENTER);
                    //最上面的标题
                    Label erroTitle = new Label("查无此人");
                    erroTitle.setTextFill(Color.RED);
                    erroTitle.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 20));
                    // 重新输入按钮
                    Button searchAgainButton = new Button("重新输入");
                    searchAgainButton.setOnAction(e2->{
                        try{
                            stage.close();
                            // 清空姓名和学号输入文本框
                            nameInput.setText("");
                            IDInput.setText("");
                        } catch (Exception e3){
                            e3.printStackTrace();
                        }
                    });
                    //舞台布置
                    erroPane.getChildren().addAll(erroTitle,searchAgainButton);
                    Scene s = new Scene(erroPane,200,100);
                    stage.setScene(s);
                    stage.show();
                }

              } catch (Exception e1){
                e1.printStackTrace();
              }
        });

        /*********************************************************************************************************************/

        //返回主页面
        Button returnButton = new Button("返回主页面");
        //返回主界面按钮引入样式表
        returnButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        returnButton.getStyleClass().add("num-button");
        //返回主界面按钮阴影效果
        DropShadow shadow3 = new DropShadow();
        returnButton.setEffect(shadow3);
        //返回功能实现
        returnButton.setOnAction(e->{
            try{
                primaryStage.close();
                new start().start(new Stage());
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

        /*********************************************************************************************************************/



        HBox BtnPane = new HBox(20);
        BtnPane.setAlignment(Pos.CENTER);
        BtnPane.getChildren().addAll(searchButton,returnButton);

        //加入到主面板
        searchPane.getChildren().addAll(title,namePane,IDPane,BtnPane);
        searchPane.setId("mainPane");
        //搭建舞台
        Scene scene = new Scene(searchPane, 400, 300);
        scene.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        primaryStage.setTitle("查询窗口");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
