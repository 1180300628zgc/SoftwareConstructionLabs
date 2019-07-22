package Log;

import java.io.*;
import java.util.*;
import java.util.Formatter;
import java.util.logging.*;

public class LogFile {

	static Logger logger = Logger.getLogger("log");

	/**
	 * @Output the error information to a txt
	 * @param path
	 * @throws IOException
	 */
	public static void WriteError(Exception e) throws IOException {

		// Test log file using logging
		FileHandler fileHandler = new FileHandler("src/output/personallog.txt");
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);
		logger.info(e.getMessage());

		File F = new File("src/output/log.txt");
		// In case the file does not exist
		if (!F.exists()) {
			F.createNewFile();
		}
		
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		
		FileWriter fw = null;
		String content = e.getClass().getName() + " Error Info: " + e.getMessage() + exceptionAsString;
		String writeDate = "Time: " + get_nowDate() + "\n" + "Error: " + content;
		try {
			// Add data mode
			fw = new FileWriter(F, true);
			fw.write(writeDate + "\r\n");
			// RandomAccessFile randomFile = new  RandomAccessFile("src/output/log.txt", "rw");
			// randomFile.seek(randomFile.length());
			/*
			PrintStream s = new PrintStream(new FileOutputStream("src/output/log.txt", true));
			
			e.printStackTrace(s);
			*/
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}

	}

	public static void WriteError(String e) throws IOException {

		File F = new File("src/output/log.txt");
		// In case the file does not exist
		if (!F.exists()) {
			F.createNewFile();
		}
		
		FileWriter fw = null;
		String content = "Operation Info: " + e;
		String writeDate = "Time: " + get_nowDate() + "\n" + content + "\n";
		try {
			// Add data mode
			fw = new FileWriter(F, true);
			fw.write(writeDate + "\r\n");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}

	}
	
	/**
	 * @Achieve the system time for recording
	 * @return
	 */
	public static String get_nowDate() {
		Calendar D = Calendar.getInstance();
		int year = 0;
		int moth = 0;
		int day = 0;
		year = D.get(Calendar.YEAR);
		moth = D.get(Calendar.MONTH) + 1;
		day = D.get(Calendar.DAY_OF_MONTH);
		String now_date = String.valueOf(year) + "-" + String.valueOf(moth) + "-" + String.valueOf(day);
		return now_date;
	}

	// 测试方法
	public static void main(String[] args) throws IOException {
		String path = "src/test.txt";
		String content = null;
		try {
			List<String> list = new ArrayList<>();
			list.add("1");
			list.add("2");
			list.add("3");
			for (String i : list) {
				System.out.println(i);
			}
			String j = list.get(3);
		} catch (Exception e) {
			content = e.getClass().getName() + "  error Info  " + e.getMessage();
			LogFile le = new LogFile();
			le.WriteError(e);
		}

	}
}
