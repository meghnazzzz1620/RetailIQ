package com.retailiq.repository;

import com.retailiq.dto.SupplierReportResponse;
import com.retailiq.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // Existing methods used by SupplierService
    boolean existsBySupplierCode(String supplierCode);

    boolean existsByEmail(String email);

    List<Supplier> findByStatusTrue();

    // Supplier Report
    @Query("""
        SELECT new com.retailiq.dto.SupplierReportResponse(

            s.contactPerson,

            s.companyName,

            s.email,

            s.phone,

            s.city,

            s.state,

            CASE
                WHEN s.status = true THEN 'Active'
                ELSE 'Inactive'
            END

        )

        FROM Supplier s

        ORDER BY s.contactPerson
        """)
    List<SupplierReportResponse> getSupplierReport();

}