package foundation.cmo.sales.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import foundation.cmo.sales.models.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, String>{
}
