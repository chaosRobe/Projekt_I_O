package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Baker;
import vod.service.ProductService;
import vod.web.rest.dto.ProductDTO;

@Component
@RequiredArgsConstructor
public class ProductValidator implements Validator {
    private final ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {return clazz.isAssignableFrom(ProductDTO.class);}

    @Override
    public void validate(Object target, Errors errors){
        ProductDTO productDTO = (ProductDTO) target;

        Baker baker = productService.getBakerById(productDTO.getBakerId());
        if(baker == null){
            errors.rejectValue("bakerId", "product.baker.missing");
        }
    }
}

