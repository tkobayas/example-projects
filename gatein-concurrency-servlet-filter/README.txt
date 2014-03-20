- mvn clean install
- copy target/gatein-concurrency-servlet-filter-1.0.jar to gatein.ear/lib
- edit gatein.ear/02portal.war/WEB-INF/web.xml to add following entries

  <filter>
    <filter-name>DashboardConcurrencyFilter</filter-name>
    <filter-class>com.redhat.jboss.support.DashboardConcurrencyFilter</filter-class>
     <init-param>
        <param-name>maxRetry</param-name>
	<param-value>100</param-value>
     </init-param>
  </filter>
  <filter>
    <filter-name>PageSaveConcurrencyFilter</filter-name>
    <filter-class>com.redhat.jboss.support.PageSaveConcurrencyFilter</filter-class>
     <init-param>
        <param-name>maxRetry</param-name>
        <param-value>100</param-value>
     </init-param>
  </filter>
  
...

  <filter-mapping>
    <filter-name>DashboardConcurrencyFilter</filter-name>
          <url-pattern>/u/*</url-pattern>
  </filter-mapping>
  <filter-mapping>
    <filter-name>PageSaveConcurrencyFilter</filter-name>
          <url-pattern>/*</url-pattern>
  </filter-mapping>
  
  
 Make sure that these <filter-mapping> elements are AFTER <filter-mapping> for LocalizationFilter.
 