package zhdd.other;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jDemo {
	public static void main(String[] args) throws DocumentException {
		long lasting = System.currentTimeMillis();
		//System.out.println(lasting);
		File f = new File("E:\\project\\terminal\\testngAnt\\output\\testng-results.xml");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(f);
		Element root = doc.getRootElement();
		System.out.println(root.attributeValue("total"));
		@SuppressWarnings("unchecked")
		List<Element> group = root.elements("group");
		for(Element g : group){
			System.out.println(g.attributeValue("name"));
		}
	}
}
