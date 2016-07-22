package com.hkamran.jdbclogger.instrument.transformers;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.apache.log4j.Logger;

import com.hkamran.jdbclogger.sql.wrappers.ConnectionWrapper;

public class DriverManagerTransformer implements ClassFileTransformer {
	
	private final static Logger log = Logger.getLogger(DriverManagerTransformer.class);
	private String searchFor = "java.sql.DriverManager";;
	
	public DriverManagerTransformer() {
		log.info("Searching for " + searchFor);
	}
	
	@Override
	public byte[] transform(ClassLoader loader, String rawclassName, Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {

		String className = rawclassName.replaceAll("/", ".");

		if (className.equals(searchFor)) {;
			try {
				log.info("Instrumenting " + this.searchFor);
				ClassPool cp = ClassPool.getDefault();

				cp.importPackage("sun.reflect");
				
				CtClass curClass = cp.get(className);
				CtClass strClass = cp.get(String.class.getCanonicalName());
				CtClass[] paramArgs = new CtClass[] { strClass, strClass, strClass };
				
				CtMethod checkMethod = curClass.getDeclaredMethod("getConnection", paramArgs);
				String connectionClass = ConnectionWrapper.class.getCanonicalName();
				checkMethod.setBody(
						"{" + 
							"java.util.Properties info = new java.util.Properties();" + 
							"if ($2 != null) { info.put(\"user\", $2); }" + 
							"if ($3 != null) { info.put(\"password\", $3); }" + 
							"return new " + connectionClass + "(getConnection($1, info, Reflection.getCallerClass()));" + 
						"}");
				
				return curClass.toBytecode();
			} catch (NotFoundException | CannotCompileException | IOException ex) {
				ex.printStackTrace();
			}
		}
		return classfileBuffer;

	}

}
