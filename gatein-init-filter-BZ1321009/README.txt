1. mvn clean install

2. Copy gatein-init-filter-BZ1321009-1.0.jar under $JBOSS_HOME/modules/system/layers/gatein/org/gatein/lib/main/

3. Add <resource-root> in $JBOSS_HOME/modules/system/layers/gatein/org/gatein/lib/main/module.xml as follows.

<module xmlns="urn:jboss:module:1.1" name="org.gatein.lib">
  <resources>
        ...
        <resource-root path="gatein-init-filter-BZ1321009-1.0.jar"/>
  </resources>

4. Edit $YOUR_PORTAL_CONTAINER_EAR/$YOUR_PORTAL_CONTAINER_WAR/WEB-INF/web.xml as follows. Comment out the default PortalCheckInitFilter and add PortalCheckInitFilter2. Note that the package name is also different.

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
    <init-param>
      <param-name>delay</param-name>
      <param-value>10</param-value>
    </init-param>
  </filter>

  <filter-mapping>
    <filter-name>PortalCheckInitFilter2</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
======
