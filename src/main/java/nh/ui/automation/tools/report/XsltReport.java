package nh.ui.automation.tools.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import net.sf.saxon.TransformerFactoryImpl;
import nh.ui.automation.tools.bean.Base;

public class XsltReport extends Base {
	public static void transformXslt(String sourcePath, String xsltPath) {
		String resultpath = outputDir + "/index.html";

		File xmlFile = new File(sourcePath);
		SAXReader saxReader = new SAXReader();

		try {
			FileInputStream fis = new FileInputStream(xmlFile);
			InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
			Document document = saxReader.read(reader);
			OutputFormat outputFormat = OutputFormat.createCompactFormat();
			outputFormat.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileWriter(sourcePath + ".xml"), outputFormat);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		TransformerFactory xmlFactory = new TransformerFactoryImpl();

		try {
			Transformer transformer = xmlFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.setParameter("testNgXslt.outputDir", outputDir);
			File file = new File(resultpath);
			transformer.transform(new StreamSource(sourcePath + ".xml"), new StreamResult(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		//XsltReport.transformXslt(ConfigLoadHelper.getValue("testng_result_xml_path"), ConfigLoadHelper.getValue("testngXslt_path"));
	}
}
