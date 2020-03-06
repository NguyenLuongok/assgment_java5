package assJava5.repository;

import assJava5.model.Bill;

import java.util.List;

public interface BillRepository extends Repository<Bill> {
    public List<Bill> saveAll(List<Bill> bill);
    public List<Bill> findByTop10Product();
    public List<Bill> findByTop10Bill();
}
