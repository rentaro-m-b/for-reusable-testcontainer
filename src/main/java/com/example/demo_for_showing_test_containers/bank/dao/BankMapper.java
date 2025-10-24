package com.example.demo_for_showing_test_containers.bank.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BankMapper {
    @Select("""
        SELECT
            banks.id,
            banks.deposit,
            banks.currency,
            banks.created_at
        FROM
            banks
        ORDER BY
            created_at
    """)
    List<BankRow> listBanks();

    @Insert("""
        INSERT INTO banks (
            id,
            deposit,
            currency,
            created_at
        ) VALUES (
            #{id},
            #{deposit},
            #{currency},
            #{createdAt}
        )
    """)
    void createBank(BankRow bank);

    @Update("""
        UPDATE banks
        SET
            deposit = #{deposit},
            currency = #{currency},
            created_at = #{createdAt}
        WHERE id = #{id}
    """)
    void updateBank(BankRow bank);

    @Delete("""
    DELETE FROM banks
    WHERE id = #{id}
""")
    void deleteBank(String id);
}
