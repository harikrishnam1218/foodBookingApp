package com.practice.service;

import java.util.List;

import com.practice.exception.DBException;
import com.practice.model.Item;

public interface ItemService {
List<Item> getItemsByVendorname(String name) throws DBException;

List<Item> getItemsByItemname(String name) throws DBException;
}
