package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.annotation.Rollback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.example.demo.model.Recommend;

import com.example.demo.repository.RecommendRepository;

@SpringBootTest
class Musix_RecommendedTest {
	
	@Autowired
	private RecommendRepository recommendrepo;
	
	
	
	@Test
	void contextLoads() {
		 
	}
	

		@Test
	     void testMockCreationrecommend(){
	        assertNotNull(recommendrepo);
	        
	    }
	
	// Test Cases Recommendations
	
	@Test
	@Rollback(false)
	 void testCreateRecommend() {
		int count=(int)recommendrepo.count();
	    Recommend savedrecommend= recommendrepo.save(new Recommend(count+1,"rishav@gmail.com","738732","Track 2","Artist 2", "Album 2","this is url","genre"));
	     
	    assertEquals(count+1,(int)recommendrepo.count());
	}
	
	@Test
	@Rollback(false)
	 void testListRecommended() {
	    List<Recommend> Recommended = (List<Recommend>) recommendrepo.findAll();
	    assertThat(Recommended).size().isPositive();
	}
	
}
