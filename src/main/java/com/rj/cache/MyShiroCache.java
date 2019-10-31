package com.rj.cache;

import lombok.Setter;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Setter
public class MyShiroCache implements Cache {
    private String name;

    public MyShiroCache() {
    }

    public MyShiroCache(String name) {
        this.name = name;
    }

    private RedisTemplate<String,Object> template;

    @Override
    public Object get(Object o) throws CacheException {
        Object data = template.opsForValue().get(o.toString());
        if (data != null) {
            return data;
        }
        return null;
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        //template.opsForValue().set(o.toString(), o2);
        template.opsForValue().set(o.toString(), o2, 1, TimeUnit.HOURS);
        return null;
    }

    @Override
    public Object remove(Object o) throws CacheException {
        template.delete(o.toString());
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set keys() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }
}
