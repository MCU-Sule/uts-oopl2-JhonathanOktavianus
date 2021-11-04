package com.example.squiddemo.DAO;

import java.util.List;

public interface DAOInterface <E>{
    public int addData (E data);
    public int delData (E data);
    public int updateData (E data);

    public List<E> showData();
}
