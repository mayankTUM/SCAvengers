package SC13Project.Milestone1.Warehouse;

public class Main {
	public static void main(String args[]) throws NotEnoughItemException, InvalidHoldingIDException
	{
		WarehouseImpl warehouseImpl = new WarehouseImpl();
		boolean flag = warehouseImpl.pickupHoldingItems("5cc67d7d-1bf4-4251-873e-fdc8b59f7881");
		if(flag)
			System.out.println("Successfully picked holded items");
		else
			System.out.println("unsuccessfull");
	}
}
