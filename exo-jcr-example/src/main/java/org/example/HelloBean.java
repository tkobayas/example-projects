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
	public String sayHello() {

		Session session = doWork();
		session.logout();
		
		return "Hello";
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Session doWork() {
		Session session = null;
		try {
			session = getSessionFromExoAPI();
			System.out.println(session.getRootNode());
			session.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return session;
	}

	private Session getSessionFromExoAPI() {

		Session session = null;
		try {
			ExoContainer container = ExoContainerContext
					.getContainerByName("portal");
			RepositoryService repos = (RepositoryService) container
					.getComponentInstanceOfType(RepositoryService.class);
			ManageableRepository repo = repos.getRepository("repository");
			SimpleCredentials credencials = new SimpleCredentials("root",
					"gtn".toCharArray());
			session = repo.login(credencials);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
	}

}
