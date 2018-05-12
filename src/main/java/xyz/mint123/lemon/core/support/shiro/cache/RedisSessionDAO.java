package xyz.mint123.lemon.core.support.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;

/**
 * Shiro redis session DAO
 *
 * @author lemon
 * @version 2018/5/12
 */
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

    private RedisTemplate<String, Session> redisTemplate;

    private ValueOperations<String, Session> valueOperations;

    private final String SESSION_KEY_PREFIX = "shiro:session:";

    /**
     * 获取 session 缓存key
     */
    private String getSessionKey(Serializable key) {
        return SESSION_KEY_PREFIX + key;
    }


    public RedisSessionDAO(CacheManager cacheManager, RedisTemplate<String, Session> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        setCacheManager(cacheManager);
    }


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        valueOperations.set(getSessionKey(sessionId), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = valueOperations.get(getSessionKey(sessionId));
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        // TODO 添加 更新缓存条件
        valueOperations.set(getSessionKey(session.getId()), session);
    }

    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(getSessionKey(session.getId()));
        super.doDelete(session);
    }
}