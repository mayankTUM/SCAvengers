package SC13Project.Milestone1.Warehouse;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//Please do not change the name of the package or this interface
//Please add here your implementation
public class WarehouseImpl implements WarehouseWS {

	Document dom;
	DocumentBuilderFactory dbf = null;
	DocumentBuilder db = null;
	Element doc = null;
	TransformerFactory transformerFactory = null;
	Transformer transformer = null;
	DOMSource source = null;
	StreamResult result = null ;
	String xml = "/home/mayank/ServiceComputing/Avengers/src/SC13Project/Milestone1/Warehouse/WareHouseDB.xml";
	
	@Override
	public int query(String resourceID) {
		createDOM();
		NodeList items = doc.getElementsByTagName("items");
		Node itemsNode = items.item(0); 
		if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
		{
			Element eElement = (Element) itemsNode;
			NodeList itemList = eElement.getElementsByTagName("item");
			int len = itemList.getLength();
			int iter = 0;
			while(iter!=len)
			{
				Node item = itemList.item(iter);
				if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element oElement = (Element) item;
					if(oElement.getElementsByTagName("resourceID").item(0).getTextContent().equals(resourceID))
					{
						return Integer.parseInt(oElement.getElementsByTagName("amount").item(0).getTextContent());
					}
				}
				iter++;
			}
		}
		return 0;
	}

	@Override
	public boolean pickupItems(String resourceID, int amount)
			throws NotEnoughItemException {
		createDOM();
		NodeList items = doc.getElementsByTagName("items");
		Node itemsNode = items.item(0); 
		if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
		{
			Element eElement = (Element) itemsNode;
			NodeList itemList = eElement.getElementsByTagName("item");
			int len = itemList.getLength();
			int iter = 0;
			while(iter!=len)
			{
				Node item = itemList.item(iter);
				if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element oElement = (Element) item;
					if(oElement.getElementsByTagName("resourceID").item(0).getTextContent().equals(resourceID))
					{
						int amountAvailable = Integer.parseInt(oElement.getElementsByTagName("amount").item(0).getTextContent());
						if (amountAvailable < amount)
						{
							throw new NotEnoughItemException();
						}
						else
						{
							int bal = amountAvailable - amount;
							oElement.getElementsByTagName("amount").item(0).setTextContent("" + bal);
							updateDOM();
							return true;
						}
					}
				}
				iter++;
			}
		}
		return false;
	}

	@Override
	public int complementStock(String resourceID, int amount) {
		createDOM();
		NodeList items = doc.getElementsByTagName("items");
		Node itemsNode = items.item(0); 
		if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
		{
			Element eElement = (Element) itemsNode;
			NodeList itemList = eElement.getElementsByTagName("item");
			int len = itemList.getLength();
			int iter = 0;
			while(iter!=len)
			{
				Node item = itemList.item(iter);
				if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element oElement = (Element) item;
					if(oElement.getElementsByTagName("resourceID").item(0).getTextContent().equals(resourceID))
					{
						int amountAvailable = Integer.parseInt(oElement.getElementsByTagName("amount").item(0).getTextContent());
						int newAmountAvailable = amountAvailable + amount;
						oElement.getElementsByTagName("amount").item(0).setTextContent("" + newAmountAvailable);
						updateDOM();
						return newAmountAvailable;
					}
				}
				iter++;
			}
		}
		return 0;
	}

	@Override
	public String holdItems(String resourceID, int amount)
			throws NotEnoughItemException {
		createDOM();
		NodeList items = doc.getElementsByTagName("items");
		Node itemsNode = items.item(0);
		boolean flag = false;
		if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
		{
			Element eElement = (Element) itemsNode;
			NodeList itemList = eElement.getElementsByTagName("item");
			int len = itemList.getLength();
			int iter = 0;
			Element foundElement = null;
			while(iter!=len)
			{
				Node item = itemList.item(iter);
				if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element oElement = (Element) item;
					if(oElement.getElementsByTagName("resourceID").item(0).getTextContent().equals(resourceID))
					{	
						foundElement = oElement;
						flag = true;
					}
				}
				iter++;
			}
			if(flag == false)
			{
				System.out.println("Item not found");
			}
			else
			{
				int itemsAvailable = Integer.parseInt(foundElement.getElementsByTagName("amount").item(0).getTextContent());
				if (itemsAvailable < amount)
				{
					throw new NotEnoughItemException();
				}
				else
				{
					Node holdingRequest = doc.getElementsByTagName("holdingRequests").item(0);
					String reqID = UUID.randomUUID().toString();
					if(holdingRequest.getNodeType() == Node.ELEMENT_NODE)
					{
						
						Element hr = (Element) holdingRequest;
						Element request = dom.createElement("request");
						hr.appendChild(request);
						
						Element requestID = dom.createElement("requestID");
						request.appendChild(requestID);
						
						Element item = dom.createElement("item");
						request.appendChild(item);
						
						Element resID = dom.createElement("resourceID");
						item.appendChild(resID);
						Element amt = dom.createElement("amount");
						item.appendChild(amt);
						
						requestID.setTextContent(reqID);
						resID.setTextContent(resourceID);
						amt.setTextContent("" + amount);
						
						int balance = itemsAvailable - amount;
						foundElement.getElementsByTagName("amount").item(0).setTextContent("" + balance);
						updateDOM();
						return reqID;
					}
				}
			}
		}
		return null;
	}

	@Override
	public void cancelHoldingItems(String holdingID) {
		createDOM();
		NodeList reqID = doc.getElementsByTagName("requestID");
    	int len = reqID.getLength();
		int iter = 0;
		boolean flag = false;
		Node parent = null;
		Node grandParent = null;
		while(iter!=len)
		{
			if(reqID.item(iter).getTextContent().equals(holdingID))
			{
				parent = reqID.item(iter).getParentNode();
				grandParent = parent.getParentNode();
				flag = true;
				break;
			}
			iter++;
		}
		if(flag)
		{
			Element eElement = (Element) parent;
			String resourceID = eElement.getElementsByTagName("resourceID").item(0).getTextContent();
			int amountToGoBack = Integer.parseInt(eElement.getElementsByTagName("amount").item(0).getTextContent());
			
			NodeList items = doc.getElementsByTagName("items");
			Node itemsNode = items.item(0);
			int currentAmount = 0;
			Node amountToBeAdded = null;
			if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element oElement = (Element) itemsNode;
				NodeList itemList = oElement.getElementsByTagName("item");
				int length = itemList.getLength();
				int iteration = 0;
				while(iteration!=length)
				{
					Node item = itemList.item(iter);
					if(itemsNode.getNodeType() == Node.ELEMENT_NODE)
					{
						Element aElement = (Element) item;
						if(aElement.getElementsByTagName("resourceID").item(0).getTextContent().equals(resourceID))
						{
						    amountToBeAdded = aElement.getElementsByTagName("amount").item(0);
							currentAmount =  Integer.parseInt(amountToBeAdded.getTextContent());
							break;
						}
					}
					iteration ++;
				}
			}
			int total = amountToGoBack + currentAmount;
			amountToBeAdded.setTextContent(""+total);
			grandParent.removeChild(parent);
			updateDOM();
		}
		else
		{
			System.out.println("holding ID not found");
		}
	}

	@Override
	public boolean pickupHoldingItems(String holdingID)
			throws InvalidHoldingIDException {
		
		createDOM();
		NodeList reqID = doc.getElementsByTagName("requestID");
    	int len = reqID.getLength();
		int iter = 0;
		boolean flag = false;
		Node parent = null;
		Node grandParent = null;
		while(iter!=len)
		{
			if(reqID.item(iter).getTextContent().equals(holdingID))
			{
				parent = reqID.item(iter).getParentNode();
				grandParent = parent.getParentNode();
				flag = true;
				break;
			}
			iter++;
		}
		if(!flag)
		{
			throw new InvalidHoldingIDException();
		}
		else
		{
			grandParent.removeChild(parent);
			updateDOM();
			return true;
		}
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
