package ru.skypro.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.weblibrary.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("store/order")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("get")
    public List<Integer> getOrder(){
        return storeService.getOrder();
    }
    @PostMapping("add")
    public String addOrder(@RequestBody List<Integer> listId){
        return storeService.addOrder(listId);
    }
}
