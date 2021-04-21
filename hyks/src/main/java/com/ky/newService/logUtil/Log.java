package com.ky.newService.logUtil;

import java.lang.annotation.*;

/**
 * @Classname:com.ky.azyx.logUtil
 * @Auther: ywj
 * @Date: 2020/8/31 15:09
 * @Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String module() default "";
    /**
     * 描述
     */
    String description() default "";
}
