package cn.violetgarden.blog.aspect;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisAspect {
    
    private static final Set<String> CACHEABLE_PREFIXES = 
        Set.of("find", "get", "query", "select", "list", "load");
    private static final Set<String> EVICT_PREFIXES = 
        Set.of("save", "update", "delete", "remove", "create", "add");
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 拦截所有Service层的方法
     */
    @Around("execution(public * cn.violetgarden.blog.service..*.*(..))")
    public Object aroundServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String methodName = method.getName();
        String className = method.getDeclaringClass().getSimpleName();
        
        // 生成缓存键
        String cacheKey = generateCacheKey(className, methodName, joinPoint.getArgs());
        
        // 处理查询方法（自动缓存）
        if (CACHEABLE_PREFIXES.stream().anyMatch(methodName::startsWith)) {
            return handleCacheableMethod(joinPoint, cacheKey);
        }
        
        // 处理更新方法（自动清除缓存）
        if (EVICT_PREFIXES.stream().anyMatch(methodName::startsWith)) {
            return handleCacheEvictMethod(joinPoint, className);
        }
        
        // 其他方法直接执行
        return joinPoint.proceed();
    }
    
    /**
     * 处理需要缓存的方法
     */
    private Object handleCacheableMethod(ProceedingJoinPoint joinPoint, String cacheKey) throws Throwable {
        // 1. 先尝试从Redis获取缓存
        try {
            Object cachedValue = redisTemplate.opsForValue().get(cacheKey);
            if (cachedValue != null) {
                System.out.println("Cache got" + cacheKey);
                return cachedValue;
            }
        } catch (Exception e) {
            System.out.println("Cache read failed" + e.getMessage());
        }
        
        // 2. 缓存未命中，执行原方法
        Object result = joinPoint.proceed();
        
        // 3. 将结果存入Redis（异步，避免影响主流程）
        if (result != null) {
            try {
                redisTemplate.opsForValue().set(cacheKey, result, Duration.ofMinutes(30));
                System.out.println("Cache seted" + cacheKey);
            } catch (Exception e) {
                System.out.println("Cache set failed " + e.getMessage());
            }
        }
        
        return result;
    }
    
    /**
     * 处理需要清除缓存的方法
     */
    private Object handleCacheEvictMethod(ProceedingJoinPoint joinPoint, String className) throws Throwable {
        // 1. 先执行更新操作
        Object result = joinPoint.proceed();
        
        // 2. 异步清除相关缓存
        try {
            clearClassCache(className);
            System.out.println("Cache cleared: " + className);
        } catch (Exception e) {
            System.out.println("Cache clear failed: " + e.getMessage());
        }
        
        return result;
    }
    
    /**
     * 生成缓存键
     */
    private String generateCacheKey(String className, String methodName, Object[] args) {
        StringBuilder key = new StringBuilder();
        key.append("cache:").append(className).append(":").append(methodName);
        
        if (args != null && args.length > 0) {
            key.append(":").append(Arrays.deepHashCode(args));
        }
        
        return key.toString();
    }
    
    /**
     * 清除指定类别的所有缓存
     */
    private void clearClassCache(String className) {
        String pattern = "cache:" + className + ":*";
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}