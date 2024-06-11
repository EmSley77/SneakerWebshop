package es.sneakerwebshop.service;
/*
*Emanuel sleyman
*2024-06-11
*this service contains all the important methods belonging to basket
*/
import es.sneakerwebshop.entity.Product;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {

    @Getter
    private List<Product> basket = new ArrayList<>();




}
