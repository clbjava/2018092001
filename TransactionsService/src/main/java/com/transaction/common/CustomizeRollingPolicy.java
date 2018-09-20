package com.transaction.common;

import java.io.File;

import ch.qos.logback.core.rolling.RollingPolicyBase;
import ch.qos.logback.core.rolling.RolloverFailure;
import ch.qos.logback.core.rolling.TriggeringPolicy;

public class CustomizeRollingPolicy<E> extends RollingPolicyBase implements TriggeringPolicy<E>{

	@Override
	public void rollover() throws RolloverFailure {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getActiveFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTriggeringEvent(File activeFile, E event) {
		// TODO Auto-generated method stub
		return false;
	}

}
