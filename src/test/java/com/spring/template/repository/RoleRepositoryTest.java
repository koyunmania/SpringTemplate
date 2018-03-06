package com.spring.template.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.template.model.Role;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;
    
    @Test
	public void testFindByRole() {
    	Role testRole = new Role();
		testRole.setRole("ROLE_USER");
		entityManager.persist(testRole);
		
		Role foundTestRole = roleRepository.findByRole("ROLE_USER");
		assertTrue(foundTestRole.equals(testRole));
	}

	/* @Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllSort() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllIterableOfID() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveIterableOfS() {
		fail("Not yet implemented");
	}

	@Test
	public void testFlush() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAndFlush() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteInBatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllInBatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOne() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllExampleOfS() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllExampleOfSSort() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllSort1() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllPageable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveS() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveIterableOfS1() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOneID() {
		fail("Not yet implemented");
	}

	@Test
	public void testExistsID() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll1() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllIterableOfID1() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteID() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteT() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteIterableOfQextendsT() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOneExampleOfS() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllExampleOfS1() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllExampleOfSSort1() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllExampleOfSPageable() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountExampleOfS() {
		fail("Not yet implemented");
	}

	@Test
	public void testExistsExampleOfS() {
		fail("Not yet implemented");
	} */

}
