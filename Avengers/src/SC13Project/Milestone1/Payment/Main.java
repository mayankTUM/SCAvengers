package SC13Project.Milestone1.Payment;

public class Main {
	
	public static void main(String args[]) throws TransactionException
	{
		boolean success = false;
		System.out.println("Started");
		PaymentImpl paymentImpl = new PaymentImpl();
		success = paymentImpl.transfer("ACC1234", "ACC5678", 5000);
		if(success)
		{
			System.out.println("Payment Successful");
		}
		int amount = paymentImpl.queryAccount("ACC1234");
		System.out.println(amount);
	}
}
