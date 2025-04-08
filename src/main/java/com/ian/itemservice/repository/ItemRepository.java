package com.ian.itemservice.repository;

import com.ian.itemservice.domain.item.Item;
import com.ian.itemservice.dto.item.ItemDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    // 자바 내부 저장소 (DB 대용)
    private static final Map<Long, Item> store = new HashMap<>();
    // ID 부여 및 자동 증가 기능 (auto_increment 역할)
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public Item update(Long id, ItemDto itemDto) {
        Item item = findById(id);
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setQuantity(itemDto.getQuantity());
        item.setOpen(itemDto.getOpen());
        item.setItemType(itemDto.getItemType());
        item.setDeliveryCode(itemDto.getDeliveryCode());

        return item;
    }

    public void clear() {
        store.clear();
    }
}
