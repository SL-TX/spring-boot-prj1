package ru.skypro.weblibrary.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import ru.skypro.weblibrary.entity.Bukket;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final Bukket bukket;

    public StoreServiceImpl(Bukket bukket) {
        this.bukket = bukket;
    }

    @Override
    public List<Integer> getOrder() {
        return this.bukket.getIds();
    }

    @Override
    public String addOrder(List<Integer> listId) {
        this.bukket.addIds(listId);
        return "done";
    }
}
