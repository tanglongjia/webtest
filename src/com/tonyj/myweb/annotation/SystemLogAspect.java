package com.tonyj.myweb.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.tonyj.myweb.po.BsLog;
import com.tonyj.myweb.po.BsUser;
import com.tonyj.myweb.service.BsLogService;

@Aspect
@Component
public class SystemLogAspect {

	@Autowired
	private BsLogService bsLogService;

	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory
			.getLogger(SystemLogAspect.class);

	// Controller层切点
	@Pointcut("@annotation(com.tonyj.myweb.annotation.SystemLogBeforeController)")
	public void controllerBeforeAspect() {
	}
	
	// Controller层切点
	@Pointcut("@annotation(com.tonyj.myweb.annotation.SystemLogAfterController)")
	public void controllerAfterAspect() {
	}
	
	//service层切点
	@Pointcut("@annotation(com.tonyj.myweb.annotation.SystemLogService)")
	public void serviceAspect(){
		
	}
	
	/**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
     @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")    
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        //读取session中的用户    
        BsUser user = (BsUser) session.getAttribute("bsUser");    
        //获取请求ip    
        String ip = request.getRemoteAddr();    
        //获取用户请求方法的参数并序列化为JSON格式字符串    
        String params = "";    
         if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
             for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
                params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";    
            }    
        }    
         try {    
              /*========控制台输出=========*/    
            System.out.println("=====异常通知开始=====");    
            System.out.println("异常代码:" + e.getClass().getName());    
            System.out.println("异常信息:" + e.getMessage());    
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));    
            System.out.println("请求人:" + user.getLoginName());    
            System.out.println("请求IP:" + ip);    
            System.out.println("请求参数:" + params);    
               /*==========数据库日志=========*/    
            BsLog log = new BsLog();    
            log.setDescription(getServiceMthodDescription(joinPoint));    
            log.setExcepCode(e.getClass().getName());    
            log.setLogType(1);    
            log.setExcepDetail(e.getMessage());    
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
            log.setParams(params);    
            log.setOperatorId(user.getId());
            log.setOperatorName(user.getLoginName());
            log.setRequestIp(ip);    
            //保存数据库    
            bsLogService.saveLog(log);   
            System.out.println("=====异常通知结束=====");    
        }  catch (Exception ex) {    
            //记录本地异常日志    
            logger.error("==异常通知异常==");    
            logger.error("异常信息:{}", ex.getMessage());    
        }    
         /*==========记录本地异常日志==========*/    
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);    
    
    }    
	
	@After("controllerAfterAspect()")
	public void doAfter(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		BsUser user = (BsUser) session.getAttribute("bsUser");
		// 请求的IP
		String ip = request.getRemoteAddr();
		try {
			// *========控制台输出=========*//
			System.out.println("====后置通知开始=====");
			System.out.println("请求方法:"
					+ (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:"
					+ getAfterControllerMethodDescription(joinPoint));
			System.out.println("请求人:" + user.getTrueName());
			System.out.println("请求IP:" + ip);
			// *========数据库日志=========*//
			BsLog log = new BsLog();
			log.setDescription(getAfterControllerMethodDescription(joinPoint));
			log.setMethod((joinPoint.getTarget().getClass().getName() + "."
					+ joinPoint.getSignature().getName() + "()"));
			log.setLogType(0);
			log.setRequestIp(ip);
			log.setExcepCode(null);
			log.setExcepDetail(null);
			log.setParams(null);
			log.setOperatorId(user.getId());
			log.setOperatorName(user.getLoginName());
			// 保存数据库
			bsLogService.saveLog(log);
			System.out.println("=====后置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==后置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerBeforeAspect()")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		// 读取session中的用户
		BsUser user = (BsUser) session.getAttribute("bsUser");
		// 请求的IP
		String ip = request.getRemoteAddr();
		try {
			// *========控制台输出=========*//
			System.out.println("=====前置通知开始=====");
			System.out.println("请求方法:"
					+ (joinPoint.getTarget().getClass().getName() + "."
							+ joinPoint.getSignature().getName() + "()"));
			System.out.println("方法描述:"
					+ getBeforeControllerMethodDescription(joinPoint));
			System.out.println("请求人:" + user.getTrueName());
			System.out.println("请求IP:" + ip);
			// *========数据库日志=========*//
			BsLog log = new BsLog();
			log.setDescription(getBeforeControllerMethodDescription(joinPoint));
			log.setMethod((joinPoint.getTarget().getClass().getName() + "."
					+ joinPoint.getSignature().getName() + "()"));
			log.setLogType(0);
			log.setRequestIp(ip);
			log.setExcepCode(null);
			log.setExcepDetail(null);
			log.setParams(null);
			log.setOperatorId(user.getId());
			log.setOperatorName(user.getLoginName());
			// 保存数据库
			bsLogService.saveLog(log);
			System.out.println("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getBeforeControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(
							SystemLogBeforeController.class).description();
					break;
				}
			}
		}
		return description;
	}
	
	public static String getAfterControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(
							SystemLogAfterController.class).description();
					break;
				}
			}
		}
		return description;
	}
	 /**  
     * 获取注解中对方法的描述信息 用于service层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static String getServiceMthodDescription(JoinPoint joinPoint)    
             throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemLogService.class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    } 
}
