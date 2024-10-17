package common.util.xml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JacksonXmlUtil {
	
	private JacksonXmlUtil() {
		super();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(JacksonXmlUtil.class);

	@SuppressWarnings("unchecked")
	public static Map<String, Object> converterXmlStrToMap(String xml) {
		Map<String, Object> map = new HashMap<String, Object>();

		XmlMapper xmlMapper = new XmlMapper();
		
		try {
			map = xmlMapper.readValue(xml, Map.class);
		} catch (JsonParseException e) {
			logger.error("", e);
		} catch (JsonMappingException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		
		return map;
	}
	
	public static String convertMapToXmlStr(Map<String, Object> map) {
		JacksonXmlModule xmlModule = new JacksonXmlModule();
		xmlModule.setDefaultUseWrapper(false);
		
		XmlMapper xmlMapper = new XmlMapper(xmlModule);
		
		String xml = "";
		try {
			xml = xmlMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("", e);
		}
		
		return xml;
	}
	
}
