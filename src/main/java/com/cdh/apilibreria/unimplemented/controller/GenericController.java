package com.cdh.apilibreria.unimplemented.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GenericController<T, K> {
    public ResponseEntity<List<T>> get();
    public ResponseEntity<T> post(@RequestBody T t);
    public ResponseEntity<T> put(@RequestBody T t);
    public ResponseEntity<T> delete(K id);
}
