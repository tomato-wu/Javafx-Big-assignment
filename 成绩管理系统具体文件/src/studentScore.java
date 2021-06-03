import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.jar.JarEntry;

class Student {
    //无参构造方法
    public Student(){ }
    /*********************************************************************************************************************/

    //变量
    String name=null;
    String ID=null;
    String JavaScore=null;
    String MathScore=null;
    String OSScore=null;
    String EngScore=null;
    String SportScore=null;
    String CScore=null;

    /*********************************************************************************************************************/

    // 提供给外部属性的set方法
    public void setCScore(String CScore) {
        this.CScore = CScore;
    }
    public void setMathScore(String mathScore) {
        MathScore = mathScore;
    }
    public void setOSScore(String OSScore) {
        this.OSScore = OSScore;
    }
    public void setEngScore(String engScore) {
        EngScore = engScore;
    }
    public void setSportScore(String sportScore) {
        SportScore = sportScore;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setJavaScore(String javaScore) {
        JavaScore = javaScore;
    }

    /*********************************************************************************************************************/
    // 录入文档
    void saveToFile(String fileName,Student pupil){
        try{
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName,true)));
            output.write(pupil.name+" "+pupil.ID+" "+pupil.JavaScore+" "+pupil.MathScore+" "
                    +pupil.EngScore+" "+pupil.OSScore+" "+pupil.SportScore+" "+pupil.CScore+"\n");
            output.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
