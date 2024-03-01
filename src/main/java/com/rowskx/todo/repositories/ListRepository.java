package com.rowskx.todo.repositories;

import com.rowskx.todo.models.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {

    @Query(value = "SELECT * from insert_list_function(CAST(:header AS VARCHAR), CAST(:userLogin AS VARCHAR));", nativeQuery = true)
    void addList(@Param("header") String header, @Param("userLogin") String userLogin);

    @Query(value = "SELECT * from delete_list(CAST(:listId AS BIGINT));", nativeQuery = true)
    void deleteById(@Param("listId") Long listId);

    @Query(value = "SELECT * from update_list(CAST(:listId AS BIGINT), CAST(:newHeader AS VARCHAR));", nativeQuery = true)
    void update(@Param("listId") Long listId, @Param("newHeader") String newHeader);

    // @Query(value = "", nativeQuery = true)
    // void removeList(@Param("listId") Long listId);
}
