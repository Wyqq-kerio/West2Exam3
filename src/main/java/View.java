public class View {
    public static void main(String[] args) throws Exception {
        Get get=new Get();
        get.insertIntoDatabase("China");
        System.out.println("China数据插入成功");
        get.insertIntoDatabase("US");
        System.out.println("US数据插入成功");
        get.insertIntoDatabase("Japan");
        System.out.println("Japan数据插入成功");
        get.insertIntoDatabase("United Kingdom");
        System.out.println("United Kingdom数据插入成功");

    }
}
