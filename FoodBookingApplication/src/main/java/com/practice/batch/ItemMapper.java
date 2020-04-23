package com.practice.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.practice.model.Item;

public class ItemMapper  implements FieldSetMapper<Item> {

	@Override
	public Item mapFieldSet(FieldSet fieldSet) throws BindException {
		Item item =new Item();
		item.setMid(fieldSet.readLong("mid"));
		item.setItemname(fieldSet.readString("itemname"));
		item.setItemdec(fieldSet.readString("itemdec"));
		item.setCost(fieldSet.readDouble("cost"));
		return item;
	}
	
}
