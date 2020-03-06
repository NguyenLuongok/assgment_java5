package assJava5.service;


import assJava5.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> findAll();
    Bill finById(Long id);
    void save(Bill bill);
    void remove(Long id);
    List<Bill> saveAll(List<Bill> bill);
    List<Bill> findByTop10Product();
    List<Bill> findByTop10Bill();
}
