package com.example.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MyFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext rt= RequestContext.getCurrentContext();
        HttpServletRequest request=rt.getRequest();
        Enumeration<String> parameterName= request.getParameterNames();
        List<String> list=new ArrayList<>();
        while (parameterName.hasMoreElements()){

            String name=parameterName.nextElement();
            list.add(name);
            System.out.println(name+":"+request.getParameter(name));
        }
        if(list.size()<=0){
            rt.setSendZuulResponse(false);
            rt.setResponseBody("At least one parameter");
        }
        return null;
    }
}
