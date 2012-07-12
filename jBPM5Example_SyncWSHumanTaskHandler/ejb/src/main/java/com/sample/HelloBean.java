package com.sample;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.SystemEventListenerFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessInstance;
import org.jbpm.process.audit.JPAWorkingMemoryDbLogger;
import org.jbpm.process.workitem.wsht.SyncWSHumanTaskHandler;
import org.jbpm.task.TaskService;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.local.LocalTaskService;

@Stateless
public class HelloBean implements HelloLocal {

	private static KnowledgeBase kbase;

	@PersistenceUnit(unitName = "org.jbpm.persistence.jpa")
	private EntityManagerFactory emf;

	public long startProcess() throws Exception {

		// load up the knowledge base
		kbase = readKnowledgeBase();

		StatefulKnowledgeSession ksession = createKnowledgeSession();
		TaskService localTaskService = getTaskService(ksession);

		// start a new process instance
		ProcessInstance processInstance = ksession
				.startProcess("com.sample.bpmn.hello");

		long processInstanceId = processInstance.getId();

		System.out.println("Process started ... : processInstanceId = "
				+ processInstanceId);

		return processInstanceId;
	}

	public String retrieveTaskByJohn() {

		StatefulKnowledgeSession ksession = createKnowledgeSession();
		TaskService localTaskService = getTaskService(ksession);

		List<TaskSummary> list = localTaskService
				.getTasksAssignedAsPotentialOwner("john", "en-UK");

		for (TaskSummary task : list) {
			System.out.println("task.getId() = " + task.getId());
			System.out.println("task.getProcessInstanceId() = "
					+ task.getProcessInstanceId());
			System.out.println("John is executing task " + task.getName());
			localTaskService.start(task.getId(), "john");
			localTaskService.complete(task.getId(), "john", null);
		}

		return "Done!";
	}

	public String retrieveTaskByMary() {

		StatefulKnowledgeSession ksession = createKnowledgeSession();
		TaskService localTaskService = getTaskService(ksession);

		List<TaskSummary> list = localTaskService
				.getTasksAssignedAsPotentialOwner("mary", "en-UK");
		
		for (TaskSummary task : list) {
			System.out.println("task.getId() = " + task.getId());
			System.out.println("task.getProcessInstanceId() = "
					+ task.getProcessInstanceId());
			System.out.println("Mary is executing task " + task.getName());
			localTaskService.start(task.getId(), "mary");
			localTaskService.complete(task.getId(), "mary", null);
		}

		return "Done!";
	}

	private StatefulKnowledgeSession createKnowledgeSession() {
		Environment env = KnowledgeBaseFactory.newEnvironment();
		env.set(EnvironmentName.ENTITY_MANAGER_FACTORY, emf);

		StatefulKnowledgeSession ksession = JPAKnowledgeService
				.newStatefulKnowledgeSession(kbase, null, env);

		new JPAWorkingMemoryDbLogger(ksession);

		return ksession;
	}

	public TaskService getTaskService(StatefulKnowledgeSession ksession) {

		org.jbpm.task.service.TaskService taskService = new org.jbpm.task.service.TaskService(
				emf, SystemEventListenerFactory.getSystemEventListener());

		SyncWSHumanTaskHandler humanTaskHandler = new SyncWSHumanTaskHandler(
				new LocalTaskService(taskService), ksession);
		humanTaskHandler.setLocal(true);
		humanTaskHandler.connect();
		ksession.getWorkItemManager().registerWorkItemHandler("Human Task",
				humanTaskHandler);

		return new LocalTaskService(taskService);
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("sample.bpmn"),
				ResourceType.BPMN2);
		return kbuilder.newKnowledgeBase();
	}

}
