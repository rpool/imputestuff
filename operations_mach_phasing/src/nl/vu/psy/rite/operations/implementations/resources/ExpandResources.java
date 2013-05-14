package nl.vu.psy.rite.operations.implementations.resources;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import nl.vu.psy.rite.operations.Operation;
import nl.vu.psy.rite.operations.implementations.GenericOperation;
import nl.vu.psy.rite.operations.implementations.OperationUtilities;

public class ExpandResources extends GenericOperation {
	private static final long serialVersionUID = -6325592624345853781L;

	public ExpandResources() {
		super();
	}

	@Override
	public Operation call() throws Exception {
		try {
			expand("mach.zip");
			expand("minimac.zip");
		} catch (Exception e) {
			this.setProperty(GenericOperation.PropertyKeys.ERROR, OperationUtilities.getStackTraceAsString(e));
			this.fail();
			this.complete();
			return this;
		}
		this.complete();
		return this;
	}

	private void expand(String fname) throws IOException {
		File file = new File(fname);
		BufferedInputStream bis = new BufferedInputStream(ExpandResources.class.getResourceAsStream("/nl/vu/psy/rite/resources/" + fname));
		FileOutputStream fos = new FileOutputStream(file);
		int b = bis.read();
		while (b != -1) {
			fos.write(b);
			b = bis.read();
		}
		bis.close();
		fos.flush();
		fos.close();
	}

	public static void main(String[] args) throws Exception {
		ExpandResources er = new ExpandResources();
		er.call();
	}
}
