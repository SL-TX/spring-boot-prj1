package ru.skypro.weblibrary.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
public class Bukket {
    private List<Integer> ids;

    public Bukket(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void addIds(List<Integer> ids) {
        this.ids.addAll(ids);
    }
}
