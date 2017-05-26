package com.tonyj.frame.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

/**
 * Author: Tony.Wang
 * Date: 12-3-18
 * Time: 下午3:24
 * Description: to write something
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.getSerializationConfig().set(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.setSerializationInclusion(Inclusion.NON_NULL); 
        objectMapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
    }


	
    /**
     * object transform json string
     *
     * @param o object
     * @return json string
     */
    public static String objectToJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (IOException e) {
            logger.error("object:{} to json error:{}.", o, ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    /**
     * json string transform object
     *
     * @param json
     * @param className
     * @param defaultInstance when json string is empty,whether build classes instance,true:build;false:not build
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> className, boolean defaultInstance) {
        if (StringUtils.isEmpty(json)) {
            if (defaultInstance) {
                try {
                    return className.newInstance();
                } catch (Exception e) {
                    logger.error("instance class:{} error:{}.", className, ExceptionUtils.getStackTrace(e));
                    return null;
                }
            } else {
                return null;
            }
        } else {
            try {
//            	logger.debug("objectMapper.readValue:{} ,classname:{}.", json,className);
                return objectMapper.readValue(json, className);
            } catch (IOException e) {
                logger.error("json:{} to object error:{}.", json, ExceptionUtils.getStackTrace(e));
                return null;
            }
        }
    }

   /* public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(json, typeReference);
            } catch (IOException e) {
                logger.error("json:{} to object error:{}.", json, ExceptionUtils.getStackTrace(e));
                return null;
            }
        }
    }*/
    
	/**
	 * Method to deserialize JSON content as tree expressed using set of
	 * JsonNode instances.
	 */
	public static JsonNode readTree(String content) 
	{
		JsonNode jsonNode =null;
		if((content == null)||content.isEmpty())
		{
			logger.warn("JacksonMapper readTree, error: content is null");
			return null;
		}
		try {
			jsonNode = objectMapper.readTree(content);
		} catch (Exception e) {
			logger.error("readTree, error:" + e.getMessage());
			for (StackTraceElement elem : e.getStackTrace()) {
				logger.error("readTree, error:" + elem.toString());
			}
		} 
		return jsonNode;
	}
	
	
}
