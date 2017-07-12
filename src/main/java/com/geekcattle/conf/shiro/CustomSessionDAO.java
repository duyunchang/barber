
package com.geekcattle.conf.shiro;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

/**
 * author 
 */
public class CustomSessionDAO extends EnterpriseCacheSessionDAO {

    @Override
    public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        sessionIdGenerator = new JavaUuidSessionIdGenerator();
        //System.out.println("SessionID"+ sessionIdGenerator);
        super.setSessionIdGenerator(sessionIdGenerator);
    }
}
