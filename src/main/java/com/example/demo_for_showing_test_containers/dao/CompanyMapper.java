package com.example.demo_for_showing_test_containers.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {
    @Select("""
        SELECT
            id,
            name,
            created_at
        FROM
            companies
        ORDER BY
            created_at
    """)
    List<CompanyRow> listCompanies();

    @Insert("""
        INSERT INTO companies (
            id,
            name,
            created_at
        ) VALUES (
            #{id},
            #{name},
            #{createdAt}
        )
    """)
    void createCompany(CompanyRow company);

    @Update("""
        UPDATE companies
        SET
            name = #{name},
            created_at = #{createdAt}
        WHERE id = #{id}
    """)
    void updateCompany(CompanyRow company);

    @Delete("""
    DELETE FROM companies
    WHERE id = #{id}
""")
    void deleteCompany(String id);
}
