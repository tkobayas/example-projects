package org.example;

import java.net.URL;

import javax.jcr.Node;
import javax.jcr.ValueFactory;
import javax.jcr.Workspace;

import junit.framework.TestCase;

import org.exoplatform.container.StandaloneContainer;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.config.WorkspaceEntry;
import org.exoplatform.services.jcr.core.CredentialsImpl;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.core.WorkspaceContainerFacade;
import org.exoplatform.services.jcr.impl.core.RepositoryImpl;
import org.exoplatform.services.jcr.impl.core.SessionImpl;
import org.exoplatform.services.jcr.impl.dataflow.serialization.ReaderSpoolFileHolder;
import org.exoplatform.services.jcr.impl.util.io.FileCleaner;
import org.exoplatform.services.jcr.impl.util.io.FileCleanerHolder;
import org.exoplatform.services.jcr.storage.WorkspaceDataContainer;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

public class HelloJCRTest extends TestCase {

	protected static Log log = ExoLogger
			.getLogger("exo.jcr.component.core.JCRTest");

	protected static String TEMP_PATH = "./temp/fsroot";

	protected static String WORKSPACE = "ws";

	protected SessionImpl session;

	protected RepositoryImpl repository;

	protected CredentialsImpl credentials;

	protected Workspace workspace;

	protected RepositoryService repositoryService;

	protected Node root;

	protected ValueFactory valueFactory;

	protected StandaloneContainer container;

	public int maxBufferSize = 200 * 1024;

	public FileCleaner fileCleaner;

	public ReaderSpoolFileHolder holder;

	public void setUp() throws Exception {
		String configPath = "/conf/standalone/test-configuration-ijdbc-jbc.xml";

		URL configUrl = HelloJCRTest.class.getResource(configPath);
		
		System.out.println(configUrl);
		
		if (configUrl != null) {
			StandaloneContainer.addConfigurationURL(configUrl.toString());
		} else {
			StandaloneContainer.addConfigurationPath(configPath);
		}
		// .addConfigurationPath("src/test/java/conf/standalone/test-configuration.xml");
		// .addConfigurationPath("src/test/java/conf/standalone/test-configuration-sjdbc.xml");
		// .addConfigurationPath("src/test/java/conf/standalone/test-configuration-sjdbc.pgsql.xml");
		// .addConfigurationPath("src/test/java/conf/standalone/test-configuration-sjdbc.ora.xml");
		// .addConfigurationPath("src/test/java/conf/standalone/test-configuration-mjdbc.mysql.xml");

		String loginConf = HelloJCRTest.class.getResource("/login.conf")
				.toString();

		container = StandaloneContainer.getInstance();

		if (System.getProperty("java.security.auth.login.config") == null) {
			System.setProperty("java.security.auth.login.config", loginConf);
		}

		credentials = new CredentialsImpl("admin", "admin".toCharArray());

		repositoryService = (RepositoryService) container
				.getComponentInstanceOfType(RepositoryService.class);
		repository = (RepositoryImpl) repositoryService.getDefaultRepository();

		session = (SessionImpl) repository.login(credentials, "ws");
		workspace = session.getWorkspace();
		root = session.getRootNode();
		valueFactory = session.getValueFactory();

		// initRepository();
		ManageableRepository repository = repositoryService
				.getDefaultRepository();
		WorkspaceContainerFacade wsc = repository.getWorkspaceContainer("ws");

		WorkspaceEntry wconf = (WorkspaceEntry) wsc
				.getComponent(WorkspaceEntry.class);

		maxBufferSize = wconf.getContainer().getParameterInteger(
				WorkspaceDataContainer.MAXBUFFERSIZE_PROP,
				WorkspaceDataContainer.DEF_MAXBUFFERSIZE);

		fileCleaner = FileCleanerHolder.getDefaultFileCleaner();
		holder = new ReaderSpoolFileHolder();
	}

	public void tearDown() throws Exception {
//		session = (SessionImpl) repository.login(credentials, WORKSPACE);
//		Node root = session.getRootNode();
//		Node node = root.getNode("TestNodesTree");
//		node.remove();
//		root.save();
//		// node.save();ipossible to call save() on removed node
//
//		super.tearDown();
	}

	public void testSayHello_String() throws Exception {

		Node root = session.getRootNode();
		
		root.addNode("hello", "nt:unstructured");

		Node node = root.getNode("hello");

		System.out.println(node.getName());
		
		assertTrue(true);
	}

}
