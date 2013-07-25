package org.example;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class HelloBean implements Hello {
	
    @Resource
    private UserTransaction ut;

	public String sayHello(String user) {

		System.out.println("hello");

		Session session = doWork(user);
		session.logout();

		return "Hello";
	}

	public Session doWork(String user) {
		Session session = null;
		try {
			ut.begin();
			
			session = getSessionFromExoAPI(user);
			//System.out.println(session.getRootNode());
			Node rootNode = session.getRootNode();
			rootNode.addNode("mytest" + System.currentTimeMillis());
			//Thread.sleep(5000);
			session.save();
			System.out.println("done");
			
			ut.commit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				ut.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
