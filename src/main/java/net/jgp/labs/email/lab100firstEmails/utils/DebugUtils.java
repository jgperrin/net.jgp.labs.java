package net.jgp.labs.email.lab100firstEmails.utils;

import java.io.File;

public class DebugUtils {

	public static void printClassPath() {
		System.out.println("CLASSPATH: "
				+ System.getProperty("java.class.path"));

	}

	public static void printSplittedClassPath() {
		String classpath = System.getProperty("java.class.path");
		String[] classpathEntries = classpath.split(File.pathSeparator);
		int i;

		for (i = 0; i < classpathEntries.length; i++) {
			System.out.println(classpathEntries[i]);
		}

	}

}
