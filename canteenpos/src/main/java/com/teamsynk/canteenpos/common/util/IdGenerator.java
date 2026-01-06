package com.teamsynk.canteenpos.common.util;

import java.util.UUID;

public class IdGenerator {

	private IdGenerator() { 
		
	} 
	
	public static UUID newUUID() { 
		return UUID.randomUUID(); 
	}
}
