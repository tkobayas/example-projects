package org.example;

import java.net.MalformedURLException;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.exoplatform.container.StandaloneContainer;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.config.RepositoryConfigurationException;
import org.exoplatform.services.jcr.core.CredentialsImpl;
import org.exoplatform.services.jcr.impl.core.RepositoryImpl;
import org.exoplatform.services.jcr.impl.core.SessionImpl;

public class ExoJcrEx01 {

	public static void main(String[] args) throws Exception {

		RepositoryImpl repository = setup();

		// Add some nodes and properties
		{
			CredentialsImpl credentials = new CredentialsImpl("root",
					"exo".toCharArray());
			SessionImpl session = (SessionImpl) repository.login(credentials,
					"production");

			Node rootNode = session.getRootNode();

			// clean up before test
			if (rootNode.hasNode("training")) {
				rootNode.getNode("training").remove();
			}
			
			Node training = rootNode.addNode("training", "nt:unstructured");
			
			Node day1 = training.addNode("Day-1");
			day1.setProperty("name", "JCR concepts, architecture and benefits");

			Node day2 = training.addNode("Day-2");
			day2.setProperty("name", "JCR for developers");

			session.save();
			session.logout();
		}

		// Read nodes and properties
		{
			CredentialsImpl credentials = new CredentialsImpl("root",
					"exo".toCharArray());
			SessionImpl session = (SessionImpl) repository.login(credentials,
					"production");

			Node rootNode = session.getRootNode();
			Node training = rootNode.getNode("training");

			NodeIterator nodeIterator = training.getNodes();

			while (nodeIterator.hasNext()) {
				Node node = (Node) nodeIterator.next();
				System.out.println(node.getName() + ":"
						+ node.getProperty("name").getString());
			}

			session.logout();
		}

	}

	private static RepositoryImpl setup() throws MalformedURLException,
			Exception, RepositoryException, RepositoryConfigurationException {
		System.setProperty(
				"java.security.auth.login.config",
				Thread.currentThread().getContextClassLoader()
						.getResource("jaas.conf").toString());

		System.setProperty("org.exoplatform.services.log.Log",
				"org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("com.arjuna.ats.arjuna.objectstore.objectStoreDir",
				"./temp");
		System.setProperty("ObjectStoreEnvironmentBean.objectStoreDir",
				"./temp");

		System.out.println(ExoJcrEx01.class
				.getResource("/exo-configuration.xml"));

		StandaloneContainer.addConfigurationURL(ExoJcrEx01.class.getResource(
				"/exo-configuration.xml").toString());

		StandaloneContainer container = StandaloneContainer.getInstance();

		RepositoryService repositoryService = (RepositoryService) container
				.getComponentInstanceOfType(RepositoryService.class);
		RepositoryImpl repository = (RepositoryImpl) repositoryService
				.getDefaultRepository();
		return repository;
	}
}
