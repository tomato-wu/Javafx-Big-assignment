import com.sun.org.apache.bcel.internal.generic.SIPUSH;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class writeScore extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //主面板
        VBox mainPane = new VBox(20);
        mainPane.setAlignment(Pos.CENTER);

        //最上面的标题
        Label title = new Label("学生成绩录入");
        title.setTextFill(Color.RED);
        title.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 24));
        //姓名
        HBox namePane = new HBox(20);
        namePane.setAlignment(Pos.CENTER);
        Label name = new Label("学生姓名");
        TextField nameInput = new TextField();
        namePane.getChildren().addAll(name,nameInput);
        //学号
        HBox IDPane = new HBox(20);
        IDPane.setAlignment(Pos.CENTER);
        Label ID = new Label("学生学号");
        TextField IDInput = new TextField();
        IDPane.getChildren().addAll(ID,IDInput);
        //Java成绩
        HBox JavaScorePane = new HBox(20);
        JavaScorePane.setAlignment(Pos.CENTER);
        Label JavaScore = new Label("Java语言");
        TextField JavaScoreInput = new TextField();
        JavaScorePane.getChildren().addAll(JavaScore,JavaScoreInput);
        //高数成绩
        HBox mathematicsPane = new HBox(20);
        mathematicsPane.setAlignment(Pos.CENTER);
        Label mathematics = new Label("高等数学");
        TextField mathematicsInput = new TextField();
        mathematicsPane.getChildren().addAll(mathematics,mathematicsInput);
        //大学英语
        HBox EnglishPane = new HBox(20);
        EnglishPane.setAlignment(Pos.CENTER);
        Label English = new Label("大学英语");
        TextField EnglishInput = new TextField();
        EnglishPane.getChildren().addAll(English,EnglishInput);
        //操作系统
        HBox OSPane = new HBox(20);
        OSPane.setAlignment(Pos.CENTER);
        Label OS = new Label("操作系统");
        TextField OSInput = new TextField();
        OSPane.getChildren().addAll(OS,OSInput);
        //体育
        HBox SportsPane = new HBox(20);
        SportsPane.setAlignment(Pos.CENTER);
        Label Sports = new Label("大学体育");
        TextField SportsInput = new TextField();
        SportsPane.getChildren().addAll(Sports,SportsInput);
        //C语言
        HBox CPane = new HBox(20);
        CPane.setAlignment(Pos.CENTER);
        Label C = new Label("C++语言");
        TextField CInput = new TextField();
        CPane.getChildren().addAll(C,CInput);

