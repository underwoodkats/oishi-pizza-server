package com.underwoodkats.oishipizza.controllers;

import com.underwoodkats.oishipizza.models.Item;
import com.underwoodkats.oishipizza.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller provide endpoints to process data about items.
 */
@Controller
@RequestMapping(path = "/menu")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * This endpoint returns List of all items that we store.
     *
     * @return ResponseEntity<List < Item>>
     */
    @GetMapping(path = "/all")
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    /**
     * This endpoint save a new item.
     *
     * @return HttpEntity
     */
    @PostMapping(path = "/save")
    public HttpEntity saveItem(@RequestBody Item item) {
        if (itemService.saveItemIfItemValid(item)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incomplete information!");
        }
    }

    /**
     * This endpoint delete all items that we store.
     *
     * @return HttpEntity
     */
    @DeleteMapping(value = "/delete")
    public HttpEntity deleteAllItems() {
        itemService.deleteAllItems();
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * This endpoint delete one specific item by its id.
     *
     * @param id - identification of the item that has to be deleted.
     * @return HttpEntity
     */
    @DeleteMapping(value = "/delete/{id}")
    public HttpEntity deleteAllItems(@PathVariable int id) {
        itemService.deleteItemById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
