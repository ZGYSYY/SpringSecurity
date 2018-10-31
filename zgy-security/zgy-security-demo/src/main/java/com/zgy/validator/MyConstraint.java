package com.zgy.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个校验注解
 */

@Target({ElementType.METHOD,ElementType.FIELD}) //说明该注解能加在方法和字段上
@Retention(RetentionPolicy.RUNTIME) //说明是一个运行时注解
@Constraint(validatedBy = MyConstraintValidator.class) //指定注解的处理逻辑在哪里
public @interface MyConstraint {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
