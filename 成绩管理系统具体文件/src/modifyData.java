import javafx.application.Application;
import javafx.application.Platform;
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

import java.io.*;

public class modifyData extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //布局设计：主面板用VBox，上面用HBox，每一个HBox面板包含两个数据项
        VBox modifyPane = new VBox(20);
        modifyPane.setAlignment(Pos.CENTER);

        //最上面的标题
        Label title = new Label("学生成绩修改系统");
        title.setTextFill(Color.RED);
        title.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 24));

        //第一行：名字和学号
        HBox hBox1 = new HBox(20);
        hBox1.setAlignment(Pos.CENTER);
        Label name = new Label("学生姓名");
        TextField nameInput = new TextField(globalUse.name);
        Label ID = new Label("学生学号");
        TextField IDInput = new TextField(globalUse.ID);
        hBox1.getChildren().addAll(name,nameInput,ID,IDInput);

        //第二行：Java 和 体育
        HBox hBox2 = new HBox(20);
        hBox2.setAlignment(Pos.CENTER);
        Label JavaScore = new Label("Java成绩");
        TextField JavaScoreInput = new TextField(globalUse.Java);
        Label SportScore = new Label("大学体育");
        TextField SportScoreInput = new TextField(globalUse.Sport);
        hBox2.getChildren().addAll(JavaScore,JavaScoreInput,SportScore,SportScoreInput);

        //第三行：操作系统 和 高等数学
        HBox hBox3 = new HBox(20);
        hBox3.setAlignment(Pos.CENTER);
        Label OSScore = new Label("操作系统");
        TextField OSScoreInput = new TextField(globalUse.OS);
        Label mathScore = new Label("高等数学");
        TextField mathScoreInput = new TextField(globalUse.Math);
        hBox3.getChildren().addAll(OSScore,OSScoreInput,mathScore,mathScoreInput);

        //第四行： 英语 和 C语言
        HBox hBox4 = new HBox(20);
        hBox4.setAlignment(Pos.CENTER);
        Label EnglishScore = new Label("英语成绩");
        TextField EnglishScoreInput = new TextField(globalUse.English);
        Label CScore = new Label("C++语言");
        TextField CScoreInput = new TextField(globalUse.C);
        hBox4.getChildren().addAll(EnglishScore,EnglishScoreInput,CScore,CScoreInput);

/*********************************************************************************************************************/

        //第五行
        HBox hBox5 = new HBox(20);
        hBox5.setAlignment(Pos.CENTER);
        //修改成绩按钮
        Button modifyButton = new Button("修改成绩");
        //修改成绩按钮引入样式表
        modifyButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        modifyButton.getStyleClass().add("num-button");
        //修改成绩按钮阴影效果
        DropShadow shadow5 = new DropShadow();
        modifyButton.setEffect(shadow5);
        //修改功能实现：新建一个temp中转文件，将修改后的信息写入temp，再把temp复制给data.txt
        modifyButton.setOnAction(e->{
            try{
                //从data.txt读取信息
                BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
                //写入到中转文件
                BufferedWriter outputToTemp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("temp.txt")));

                String str;
                //扫描文件，找到目标数据项的位置
                // 循环读取一行
                while((str = input.readLine())!= null)
                {
                    String[] fields = str.split(" "); //用空格分离,存入数组
                    // 找到需要修改的数据项
                    if( fields[0].equals(globalUse.name) && fields[1].equals(globalUse.ID) )
                    {//找到该数据项在文件中的位置,修改应该改的信息，再存入到中转文件
                        outputToTemp.write(nameInput.getText()+" "+IDInput.getText()+" "+JavaScoreInput.getText()+" "+mathScoreInput.getText()+" "+EnglishScoreInput.getText()+" "+OSScoreInput.getText()+" "+SportScoreInput.getText()+" "+CScoreInput.getText()+"\n");
                    }
                    // 其他信息原封不动存入中转文件
                    else {
                        outputToTemp.write(str+"\n");
                    }
                }
                //关闭文件流
                input.close();
                outputToTemp.close();

                //从中转文件temp.txt读取信息
                BufferedReader inputFromTemp = new BufferedReader(new InputStreamReader(new FileInputStream("temp.txt")));
                //新建一个data.txt 写入信息
                BufferedWriter outputToData = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("data.txt"))));

                while((str = inputFromTemp.readLine()) != null)
                {
                    outputToData.write(str+"\n");
                }
                //关闭文件流
                inputFromTemp.close();
                outputToData.close();

                //弹出提示框 显示修改成功，提供ok按钮，按下返回
                {
                    Stage stage = new Stage();
                    VBox okPane = new VBox(20);
                    okPane.setAlignment(Pos.CENTER);
                    //最上面的标题
                    Label okTitle = new Label("修改成功！");
                    okTitle.setTextFill(Color.RED);
                    okTitle.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 20));
                    // ok按钮
                    Button modifyAgainButton = new Button("OK");
                    modifyAgainButton.setOnAction(e2->{
                        try{
                            stage.close();
                        } catch (Exception e3){
                            e3.printStackTrace();
                        }
                    });
                    //舞台布置
                    okPane.getChildren().addAll(okTitle,modifyAgainButton);
                    Scene s = new Scene(okPane,200,100);
                    stage.setScene(s);
                    stage.show();
                    //2000millis后自动关闭
                    Thread thread = new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                            if (stage.isShowing()) {
                                Platform.runLater(() -> stage.close());
                            }
                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }
                    });
                    thread.setDaemon(true);
                    thread.start();

                }

            }catch (IOException a){
                a.printStackTrace();
            }
        });

