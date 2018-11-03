package com.zgy.code;

import com.zgy.security.core.validate.code.ImageCode;
import com.zgy.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("这是一个更高级的验证码生成逻辑");
        return null;
    }
}
