package com.tang.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MagicCondition implements Condition {

	@Override
	public boolean matches(ConditionContext arg0, AnnotatedTypeMetadata arg1) {
		Environment env = arg0.getEnvironment();
		String  magic = env.getProperty("magic");
		if(magic.equals("true")){
			return true;
		}
		return false;
	}

}