/*********************************************************************************************************************/

        //删除数据项按钮
        Button deleteButton = new Button("删除数据项");
        //删除数据项按钮引入样式表
        deleteButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        deleteButton.getStyleClass().add("num-button");
        //删除数据项按钮阴影效果
        DropShadow shadow6 = new DropShadow();
        deleteButton.setEffect(shadow6);
        //删除功能实现：新建一个temp中转文件，将修改后的信息写入temp，再把temp复制给data.txt
        deleteButton.setOnAction(e->{
            try{
                //从data.txt读取信息
                BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("data.txt")));
                //写入到中转文件
                BufferedWriter outputToTemp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("temp.txt")));


                String str;
                //扫描文件，找到目标数据项的位置
                // 循环读取一行
                while((str = input.readLine())!= null)
                {
                    String[] fields = str.split(" "); //用空格分离,存入数组
                    // 找到需要修改的数据项
                    if( fields[0].equals(globalUse.name) && fields[1].equals(globalUse.ID) )
                    {
                        continue;
                    }
                    // 其他信息原封不动存入中转文件
                    else {
                        outputToTemp.write(str+"\n");
                    }
                }
                //关闭文件流
                input.close();
                outputToTemp.close();

                //从中转文件temp.txt读取信息
                BufferedReader inputFromTemp = new BufferedReader(new InputStreamReader(new FileInputStream("temp.txt")));
                //新建一个data.txt 写入信息
                BufferedWriter outputToData = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("data.txt"))));
                //文件复制
                while((str = inputFromTemp.readLine()) != null)
                {
                    outputToData.write(str+"\n");
                }
                //关闭文件流
                inputFromTemp.close();
                outputToData.close();


                //弹出提示框 显示删除成功，提供ok按钮，按下返回
                {
                    Stage stage = new Stage();
                    VBox okPane = new VBox(20);
                    okPane.setAlignment(Pos.CENTER);
                    //最上面的标题
                    Label okTitle = new Label("删除成功！");
                    okTitle.setTextFill(Color.RED);
                    okTitle.setFont(Font.font("2", FontWeight.BOLD, FontPosture.ITALIC, 20));
                    // ok按钮
                    Button modifyAgainButton = new Button("OK");
                    modifyAgainButton.setOnAction(e2->{
                        try{
                            stage.close();
                            primaryStage.close();
                            new searchScore().start(new Stage());


                        } catch (Exception e3){
                            e3.printStackTrace();
                        }
                    });
                    //提示框舞台布置
                    okPane.getChildren().addAll(okTitle,modifyAgainButton);
                    Scene s = new Scene(okPane,200,100);
                    stage.setScene(s);
                    stage.show();
                    //2000millis后自动关闭提示框
                    Thread thread = new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                            if (stage.isShowing()) {
                                Platform.runLater(() -> stage.close());
                            }
                        } catch (Exception exp) {
                            exp.printStackTrace();
                        }
                    });
                    thread.setDaemon(true);
                    thread.start();
                }

            }catch (IOException a){
                a.printStackTrace();
            }
        });

        hBox5.getChildren().addAll(modifyButton,deleteButton);

/*********************************************************************************************************************/

        //第6行 :  返回主查询页面按钮
        Button BackButton = new Button("返回查询页面");
        //返回主查询页面按钮引入样式表
        BackButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        BackButton.getStyleClass().add("num-button");
        //返回主查询页面按钮阴影效果
        DropShadow shadow3 = new DropShadow();
        BackButton.setEffect(shadow3);
        //返回按钮功能实现
        BackButton.setOnAction(e->{
            try{
                primaryStage.close();
                new searchScore().start(new Stage());
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });

 /*********************************************************************************************************************/

        //第7行 :  返回主页面按钮
        Button returnButton = new Button("返回主页面");
        //返回主界面按钮引入样式表
        returnButton.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        returnButton.getStyleClass().add("num-button");
        //返回主界面按钮阴影效果
        DropShadow shadow4 = new DropShadow();
        returnButton.setEffect(shadow4);
        //返回主页面按钮功能实现
        returnButton.setOnAction(e->{
            try{
                primaryStage.close();
                new start().start(new Stage());
            } catch (Exception e1){
                e1.printStackTrace();
            }
        });







        //加入到主面板
        modifyPane.getChildren().addAll(title,hBox1,hBox2,hBox3,hBox4,hBox5,BackButton,returnButton);
        modifyPane.setId("mainPane");
        //搭建舞台
        Scene scene = new Scene(modifyPane, 600, 500);
        scene.getStylesheets().add(getClass().getResource("writeScorestyle.css").toExternalForm());
        primaryStage.setTitle("成绩管理窗口");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

