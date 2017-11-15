See https://access.redhat.com/solutions/749103 for configuration

===== for my local test

- Create directory structure

     $JBOSS_HOME/modules/com/example/custom-valve/main/custom-http-header-valve-1.0.jar
     $JBOSS_HOME/modules/com/example/custom-valve/main/module.xml

- module.xml

       <module xmlns="urn:jboss:module:1.1" name="com.example.custom-valve">
          <resources>
            <resource-root path="custom-http-header-valve-1.0.jar"/>
          </resources>
          <dependencies>
            <module name="sun.jdk"/>
            <module name="javax.servlet.api"/>
            <module name="org.jboss.as.web"/>
            <module name="org.jboss.logging"/>
          </dependencies>
        </module>
        
- standalone.xml

		<subsystem xmlns="urn:jboss:domain:web:2.2" default-virtual-server="default-host" native="false">
            <connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/>
            <virtual-server name="default-host" enable-welcome-root="true">
                <alias name="localhost"/>
                <alias name="example.com"/>
            </virtual-server>
            <valve name="AddCustomHeaderValve" module="com.example.custom-valve" class-name="com.example.AddCustomHeaderValve"/>
        </subsystem>