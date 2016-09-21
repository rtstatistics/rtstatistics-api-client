/**
 * 
 */
package com.rtstatistics.client.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

/**
 * @author James Hu (Zhengmao Hu)
 *
 */
public class StatisticsTest {
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void shouldSortNullKeyFieldsCorrectly() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException{
		assertNull(roundTrip(null));
		assertNull((Object[])roundTrip(null));
		assertNull(roundTrip());
		assertNull(roundTrip(new Object[0]));
	}
	
	@Test
	public void shouldSortSimpleKeyFieldsCorrectly() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException{
		shouldBe(new Object[]{"abc"}, "abc");
		shouldBe(new Object[]{"abc", "xyz"}, "abc", "xyz");
		shouldBe(new Object[]{"abc", "uvw", "xyz"}, "uvw", "xyz", "abc");

		shouldBe(new Object[]{"123", "xyz"}, new Integer(123), "xyz");
	}
	
	@Test
	public void shouldSortComplexKeyFieldsCorrectly() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException{
		shouldBe(new Object[]{"abc", new String[]{"city", "uvw"}, "xyz"}, new String[]{"uvw", "city"}, "xyz", "abc");
		shouldBe(new Object[]{"abc", new String[]{"city", "uvw"}, "xyz"}, new String[]{"uvw", "city"}, "abc", "xyz");
		shouldBe(new Object[]{"abc", new String[]{"city", "uvw"}, "xyz"}, "xyz", new String[]{"uvw", "city"}, "abc");
		shouldBe(new Object[]{"abc", new String[]{"city", "uvw"}, "xyz"}, "xyz", new String[]{"city", "uvw"}, "abc");
		shouldBe(new Object[]{"abc", new String[]{"city", "uvw"}, "xyz"}, "abc", "xyz", new String[]{"uvw", "city"});
		shouldBe(new Object[]{"abc", new String[]{"city", "uvw"}, "xyz"}, "abc", "xyz", new String[]{"city", "uvw"});
	}
	
	@Test
	public void shouldSortMoreComplexKeyFieldsCorrectly() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException{
		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, "xyz", new String[]{"xyz", "zzz"}, new String[]{"city", "uvw", "zzz"});
		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, new String[]{"xyz", "zzz"}, "xyz", new String[]{"city", "uvw", "zzz"});
		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, new String[]{"xyz", "zzz"}, new String[]{"city", "uvw", "zzz"}, "xyz");

		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, "xyz", new String[]{"zzz", "xyz"}, new String[]{"zzz", "uvw", "city"});
		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, new String[]{"xyz", "zzz"}, "xyz", new String[]{"uvw", "city", "zzz"});
		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, new String[]{"zzz", "xyz"}, new String[]{"city", "uvw", "zzz"}, "xyz");

		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, "xyz", Lists.newArrayList("zzz", "xyz"), new String[]{"zzz", "uvw", "city"});
		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, new String[]{"xyz", "zzz"}, "xyz", Lists.newArrayList("uvw", "city", "zzz"));
		shouldBe(new Object[]{new String[]{"city", "uvw", "zzz"}, "xyz", new String[]{"xyz", "zzz"}}, Lists.newArrayList("zzz", "xyz"), Lists.newArrayList("city", "uvw", "zzz"), "xyz");
	}
	
	protected Object[] roundTrip(Object... keys) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException{
		Statistics s = new Statistics();
		s.setKeyFields(keys);
		s = mapper.readValue(mapper.writeValueAsBytes(s), Statistics.class);
		s.normalizeAndSortKeyFields();
		return s.getKeyFields();
	}
	
	protected void shouldBe(Object[] expected, Object... input) throws JsonParseException, JsonMappingException, JsonProcessingException, IOException{
		Object[] actual = roundTrip(input);
		assertArrayEquals(expected, actual);
	}
}
