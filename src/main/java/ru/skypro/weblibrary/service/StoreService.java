package ru.skypro.weblibrary.service;

import java.util.List;

public interface StoreService {
    List<Integer> getOrder();

    String addOrder(List<Integer> listId);
}
