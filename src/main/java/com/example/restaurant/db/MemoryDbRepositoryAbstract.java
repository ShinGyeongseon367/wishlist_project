package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    private final List<T> db = new ArrayList<T>();
    private int index;

    @Override
    public Optional<T> findById(int index) {

        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    @Override
    public T save(T entity) {
        // index 하나를 늘리고 거기다가 저장을 한다.

        // 상황은 두 가지가 존재할 수 있다. 데이터가 존재하는 경우 , 존재하지 않는 경우

        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if (optionalEntity.isEmpty()) {
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        } else {
            int preIindex = optionalEntity.get().getIndex();
            entity.setIndex(preIindex);

            deleteById(preIindex);
            entity.setIndex(preIindex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(et -> et.getIndex() == index).findFirst();

        if (optionalEntity.isPresent()) {
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> findAll() {
        return db;
    }

}
