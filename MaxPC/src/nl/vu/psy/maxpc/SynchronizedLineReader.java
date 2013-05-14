package nl.vu.psy.maxpc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SynchronizedLineReader {
	// TODO implement closing of scanner and file
	private File file;
	private Scanner lineScanner;
	private long linecount = 0; 

	public SynchronizedLineReader(File f) throws IOException {
		this.file = f;
		initScanner();
	}

	private void initScanner() throws IOException {
		if (file != null) {
			lineScanner = new Scanner(new BufferedReader(new FileReader(this.file)));
			lineScanner.useDelimiter("\n");
		}
	}

	public synchronized String getNextLine() {
		String line = null;
		if (lineScanner != null && lineScanner.hasNext()) {
			line = lineScanner.next();
		}
		linecount++;
		return line;
	}

	public long getLineCount() {
		return linecount;
	}
}
