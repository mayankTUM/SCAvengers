package JuddiClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.FindTModel;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.GetTModelDetail;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.OverviewURL;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceList;
import org.uddi.api_v3.TModelDetail;
import org.uddi.api_v3.TModelInfo;
import org.uddi.api_v3.TModelList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;

public class QueryJuddi {
	
	private Transport transport;

	public QueryJuddi(Transport transport) {
		this.transport = transport;
		
	}
	
	
	public List<String> query(String authToken, String query) throws TransportException, DispositionReportFaultMessage, RemoteException {
		
		List<String> services = new ArrayList<String>();
		
		services.addAll(findService(authToken, query));
		
		return services;
	}
	
	private List<String> findService(String authToken, String query) throws DispositionReportFaultMessage, RemoteException, TransportException {
		
		List<String> services = new ArrayList<String>(); //TODO string alone?
		
		FindService fs = new FindService();
		fs.setAuthInfo(authToken);
		fs.getName().add(getWildcardName()); //this returns all		
		fs.setFindQualifiers(approximateQualifier()); //try different qualifiers, //http://svn.apache.org/viewvc/juddi/tags/juddi-3.1.3/juddi-core/src/main/java/org/apache/juddi/query/util/FindQualifiers.java?view=markup
						
		UDDIInquiryPortType uddiInquiryService = transport.getUDDIInquiryService();
		ServiceList foundServices = uddiInquiryService.findService(fs);
		
		
		//TODO, search code
		
		return services;
	}

	
	private Name getWildcardName() {
		Name name = new Name();
		name.setValue("%");
		return name;
	}

	private FindQualifiers approximateQualifier() {
		FindQualifiers fq = new FindQualifiers();
		fq.getFindQualifier().add("approximateMatch");
		return fq;
	}
}
