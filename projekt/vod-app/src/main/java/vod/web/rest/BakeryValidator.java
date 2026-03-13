package vod.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Bakery;
import vod.service.BakeryService;
@Component
@RequiredArgsConstructor
public class BakeryValidator implements Validator {
    private final BakeryService bakeryService;

    @Override
    public boolean supports(Class<?> clazz) {return clazz.isAssignableFrom(Bakery.class);}

    @Override
    public void validate(Object target, Errors errors){
        Bakery validatedBakery = (Bakery) target;

        boolean duplicated = bakeryService.getAllBakeries().stream()
                .anyMatch(bakery -> bakery.getName().equalsIgnoreCase(validatedBakery.getName()));
        if(duplicated){
            errors.rejectValue("name", "bakery.name.duplicated");
        }
    }
}
