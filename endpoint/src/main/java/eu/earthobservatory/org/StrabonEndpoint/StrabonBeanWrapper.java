package eu.earthobservatory.org.StrabonEndpoint;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openrdf.model.Resource;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.TupleQueryResultHandlerException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepositoryConnection;
import org.openrdf.rio.RDFFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.earthobservatory.runtime.postgis.Strabon;

public class StrabonBeanWrapper implements org.springframework.beans.factory.DisposableBean {
	private static Logger logger = LoggerFactory.getLogger(eu.earthobservatory.org.StrabonEndpoint.StrabonBeanWrapper.class);
	
	private static final String FILE_PROTOCOL = "file";
	
	public class Entry {
		private String label;
		private String bean;
		private String statement;
		private String format;

		public Entry(String label, String bean, String statement, String format) {
			this.label = label;
			this.bean = bean;
			this.statement = statement;
			this.format = format;
		}
		
		public String getLabel() {
			return label;
		}
		
		public void setLabel(String label) {
			this.label = label;
		}
		
		public String getBean() {
			return bean;
		}
		
		public void setBean(String bean) {
			this.bean = bean;
		}
		
		public String getStatement() {
			return statement;
		}
		
		public void setStatement(String statement) {
			this.statement = statement;
		}
		
		public String getFormat() {
			return format;
		}
		
		public void setFormat(String format) {
			this.format = format;
		}
	}

	private String serverName;
	private int port;
	private String databaseName;
	private String user;
	private String password;

	private Strabon strabon = null;
	private boolean checkForLockTable;
	private List<Entry> entries;

	public StrabonBeanWrapper() {
		this.strabon = null;
	}

	public StrabonBeanWrapper(Strabon strabon) {
		this.strabon = strabon;
	}

	public StrabonBeanWrapper(String databaseName, String user, String password, 
			int port, String serverName, boolean checkForLockTable, List<List<String>> args) {
		this.serverName = serverName;
		this.port = port;
		this.databaseName = databaseName;
		this.user = user;
		this.password = password;
		this.checkForLockTable = checkForLockTable;
		this.entries = new ArrayList<StrabonBeanWrapper.Entry>(args.size());
		
		Iterator<List<String>> entryit = args.iterator();
		
		while (entryit.hasNext()) {
			List<String> list = entryit.next();
			Iterator<String> it = list.iterator();
			
			while (it.hasNext()) {
				int items = 0;
				String label = "", bean = "'", statement = "", format = ""; 
	
				if (it.hasNext()) {
					bean = it.next();
					items++;
				}
				if (it.hasNext()) {
					format = it.next();
					items++;
				}
				if (it.hasNext()) {
					label = it.next();
					items++;
				}
				if (it.hasNext()) {
					statement = it.next();
					items++;
				}
				
				if (items == 4) {
					Entry entry = new Entry(label, bean, statement, format);
					this.entries.add(entry);
				}
			}
		}

		init();
	}

	private boolean init() {
		if (this.strabon == null) {
			try {
				logger.warn("Strabon not initialized yet.");
				logger.warn("Initializing strabon.");
				logger.info(this.getDetails());
				this.strabon = new Strabon(databaseName, user, password, port, serverName, checkForLockTable);
			} catch (Exception e) {
				logger.error("Exception occured while creating Strabon.\n"+this.getDetails(), e);
				return false;
			}
		}

		return true;
	}

	public StrabonBeanWrapper(String databaseName, String user, String password, int port, String serverName) 
	throws SQLException, ClassNotFoundException {
		this.strabon = new Strabon(databaseName, user, password, port, serverName, true);
	}

	public Strabon getStrabon() {
		return strabon;
	}

	public void setStrabon(Strabon strabon) {
		this.strabon = strabon;
	}

	public void destroy() throws Exception {
		strabon.close();
	}

	public Object query(String queryString, String answerFormatStrabon)
	throws MalformedQueryException, RepositoryException, QueryEvaluationException, 
	TupleQueryResultHandlerException, IOException, ClassNotFoundException {
		logger.info("[StrabonEndpoint] Received SELECT query.");
		if ((this.strabon == null) && (!init())) {
			throw new RepositoryException("Could not connect to Strabon.");
		} 

		return strabon.query(queryString, answerFormatStrabon, strabon.getSailRepoConnection());
	}

	public Object update(String updateString, String answerFormatStrabon) 
	throws MalformedQueryException, RepositoryException, QueryEvaluationException, 
	TupleQueryResultHandlerException, IOException, ClassNotFoundException {
		logger.info("[StrabonEndpoint] Received UPDATE query.");
		logger.info("[StrabonEndpoint] Answer format: " + answerFormatStrabon);
		
		if ((this.strabon == null) && (!init())) {
			throw new RepositoryException("Could not connect to Strabon.");
		} 

		strabon.update(updateString, strabon.getSailRepoConnection());
		return "OK!";
	}

	/**
	 * Store the given data in the given format into Strabon repository. If url is true, then
	 * input comes from a URL. 
	 * 
	 * Returns true on success, false otherwise.
	 * 
	 * @param source_data
	 * @param format
	 * @param url
	 * @return
	 * @throws MalformedQueryException
	 * @throws RepositoryException
	 * @throws QueryEvaluationException
	 * @throws TupleQueryResultHandlerException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public boolean store(String source_data, RDFFormat format, boolean url) throws Exception {
		logger.info("[StrabonEndpoint] Received STORE request.");
		
		if ((this.strabon == null) && (!init())) {
			throw new RepositoryException("Could not connect to Strabon.");
		}

		// get sail connection
		SailRepositoryConnection conn = strabon.getSailRepoConnection();

		try {
			// store data
			if (url) {
				URL source = new URL(source_data);
				if (source.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
					// it would be a security issue if we read from the server's filesystem
					throw new IllegalArgumentException("The protocol of the URL should be one of http or ftp.");
				} 
				conn.add(source, "", format, new Resource[1]);

			} else {
				conn.add(new StringReader(source_data), "", format, new Resource[1]);
			}
			
			logger.info("[StrabonEndpoint] STORE was successful.");

		} catch (Exception e) {
			throw e;
		}

		return true;
	}

	private String getDetails() {
		String details = "-----------------------------------------\n";
		details += "Connection details:\n";
		details += "host     : " + serverName + "\n";
		details += "port     : " + port + "\n";
		details += "database : " + databaseName + "\n";
		details += "user     : " + user + "\n";
		details += "password : " + password + "\n";
		details += "-----------------------------------------\n";

		return details;
	}

	public List<Entry> getEntries() {
		return this.entries;
	}
	
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
	public Entry getEntry(int i) {
		if (i < 0 || i >= this.entries.size())
			return null;
		
		return this.entries.get(i);
	}
	
}

