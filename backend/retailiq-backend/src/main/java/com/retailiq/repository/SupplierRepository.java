package com.retailiq.repository;

import com.retailiq.dto.SupplierReportResponse;
import com.retailiq.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsBySupplierCode(String supplierCode);

    boolean existsByEmail(String email);

    List<Supplier> findByStatusTrue();

    @Query("""
            SELECT s
            FROM Supplier s
            WHERE s.status = true
            AND (
                LOWER(s.companyName) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(s.contactPerson) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(s.supplierCode) LIKE LOWER(CONCAT('%', :keyword, '%'))
            )
            """)
    List<Supplier> searchSuppliers(@Param("keyword") String keyword);

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