package com.godmonth.util.hc;

import java.util.Map;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author shenyue
 */
public class HttpParamsFactoryBean implements FactoryBean<HttpParams>, InitializingBean {
	private Map<String, Object> params;
	private HttpParams httpParams;

	@Override
	public void afterPropertiesSet() {
		httpParams = new BasicHttpParams();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			httpParams.setParameter(entry.getKey(), entry.getValue());
		}
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public HttpParams getObject() throws Exception {
		return httpParams;
	}

	@Override
	public Class<?> getObjectType() {
		return HttpParams.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
