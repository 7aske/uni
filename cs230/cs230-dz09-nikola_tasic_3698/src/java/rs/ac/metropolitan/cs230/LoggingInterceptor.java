package rs.ac.metropolitan.cs230;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SetupLoggingInterceptor
@Interceptor
public class LoggingInterceptor implements Serializable {

	private static final Logger logger = Logger.getLogger(
			LoggingInterceptor.class.getName());

	@AroundInvoke
	public Object logMethodCall(InvocationContext invocationContext) throws Exception {
		logger.log(Level.INFO, "Entry " + invocationContext.getMethod().getName() + " method");
		Object retVal = invocationContext.proceed();
		logger.log(Level.INFO, "Exit " + invocationContext.getMethod().getName() + " method");
		return retVal;
	}
}
