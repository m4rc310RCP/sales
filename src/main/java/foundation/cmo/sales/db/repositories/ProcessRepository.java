package foundation.cmo.sales.db.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import foundation.cmo.sales.models.Process;

@Repository
public interface ProcessRepository extends CrudRepository<Process, Long>{
	boolean existsByDeviceDeviceIDAndDateCloseIsNull(String deviceId);
	Optional<Process> findByDeviceDeviceIDAndDateCloseIsNull(String deviceId);
	Iterable<Process> findAllByDeviceDeviceID(String deviceId);
	Iterable<Process> findAllByDateCloseIsNull();
}
