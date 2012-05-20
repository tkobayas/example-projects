package org.example;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;

@Stateless
public class HelloBean implements Hello {

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String sayHello(String user) {

		System.out.println("hello");

		Session session = doWork(user);
		session.logout();

		return "Hello";
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Session doWork(String user) {
		Session session = null;
		try {
			session = getSessionFromExoAPI(user);
			System.out.println(session.getRootNode());
			Thread.sleep(5000);
			session.save();
			System.out.println("done");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return session;
	}

	private Session getSessionFromExoAPI(String user) throws Exception {

		Session session = null;

		ExoContainer container = ExoContainerContext
				.getContainerByName("portal");
		RepositoryService repos = (RepositoryService) container
				.getComponentInstanceOfType(RepositoryService.class);
		ManageableRepository repo = repos.getRepository("repository");
		SimpleCredentials credencials = new SimpleCredentials(user,
				"gtn".toCharArray());
		session = repo.login(credencials, "portal-system");

		return session;
	}

}
