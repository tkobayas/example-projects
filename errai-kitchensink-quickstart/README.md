kitchensink: Example Using Multiple Java EE 6 Technologies Deployed as a WAR
============================================================================
Author: Christian Sadilek and Jonathan Fuerth, based on Pete Muir's JSF Kitchen Sink demo

What is it?
-----------

This is your project! It's a sample, deployable Maven 3 project to help you
get your foot in the door developing with Errai and Java EE 6 on JBoss AS 7 or
JBoss Enterprise Application Platform 6. This project is setup to allow you to
create a compliant Java EE 6 application using CDI 1.0, JPA 2.0 and Bean Validation
1.0 with a typesafe, CDI-enabled client written with the Errai framework. It includes
a persistence unit and some sample persistence and transaction code to help 
you get your feet wet with database access in enterprise Java. It also demonstrates
Errai features such as client-side CDI, Errai RPC, and server-to-client eventing.
Further, this project showcases several modern GWT technologies such as UiBinder,
CellTable, and SafeHtml.

Unlike the other Errai quickstart projects, this project is only supported for use
on JBoss AS 7. Although you could get it to work on any Java web server, we do
not provide profiles in the POM to help you with that.

Try opening the demo in multiple browsers at the same time (use different browsers,
not just separate windows of the same browser). Notice that every time you register
a new member, it will appear in all open browsers simultaneously. This happens because
the Member updates are broadcast to all clients via the Errai Bus.


System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven
3.0 or better.

The application this project produces is designed to be run on a JBoss AS 7 or EAP 6. 
The following instructions target JBoss AS 7, but they also apply to JBoss Enterprise Application Platform 6.
 
With the prerequisites out of the way, you're ready to build and deploy.

Deploying the application (command line)
----------------------------------------
 
First you need to start JBoss AS 7 (or JBoss Enterprise Application Platform 6). To do this, run
  
    $JBOSS_HOME/bin/standalone.sh
  
or if you are using windows
 
    $JBOSS_HOME/bin/standalone.bat

To deploy the application, you first need to produce the archive to deploy using
the following Maven goal:

    mvn package

You can now deploy the artifact to JBoss AS by executing the following command:

    mvn jboss-as:deploy

This will deploy `target/jboss-as-kitchensink-errai.war`.
 
The application will be running at the following URL <http://localhost:8080/jboss-as-kitchensink-errai/>.

To undeploy from JBoss AS, run this command:

    mvn jboss-as:undeploy

You can also start JBoss AS 7 and deploy the project using Eclipse. See the JBoss AS 7
<a href="https://docs.jboss.org/author/display/AS71/Getting+Started+Developing+Applications+Guide" title="Getting Started Developing Applications Guide">Getting Started Developing Applications Guide</a> for more information.

Running the Application in GWT Dev Mode
=======================================

GWT Dev Mode provides an edit-save-refresh development experience. If you plan to try 
modifying this demo, we recommend you start the application in Dev Mode so you don't 
have to repackage the whole application every time you change it.

Deploy the war file and start JBoss AS 7 as described above.

Then execute the command:

    mvn gwt:run

Running the Application in JBoss Developer Studio or Eclipse with JBoss Tools
=============================================================================

To run the project and try it out, you will need to perform a maven
compilation before the first time you deploy, as some resources and
classes can not be generated via the GWT/Eclipse compilers alone:

1. Right-click on the project the project node in the Package Explorer and select Run As > Maven Build...
2. Set goals = package and click Run. GWT compilation might take a few minutes.
3. Once the build is complete, Right-click on the on the project node in the Package Explorer and select Refresh.

These steps will have to be repeated whenever you perform a clean build in Eclipse.

Now that your project is ready to be deployed, right-click on the
project node in the Package Explorer and select Run As > Run On Server
from the popup menu. Choose the appropiate JBoss AS server in
the dialog that appears.

If you want to make changes to client-side code, we strongly recommend that you
take advantage of GWT Dev Mode. Follow these steps:

1. Deploy to JBoss AS (as in the above paragraph)
2. Right-click the project in the Package Explorer
3. From the popup menu, select Properties > Google > Web Toolkit
4. Click Add... and add the KitchenSink module, then OK
5. Right-click the project in the Package Explorer
6. From the popup menu, choose Run As > Web Application (running on external server)
7. In the dialog that appears:
   * Ensure External Server Root is set to http://localhost:8080/<your application name>
   * Ensure Select an HTML page is checked
   * Select KitchenSink.html from the Matching Items list

Importing the project into another IDE
======================================

If you created the project using the Maven archetype wizard in your IDE
(Eclipse, NetBeans or IntelliJ IDEA), then there is nothing to do. You should
already have an IDE project.

Detailed instructions for using Eclipse with JBoss AS 7 are provided in the
JBoss AS 7 <a href="https://docs.jboss.org/author/display/AS71/Getting+Started+Developing+Applications+Guide" title="Getting Started Developing Applications Guide">Getting Started Developing Applications Guide</a>.

If you created the project from the commandline using archetype:generate, then
you need to import the project into your IDE. If you are using NetBeans 6.8 or
IntelliJ IDEA 9, then all you have to do is open the project as an existing
project. Both of these IDEs recognize Maven projects natively.

Downloading the sources and Javadocs
====================================

If you want to be able to debug into the source code or look at the Javadocs
of any library in the project, you can run either of the following two
commands to pull them into your local repository. The IDE should then detect
them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
