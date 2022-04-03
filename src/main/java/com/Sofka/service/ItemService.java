package com.Sofka.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sofka.domain.Item;
import com.Sofka.repository.ItemRepository;
import com.Sofka.service.Interface.Iitem;

@Service
public class ItemService implements Iitem {

	@Autowired
	private ItemRepository itemrepository;
	@Override
	public List<Item> getItem() {
		return itemrepository.findall();
	}

	@Override
	public Item saveItem(Item item) {
		item.setCreate_at(Instant.now());
		return itemrepository.save(item);
	}

	@Override
	public Item updateItem(Integer id, Item item) {
		item.setId(id);
		item.setCreate_at(item.getCreate_at());
		return itemrepository.save(item);
	}

	@Override
	public Item deleteItem(Integer id) {
		var delete = itemrepository.findById(id);
		if(delete.isPresent()) {
			itemrepository.delete(delete.get());
			return delete.get();
					
		}else {
	    	return null;
		}
	}
	
	public Item deleteLogicoItem(Integer id , Item item) {
	var subcate = itemrepository.findById(id);
	if(subcate.isPresent()) {
		itemrepository.deleteLogic(id, item.getStatus());
		return subcate.get();
	}else {
		return null;
	}
	}
}
