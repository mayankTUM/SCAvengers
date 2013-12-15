package SC13Project.Milestone2.SearchEngine;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.v3_service.DispositionReportFaultMessage;


public class Main{

	public static void main(String args[]) throws ConfigurationException, TransportException, DispositionReportFaultMessage, RemoteException
	{
		WSSearchEngineImpl impl = new WSSearchEngineImpl();
		List<ServiceInfo> queryResults = impl.search("list"); //TODO only returns some strings, but it should also return the wsdl
		Iterator<ServiceInfo> iter = queryResults.iterator(); 
		while(iter.hasNext())
		{
			ServiceInfo info = iter.next();
			String url = info.getUrl().trim().replaceAll("\n", "");
			String description = info.getDescription().trim().replaceAll("\n", "");
			System.out.println("Found url : "+ url);
			System.out.println("Description : "+ description);
		}
	}
}
