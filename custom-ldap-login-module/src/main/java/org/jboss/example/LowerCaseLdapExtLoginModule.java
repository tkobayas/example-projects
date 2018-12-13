package org.jboss.example;

import javax.security.auth.login.LoginException;

import org.jboss.security.auth.spi.LdapExtLoginModule;

public class LowerCaseLdapExtLoginModule extends LdapExtLoginModule {

    @Override
    protected String[] getUsernameAndPassword() throws LoginException {
        String[] info = super.getUsernameAndPassword();
        info[0] = info[0].toLowerCase();
        System.out.println("info[0] = " + info[0]);
        return info;
    }

}
