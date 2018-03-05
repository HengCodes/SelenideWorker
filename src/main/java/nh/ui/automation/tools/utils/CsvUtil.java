package nh.ui.automation.tools.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import nh.ui.automation.tools.entity.TestResultInfo;

/**
 * 项目 ：UI自动化测试 Selenide 类描述：
 * 
 * @author Eric
 * @date 2017年2月9日 nh.ui.automation.tools.utils
 */
public class CsvUtil {

	private static final String NEW_LINE_SEPARATOR = "\n";
	private File file;
	private List<String[]> resultList = new ArrayList<String[]>();

	public CsvUtil(File file) {
		this.file = file;
	}

	public CsvUtil(String filePath) {
		file = new File(filePath);
	}

	public static List<CSVRecord> getData(String csvPath) {
		List<CSVRecord> list = new ArrayList<CSVRecord>();
		Reader in = null;
		try {
			in = new FileReader(csvPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Iterable<CSVRecord> records = null;
		try {
			records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord(true).parse(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (CSVRecord record : records) {
			list.add(record);
		}
		return list;
	}

	public static void writeData(Object[] fileHeader, List<TestResultInfo> resultList, String fileName) {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {
			// initialize FileWriter object
			File file = new File(fileName);
			file.createNewFile();
			fileWriter = new FileWriter(fileName);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			// Create CSV file header
			csvFilePrinter.printRecord(fileHeader);

			for (TestResultInfo testResultInfo : resultList) {
				csvFilePrinter.printRecord(testResultInfo.getClassname(), testResultInfo.getResult(),
						testResultInfo.getDetail());
			}

			System.out.println("Result is saved in " + fileName);

		} catch (Exception e) {

			System.out.println("Error in CsvFileWriter !!!");

			e.printStackTrace();

		} finally {

			try {

				fileWriter.flush();

				fileWriter.close();

				csvFilePrinter.close();

			} catch (IOException e) {

				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");

				e.printStackTrace();

			}

		}

	}

	public List<String[]> parse() {
		try {
			BufferedReader br = getReader();
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] elements = line.split(",");
				store(elements);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	private void store(String[] elements) {
		resultList.add(elements);
	}

	private BufferedReader getReader() throws UnsupportedEncodingException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}

}
