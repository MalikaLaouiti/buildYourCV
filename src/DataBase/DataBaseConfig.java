package DataBase;

public class DataBaseConfig {
    public static final String NOM_DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String IPServer="localhost";
    public static final String PORT= "3306";
    public static final String DataBaseName="tpjavaDB";
    public static final String URL_DB ="jdbc:mysql://"+IPServer+":"+PORT+"/"+DataBaseName;
    public static final String USERNAME="root";
    public static final String PASSWORD="";
}
