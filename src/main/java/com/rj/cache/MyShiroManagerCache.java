package com.rj.cache;

import lombok.Setter;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

@Setter
public class MyShiroManagerCache extends AbstractCacheManager {
    private RedisTemplate<String,Object> template;
    @Override
    protected Cache createCache(String s) throws CacheException {
        MyShiroCache myShiroCache = new MyShiroCache(s);
        myShiroCache.setTemplate(template);
        return myShiroCache;
    }
}
