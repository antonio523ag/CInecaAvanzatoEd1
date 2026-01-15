package del.antoniogrillo.example;

public class EsempioRecord {

    public static void main(String[] args) {
        LoginRequest lr=new LoginRequest("Antonio","Grillo");
        System.out.println(lr.username());
    }
}
