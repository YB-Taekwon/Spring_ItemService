package com.ian.itemservice.repository;

import com.ian.itemservice.domain.item.Item;
import com.ian.itemservice.dto.item.ItemDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    void save() {
        // given
        Item item = new Item("ItemA", 10000, 10);

        // when
        Item saveItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("ItemA", 10000, 10);
        Item item2 = new Item("ItemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void upadate() {
        // given
        Item item = new Item("ItemA", 10000, 10);

        Item saveItem = itemRepository.save(item);
        Long id = saveItem.getId();

        // when
        ItemDto itemDto = new ItemDto("ItemB", 20000, 2);
        itemRepository.update(id, itemDto);

        // then
        Item findItem = itemRepository.findById(id);

        assertThat(findItem.getName()).isEqualTo(itemDto.getName());
        assertThat(findItem.getPrice()).isEqualTo(itemDto.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(itemDto.getQuantity());
    }
}