package com.ender.util;
import com.ender.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * 强转一下
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
