package SC13Project.Milestone1.Warehouse;

public class Main {
	public static void main(String args[])
	{
		WarehouseImpl a = new WarehouseImpl();
		int num = a.query("residone");
		System.out.println(num);
	}
}
