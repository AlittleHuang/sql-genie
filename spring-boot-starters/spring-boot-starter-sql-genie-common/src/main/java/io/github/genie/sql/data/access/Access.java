package io.github.genie.sql.data.access;

import java.util.List;
import java.util.Map;

public interface Access<T, ID> extends BaseAccess<T> {

    T get(ID id);

    List<T> getAll(Iterable<? extends ID> ids);

    Map<ID, T> getMap(Iterable<? extends ID> ids);

}
