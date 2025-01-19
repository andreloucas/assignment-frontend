package gr.assignment.frontend.repository;

import gr.assignment.frontend.entity.RevisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionRepository extends JpaRepository<RevisionEntity, Long> {

    List<RevisionEntity> findRevisionsByResourceId(Long resourceId);
}
