/**
 * 
 */
package com.rtstatistics.client;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author James Hu (Zhengmao Hu)
 *
 */
public class Jackson2Test {

	static public class Pojo{
		public String name;
		public Pojo2 pojo2;
		
		public Pojo(){
			
		}
		
		public Pojo(String name){
			this.name = name;
		}
	}
	
	static public class Pojo2{
		public Integer id;
	}
	
	@Test
	public void shouldSerializeArray() throws JsonProcessingException{
		Pojo[] objs = new Pojo[]{new Pojo("name1"), new Pojo("name2"), new Pojo("name3")};
		String json = Jackson2.objectMapper.writeValueAsString(objs);
		assertEquals("[{\"name\":\"name1\"},{\"name\":\"name2\"},{\"name\":\"name3\"}]", StringUtils.remove(json, ' '));
	}
	
	@Test
	public void shouldSerializeCollection() throws JsonProcessingException{
		List<Pojo> objs = Arrays.asList(new Pojo("name1"), new Pojo("name2"), new Pojo("name3"));
		String json = Jackson2.objectMapper.writeValueAsString(objs);
		assertEquals("[{\"name\":\"name1\"},{\"name\":\"name2\"},{\"name\":\"name3\"}]", StringUtils.remove(json, ' '));
	}
}
