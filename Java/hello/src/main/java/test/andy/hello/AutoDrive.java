package test.andy.hello;

public class AutoDrive {
	
    public enum Color {
        WHITE,
        RED,
        BLUE
    }
    private String vendor;

    private Color color;

    public AutoDrive(String vendor, Color color) {
        super();
        this.vendor = vendor;
        this.color = color;
    }

    public AutoDrive() {
        vendor = "Nissan";
        color = Color.WHITE;
    }

    public void drive(){

        boot();

        turnOnLeftLight();

        cailiheguayidang();

        songshousha();

        tips();

    }

    private void tips() {
        System.out.println("您正在驾驶 "+color+" "+vendor+" 汽车，小心行驶。");
    }

    private void songshousha() {
        // TODO Auto-generated method stub
        System.out.println("起步松手铩。");
    }

    private void cailiheguayidang() {
        // TODO Auto-generated method stub
        System.out.println("踩离合器，挂一档");
    }

    private void turnOnLeftLight() {
        // TODO Auto-generated method stub
        System.out.println("打左向灯");
    }

    private void boot() {
        // TODO Auto-generated method stub
        System.out.println("空档发动汽车");

    }

}
