package xyz.mint123.lemon.core.support.shiro.cache;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;

/**
 * Shiro redis session DAO
 *
 * @author lemon
 * @version 2018/5/12
 */
public class RedisSessionDAO extends CachingSessionDAO {


    public RedisSessionDAO(CacheManager cacheManager) {
        setCacheManager(cacheManager);
    }


    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }
}