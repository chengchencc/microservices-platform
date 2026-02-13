package com.central.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Optional;

/**
 * 租户holder
 *
 * @author zlt
 * @date 2019/8/5
 */
public class TenantContextHolder {
    public static final String DEFAULT_TENANT_ID="master";
    /**
     * 支持父子线程之间的数据传递
     */
    private static final ThreadLocal<String> CONTEXT = new TransmittableThreadLocal<>();

    public static void setTenant(String tenant) {
        CONTEXT.set(tenant);
    }

    public static String getTenant() {
        return CONTEXT.get();
    }

    public static String getTenantOrDefault(){
        return Optional.ofNullable(TenantContextHolder.getTenant())
                .orElse(TenantContextHolder.DEFAULT_TENANT_ID);
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
