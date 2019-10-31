package com.rj.cache;

import lombok.Setter;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Setter
public class MySessionDao extends AbstractSessionDAO {
    private RedisTemplate<String,Object> template;
    @Override
    protected Serializable doCreate(Session session) {
        Serializable JSESSIONID = this.generateSessionId(session);
        this.assignSessionId(session, JSESSIONID);
        template.opsForValue().set("rj_"+JSESSIONID, session, 10, TimeUnit.SECONDS);
        return JSESSIONID;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        template.expire("rj_"+sessionId, 10, TimeUnit.SECONDS);
        SimpleSession simpleSession = (SimpleSession) template.opsForValue().get("rj_" + sessionId);
        return simpleSession;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        template.expire("rj_"+session.getId(), 10, TimeUnit.SECONDS);
        template.opsForValue().set("rj_"+session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        template.delete("rj_"+session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set keys = template.keys("rj_*");
        List<Session> sessions = template.opsForValue().multiGet(keys);
        return sessions;
    }
}
