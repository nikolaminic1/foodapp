package com.example.foodapp.product.service.business;

import com.example.foodapp.product.model.Appendices;
import com.example.foodapp.product.model.AppendicesCategory;
import com.example.foodapp.product.model.Product;
import com.example.foodapp.product.model.Request.AppendicesCreateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface OwnerAppendicesService {
    Appendices create(AppendicesCreateRequest appendicesCreateRequest, Principal principal) throws Exception;
    Appendices get(Long id);
    List<Appendices> getAllAppendices(Principal principal);
    List<Appendices> getAppendicesByCategory(AppendicesCategory appendicesCategory, Principal principal);
    List<Appendices> getAppendicesByProduct(Product product, Principal principal);
    Appendices update(Long id);
    Boolean delete(Long id, Principal principal);
}
