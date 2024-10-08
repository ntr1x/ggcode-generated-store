package com.example.service.security.resolver;

import com.example.service.security.model.PrincipalModel;
import org.ntr1x.common.web.component.AppArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

@Component
public class PrincipalModelResolver implements AppArgumentResolver {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return PrincipalModel.class.equals(parameter.getParameterType());
    }

    @Override
    public PrincipalModel resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        ConversionService conversionService = applicationContext.getBean(ConversionService.class);
        Principal principal = webRequest.getUserPrincipal();
        return conversionService.convert(principal, PrincipalModel.class);
    }
}