/*********************************************************************************************************************/

        //提交
        Button submitButton = new Button("提交");
        //提交按钮引入样式表
        submitButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        submitButton.setId("submitButton");
        //提交按钮阴影效果
        DropShadow shadow = new DropShadow();
        submitButton.setEffect(shadow);
        //功能实现：实例化一个Student对象，从文本框中获取值，值传给函数savaToFile,录入信息到文档中
        submitButton.setOnAction(e->{    //成绩录入
            boolean ifError = false ;
              //检查是否有空
            {

                if(nameInput.getText().trim().equals("") )   ifError = true ;
                if(IDInput.getText().trim().equals("") )   ifError = true ;
                if(JavaScoreInput.getText().trim().equals("") )   ifError = true ;
                if(mathematicsInput.getText().trim().equals("") )   ifError = true ;
                if(EnglishInput.getText().trim().equals("") )   ifError = true ;
                if(OSInput.getText().trim().equals("") )   ifError = true ;
                if(SportsInput.getText().trim().equals("") )   ifError = true ;
                if(CInput.getText().trim().equals("") )   ifError = true;

                if(ifError)
                {
                    primaryStage.close();
                    Stage stage = new Stage();
                    VBox okPane = new VBox(20);
                    okPane.setAlignment(Pos.CENTER);
                    //最上面的标题
                    Label okTitle = new Label("输入错误：含无效值！");
                    okTitle.setTextFill(Color.RED);
                    okTitle.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 20));
                    // 重新输入按钮
                    Button writeAgainButton = new Button("重新输入");
                    writeAgainButton.setOnAction(e2->{
                        try{
                            stage.close();
                            new writeScore().start(new Stage());
                        } catch (Exception e3){
                            e3.printStackTrace();
                        }
                    });
                    //舞台布置
                    okPane.getChildren().addAll(okTitle,writeAgainButton);
                    Scene s = new Scene(okPane,300,300);
                    stage.setScene(s);
                    stage.setTitle("错误");
                    stage.show();
                }

            }
               if(!ifError) {
                   System.out.println(1);
                   Student pupil = new Student();
                   pupil.setName(nameInput.getText().trim());
                   pupil.setID(IDInput.getText().trim());
                   pupil.setJavaScore(JavaScoreInput.getText().trim());
                   pupil.setMathScore(mathematicsInput.getText().trim());
                   pupil.setEngScore(EnglishInput.getText().trim());
                   pupil.setOSScore(OSInput.getText().trim());
                   pupil.setSportScore(SportsInput.getText().trim());
                   pupil.setCScore(CInput.getText().trim());

                   //检查该学号和姓名是否已存在于数据库中，若存在，提示并跳转到修改界面
                   try {
                       BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
                       String str;
                       boolean CanFind = false;                  // 用来标记是否找到该数据项
                       while ((str = input.readLine()) != null)  // 读取一行
                       {
                           String[] fields = str.split(" "); //用空格分离
                           //这里出bug了 fields的长度必须到达8，也就是说fields[7]一定要有值
                           if (fields[0].equals(pupil.name) && fields[1].equals(pupil.ID)) {
                               CanFind = true;//data.txt中存在该数据

                               //把这个学生的数据传给全球变量，以便在修改界面中显示
                               {
                                   globalUse.name = pupil.name;
                                   globalUse.ID = pupil.ID;
                                   globalUse.Java = fields[2];
                                   globalUse.Math = fields[3];
                                   globalUse.English = fields[4];
                                   globalUse.OS = fields[5];
                                   globalUse.Sport = fields[6];
                                   globalUse.C = fields[7];
                               }
                           }
                       }

                       //找得到
                       if (CanFind) {  //找得到，跳转到修改界面 modifyData
                           //弹出提示框 显示录入成功，提供ok按钮，按下返回并清除文本框内容
                           {

                               Stage stage = new Stage();
                               VBox okPane = new VBox(20);
                               okPane.setAlignment(Pos.CENTER);
                               //最上面的标题
                               Label okTitle = new Label("该学生成绩已存在！");
                               okTitle.setTextFill(Color.RED);
                               okTitle.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 20));
                               // 重新输入按钮
                               Button writeAgainButton = new Button("重新输入");
                               writeAgainButton.setOnAction(e2 -> {
                                   try {
                                       stage.close();
                                       nameInput.setText("");
                                       IDInput.setText("");
                                       JavaScoreInput.setText("");
                                       mathematicsInput.setText("");
                                       OSInput.setText("");
                                       EnglishInput.setText("");
                                       SportsInput.setText("");
                                       CInput.setText("");
                                   } catch (Exception e3) {
                                       e3.printStackTrace();
                                   }
                               });
                               //跳转到修改界面
                               Button JumpToModifyButton = new Button("修改该学生信息");
                               JumpToModifyButton.setOnAction(e2 -> {
                                   try {
                                       stage.close();
                                       primaryStage.close();
                                       new modifyData().start(new Stage());
                                   } catch (Exception e3) {
                                       e3.printStackTrace();
                                   }
                               });
                               //舞台布置
                               okPane.getChildren().addAll(okTitle, writeAgainButton, JumpToModifyButton);
                               Scene s = new Scene(okPane, 300, 300);
                               stage.setScene(s);
                               stage.setTitle("错误");
                               stage.show();
                           }
                       }


                       // 找不到,data.txt中没有该数据项，正常录入
                       else {
                           pupil.saveToFile("data.txt", pupil);
                           //弹出提示框 显示录入成功，提供ok按钮，按下返回并清除文本框内容
                           {

                               Stage stage = new Stage();
                               VBox okPane = new VBox(20);
                               okPane.setAlignment(Pos.CENTER);
                               //最上面的标题
                               Label okTitle = new Label("录入成功！");
                               okTitle.setTextFill(Color.RED);
                               okTitle.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 20));
                               // 重新输入按钮
                               Button writeAgainButton = new Button("OK");
                               writeAgainButton.setOnAction(e2 -> {
                                   try {
                                       stage.close();
                                       nameInput.setText("");
                                       IDInput.setText("");
                                       JavaScoreInput.setText("");
                                       mathematicsInput.setText("");
                                       OSInput.setText("");
                                       EnglishInput.setText("");
                                       SportsInput.setText("");
                                       CInput.setText("");
                                   } catch (Exception e3) {
                                       e3.printStackTrace();
                                   }
                               });
                               //舞台布置
                               okPane.getChildren().addAll(okTitle, writeAgainButton);
                               Scene s = new Scene(okPane, 200, 100);
                               stage.setScene(s);
                               stage.show();

                           }
                       }

                   } catch (Exception e1) {
                       e1.printStackTrace();
                   }

               }//如果没错误则执行if块的代码
        });


/*********************************************************************************************************************/

        //返回
        Button returnButton = new Button("返回主页面");
        //返回主界面按钮引入样式表
        returnButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        returnButton.getStyleClass().add("num-button");
        //返回主界面按钮阴影效果
        DropShadow shadow3 = new DropShadow();
        returnButton.setEffect(shadow3);
        //功能实现：返回主页面
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
        BtnPane.getChildren().addAll(submitButton,returnButton);

        //加入到主面板
        mainPane.getChildren().addAll(title,namePane,IDPane,JavaScorePane,mathematicsPane,EnglishPane,OSPane,SportsPane,CPane,BtnPane);;
        mainPane.setId("mainPane");
        //搭建舞台
        Scene scene = new Scene(mainPane, 500, 500);
        scene.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        primaryStage.setTitle("录入成绩窗口");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
