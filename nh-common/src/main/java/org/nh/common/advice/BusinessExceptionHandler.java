package org.nh.common.advice;

import org.nh.common.exception.BusinessException;
import org.nh.common.util.ExceptionUtil;
import org.nh.common.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class BusinessExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionHandler.class);

	private static final String BUSINESS_EXCEPTION_ADVICE = "[业务异常拦截] ";
	private static final String SYSTEM_EXCEPTION_ADVICE = "[系统异常拦截] ";

	@ResponseBody
	@ExceptionHandler(value = BusinessException.class)
	public ResponseResult<Object> handler(HttpServletRequest request, BusinessException e) throws Exception {
		ResponseResult<Object> result = new ResponseResult<>();
		result.setSubCode(e.getErrorCode());
		result.setSubMessage(e.getMessage());
		LOGGER.error("{}:{}", BUSINESS_EXCEPTION_ADVICE, ExceptionUtil.parseStackTrace(e));
		return result;
	}

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseResult<Object> handler(HttpServletRequest request, Exception e) throws Exception {
		ResponseResult<Object> result = new ResponseResult<>();
		result.setSubCode(ResponseResult.CODE_ERROR);
		result.setSubMessage(e.getMessage());
		LOGGER.error("{}:{}", SYSTEM_EXCEPTION_ADVICE, ExceptionUtil.parseStackTrace(e));
		return result;
	}

}
