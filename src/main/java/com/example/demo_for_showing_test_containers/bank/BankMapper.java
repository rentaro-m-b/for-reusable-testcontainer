package com.example.demo_for_showing_test_containers.bank;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BankMapper {
    @Select(
            """
                SELECT
                    banks.id,
                    banks.value,
                    banks.unit,
                    banks.created_at
                FROM
                    banks
                ORDER BY
                    created_at
            """
    )
    List<BankRow> listBanks();

    @Insert("""
        INSERT INTO banks (
            id,
            value,
            unit,
            created_at
        ) VALUES (
            #{id},
            #{value},
            #{unit},
            #{createdAt}
        )
    """)
    void createBank(BankRow bank);

    @Update("""
        UPDATE banks
        SET
            value = #{value},
            unit = #{unit},
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
