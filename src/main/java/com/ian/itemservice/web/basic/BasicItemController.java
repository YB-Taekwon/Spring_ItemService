package com.ian.itemservice.web.basic;

import com.ian.itemservice.domain.item.Item;
import com.ian.itemservice.dto.item.ItemDto;
import com.ian.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;


    // 테스트 더미 데이터 추가
    @PostMapping
    public void init() {
        itemRepository.save(new Item("testItem1", 10000, 10));
        itemRepository.save(new Item("testItem2", 20000, 20));
    }


    // 상품 목록 조회
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }


    // 상품 상세 조회
    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable("itemId") Long itemId) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/item";
    }


    // 상품 등록 폼
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    // 상품 등록
    @PostMapping("/add")
    public String add(@ModelAttribute("item") Item item, RedirectAttributes redirectAttributes) {
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }


    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }


    // 상품 수정
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, ItemDto itemDto, Model model) {
        Item item = itemRepository.update(itemId, itemDto);
        model.addAttribute("item", item);

        return "redirect:/basic/items/{itemId}";
    }
}
