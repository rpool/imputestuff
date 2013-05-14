package nl.vu.psy.rite.operations.implementations.fileresolution;

import nl.vu.psy.rite.operations.Operation;
import nl.vu.psy.rite.operations.OperationPropertyKeys;
import nl.vu.psy.rite.operations.implementations.GenericOperation;
import nl.vu.psy.rite.operations.implementations.OperationUtilities;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

public class CopyInMongoFile extends GenericOperation {
	private static final long serialVersionUID = 426425234812416095L;

	public enum PropertyKeys implements OperationPropertyKeys {
		FILENAME("filename", "", false);

		private final String key;
		private final String defaultValue;
		private final boolean nullable;

		private PropertyKeys(String key, String defaultValue, boolean nullable) {
			this.key = key;
			this.defaultValue = defaultValue;
			this.nullable = nullable;
		}

		@Override
		public String getDefaultValue() {
			return defaultValue;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public boolean isNullable() {
			return nullable;
		}
	}

	public CopyInMongoFile() {
		super();
		OperationUtilities.initialize(this, PropertyKeys.values());
	}

	@Override
	public Operation call() throws Exception {
		try {
			String host = "fluke.psy.vu.nl";
			int port = 989;
			Mongo mongo = new Mongo(host, port);
			String dbName = "conversion";
			DB db = mongo.getDB(dbName);
			String user = "";
			String pass = "";
			db.authenticate(user, pass.toCharArray());
			GridFS gfs = new GridFS(db);
			String filenname = getFileName();
			GridFSDBFile file = gfs.findOne(filenname);
			file.writeTo(filenname);
		} catch (Exception e) {
			this.setProperty(GenericOperation.PropertyKeys.ERROR, OperationUtilities.getStackTraceAsString(e));
			this.fail();
			this.complete();
			return this;
		}
		this.complete();
		return this;
	}

	public void setFileName(String name) {
		setProperty(PropertyKeys.FILENAME, name);
	}

	public String getFileName() {
		return getProperty(PropertyKeys.FILENAME);
	}
}
