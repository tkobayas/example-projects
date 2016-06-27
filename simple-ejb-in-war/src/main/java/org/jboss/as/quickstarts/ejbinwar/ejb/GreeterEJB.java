/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.ejbinwar.ejb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

/**
 * A simple Hello World EJB. The EJB does not use an interface.
 * 
 * @author paul.robinson@redhat.com, 2011-12-21
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GreeterEJB {

    @Resource(name = "java:jboss/datasources/ExampleDS")
    private DataSource ds;

    @Resource
    private EJBContext context;
    
    /**
     * This method takes a name and returns a personalised greeting.
     * 
     * @param name
     *            the name of the person to be greeted
     * @return the personalised greeting.
     */
    public String sayHello( String name ) {

        int count = checkTable();

        try ( Connection conn = ds.getConnection(); Statement stmt = conn.createStatement(); ) {
            String sql = "insert into TEST (id, name) values (" + (count + 1) + ", 'john')";

            stmt.execute( sql );

            System.out.println( "done! " + sql );

            if ( true ) {
                throw new SQLException( "dummy" );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();

            context.setRollbackOnly();
//            throw new RuntimeException( e );
            
        }

        return "Hello " + name;
    }

    private int checkTable() {
        int count = 0;
        try {

            String sql = "select count(*) from TEST";
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            while ( rs.next() ) {
                count = rs.getInt( 1 );
            }

            System.out.println( "done! " + sql );

            stmt.close();
            conn.close();

        } catch ( Exception e ) {
            try {
                String sql = "create table TEST (id int primary key, name varchar)";
                Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement();
                stmt.execute( sql );

                System.out.println( "create! " + sql );

                stmt.close();
                conn.close();

                return 0;
            } catch ( Exception e1 ) {
                e1.printStackTrace();
            }
        }
        return count;
    }
}
