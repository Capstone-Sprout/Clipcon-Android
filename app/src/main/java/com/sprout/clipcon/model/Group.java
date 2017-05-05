package com.sprout.clipcon.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Getter
    @Setter
    private String primaryKey;
    @Getter
    @Setter
    private List<User> userList;
    @Getter
    @Setter
    private History history = new History();

    public Group(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void addContents(Contents contents) {
        history.addContents(contents);
    }

    public Contents getContents(String key) {
        return history.getContentsByPK(key);
    }
}
