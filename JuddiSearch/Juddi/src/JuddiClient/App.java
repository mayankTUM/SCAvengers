package JuddiClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.v3_service.DispositionReportFaultMessage;

public class App {

	
	/**
	 * @param args
	 * @throws ConfigurationException 
	 * @throws TransportException 
	 * @throws RemoteException 
	 * @throws DispositionReportFaultMessage 
	 */
	public static void main(String[] args) throws ConfigurationException, TransportException, DispositionReportFaultMessage, RemoteException {
		
		Transport transport = new BuildClient().buildClearkManager();
		String authToken = new Authenticate(transport).getRootAuthToken();
		List<CreateBusinessServicePOJO> list = new ArrayList<CreateBusinessServicePOJO>();
		
		CreateBusinessServicePOJO config = new CreateBusinessServicePOJO();
		config.setBusinessEntityName("g51 Business");
		config.setModelName("BooksModel");
		config.setUrl("http://vmjacobsen4.informatik.tu-muenchen.de:8080/axis2/services/g51/BookStoreImpl?wsdl");
		config.setBusinessServiceName("BooksBusinessService");
		config.setEndpoint("http://vmjacobsen4.informatik.tu-muenchen.de:8080/axis2/services/g51/BookStoreImpl.BookStoreImplHttpSoap12Endpoint");
		config.setDescription("this is the bookstore web service");
		list.add(config);
		
		config = new CreateBusinessServicePOJO();
		config.setBusinessEntityName("g51 Business");
		config.setModelName("WarehouseModel");
		config.setUrl("http://vmjacobsen4.informatik.tu-muenchen.de:8080/axis2/services/g51/WarehouseImpl?wsdl");
		config.setBusinessServiceName("WarehouseBusinessService");
		config.setEndpoint("http://vmjacobsen4.informatik.tu-muenchen.de:8080/axis2/services/g51/WarehouseImpl.WarehouseImplHttpSoap12Endpoint");
		config.setDescription("this is the warehouse web service");
		list.add(config);
		
		config = new CreateBusinessServicePOJO();
		config.setBusinessEntityName("g51 Business");
		config.setModelName("PaymentModel");
		config.setUrl("http://vmjacobsen4.informatik.tu-muenchen.de:8080/axis2/services/g51/PaymentImpl?wsdl");
		config.setBusinessServiceName("PaymentBusinessService");
		config.setEndpoint("http://vmjacobsen4.informatik.tu-muenchen.de:8080/axis2/services/g51/PaymentImpl.PaymentImplHttpSoap12Endpoint");
		config.setDescription("this is the payment model service");
		list.add(config);
		
		RegisterService register = new RegisterService(transport);
		register.registerService(authToken, list); 
		//#TODO: actually, this code can only register a single service, but should register all service
		
		
//		DeleteBusiness delService = new DeleteBusiness(transport); //#Just in case
//		delService.deleteBusiness(authToken, "g51 Bussiness");
		
		
//		QueryJuddi q = new QueryJuddi(transport);
//		List<String> queryResults = q.query(authToken, "Book"); //TODO only returns some strings, but it should also return the wsdl
//		for(String found : queryResults) {
//			System.out.println("found: " + found); 
//		}
//		if(queryResults.size() == 0) System.out.println("nothing found");
//		
//		        

	}

}
