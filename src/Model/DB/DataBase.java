package Model.DB;

import java.io.File;

public class DataBase {
    //public static final File =new File()
    private DataBase() {
    }
    public  static DataBase dataBase=new DataBase();

    public static DataBase getDataBase() {
        return dataBase;
    }
    public synchronized  void loadfirst(){

       // File file=new File(PROFILE_FILE);




    }
}
