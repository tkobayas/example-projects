1. mvn clean install

2. Copy gatein-init-filter-BZ1321009-1.0.jar to $JBOSS_HOME/modules/system/layers/gatein/org/gatein/lib/main/

3. Add <resource-root path="gatein-init-filter-BZ1321009-1.0.jar"/> in $JBOSS_HOME/modules/system/layers/gatein/org/gatein/lib/main/module.xml as follows.

<module xmlns="urn:jboss:module:1.1" name="org.gatein.lib">
  <resources>
        ...
        <resource-root path="gatein-init-filter-BZ1321009-1.0.jar"/>
  </resources>
  
4. Add <module name="org.jboss.remoting-jmx" services="import" export="true"/> to $YOUR_PORTAL_CONTAINER_EAR/META-INF/jboss-deployment-structure.xml

<jboss-deployment-structure>
  <ear-subdeployments-isolated>false</ear-subdeployments-isolated>

  <sub-deployment name="sample-portal.war" >
    <dependencies>
      <module name="org.gatein.portal.container-dependencies"/>
      <module name="org.jboss.remoting-jmx" services="import" export="true"/>
    </dependencies>
  </sub-deployment>

</jboss-deployment-structure>


5. Edit $YOUR_PORTAL_CONTAINER_EAR/$YOUR_PORTAL_CONTAINER_WAR/WEB-INF/web.xml as follows. Comment out the default PortalCheckInitFilter and add PortalCheckInitFilter2. Note that the package name is also different.

=====
  <!-- <filter> -->
  <!--   <filter-name>PortalCheckInitFilter</filter-name> -->
  <!--   <filter-class>org.gatein.portal.init.PortalCheckInitFilter</filter-class> -->
  <!-- </filter>   -->

  <!-- <filter-mapping> -->
  <!--   <filter-name>PortalCheckInitFilter</filter-name> -->
  <!--   <url-pattern>/*</url-pattern> -->
  <!-- </filter-mapping>   -->

  <filter>
    <filter-name>PortalCheckInitFilter2</filter-name>
    <filter-class>com.redhat.jboss.support.PortalCheckInitFilter2</filter-class>
  </filter>

  <filter-mapping>
    <filter-name>PortalCheckInitFilter2</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
======
