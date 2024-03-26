package io.github.genie.sql.data.access;

import io.github.genie.sql.api.Query.Select;
import io.github.genie.sql.api.Updater;

interface BaseAccess<T> extends Select<T>, Updater<T> {
}
