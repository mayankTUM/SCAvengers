package SC13Project.Milestone1.Payment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

//Please do not change the name of the package or this interface
//Please add here your implementation
public class PaymentImpl implements PaymentWS {

	Document dom;
	DocumentBuilderFactory dbf = null;
	DocumentBuilder db = null;
	Element doc = null;
	TransformerFactory transformerFactory = null;
	Transformer transformer = null;
	DOMSource source = null;
	StreamResult result = null ;
	String xml = "/home/mayank/ServiceComputing/Avengers/src/SC13Project/Milestone1/Payment/bankDB.xml";
	
	@Override
	public int queryAccount(String accountID) {
		// TODO Auto-generated method stub
		createDOM();
		NodeList nl = doc.getElementsByTagName("accountID");
		int len = nl.getLength();
		int iter = 0;
		while(iter!=len)
		{
			if(nl.item(iter).getTextContent().equals(accountID))
			{
				Node t1 = nl.item(iter).getParentNode();
				if (t1.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) t1;
					Node amount = eElement.getElementsByTagName("amount").item(0);
					return Integer.parseInt(amount.getTextContent());
				}
			}
			iter++;
		}
		return -1;
	}

	@Override
	public boolean transfer(String accountID1, String accountID2, int amount)
			throws TransactionException{
		if(accountID1.equals(accountID2))
		{
			System.out.println("Payee and benifiter needs to be different");
			return false;
		}
		createDOM();
		NodeList nl = doc.getElementsByTagName("accountID");
		int len = nl.getLength();
		int iter = 0;
		Node payee = null;
		Node benefiter = null;
		while(iter!=len)
		{
			if(nl.item(iter).getTextContent().equals(accountID1))
			{
				payee = nl.item(iter).getParentNode();
			}
			if(nl.item(iter).getTextContent().equals(accountID2))
			{
				benefiter = nl.item(iter).getParentNode();
			}
			iter++;
		}
		if(payee.getNodeType() == Node.ELEMENT_NODE && benefiter.getNodeType() == Node.ELEMENT_NODE)
		{
			Element payeeElement = (Element) payee;
			Element benefiterElement = (Element) benefiter;
			
			int payeeamt = Integer.parseInt(payeeElement.getElementsByTagName("amount").item(0).getTextContent());
			int benefiteramt = Integer.parseInt(benefiterElement.getElementsByTagName("amount").item(0).getTextContent());
			if (payeeamt < amount)
			{
				System.out.println("Insufficient funds in payee account");
				return false;
			}
			else
			{
				int payeebalance = payeeamt - amount;
				System.out.println("Payee balance is "+ payeebalance);
				int benefiterbalance = benefiteramt + amount;
				System.out.println("Benefiter balance is "+ benefiterbalance);
				payeeElement.getElementsByTagName("amount").item(0).setTextContent("" + payeebalance);
				benefiterElement.getElementsByTagName("amount").item(0).setTextContent("" + benefiterbalance);
				updateDOM();
				return true;
			}
		}
		return false;
	}
	
	private void createDOM()
	{
		// Make an  instance of the DocumentBuilderFactory
		dbf = DocumentBuilderFactory.newInstance();
		try {
			db =  dbf.newDocumentBuilder();
			dom = db.parse(xml);
			doc = dom.getDocumentElement();
		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (SAXException se) {
			System.out.println(se.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

	}
	private void updateDOM()
	{
		try
		{
		transformerFactory = TransformerFactory.newInstance();
		transformer = transformerFactory.newTransformer();
		source = new DOMSource(doc);
		result = new StreamResult(new File(xml));
		transformer.transform(source, result);
		}
		catch(TransformerException te)
		{
			System.out.println(te.getMessage());
		}
	}

}
