package SC13Project.Milestone2.SearchEngine;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.GetTModelDetail;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceList;
import org.uddi.api_v3.TModelDetail;
import org.uddi.api_v3.TModelInstanceInfo;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;

public class WSSearchEngineImpl implements WSSearchEngine {

	Transport transport = null;
	String authToken = null;

	@Override
	public List<SC13Project.Milestone2.SearchEngine.ServiceInfo> search(String keywords)  {
		// TODO Auto-generated method stub
		try {
			transport = new BuildClient().buildClearkManager();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			authToken = new Authenticate(transport).getRootAuthToken();
		} catch (ConfigurationException
				| RemoteException | TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<SC13Project.Milestone2.SearchEngine.ServiceInfo> services = new ArrayList<SC13Project.Milestone2.SearchEngine.ServiceInfo>();

		try {
			services.addAll(findService(authToken, keywords));
		} catch (DispositionReportFaultMessage e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return services;
		
		
	}
	private List<SC13Project.Milestone2.SearchEngine.ServiceInfo> findService(String authToken, String query) throws DispositionReportFaultMessage, RemoteException, TransportException {

		List<SC13Project.Milestone2.SearchEngine.ServiceInfo> services = new ArrayList<SC13Project.Milestone2.SearchEngine.ServiceInfo>(); //TODO string alone?

		FindService fs = new FindService();
		fs.setAuthInfo(authToken);
		fs.getName().add(getWildcardName()); //this returns all		
		fs.setFindQualifiers(approximateQualifier()); //try different qualifiers, //http://svn.apache.org/viewvc/juddi/tags/juddi-3.1.3/juddi-core/src/main/java/org/apache/juddi/query/util/FindQualifiers.java?view=markup

		UDDIInquiryPortType uddiInquiryService = transport.getUDDIInquiryService();
		ServiceList foundServices = uddiInquiryService.findService(fs);

		//TODO, search code


		// Get the service Detail
		ServiceDetail sd = null;

		// Get the TModel 
		String tModelKey = null;
		TModelDetail gtmd = null;
		String serviceWsdl = null;
		List<BindingTemplate> gsdBindtemplate = null;
		GetTModelDetail tm = new GetTModelDetail();
		tm.setAuthInfo(authToken);

		//---------------------------------------------------------------------------
		List<ServiceInfo> fsInfos= foundServices.getServiceInfos().getServiceInfo();

		if(fsInfos != null)
		{
			for(int i = 0; i < fsInfos.size()-1; i++){
				GetServiceDetail gsd = new GetServiceDetail();

				gsd.setAuthInfo(authToken);
				gsd.getServiceKey().add(fsInfos.get(i).getServiceKey());
				sd = uddiInquiryService.getServiceDetail(gsd);
				if(!sd.getBusinessService().get(0).getDescription().isEmpty())
				{
					String text = sd.getBusinessService().get(0).getDescription().get(0).getValue().toLowerCase();
					System.out.println(text);
					String keyword = query.toLowerCase();

					if(text.contains(keyword))
					{
						gsdBindtemplate = sd.getBusinessService().  
								get(0).getBindingTemplates()
								.getBindingTemplate();



						for(int j=0;j< gsdBindtemplate.size();j++)
						{
							List<TModelInstanceInfo> tmId;
							if((tmId = gsdBindtemplate.get(j).getTModelInstanceDetails().
									getTModelInstanceInfo()) != null){

								tm.getTModelKey().add(tmId.get(0).getTModelKey());
								gtmd = uddiInquiryService.getTModelDetail(tm);
								serviceWsdl = gtmd.getTModel().get(0).getOverviewDoc().get(0).getOverviewURL().getValue();
								SC13Project.Milestone2.SearchEngine.ServiceInfo sinfo = new SC13Project.Milestone2.SearchEngine.ServiceInfo();
								sinfo.setUrl(serviceWsdl);
								sinfo.setDescription(text);
								services.add(sinfo);
								tm = new GetTModelDetail();
								tm.setAuthInfo(authToken);

							}
						}
					}
				}
			}
		}
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
