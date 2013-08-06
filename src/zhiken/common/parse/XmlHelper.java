package zhiken.common.parse;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import android.annotation.SuppressLint;
import android.util.Log;

public class XmlHelper
{

	/**
	 * xml字符串转换成dom
	 * 
	 * @param strxml
	 * @return
	 */
	public static Document getDocument(String strxml)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			strxml = new String(strxml.getBytes(), "UTF-8");
			StringReader stringReader = new StringReader(strxml);
			InputSource inputSource = new InputSource(stringReader);
			Document document = builder.parse(inputSource);
			return document;
		} catch (Exception e)
		{
			e.printStackTrace();
			Log.e("xmlString2Document", "xml转换成dom失败了！");
		}
		return null;
	}

	@SuppressLint("NewApi")
	private String getInnerText(Node node)
	{
		if (node == null)
		{
			return null;
		}

		Node innernode = node.getFirstChild();
		if (innernode == null)
		{
			return null;
		}

		return innernode.getTextContent();
	}

	// private void dom2Array(Element ele, Object obj) {
	// NodeList items = ele.getChildNodes();
	// if(items)
	// }
}