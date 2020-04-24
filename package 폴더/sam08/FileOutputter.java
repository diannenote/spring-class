package sam08;
import java.io.*;
public class FileOutputter implements Outputter {
	private String fileName;	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void output(String msg) {
		try {
			System.out.println("fileName:" + fileName);
			FileWriter fw = new FileWriter(new File(fileName));
			fw.write(msg);
			fw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}