package dev.wcs.nad.tariffmanager.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.wcs.nad.tariffmanager.persistence.entity.Department;
import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.repository.DepartmentRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;

@DataJpaTest
public class TariffDepartmentTest {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    DepartmentRepository departmentRepository;
    
    @Test
    public void testCreateTariffWithDepartment(){

        Department department = new Department();
        department.setName("Sushi department");

        Tariff tariff = new Tariff();
        tariff.setName("SUSHI");
        tariff.setPrice(BigDecimal.TEN);
        tariff.setDepartment(department);

        tariffRepository.save(tariff);
        departmentRepository.save(department);

        Tariff savedTariff = tariffRepository.findById(tariff.getId()).orElse(null);
        Department savedDepartment = departmentRepository.findById(department.getId()).orElse(null);

        assertNotNull(savedTariff);
        assertNotNull(savedDepartment);

        assertEquals(savedDepartment.getId(), savedTariff.getDepartment().getId());
        }
}
