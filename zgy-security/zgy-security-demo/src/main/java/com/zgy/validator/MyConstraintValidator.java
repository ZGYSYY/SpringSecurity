package com.zgy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//MyConstraint注解的具体业务逻辑
/*
ConstraintValidator<MyConstraint,Object>:MyConstraint指定那个注解，Object指定是注解的类型为Object，也可以是其他类型
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object>{

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("自定义校验器初始化！");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("自定义校验器，处理逻辑");
        return false;
    }
}
