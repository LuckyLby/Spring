package cn.itcast.spring3.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
	private UserDao userDao;

	public JDKProxy(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public UserDao createProxy() {
		UserDao proxy = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),
				userDao.getClass().getInterfaces(), this);
		return proxy;
	}

	@Override
	//调用目标对象的任何一个方法都相当于调用invoke();
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		if("add".equals(method.getName())) {
			//记录日志
			System.out.println("日志记录******************");
			Object result = method.invoke(userDao, args);
			return result;
		}
		return method.invoke(userDao, args);
	}
}
