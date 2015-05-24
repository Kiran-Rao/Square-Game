package com.rao.kiran.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentTools {

	/**
	 * Creates a Class<?> from className
	 * Creates and returns intent from context and package name
	 * @param packageContext Context of activity calling function
	 * @param className Class name to convert into intent
	 * @return Intent returned based on class name and context
	 * @throws ClassNotFoundException Throws if class name is invalid
	 */
	public static Intent createIntentFromClassName (Context packageContext, String className) throws ClassNotFoundException {
		Class<?> cls = Class.forName(className);
		Intent intent = new Intent(packageContext, cls);
		return intent;
	}
	
	public static void startActivityFromClassName (Context packageContext, String className) {
		try {
			Intent intent = createIntentFromClassName (packageContext, className);
			packageContext.startActivity(intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void startActivityFromClassName (Context packageContext, String className, Bundle bundle) {
		try {
			Intent intent = createIntentFromClassName (packageContext, className);
			intent.putExtras(bundle);
			packageContext.startActivity(intent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
