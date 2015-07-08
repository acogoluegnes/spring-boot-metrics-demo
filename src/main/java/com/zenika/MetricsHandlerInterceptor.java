package com.zenika;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by acogoluegnes on 08/07/15.
 */
public class MetricsHandlerInterceptor implements HandlerInterceptor {

    @Autowired MetricRegistry registry;

    ThreadLocal<Timer.Context> contexts = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Timer.Context context = registry.timer(createMetricId(handler)).time();
        contexts.set(context);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            contexts.get().stop();
        } finally {
            contexts.remove();
        }
    }

    protected String createMetricId(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        return handlerMethod.getBeanType().getName()+"."+handlerMethod.getMethod().getName();
    }

}
